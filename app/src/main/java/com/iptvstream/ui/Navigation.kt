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

    // ─── Account flow screens ────────────────────────────────────────────────
    data object AccountWelcome : Screen("account_welcome")
    data object AccountQRCode : Screen("account_qr")
    data object AccountConfirm : Screen("account_confirm")
    data object AccountProfile : Screen("account_profile")
    data object AccountLinked : Screen("account_linked")
    data object TrialExpired : Screen("trial_expired")

    data object MobileApp : Screen("mobile_app")
    data object Player : Screen("player")
}

fun String.decodeFromRoute() = this
fun String.encodeForRoute() = this
