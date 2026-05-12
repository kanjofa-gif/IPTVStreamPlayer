package com.iptvstream.ui

sealed class Screen(val route: String) {
    data object Setup : Screen("setup")
    data object Loading : Screen("loading")
    data object Home : Screen("home")
    data object Live : Screen("live")
    data object Movies : Screen("movies")
    data object Series : Screen("series")
    data object Search : Screen("search")
    data object Epg : Screen("epg")
    data object Settings : Screen("settings")
    data object ManagePlaylists : Screen("manage_playlists")
    data object ManageCategories : Screen("manage_categories/{type}") {
        fun createRoute(type: String) = "manage_categories/$type"
    }
    data object Account : Screen("account")
    data object Whats_New : Screen("whats_new")
    data object MobileApp : Screen("mobile_app")
    data object Player : Screen("player")
}

fun String.decodeFromRoute() = this
fun String.encodeForRoute() = this
