package com.iptvstream.ui.screens.playlists;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\\\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2$\u0010\t\u001a \u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a \u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007\u001a:\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007\u001a\b\u0010\u0015\u001a\u00020\u0016H\u0007\u00a8\u0006\u0017"}, d2 = {"AddPlaylistDialog", "", "initialName", "", "initialUrl", "initialUsername", "initialPassword", "onDismiss", "Lkotlin/Function0;", "onSave", "Lkotlin/Function4;", "ManagePlaylistsScreen", "viewModel", "Lcom/iptvstream/ui/screens/playlists/ManagePlaylistsViewModel;", "onBack", "PlaylistItem", "playlist", "Lcom/iptvstream/data/model/Playlist;", "onSelect", "onEdit", "onDelete", "dialogTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "app_debug"})
public final class ManagePlaylistsScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void ManagePlaylistsScreen(@org.jetbrains.annotations.NotNull()
    com.iptvstream.ui.screens.playlists.ManagePlaylistsViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PlaylistItem(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Playlist playlist, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSelect, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AddPlaylistDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String initialName, @org.jetbrains.annotations.NotNull()
    java.lang.String initialUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String initialUsername, @org.jetbrains.annotations.NotNull()
    java.lang.String initialPassword, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function4<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onSave) {
    }
    
    @androidx.compose.runtime.Composable()
    @org.jetbrains.annotations.NotNull()
    public static final androidx.compose.material3.TextFieldColors dialogTextFieldColors() {
        return null;
    }
}