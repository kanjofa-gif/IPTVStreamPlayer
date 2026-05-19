package com.iptvstream.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import com.iptvstream.data.account.AccountRepository
import com.iptvstream.data.repository.IPTVRepository
import com.iptvstream.data.trial.TrialManager
import com.iptvstream.data.trial.TrialState
import com.iptvstream.ui.components.NavTab
import com.iptvstream.ui.components.SettingsDrawer
import com.iptvstream.ui.screens.account.*
import com.iptvstream.ui.screens.home.HomeScreen
import com.iptvstream.ui.screens.live.LiveScreen
import com.iptvstream.ui.screens.loading.LoadingScreen
import com.iptvstream.ui.screens.movies.MoviesScreen
import com.iptvstream.ui.screens.player.PlayerScreen
import com.iptvstream.ui.screens.playlists.ManagePlaylistsScreen
import com.iptvstream.ui.screens.search.SearchScreen
import com.iptvstream.ui.screens.series.SeriesScreen
import com.iptvstream.ui.screens.settings.SettingsScreen
import com.iptvstream.ui.screens.setup.SetupScreen
import com.iptvstream.ui.theme.IPTVTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var repository: IPTVRepository
    @Inject lateinit var trialManager: TrialManager
    @Inject lateinit var accountRepository: AccountRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPTVTheme {
                IPTVNavHost(repository, trialManager, accountRepository)
            }
        }
    }
}

