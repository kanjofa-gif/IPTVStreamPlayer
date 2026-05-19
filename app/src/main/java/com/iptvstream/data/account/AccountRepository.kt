package com.iptvstream.data.account

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

private val Context.accountDataStore by preferencesDataStore("account_prefs")

/**
 * Represents the locally stored user account.
 * When Google Sign-In is wired up (Firebase config), only the `email`, `displayName`,
 * `photoUrl`, and `googleId` will be populated from the real Google account.
 */
data class UserAccount(
    val userId: String,           // local stable id (generated once)
    val email: String?,
    val displayName: String?,
    val photoUrl: String?,
    val googleId: String?,        // populated after real Google Sign-In
    val appleId: String?,         // populated after Apple Sign-In (later)
    val signedInAt: Long          // 0 if not signed in
) {
    val isSignedIn: Boolean get() = googleId != null || appleId != null

    companion object {
        val ANONYMOUS = UserAccount(
            userId = "",
            email = null,
            displayName = null,
            photoUrl = null,
            googleId = null,
            appleId = null,
            signedInAt = 0L
        )
    }
}

@Singleton
class AccountRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val USER_ID = stringPreferencesKey("user_id")
    private val EMAIL = stringPreferencesKey("email")
    private val DISPLAY_NAME = stringPreferencesKey("display_name")
    private val PHOTO_URL = stringPreferencesKey("photo_url")
    private val GOOGLE_ID = stringPreferencesKey("google_id")
    private val APPLE_ID = stringPreferencesKey("apple_id")
    private val SIGNED_IN_AT = stringPreferencesKey("signed_in_at")

    /** Ensures a local userId exists (created once, stable across signs in/out). */
    suspend fun ensureLocalUserId(): String {
        val prefs = context.accountDataStore.data.first()
        return prefs[USER_ID] ?: run {
            val newId = generateLocalId()
            context.accountDataStore.edit { it[USER_ID] = newId }
            newId
        }
    }

    /** Save authenticated user data (called after Google/Apple Sign-In completes). */
    suspend fun saveSignedInUser(
        email: String,
        displayName: String,
        photoUrl: String?,
        googleId: String? = null,
        appleId: String? = null
    ) {
        val userId = ensureLocalUserId()
        context.accountDataStore.edit { prefs ->
            prefs[USER_ID] = userId
            prefs[EMAIL] = email
            prefs[DISPLAY_NAME] = displayName
            photoUrl?.let { prefs[PHOTO_URL] = it }
            googleId?.let { prefs[GOOGLE_ID] = it }
            appleId?.let { prefs[APPLE_ID] = it }
            prefs[SIGNED_IN_AT] = System.currentTimeMillis().toString()
        }
    }

    /** Sign out — clears credentials but keeps local userId. */
    suspend fun signOut() {
        context.accountDataStore.edit { prefs ->
            prefs.remove(EMAIL)
            prefs.remove(DISPLAY_NAME)
            prefs.remove(PHOTO_URL)
            prefs.remove(GOOGLE_ID)
            prefs.remove(APPLE_ID)
            prefs.remove(SIGNED_IN_AT)
        }
    }

    /** Unlink a specific provider (Google or Apple). */
    suspend fun unlinkProvider(provider: AccountProvider) {
        context.accountDataStore.edit { prefs ->
            when (provider) {
                AccountProvider.GOOGLE -> prefs.remove(GOOGLE_ID)
                AccountProvider.APPLE -> prefs.remove(APPLE_ID)
            }
        }
    }

    fun observeAccount(): Flow<UserAccount> =
        context.accountDataStore.data.map { prefs ->
            UserAccount(
                userId = prefs[USER_ID] ?: "",
                email = prefs[EMAIL],
                displayName = prefs[DISPLAY_NAME],
                photoUrl = prefs[PHOTO_URL],
                googleId = prefs[GOOGLE_ID],
                appleId = prefs[APPLE_ID],
                signedInAt = prefs[SIGNED_IN_AT]?.toLongOrNull() ?: 0L
            )
        }

    suspend fun getAccount(): UserAccount {
        val prefs = context.accountDataStore.data.first()
        return UserAccount(
            userId = prefs[USER_ID] ?: "",
            email = prefs[EMAIL],
            displayName = prefs[DISPLAY_NAME],
            photoUrl = prefs[PHOTO_URL],
            googleId = prefs[GOOGLE_ID],
            appleId = prefs[APPLE_ID],
            signedInAt = prefs[SIGNED_IN_AT]?.toLongOrNull() ?: 0L
        )
    }

    private fun generateLocalId(): String {
        // 28-char URL-safe id, similar in style to Firebase user IDs
        return UUID.randomUUID().toString().replace("-", "").take(28)
    }
}

enum class AccountProvider { GOOGLE, APPLE }
