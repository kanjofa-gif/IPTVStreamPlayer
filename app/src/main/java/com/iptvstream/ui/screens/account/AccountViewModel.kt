package com.iptvstream.ui.screens.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.account.AccountProvider
import com.iptvstream.data.account.AccountRepository
import com.iptvstream.data.account.DeviceCodeResponse
import com.iptvstream.data.account.GoogleSignInHelper
import com.iptvstream.data.account.UserAccount
import com.iptvstream.data.trial.TrialManager
import com.iptvstream.data.trial.TrialState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AccountUiState(
    val account: UserAccount = UserAccount.ANONYMOUS,
    val trial: TrialState = TrialState.NOT_STARTED,
    val deviceCode: DeviceCodeResponse? = null,
    val isPolling: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val trialManager: TrialManager,
    private val googleSignIn: GoogleSignInHelper
) : ViewModel() {

    private val _deviceCode = MutableStateFlow<DeviceCodeResponse?>(null)
    private val _isPolling = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)

    val uiState: StateFlow<AccountUiState> = combine(
        accountRepository.observeAccount(),
        trialManager.observeTrialState(),
        _deviceCode,
        _isPolling,
        _error
    ) { account, trial, code, polling, err ->
        AccountUiState(account, trial, code, polling, err)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AccountUiState()
    )

    /** Step 1: Get device + user code from Google, show QR. */
    fun startGoogleSignIn() {
        viewModelScope.launch {
            _error.value = null
            val code = googleSignIn.requestDeviceCode()
            _deviceCode.value = code
            pollForCompletion(code)
        }
    }

    private fun pollForCompletion(code: DeviceCodeResponse) {
        viewModelScope.launch {
            _isPolling.value = true
            val token = googleSignIn.pollForToken(code.deviceCode, code.interval)
            if (token != null) {
                accountRepository.saveSignedInUser(
                    email = token.email,
                    displayName = token.displayName,
                    photoUrl = token.photoUrl,
                    googleId = token.googleId
                )
                _deviceCode.value = null
            } else {
                _error.value = "انتهت مهلة التفويض. حاول مرة أخرى"
            }
            _isPolling.value = false
        }
    }

    fun cancelSignIn() {
        _deviceCode.value = null
        _isPolling.value = false
        _error.value = null
    }

    fun signOut() {
        viewModelScope.launch { accountRepository.signOut() }
    }

    fun unlink(provider: AccountProvider) {
        viewModelScope.launch { accountRepository.unlinkProvider(provider) }
    }

    /** Called when subscription is purchased (later). */
    fun markSubscribed() {
        viewModelScope.launch { trialManager.setSubscribed(true) }
    }

    /** For testing - reset trial state. */
    fun resetTrial() {
        viewModelScope.launch { trialManager.resetTrial() }
    }
}
