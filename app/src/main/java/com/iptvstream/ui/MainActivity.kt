package com.iptvstream.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.iptvstream.data.repository.IPTVRepository
import com.iptvstream.ui.components.NavTab
import com.iptvstream.ui.screens.home.HomeScreen
import com.iptvstream.ui.screens.live.LiveScreen
import com.iptvstream.ui.screens.loading.LoadingScreen
import com.iptvstream.ui.screens.movies.MoviesScreen
import com.iptvstream.ui.screens.player.PlayerScreen
import com.iptvstream.ui.screens.playlists.ManagePlaylistsScreen
import com.iptvstream.ui.screens.settings.SettingsScreen
import com.iptvstream.ui.screens.setup.SetupScreen
import com.iptvstream.ui.theme.IPTVTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: IPTVRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPTVTheme {
                IPTVNavHost(repository)
            }
        }
    }
}

@Composable
fun IPTVNavHost(repository: IPTVRepository) {
    val navController = rememberNavController()
    var currentTab by remember { mutableStateOf(NavTab.HOME) }
    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val playlist = repository.getSelectedPlaylist()
        startDestination = if (playlist != null) Screen.Home.route else Screen.Setup.route
    }

    if (startDestination == null) return

    NavHost(navController = navController, startDestination = startDestination!!) {

        composable(Screen.Setup.route) {
            SetupScreen(
                onSuccess = {
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
                currentTab = currentTab,
                onTabSelected = { tab ->
                    currentTab = tab
                    when (tab) {
                        NavTab.LIVE -> navController.navigate(Screen.Live.route)
                        NavTab.MOVIES -> navController.navigate(Screen.Movies.route)
                        NavTab.SERIES -> navController.navigate(Screen.Movies.route)
                        NavTab.SEARCH -> {}
                        NavTab.EPG -> {}
                        else -> {}
                    }
                },
                onNavigate = { },
                onPlayStream = { type, id, url, title, icon ->
                    PlayerHolder.set(type, id, url, title, icon)
                    navController.navigate(Screen.Player.route)
                },
                onSettingsClick = {}
            )
        }

        composable(Screen.Live.route) {
            LiveScreen(
                currentTab = NavTab.LIVE,
                onTabSelected = { tab ->
                    currentTab = tab
                    when (tab) {
                        NavTab.HOME -> navController.navigate(Screen.Home.route)
                        NavTab.MOVIES -> navController.navigate(Screen.Movies.route)
                        else -> {}
                    }
                },
                onPlayStream = { type, id, url, title, icon ->
                    PlayerHolder.set(type, id, url, title, icon)
                    navController.navigate(Screen.Player.route)
                },
                onSettingsClick = {}
            )
        }

        composable(Screen.Movies.route) {
            MoviesScreen(
                currentTab = NavTab.MOVIES,
                onTabSelected = { tab ->
                    currentTab = tab
                    when (tab) {
                        NavTab.HOME -> navController.navigate(Screen.Home.route)
                        NavTab.LIVE -> navController.navigate(Screen.Live.route)
                        else -> {}
                    }
                },
                onPlayMovie = { url, id, title, icon ->
                    PlayerHolder.set("movie", id, url, title, icon)
                    navController.navigate(Screen.Player.route)
                },
                onSettingsClick = {}
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.ManagePlaylists.route) {
            ManagePlaylistsScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.Player.route) {
            PlayerScreen(
                type = PlayerHolder.type,
                id = PlayerHolder.id,
                url = PlayerHolder.url,
                title = PlayerHolder.title,
                icon = PlayerHolder.icon,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
