package com.iptvstream.ui.screens.series

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.iptvstream.data.model.Episode
import com.iptvstream.ui.components.*
import com.iptvstream.ui.theme.*

@Composable
fun SeriesScreen(
    viewModel: SeriesViewModel = hiltViewModel(),
    onPlayEpisode: (url: String, id: String, title: String, icon: String) -> Unit,
    onSettingsClick: () -> Unit,
    currentTab: NavTab,
    onTabSelected: (NavTab) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        Column(modifier = Modifier.fillMaxSize()) {
            IPTVTopBar(
                currentTab = currentTab,
                onTabSelected = onTabSelected,
                onSettingsClick = onSettingsClick,
                onContinueWatchingClick = {}
            )
            HorizontalDivider(color = Divider, thickness = 0.5.dp)

            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    if (state.isLoading) {
                        LoadingState()
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 140.dp),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.filteredSeries, key = { it.series_id }) { series ->
                                MovieCard(
                                    name = series.name,
                                    icon = series.cover,
                                    rating = if (series.rating_5based > 0) String.format("%.1f", series.rating_5based) else "",
                                    onClick = { viewModel.selectSeries(series) }
                                )
                            }
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .width(260.dp)
                        .fillMaxHeight()
                        .background(SurfaceVariant)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    item {
                        CategoryListItem("الكل", state.selectedCategoryId == null || state.selectedCategoryId == "all") {
                            viewModel.selectCategory("all")
                        }
                        CategoryListItem("المفضلات", state.selectedCategoryId == "favorites") {
                            viewModel.selectCategory("favorites")
                        }
                    }
                    items(state.categories, key = { it.category_id }) { cat ->
                        CategoryListItem(cat.category_name, state.selectedCategoryId == cat.category_id) {
                            viewModel.selectCategory(cat.category_id)
                        }
                    }
                }
            }
        }

        state.selectedSeries?.let { series ->
            Dialog(
                onDismissRequest = viewModel::dismissDetail,
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                )
            ) {
                SeriesDetailContent(
                    seriesName = series.name,
                    cover = series.cover,
                    plot = series.plot,
                    isLoading = state.isLoadingInfo,
                    seriesInfo = state.seriesInfo,
                    onDismiss = viewModel::dismissDetail,
                    onPlayEpisode = { episode ->
                        val url = viewModel.episodeUrl(episode.id, episode.container_extension ?: "mkv")
                        onPlayEpisode(url, episode.id, episode.title ?: series.name, series.cover)
                        viewModel.dismissDetail()
                    }
                )
            }
        }
    }
}

@Composable
fun SeriesDetailContent(
    seriesName: String,
    cover: String,
    plot: String?,
    isLoading: Boolean,
    seriesInfo: com.iptvstream.data.model.SeriesInfo?,
    onDismiss: () -> Unit,
    onPlayEpisode: (Episode) -> Unit
) {
    var selectedSeason by remember { mutableStateOf("1") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.95f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.92f)
                .clip(RoundedCornerShape(16.dp))
                .background(Surface)
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                AsyncImage(
                    model = cover,
                    contentDescription = seriesName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(140.dp)
                        .aspectRatio(2f / 3f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardBackground)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, null, tint = TextPrimary)
                        }
                        Text(
                            seriesName,
                            color = TextPrimary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (!plot.isNullOrBlank()) {
                        Text(
                            plot,
                            color = TextSecondary,
                            fontSize = 13.sp,
                            textAlign = TextAlign.End,
                            maxLines = 4
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            HorizontalDivider(color = Divider)
            Spacer(Modifier.height(16.dp))

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Primary)
                }
            } else if (seriesInfo != null) {
                val seasons = seriesInfo.episodes?.keys?.sortedBy { it.toIntOrNull() ?: 0 } ?: emptyList()

                if (seasons.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("لا توجد حلقات", color = TextSecondary, fontSize = 16.sp)
                    }
                } else {
                    if (selectedSeason !in seasons) selectedSeason = seasons.first()

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(seasons) { season ->
                            SeasonTab(
                                season = season,
                                isSelected = selectedSeason == season,
                                onClick = { selectedSeason = season }
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    val episodes = seriesInfo.episodes?.get(selectedSeason) ?: emptyList()
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 280.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(episodes, key = { it.id }) { episode ->
                            EpisodeCard(
                                episode = episode,
                                seriesCover = cover,
                                onClick = { onPlayEpisode(episode) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SeasonTab(
    season: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val active = isFocused || isSelected
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (active) Primary else SurfaceVariant)
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            "الموسم $season",
            color = if (active) Color.White else TextPrimary,
            fontSize = 14.sp,
            fontWeight = if (active) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun EpisodeCard(
    episode: Episode,
    seriesCover: String,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (isFocused) Primary.copy(alpha = 0.3f) else CardBackground)
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Primary else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = episode.info?.movie_image ?: seriesCover,
            contentDescription = episode.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.DarkGray)
        )
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                "الحلقة ${episode.episode_num}",
                color = if (isFocused) Color.White else Primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                episode.title ?: "",
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            episode.info?.plot?.let { plotText ->
                if (plotText.isNotBlank()) {
                    Text(plotText, color = TextSecondary, fontSize = 11.sp, maxLines = 2)
                }
            }
        }
    }
}
