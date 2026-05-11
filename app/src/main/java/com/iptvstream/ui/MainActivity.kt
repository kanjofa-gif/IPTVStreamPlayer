package com.iptvstream.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPTVTheme {
                IPTVNavHost()
            }
        }
    }
}

@Composable
fun IPTVNavHost() {
    val navController = rememberNavController()
    var currentTab by remember { mutableStateOf(NavTab.HOME) }
    var isDrawerOpen by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = Screen.Setup.route) {

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
                        NavTab.SERIES -> navController.navigate(Screen.Series.route)
                        NavTab.SEARCH -> navController.navigate(Screen.Search.route)
                        NavTab.EPG -> navController.navigate(Screen.Epg.route)
                        else -> {}
                    }
                },
                onNavigate = { route -> navController.navigate(route) },
                onPlayStream = { type, id, url, title, icon ->
                    navController.navigate(Screen.Player.createRoute(type, id, url, title, icon))
                },
                onSettingsClick = { isDrawerOpen = !isDrawerOpen }
            )
        }

        composable(Screen.Live.route) {
            LiveScreen(
                currentTab = NavTab.LIVE,
                onTabSelected = { tab ->
                    currentTab = tab
                    navController.navigate(when (tab) {
                        NavTab.HOME -> Screen.Home.route
                        NavTab.MOVIES -> Screen.Movies.route
                        NavTab.SERIES -> Screen.Series.route
                        NavTab.SEARCH -> Screen.Search.route
                        NavTab.EPG -> Screen.Epg.route
                        else -> Screen.Live.route
                    })
                },
                onPlayStream = { type, id, url, title, icon ->
                    navController.navigate(Screen.Player.createRoute(type, id, url, title, icon))
                },
                onSettingsClick = {}
            )
        }

        composable(Screen.Movies.route) {
            MoviesScreen(
                currentTab = NavTab.MOVIES,
                onTabSelected = { tab ->
                    currentTab = tab
                    navController.navigate(when (tab) {
                        NavTab.HOME -> Screen.Home.route
                        NavTab.LIVE -> Screen.Live.route
                        NavTab.SERIES -> Screen.Series.route
                        NavTab.SEARCH -> Screen.Search.route
                        NavTab.EPG -> Screen.Epg.route
                        else -> Screen.Movies.route
                    })
                },
                onPlayMovie = { url, id, title, icon ->
                    navController.navigate(Screen.Player.createRoute("movie", id, url, title, icon))
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

        composable(
            route = Screen.Player.route,
            arguments = listOf(
                navArgument("type") { type = NavType.StringType },
                navArgument("id") { type = NavType.StringType },
                navArgument("url") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("icon") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            PlayerScreen(
                type = backStackEntry.arguments?.getString("type") ?: "",
                id = backStackEntry.arguments?.getString("id") ?: "",
                url = backStackEntry.arguments?.getString("url")?.decodeFromRoute() ?: "",
                title = backStackEntry.arguments?.getString("title")?.decodeFromRoute() ?: "",
                icon = backStackEntry.arguments?.getString("icon")?.decodeFromRoute() ?: "",
                onBack = { navController.popBackStack() }
            )
        }
    }
}
