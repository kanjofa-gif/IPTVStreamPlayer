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
import com.iptvstream.ui.screens.search.SearchScreen
import com.iptvstream.ui.screens.series.SeriesScreen
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

    fun handleTabSelection(tab: NavTab) {
        currentTab = tab
        when (tab) {
            NavTab.HOME -> navController.navigate(Screen.Home.route)
            NavTab.LIVE -> navController.navigate(Screen.Live.route)
            NavTab.MOVIES -> navController.navigate(Screen.Movies.route)
            NavTab.SERIES -> navController.navigate(Screen.Series.route)
            NavTab.SEARCH -> navController.navigate(Screen.Search.route)
            NavTab.EPG -> {} // placeholder
        }
    }

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
                currentTab = NavTab.HOME,
                onTabSelected = ::handleTabSelection,
                onNavigate = { },
                onPlayStream = { type, id, url, title, icon ->
                    PlayerHolder.setSingle(type, id, url, title, icon)
                    navController.navigate(Screen.Player.route)
                },
                onSettingsClick = { navController.navigate(Screen.Settings.route) }
            )
        }

        composable(Screen.Live.route) {
            LiveScreen(
                currentTab = NavTab.LIVE,
                onTabSelected = ::handleTabSelection,
                onPlayStream = { type, id, url, title, icon ->
                    navController.navigate(Screen.Player.route)
                },
                onSettingsClick = { navController.navigate(Screen.Settings.route) }
            )
        }

        composable(Screen.Movies.route) {
            MoviesScreen(
                currentTab = NavTab.MOVIES,
                onTabSelected = ::handleTabSelection,
                onPlayMovie = { url, id, title, icon ->
                    navController.navigate(Screen.Player.route)
                },
                onSettingsClick = { navController.navigate(Screen.Settings.route) }
            )
        }

        composable(Screen.Series.route) {
            SeriesScreen(
                currentTab = NavTab.SERIES,
                onTabSelected = ::handleTabSelection,
                onPlayEpisode = { url, id, title, icon ->
                    navController.navigate(Screen.Player.route)
                },
                onSettingsClick = { navController.navigate(Screen.Settings.route) }
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                currentTab = NavTab.SEARCH,
                onTabSelected = ::handleTabSelection,
                onPlayStream = { type, id, url, title, icon ->
                    navController.navigate(Screen.Player.route)
                },
                onOpenSeries = { navController.navigate(Screen.Series.route) },
                onSettingsClick = { navController.navigate(Screen.Settings.route) }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.ManagePlaylists.route) {
            ManagePlaylistsScreen(onBack = { navController.popBackStack() })
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
}
