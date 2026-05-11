package com.iptvstream.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
                            item
