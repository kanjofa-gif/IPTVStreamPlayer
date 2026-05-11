package com.iptvstream.ui.screens.live

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iptvstream.ui.components.*
import com.iptvstream.ui.theme.*

@Composable
fun LiveScreen(
    viewModel: LiveViewModel = hiltViewModel(),
    onPlayStream: (type: String, id: String, url: String, title: String, icon: String) -> Unit,
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
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.filteredStreams, key = { it.stream_id }) { stream ->
                                ChannelCard(
                                    name = stream.name,
                                    icon = stream.stream_icon,
                                    onClick = {
                                        onPlayStream(
                                            "live",
                                            stream.stream_id.toString(),
                                            state.streamUrl(stream.stream_id),
                                            stream.name,
                                            stream.stream_icon
                                        )
                                    }
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .width(260.dp)
                        .fillMaxHeight()
                        .background(SurfaceVariant)
                        .padding(8.dp)
                ) {
                    OutlinedTextField(
                        value = state.searchQuery,
                        onValueChange = viewModel::onSearch,
                        placeholder = { Text("بحث...", color = TextMuted, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()) },
                        leadingIcon = { Icon(Icons.Default.Search, null, tint = TextMuted, modifier = Modifier.size(18.dp)) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = Color(0xFF333333),
                            focusedContainerColor = Surface,
                            unfocusedContainerColor = Surface,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().height(48.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        item {
                            CategoryListItem(
                                name = "المفضلات",
                                isSelected = state.selectedCategoryId == "favorites",
                                onClick = { viewModel.selectCategory("favorites") }
                            )
                        }
                        items(state.categories, key = { it.category_id }) { cat ->
                            CategoryListItem(
                                name = cat.category_name,
                                isSelected = state.selectedCategoryId == cat.category_id,
                                onClick = { viewModel.selectCategory(cat.category_id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
