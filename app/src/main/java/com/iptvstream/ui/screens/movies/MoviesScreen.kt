package com.iptvstream.ui.screens.movies

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.iptvstream.data.model.VodStream
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
            Divider(color = Divider, thickness = 0.5.dp)

            Row(modifier = Modifier.fillMaxSize()) {
                // Movies grid
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
                                    rating = movie.rating_5based.let { if (it > 0) String.format("%.1f", it) else "" },
                                    onClick = { viewModel.selectMovie(movie) }
                                )
                            }
                        }
                    }
                }

                // Categories list
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

        // Movie Detail Dialog
        state.selectedMovie?.let { movie ->
            MovieDetailDialog(
                movie = movie,
                watchProgress = state.watchProgress,
                isFavorite = state.isFavorite,
                onDismiss = viewModel::dismissDetail,
                onPlay = { url ->
                    onPlayMovie(url, movie.stream_id.toString(), movie.name, movie.stream_icon)
                    viewModel.dismissDetail()
                },
                onPlayNew = {
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

@Composable
fun MovieDetailDialog(
    movie: VodStream,
    watchProgress: Long?,
    isFavorite: Boolean,
    onDismiss: () -> Unit,
    onPlay: (String) -> Unit,
    onPlayNew: () -> Unit,
    onToggleFavorite: () -> Unit,
    onRemoveProgress: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.85f))
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(16.dp))
                .background(Surface)
                .clickable { }
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Poster
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

            // Info
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(movie.name, color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.End)

                val genre = movie.genre ?: ""
                val duration = movie.duration ?: ""
                if (genre.isNotBlank() || duration.isNotBlank()) {
                    Text(
                        text = listOf(duration, genre).filter { it.isNotBlank() }.joinToString(" · "),
                        color = TextSecondary, fontSize = 14.sp
                    )
                }

                // Action buttons row
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Favorite
                    OutlinedButton(
                        onClick = onToggleFavorite,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = if (isFavorite) AccentOrange else TextPrimary),
                        border = BorderStroke(1.dp, if (isFavorite) AccentOrange else Color(0xFF444444))
                    ) {
                        Icon(if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder, null, modifier = Modifier.size(18.dp))
                    }

                    // Trailer
                    if (!movie.youtube_trailer.isNullOrBlank()) {
                        OutlinedButton(onClick = {}) {
                            Text("المقطع الدعائي", fontSize = 13.sp)
                        }
                    }

                    // Watch status
                    OutlinedButton(onClick = {}) {
                        Icon(Icons.Default.Close, null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("لم تتم المشاهدة", fontSize = 13.sp)
                    }
                }

                // Progress bar if watching
                if (watchProgress != null && watchProgress > 0) {
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(formatDuration(watchProgress), color = TextSecondary, fontSize = 13.sp)
                        LinearProgressIndicator(
                            progress = { 0.5f },
                            modifier = Modifier.weight(1f).height(3.dp).clip(RoundedCornerShape(2.dp)),
                            color = Primary,
                            trackColor = Color(0xFF333333)
                        )
                        Text("2:15", color = TextSecondary, fontSize = 13.sp)
                    }
                }

                Spacer(Modifier.height(8.dp))

                // Play buttons
                Button(
                    onClick = { onPlay("") },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.PlayArrow, null)
                    Spacer(Modifier.width(8.dp))
                    Text("متابعة", fontSize = 15.sp)
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

                // Description
                if (!movie.plot.isNullOrBlank()) {
                    Text(movie.plot!!, color = TextSecondary, fontSize = 13.sp, textAlign = TextAlign.End, maxLines = 4)
                }
            }
        }
    }
}

private fun formatDuration(ms: Long): String {
    val totalSec = ms / 1000
    val h = totalSec / 3600
    val m = (totalSec % 3600) / 60
    val s = totalSec % 60
    return if (h > 0) "%d:%02d:%02d".format(h, m, s) else "%d:%02d".format(m, s)
}
