package com.iptvstream.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iptvstream.ui.components.*
import com.iptvstream.ui.theme.Background

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit,
    onPlayStream: (type: String, id: String, url: String, title: String, icon: String) -> Unit,
    onSettingsClick: () -> Unit,
    currentTab: NavTab,
    onTabSelected: (NavTab) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(com.iptvstream.ui.theme.Background)) {
        Column(modifier = Modifier.fillMaxSize()) {

            IPTVTopBar(
                currentTab = currentTab,
                onTabSelected = onTabSelected,
                onSettingsClick = onSettingsClick,
                onContinueWatchingClick = { onNavigate("player") }
            )

            HorizontalDivider(color = com.iptvstream.ui.theme.Divider, thickness = 0.5.dp)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                if (state.recentLive.isNotEmpty()) {
                    SectionRow(title = "البث المباشر") {
                        HorizontalScrollRow(
                            items = state.recentLive,
                            key = { it.streamId }
                        ) { item ->
                            ChannelCard(
                                name = item.streamName,
                                icon = item.streamIcon,
                                onClick = {
                                    onPlayStream("live", item.streamId, item.streamUrl, item.streamName, item.streamIcon)
                                }
                            )
                        }
                    }
                }

                if (state.latestMovies.isNotEmpty()) {
                    SectionRow(title = "الأفلام") {
                        HorizontalScrollRow(
                            items = state.latestMovies,
                            key = { it.stream_id }
                        ) { movie ->
                            MovieCard(
                                name = movie.name,
                                icon = movie.stream_icon,
                                rating = movie.rating_5based.let { if (it > 0) String.format("%.1f", it) else "" },
                                year = movie.added.takeIf { it.isNotBlank() }?.let {
                                    try { java.util.Date(it.toLong() * 1000).let { d ->
                                        java.text.SimpleDateFormat("yyyy").format(d)
                                    }} catch (e: Exception) { "" }
                                } ?: "",
                                onClick = { onNavigate("movies/${movie.stream_id}") }
                            )
                        }
                    }
                }

                if (state.latestSeries.isNotEmpty()) {
                    SectionRow(title = "المسلسلات") {
                        HorizontalScrollRow(
                            items = state.latestSeries,
                            key = { it.series_id }
                        ) { series ->
                            MovieCard(
                                name = series.name,
                                icon = series.cover,
                                rating = series.rating_5based.let { if (it > 0) String.format("%.1f", it) else "" },
                                onClick = { onNavigate("series/${series.series_id}") }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
            }
        }

        if (state.isDrawerOpen) {
            SettingsDrawer(
                isVisible = true,
                userInfo = state.userInfo,
                expDate = state.expDate,
                maxConnections = state.maxConnections,
                isTrial = state.isTrial,
                onDismiss = viewModel::closeDrawer,
                onAccount = { viewModel.closeDrawer(); onNavigate("account") },
                onRefresh = viewModel::refreshData,
                onManagePlaylists = { viewModel.closeDrawer(); onNavigate("manage_playlists") },
                onManageCategories = { viewModel.closeDrawer(); onNavigate("manage_categories/live") },
                onSettings = { viewModel.closeDrawer(); onNavigate("settings") },
                onWhatsNew = { viewModel.closeDrawer(); onNavigate("whats_new") },
                onMobileApp = { viewModel.closeDrawer(); onNavigate("mobile_app") },
                onNotes = { viewModel.closeDrawer() }
            )
        }
    }
}
