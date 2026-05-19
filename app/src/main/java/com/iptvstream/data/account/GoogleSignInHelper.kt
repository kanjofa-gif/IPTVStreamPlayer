package com.iptvstream.data.account

import kotlinx.coroutines.delay
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implements Google's OAuth 2.0 Device Authorization Flow.
 *
 * SETUP REQUIRED (one-time):
 *   1. Go to https://console.cloud.google.com → Credentials
 *   2. Create OAuth 2.0 Client ID of type "TVs and Limited Input devices"
 *   3. Paste the client_id and client_secret into BuildConfig or strings.xml
 *   4. Set GOOGLE_CLIENT_ID below
 *
 * Once configured, the QR code flow on the TV will work automatically.
 */
@Singleton
class GoogleSignInHelper @Inject constructor() {

    // ⚠️ Replace these with real values from Google Cloud Console
    // (Leave as placeholders to use mock mode for now)
    private val clientId: String = "PASTE_YOUR_GOOGLE_CLIENT_ID_HERE"
    private val clientSecret: String = "PASTE_YOUR_GOOGLE_CLIENT_SECRET_HERE"

    private val client = OkHttpClient()

    /** True only when real Google Sign-In is configured. */
    val isConfigured: Boolean
        get() = clientId.isNotBlank() && !clientId.startsWith("PASTE_")

    /**
     * Step 1: Request device + user code.
     * Returns a DeviceCodeResponse with verification_url and user_code (shown on TV).
     */
    suspend fun requestDeviceCode(): DeviceCodeResponse {
        if (!isConfigured) return DeviceCodeResponse.mock()

        val body = FormBody.Builder()
            .add("client_id", clientId)
            .add("scope", "openid email profile")
            .build()

        val request = Request.Builder()
            .url("https://oauth2.googleapis.com/device/code")
            .post(body)
            .build()

        return runCatching {
            val response = client.newCall(request).execute()
            val json = JSONObject(response.body?.string() ?: "{}")
            DeviceCodeResponse(
                deviceCode = json.getString("device_code"),
                userCode = json.getString("user_code"),
                verificationUrl = json.getString("verification_url"),
                expiresIn = json.optInt("expires_in", 1800),
                interval = json.optInt("interval", 5)
            )
        }.getOrElse { DeviceCodeResponse.mock() }
    }

    /**
     * Step 2: Poll Google until user authorizes on their phone.
     * Returns TokenResponse on success, null on timeout/error.
     */
    suspend fun pollForToken(deviceCode: String, interval: Int = 5): TokenResponse? {
        if (!isConfigured) {
            delay(3000) // Pretend we waited
            return TokenResponse.mock()
        }

        repeat(60) { // try up to 5 minutes
            delay(interval * 1000L)
            val body = FormBody.Builder()
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .add("device_code", deviceCode)
                .add("grant_type", "urn:ietf:params:oauth:grant-type:device_code")
                .build()
            val request = Request.Builder()
                .url("https://oauth2.googleapis.com/token")
                .post(body)
                .build()

            runCatching {
                val response = client.newCall(request).execute()
                val text = response.body?.string() ?: "{}"
                val json = JSONObject(text)
                if (json.has("access_token")) {
                    val token = json.getString("access_token")
                    val userInfo = fetchUserInfo(token)
                    return TokenResponse(
                        accessToken = token,
                        idToken = json.optString("id_token"),
                        refreshToken = json.optString("refresh_token"),
                        email = userInfo.email,
                        displayName = userInfo.name,
                        photoUrl = userInfo.picture,
                        googleId = userInfo.sub
                    )
                }
            }
        }
        return null
    }

    private fun fetchUserInfo(accessToken: String): GoogleUserInfo {
        val request = Request.Builder()
            .url("https://www.googleapis.com/oauth2/v3/userinfo")
            .header("Authorization", "Bearer $accessToken")
            .build()
        return runCatching {
            val text = client.newCall(request).execute().body?.string() ?: "{}"
            val json = JSONObject(text)
            GoogleUserInfo(
                sub = json.getString("sub"),
                email = json.getString("email"),
                name = json.optString("name", ""),
                picture = json.optString("picture", null)
            )
        }.getOrElse { GoogleUserInfo("", "", "", null) }
    }
}

data class DeviceCodeResponse(
    val deviceCode: String,
    val userCode: String,
    val verificationUrl: String,
    val expiresIn: Int,
    val interval: Int
) {
    companion object {
        fun mock() = DeviceCodeResponse(
            deviceCode = "mock_device_code_${System.currentTimeMillis()}",
            userCode = "QCM-KQK-KKX",
            verificationUrl = "https://www.google.com/device",
            expiresIn = 1800,
            interval = 5
        )
    }
}

data class TokenResponse(
    val accessToken: String,
    val idToken: String,
    val refreshToken: String,
    val email: String,
    val displayName: String,
    val photoUrl: String?,
    val googleId: String
) {
    companion object {
        fun mock() = TokenResponse(
            accessToken = "mock_token",
            idToken = "",
            refreshToken = "",
            email = "user@gmail.com",
            displayName = "Demo User",
            photoUrl = null,
            googleId = "mock_google_id_${System.currentTimeMillis()}"
        )
    }
}

private data class GoogleUserInfo(
    val sub: String,
    val email: String,
    val name: String,
    val picture: String?
)