@Composable
fun IPTVNavHost(
    repository: IPTVRepository,
    trialManager: TrialManager,
    accountRepository: AccountRepository
) {
    val navController = rememberNavController()
    var currentTab by remember { mutableStateOf(NavTab.HOME) }
    var startDestination by remember { mutableStateOf<String?>(null) }
    var drawerVisible by remember { mutableStateOf(false) }
    var trialState by remember { mutableStateOf(TrialState.NOT_STARTED) }
    var userInfo by remember { mutableStateOf<com.iptvstream.data.model.UserInfo?>(null) }
    var playlistName by remember { mutableStateOf<String?>(null) }
    var isAccountSignedIn by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    // Determine start destination based on setup + trial state
    LaunchedEffect(Unit) {
        accountRepository.ensureLocalUserId()
        val playlist = repository.getSelectedPlaylist()
        val trial = trialManager.getTrialState()
        trialState = trial

        startDestination = when {
            playlist == null -> Screen.Setup.route
            trial.isExpired && !trial.isSubscribed -> Screen.TrialExpired.route
            else -> Screen.Home.route
        }
    }

    // Observe trial state and user info live
    LaunchedEffect(Unit) {
        trialManager.observeTrialState().collectLatest { trialState = it }
    }

    if (startDestination == null) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black))
        return
    }

    fun handleTabSelection(tab: NavTab) {
        currentTab = tab
        when (tab) {
            NavTab.HOME -> navController.navigate(Screen.Home.route)
            NavTab.LIVE -> navController.navigate(Screen.Live.route)
            NavTab.MOVIES -> navController.navigate(Screen.Movies.route)
            NavTab.SERIES -> navController.navigate(Screen.Series.route)
            NavTab.SEARCH -> navController.navigate(Screen.Search.route)
            NavTab.EPG -> {}
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = startDestination!!) {

            composable(Screen.Setup.route) {
                SetupScreen(
                    onSuccess = {
                        // Start trial countdown on first successful IPTV setup
                        coroutineScope.launch {
                            trialManager.startTrialIfNotStarted()
                        }
                        navController.navigate(Screen.Loading.route) {
                            popUpTo(Screen.Setup.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Loading.route) {
                LoadingScreen(
                    onComplete = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Loading.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    currentTab = NavTab.HOME,
                    onTabSelected = ::handleTabSelection,
                    onNavigate = { },
                    onPlayStream = { type, id, url, title, icon ->
                        PlayerHolder.setSingle(type, id, url, title, icon)
                        navController.navigate(Screen.Player.route)
                    },
                    onSettingsClick = { drawerVisible = true }
                )
            }

            composable(Screen.Live.route) {
                LiveScreen(
                    currentTab = NavTab.LIVE,
                    onTabSelected = ::handleTabSelection,
                    onPlayStream = { _, _, _, _, _ -> navController.navigate(Screen.Player.route) },
                    onSettingsClick = { drawerVisible = true }
                )
            }

            composable(Screen.Movies.route) {
                MoviesScreen(
                    currentTab = NavTab.MOVIES,
                    onTabSelected = ::handleTabSelection,
                    onPlayMovie = { _, _, _, _ -> navController.navigate(Screen.Player.route) },
                    onSettingsClick = { drawerVisible = true }
                )
            }

            composable(Screen.Series.route) {
                SeriesScreen(
                    currentTab = NavTab.SERIES,
                    onTabSelected = ::handleTabSelection,
                    onPlayEpisode = { _, _, _, _ -> navController.navigate(Screen.Player.route) },
                    onSettingsClick = { drawerVisible = true }
                )
            }

            composable(Screen.Search.route) {
                SearchScreen(
                    currentTab = NavTab.SEARCH,
                    onTabSelected = ::handleTabSelection,
                    onPlayStream = { _, _, _, _, _ -> navController.navigate(Screen.Player.route) },
                    onOpenSeries = { navController.navigate(Screen.Series.route) },
                    onSettingsClick = { drawerVisible = true }
                )
            }

            composable(Screen.Settings.route) {
                SettingsScreen(onBack = { navController.popBackStack() })
            }

            composable(Screen.ManagePlaylists.route) {
                ManagePlaylistsScreen(onBack = { navController.popBackStack() })
            }

            // ─── Account flow ──────────────────────────────────────────────────
            composable(Screen.AccountWelcome.route) {
                val vm: AccountViewModel = hiltViewModel()
                val state by vm.uiState.collectAsState()

                // Auto-advance when device code arrives
                LaunchedEffect(state.deviceCode) {
                    if (state.deviceCode != null) {
                        navController.navigate(Screen.AccountQRCode.route)
                    }
                }
                // Auto-advance after sign-in completes
                LaunchedEffect(state.account.isSignedIn) {
                    if (state.account.isSignedIn) {
                        navController.navigate(Screen.AccountConfirm.route) {
                            popUpTo(Screen.AccountWelcome.route) { inclusive = true }
                        }
                    }
                }

                AccountWelcomeScreen(
                    onGoogleSignIn = { vm.startGoogleSignIn() },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screen.AccountQRCode.route) {
                val vm: AccountViewModel = hiltViewModel()
                val state by vm.uiState.collectAsState()
                val code = state.deviceCode

                LaunchedEffect(state.account.isSignedIn) {
                    if (state.account.isSignedIn) {
                        navController.navigate(Screen.AccountConfirm.route) {
                            popUpTo(Screen.AccountWelcome.route) { inclusive = true }
                        }
                    }
                }

                if (code != null) {
                    AccountQRCodeScreen(
                        userCode = code.userCode,
                        verificationUrl = code.verificationUrl,
                        isPolling = state.isPolling,
                        onCancel = {
                            vm.cancelSignIn()
                            navController.popBackStack()
                        }
                    )
                } else {
                    LaunchedEffect(Unit) { navController.popBackStack() }
                }
            }

            composable(Screen.AccountConfirm.route) {
                val vm: AccountViewModel = hiltViewModel()
                val state by vm.uiState.collectAsState()
                val acc = state.account

                AccountConfirmScreen(
                    displayName = acc.displayName ?: "User",
                    email = acc.email ?: "",
                    onContinue = {
                        navController.navigate(Screen.AccountProfile.route) {
                            popUpTo(Screen.AccountWelcome.route) { inclusive = true }
                        }
                    },
                    onCancel = {
                        vm.signOut()
                        navController.popBackStack(Screen.Home.route, inclusive = false)
                    }
                )
            }

            composable(Screen.AccountProfile.route) {
                val vm: AccountViewModel = hiltViewModel()
                AccountProfileScreen(
                    onManageLinkedAccounts = { navController.navigate(Screen.AccountLinked.route) },
                    onSignOut = {
                        vm.signOut()
                        navController.popBackStack()
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screen.AccountLinked.route) {
                AccountLinkedScreen(
                    onBack = { navController.popBackStack() },
                    onLinkGoogle = { navController.navigate(Screen.AccountWelcome.route) }
                )
            }

            composable(Screen.TrialExpired.route) {
                TrialExpiredScreen(
                    onSignInAndSubscribe = {
                        navController.navigate(Screen.AccountWelcome.route)
                    },
                    onSubscribeOnly = {
                        // TODO: Stripe integration. For now just mark subscribed for testing.
                        coroutineScope.launch {
                            trialManager.setSubscribed(true)
                        }
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.TrialExpired.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Player.route) {
                val item = PlayerHolder.current ?: run {
                    navController.popBackStack()
                    return@composable
                }
                PlayerScreen(
                    type = item.type,
                    id = item.id,
                    url = item.url,
                    title = item.title,
                    icon = item.icon,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        // Side drawer overlay
        SettingsDrawer(
            isVisible = drawerVisible,
            userInfo = userInfo?.copy(username = playlistName ?: userInfo?.username ?: ""),
            expDate = userInfo?.exp_date?.let { formatExpiry(it) } ?: "—",
            maxConnections = userInfo?.max_connections ?: "—",
            isTrial = userInfo?.is_trial == "1",
            trialDaysRemaining = if (trialState.isStarted && !trialState.isSubscribed) {
                trialState.daysRemaining
            } else null,
            onDismiss = { drawerVisible = false },
            onAccount = {
                drawerVisible = false
                val route = if (isAccountSignedIn) Screen.AccountProfile.route
                            else Screen.AccountWelcome.route
                navController.navigate(route)
            },
            onRefresh = {
                drawerVisible = false
                navController.navigate(Screen.Loading.route)
            },
            onManagePlaylists = {
                drawerVisible = false
                navController.navigate(Screen.ManagePlaylists.route)
            },
            onManageCategories = {
                drawerVisible = false
                // TODO: implement categories screen
            },
            onSettings = {
                drawerVisible = false
                navController.navigate(Screen.Settings.route)
            },
            onMobileApp = {
                drawerVisible = false
                // TODO: implement mobile app QR screen
            }
        )
    }

    // Trigger background user info refresh
    LaunchedEffect(Unit) {
        val playlist = repository.getSelectedPlaylist()
        if (playlist != null) {
            playlistName = playlist.name
            repository.authenticate(playlist).onSuccess { auth ->
                userInfo = auth.user_info
            }
        }
    }

    // Track sign-in state for drawer's account action
    LaunchedEffect(Unit) {
        accountRepository.observeAccount().collect { acc ->
            isAccountSignedIn = acc.isSignedIn
        }
    }
}

// Format Unix timestamp from Xtream API to readable Arabic date
private fun formatExpiry(timestamp: String): String {
    return try {
        val ts = timestamp.toLongOrNull() ?: return timestamp
        val date = java.util.Date(ts * 1000)
        val sdf = java.text.SimpleDateFormat("d MMMM yyyy", java.util.Locale("ar"))
        sdf.format(date)
    } catch (_: Exception) {
        timestamp
    }
}
