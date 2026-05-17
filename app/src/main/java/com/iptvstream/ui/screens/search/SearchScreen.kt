package com.iptvstream.ui.screens.search

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iptvstream.ui.PlayItem
import com.iptvstream.ui.PlayerHolder
import com.iptvstream.ui.components.*
import com.iptvstream.ui.theme.*

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onPlayStream: (type: String, id: String, url: String, title: String, icon: String) -> Unit,
    onOpenSeries: () -> Unit,
    onSettingsClick: () -> Unit,
    currentTab: NavTab,
    onTabSelected: (NavTab) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val searchFocus = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        try { searchFocus.requestFocus() } catch (e: Exception) {}
    }

    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        Column(modifier = Modifier.fillMaxSize()) {
            IPTVTopBar(
                currentTab = currentTab,
                onTabSelected = onTabSelected,
                onSettingsClick = onSettingsClick,
                onContinueWatchingClick = {}
            )
            HorizontalDivider(color = Divider, thickness = 0.5.dp)

            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = state.query,
                    onValueChange = viewModel::updateQuery,
                    placeholder = {
                        Text(
                            "ابحث عن قناة، فيلم، أو مسلسل...",
                            color = TextMuted,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    leadingIcon = { Icon(Icons.Default.Search, null, tint = TextMuted) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = Color(0xFF333333),
                        focusedContainerColor = Surface,
                        unfocusedContainerColor = Surface,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .focusRequester(searchFocus)
                )
            }

            // Tab Filters
            if (state.query.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
                ) {
                    SearchTab.entries.forEach { tab ->
                        val count = when (tab) {
                            SearchTab.ALL -> state.totalResults
                            SearchTab.LIVE -> state.liveResults.size
                            SearchTab.MOVIES -> state.movieResults.size
                            SearchTab.SERIES -> state.seriesResults.size
                        }
                        SearchTabChip(
                            label = "${tab.label} ($count)",
                            isSelected = state.selectedTab == tab,
                            onClick = { viewModel.selectTab(tab) }
                        )
                    }
                }
            }

            // Results
            when {
                state.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Primary)
                    }
                }
                state.query.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Default.Search,
                                null,
                                tint = TextMuted,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(Modifier.height(16.dp))
                            Text("ابدأ البحث", color = TextSecondary, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                            Spacer(Modifier.height(4.dp))
                            Text("ابحث في القنوات والأفلام والمسلسلات", color = TextMuted, fontSize = 13.sp)
                        }
                    }
                }
                state.totalResults == 0 -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("لا توجد نتائج", color = TextSecondary, fontSize = 16.sp)
                    }
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 140.dp),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Live results
                        if (state.selectedTab == SearchTab.ALL || state.selectedTab == SearchTab.LIVE) {
                            items(state.liveResults, key = { "live_${it.stream_id}" }) { stream ->
                                ChannelCard(
                                    name = stream.name,
                                    icon = stream.stream_icon,
                                    onClick = {
                                        val items = state.liveResults.map {
                                            PlayItem(
                                                type = "live",
                                                id = it.stream_id.toString(),
                                                url = state.liveUrl(it.stream_id),
                                                title = it.name,
                                                icon = it.stream_icon
                                            )
                                        }
                                        val index = state.liveResults.indexOf(stream)
                                        PlayerHolder.setPlaylist(items, index)
                                        onPlayStream("live", stream.stream_id.toString(),
                                            state.liveUrl(stream.stream_id), stream.name, stream.stream_icon)
                                    }
                                )
                            }
                        }

                        // Movies results
                        if (state.selectedTab == SearchTab.ALL || state.selectedTab == SearchTab.MOVIES) {
                            items(state.movieResults, key = { "movie_${it.stream_id}" }) { movie ->
                                MovieCard(
                                    name = movie.name,
                                    icon = movie.stream_icon,
                                    rating = if (movie.rating_5based > 0) String.format("%.1f", movie.rating_5based) else "",
                                    onClick = {
                                        val items = state.movieResults.map {
                                            PlayItem(
                                                type = "movie",
                                                id = it.stream_id.toString(),
                                                url = state.movieUrl(it.stream_id, it.container_extension),
                                                title = it.name,
                                                icon = it.stream_icon
                                            )
                                        }
                                        val index = state.movieResults.indexOf(movie)
                                        PlayerHolder.setPlaylist(items, index)
                                        val url = state.movieUrl(movie.stream_id, movie.container_extension)
                                        onPlayStream("movie", movie.stream_id.toString(), url, movie.name, movie.stream_icon)
                                    }
                                )
                            }
                        }

                        // Series results
                        if (state.selectedTab == SearchTab.ALL || state.selectedTab == SearchTab.SERIES) {
                            items(state.seriesResults, key = { "series_${it.series_id}" }) { series ->
                                MovieCard(
                                    name = series.name,
                                    icon = series.cover,
                                    rating = if (series.rating_5based > 0) String.format("%.1f", series.rating_5based) else "",
                                    onClick = { onOpenSeries() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchTabChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val active = isFocused || isSelected
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (active) Primary else SurfaceVariant)
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            label,
            color = if (active) Color.White else TextPrimary,
            fontSize = 13.sp,
            fontWeight = if (active) FontWeight.Bold else FontWeight.Normal
        )
    }
}
