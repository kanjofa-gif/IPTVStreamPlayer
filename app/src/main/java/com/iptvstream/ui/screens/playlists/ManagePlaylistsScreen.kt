package com.iptvstream.ui.screens.playlists

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iptvstream.data.model.Playlist
import com.iptvstream.ui.theme.*

@Composable
fun ManagePlaylistsScreen(
    viewModel: ManagePlaylistsViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, null, tint = TextPrimary)
                }
                Spacer(Modifier.weight(1f))
                Text(
                    "إدارة قوائم التشغيل",
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.playlists, key = { it.id }) { playlist ->
                    PlaylistItem(
                        playlist = playlist,
                        onSelect = { viewModel.selectPlaylist(playlist) },
                        onEdit = { viewModel.editPlaylist(playlist) },
                        onDelete = { viewModel.deletePlaylist(playlist) }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Add button
            OutlinedButton(
                onClick = viewModel::showAddDialog,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFF444444))
            ) {
                Icon(Icons.Default.Add, null, tint = TextPrimary)
                Spacer(Modifier.width(8.dp))
                Text("إضافة قائمة تشغيل", color = TextPrimary, fontSize = 15.sp)
            }
        }

        // Add/Edit Dialog
        if (state.showDialog) {
            AddPlaylistDialog(
                initialName = state.editingPlaylist?.name ?: "",
                initialUrl = state.editingPlaylist?.url ?: "",
                initialUsername = state.editingPlaylist?.username ?: "",
                initialPassword = state.editingPlaylist?.password ?: "",
                onDismiss = viewModel::hideDialog,
                onSave = { name, url, username, password ->
                    viewModel.savePlaylist(name, url, username, password)
                }
            )
        }
    }
}

@Composable
fun PlaylistItem(
    playlist: Playlist,
    onSelect: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (playlist.isSelected) Primary.copy(alpha = 0.15f) else Surface)
            .border(
                width = if (playlist.isSelected) 1.dp else 0.5.dp,
                color = if (playlist.isSelected) Primary else Color(0xFF2A2A2A),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Delete
        IconButton(onClick = onDelete, modifier = Modifier.size(36.dp)) {
            Icon(Icons.Default.Delete, null, tint = AccentRed, modifier = Modifier.size(20.dp))
        }

        // Edit
        IconButton(onClick = onEdit, modifier = Modifier.size(36.dp)) {
            Icon(Icons.Default.Edit, null, tint = TextSecondary, modifier = Modifier.size(20.dp))
        }

        Spacer(Modifier.weight(1f))

        // Name + selected status
        Column(horizontalAlignment = Alignment.End) {
            Text(playlist.name, color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Medium)
            if (playlist.isSelected) {
                Text("✓ محددة", color = AccentGreen, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun AddPlaylistDialog(
    initialName: String,
    initialUrl: String,
    initialUsername: String,
    initialPassword: String,
    onDismiss: () -> Unit,
    onSave: (String, String, String, String) -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var url by remember { mutableStateOf(initialUrl) }
    var username by remember { mutableStateOf(initialUsername) }
    var password by remember { mutableStateOf(initialPassword) }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.8f)).clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(600.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Surface)
                .clickable { }
                .padding(24.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("إضافة قائمة تشغيل", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = url, onValueChange = { url = it },
                    placeholder = { Text("الرابط", color = TextMuted, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()) },
                    modifier = Modifier.weight(1f),
                    colors = dialogTextFieldColors(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = name, onValueChange = { name = it },
                    placeholder = { Text("اسم القائمة", color = TextMuted, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()) },
                    modifier = Modifier.weight(1f),
                    colors = dialogTextFieldColors(),
                    singleLine = true
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = password, onValueChange = { password = it },
                    placeholder = { Text("كلمة المرور", color = TextMuted, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.weight(1f),
                    colors = dialogTextFieldColors(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = username, onValueChange = { username = it },
                    placeholder = { Text("اسم المستخدم", color = TextMuted, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()) },
                    modifier = Modifier.weight(1f),
                    colors = dialogTextFieldColors(),
                    singleLine = true
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onDismiss, border = BorderStroke(1.dp, Color(0xFF444444))) {
                    Text("إلغاء", color = TextPrimary)
                }
                Button(
                    onClick = { onSave(name, url, username, password) },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    enabled = url.isNotBlank()
                ) {
                    Text("حفظ")
                }
            }
        }
    }
}

@Composable
fun dialogTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Primary,
    unfocusedBorderColor = Color(0xFF333333),
    focusedContainerColor = SurfaceVariant,
    unfocusedContainerColor = SurfaceVariant,
    focusedTextColor = TextPrimary,
    unfocusedTextColor = TextPrimary
)
