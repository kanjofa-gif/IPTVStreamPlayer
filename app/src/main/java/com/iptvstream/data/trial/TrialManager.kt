package com.iptvstream.data.trial

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.trialDataStore by preferencesDataStore("trial_prefs")

data class TrialState(
    val isStarted: Boolean,
    val startedAt: Long,
    val daysRemaining: Int,
    val isExpired: Boolean,
    val isSubscribed: Boolean
) {
    companion object {
        val NOT_STARTED = TrialState(false, 0L, 7, false, false)
    }
}

@Singleton
class TrialManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val START_KEY = longPreferencesKey("trial_started_at")
    private val SUBSCRIBED_KEY = booleanPreferencesKey("is_subscribed")

    companion object {
        const val TRIAL_DAYS = 7L
        const val MS_PER_DAY = 24L * 60L * 60L * 1000L
    }

    /** Start trial countdown (called when user successfully adds IPTV playlist). */
    suspend fun startTrialIfNotStarted() {
        context.trialDataStore.edit { prefs ->
            if (prefs[START_KEY] == null || prefs[START_KEY] == 0L) {
                prefs[START_KEY] = System.currentTimeMillis()
            }
        }
    }

    /** Mark user as subscribed (unlocks app permanently). */
    suspend fun setSubscribed(value: Boolean) {
        context.trialDataStore.edit { it[SUBSCRIBED_KEY] = value }
    }

    /** Returns current trial state as a Flow. */
    fun observeTrialState(): Flow<TrialState> =
        context.trialDataStore.data.map { prefs ->
            val startedAt = prefs[START_KEY] ?: 0L
            val isSubscribed = prefs[SUBSCRIBED_KEY] ?: false
            buildState(startedAt, isSubscribed)
        }

    /** Returns current trial state once (suspend). */
    suspend fun getTrialState(): TrialState {
        val prefs = context.trialDataStore.data.first()
        val startedAt = prefs[START_KEY] ?: 0L
        val isSubscribed = prefs[SUBSCRIBED_KEY] ?: false
        return buildState(startedAt, isSubscribed)
    }

    private fun buildState(startedAt: Long, isSubscribed: Boolean): TrialState {
        if (startedAt == 0L) return TrialState.NOT_STARTED
        val elapsedMs = System.currentTimeMillis() - startedAt
        val elapsedDays = (elapsedMs / MS_PER_DAY).toInt()
        val remaining = (TRIAL_DAYS.toInt() - elapsedDays).coerceAtLeast(0)
        val expired = elapsedDays >= TRIAL_DAYS && !isSubscribed
        return TrialState(
            isStarted = true,
            startedAt = startedAt,
            daysRemaining = remaining,
            isExpired = expired,
            isSubscribed = isSubscribed
        )
    }

    /** For testing only - reset trial. */
    suspend fun resetTrial() {
        context.trialDataStore.edit { it.clear() }
    }
}
