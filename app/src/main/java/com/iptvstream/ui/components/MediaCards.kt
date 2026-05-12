package com.iptvstream.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.iptvstream.ui.theme.*

@Composable
fun ChannelCard(
    name: String,
    icon: String,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 140.dp,
    height: Dp = 100.dp
) {
    var isFocused by remember { mutableStateOf(false) }
    val active = isFocused || isSelected

    Box(
        modifier = modifier
            .size(width, height)
            .scale(if (active) 1.08f else 1f)
            .clip(RoundedCornerShape(12.dp))
            .background(if (active) Primary.copy(alpha = 0.25f) else CardBackground)
            .border(
                width = if (active) 3.dp else 0.5.dp,
                color = if (active) Primary else Color(0xFF2A2A2A),
                shape = RoundedCornerShape(12.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = icon,
                contentDescription = name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(64.dp, 48.dp)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = name,
                color = TextPrimary,
                fontSize = 11.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun MovieCard(
    name: String,
    icon: String,
    rating: String = "",
    year: String = "",
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 140.dp,
    height: Dp = 200.dp
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .width(width)
            .scale(if (isFocused) 1.08f else 1f)
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .height(height)
                .clip(RoundedCornerShape(10.dp))
                .background(CardBackground)
                .border(
                    width = if (isFocused) 3.dp else 0.dp,
                    color = if (isFocused) Primary else Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            AsyncImage(
                model = icon,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            if (rating.isNotBlank() && rating != "0") {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp)
                        .background(Color(0xCC000000), RoundedCornerShape(4.dp))
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                ) {
                    Text("⭐ $rating", color = Color.White, fontSize = 10.sp)
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = if (year.isNotBlank()) "$name ($year)" else name,
            color = if (isFocused) Primary else TextPrimary,
            fontSize = 12.sp,
            fontWeight = if (isFocused) FontWeight.Bold else FontWeight.Normal,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SectionRow(
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )
        content()
    }
}

@Composable
fun <T> HorizontalScrollRow(
    items: List<T>,
    key: (T) -> Any,
    itemContent: @Composable (T) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items, key = key) { item ->
            itemContent(item)
        }
    }
}

@Composable
fun CategoryListItem(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val active = isFocused || isSelected

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                when {
                    isFocused -> Primary.copy(alpha = 0.35f)
                    isSelected -> Primary.copy(alpha = 0.2f)
                    else -> Color.Transparent
                }
            )
            .border(
                width = if (active) 1.dp else 0.dp,
                color = if (active) Primary else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = name,
            color = if (active) Color.White else TextSecondary,
            fontSize = 15.sp,
            fontWeight = if (active) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun EmptyState(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(message, color = TextSecondary, fontSize = 16.sp)
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Primary)
    }
}
