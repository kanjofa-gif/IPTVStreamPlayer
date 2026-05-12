package com.iptvstream.ui.screens.movies

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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import com.iptvstream.data.model.VodStream
import com.iptvstream.ui.PlayItem
import com.iptvstream.ui.PlayerHolder
import com.iptvstream.ui.components.*
import com.iptvstream.ui.theme.*

@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onPlayMovie: (url: String, id: String, title: String, icon: String) -> Unit,
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
                            items(state.filteredMovies, key = { it.stream_id }) { movie ->
                                MovieCard(
                                    name = movie.name,
                                    icon = movie.stream_icon,
                                    rating = if (movie.rating_5based > 0) String.format("%.1f", movie.rating_5based) else "",
                                    onClick = { viewModel.selectMovie(movie) }
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
                        CategoryListItem("المفضلات", state.selectedCategoryId == "favorites") { viewModel.selectCategory("favorites") }
                        CategoryListItem("متابعة المشاهدة", state.selectedCategoryId == "continue") { viewModel.selectCategory("continue") }
                        CategoryListItem("آخر المضاف", state.selectedCategoryId == "latest") { viewModel.selectCategory("latest") }
                    }
                    items(state.categories, key = { it.category_id }) { cat ->
                        CategoryListItem(cat.category_name, state.selectedCategoryId == cat.category_id) {
                            viewModel.selectCategory(cat.category_id)
                        }
                    }
                }
            }
        }

        state.selectedMovie?.let { movie ->
            Dialog(
                onDismissRequest = viewModel::dismissDetail,
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                )
            ) {
                MovieDetailContent(
                    movie = movie,
                    watchProgress = state.watchProgress,
                    isFavorite = state.isFavorite,
                    onDismiss = viewModel::dismissDetail,
                    onPlay = {
                        val items = state.filteredMovies.map {
                            PlayItem(
                                type = "movie",
                                id = it.stream_id.toString(),
                                url = state.movieUrl(it.stream_id, it.container_extension),
                                title = it.name,
                                icon = it.stream_icon
                            )
                        }
                        val index = state.filteredMovies.indexOf(movie)
                        PlayerHolder.setPlaylist(items, index)
                        val url = state.movieUrl(movie.stream_id, movie.container_extension)
                        onPlayMovie(url, movie.stream_id.toString(), movie.name, movie.stream_icon)
                        viewModel.dismissDetail()
                    },
                    onPlayNew = {
                        val items = state.filteredMovies.map {
                            PlayItem(
                                type = "movie",
                                id = it.stream_id.toString(),
                                url = state.movieUrl(it.stream_id, it.container_extension),
                                title = it.name,
                                icon = it.stream_icon
                            )
                        }
                        val index = state.filteredMovies.indexOf(movie)
                        PlayerHolder.setPlaylist(items, index)
                        val url = state.movieUrl(movie.stream_id, movie.container_extension)
                        onPlayMovie(url, movie.stream_id.toString(), movie.name, movie.stream_icon)
                        viewModel.dismissDetail()
                    },
                    onToggleFavorite = viewModel::toggleFavorite,
                    onRemoveProgress = viewModel::removeProgress
                )
            }
        }
    }
}

@Composable
fun MovieDetailContent(
    movie: VodStream,
    watchProgress: Long?,
    isFavorite: Boolean,
    onDismiss: () -> Unit,
    onPlay: () -> Unit,
    onPlayNew: () -> Unit,
    onToggleFavorite: () -> Unit,
    onRemoveProgress: () -> Unit
) {
    val playFocus = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        try { playFocus.requestFocus() } catch (e: Exception) {}
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.92f)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(16.dp))
                .background(Surface)
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AsyncImage(
                model = movie.stream_icon,
                contentDescription = movie.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(180.dp)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardBackground)
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(movie.name, color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.End)

                if (!movie.plot.isNullOrBlank()) {
                    Text(movie.plot!!, color = TextSecondary, fontSize = 13.sp, textAlign = TextAlign.End, maxLines = 4)
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = onPlay,
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    modifier = Modifier.fillMaxWidth().focusRequester(playFocus)
                ) {
                    Icon(Icons.Default.PlayArrow, null, tint = Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text("تشغيل", fontSize = 15.sp, color = Color.White)
                }

                OutlinedButton(
                    onClick = onPlayNew,
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, Color(0xFF444444))
                ) {
                    Icon(Icons.Default.Refresh, null, modifier = Modifier.size(18.dp), tint = TextPrimary)
                    Spacer(Modifier.width(8.dp))
                    Text("ابدأ من جديد", color = TextPrimary)
                }

                OutlinedButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, Color(0xFF444444))
                ) {
                    Icon(
                        if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        null,
                        modifier = Modifier.size(18.dp),
                        tint = if (isFavorite) AccentOrange else TextPrimary
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(if (isFavorite) "إزالة من المفضلة" else "إضافة للمفضلة", color = TextPrimary)
                }

                if (watchProgress != null) {
                    OutlinedButton(
                        onClick = onRemoveProgress,
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, Color(0xFF444444))
                    ) {
                        Icon(Icons.Default.Close, null, modifier = Modifier.size(18.dp), tint = AccentRed)
                        Spacer(Modifier.width(8.dp))
                        Text("إزالة من متابعة المشاهدة", color = AccentRed)
                    }
                }

                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, Color(0xFF444444))
                ) {
                    Icon(Icons.Default.Close, null, modifier = Modifier.size(18.dp), tint = TextPrimary)
                    Spacer(Modifier.width(8.dp))
                    Text("إغلاق", color = TextPrimary)
                }
            }
        }
    }
}
