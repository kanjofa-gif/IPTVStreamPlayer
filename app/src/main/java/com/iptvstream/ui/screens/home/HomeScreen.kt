package com.iptvstream.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iptvstream.ui.components.*

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
                onContinueWatchingClick = { }
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
                                rating = if (movie.rating_5based > 0) String.format("%.1f", movie.rating_5based) else "",
                                year = "",
                                onClick = {
                                    val url = viewModel.movieUrl(movie.stream_id, movie.container_extension)
                                    onPlayStream("movie", movie.stream_id.toString(), url, movie.name, movie.stream_icon)
                                }
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
                                rating = if (series.rating_5based > 0) String.format("%.1f", series.rating_5based) else "",
                                onClick = {
                                    onTabSelected(NavTab.SERIES)
                                }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
