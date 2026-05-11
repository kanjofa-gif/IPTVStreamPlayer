package com.iptvstream.ui.screens.playlists;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\rJ&\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0014J\u000e\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0019\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001a"}, d2 = {"Lcom/iptvstream/ui/screens/playlists/ManagePlaylistsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/iptvstream/data/repository/IPTVRepository;", "(Lcom/iptvstream/data/repository/IPTVRepository;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/iptvstream/ui/screens/playlists/ManagePlaylistsState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "deletePlaylist", "", "playlist", "Lcom/iptvstream/data/model/Playlist;", "editPlaylist", "hideDialog", "savePlaylist", "name", "", "url", "username", "password", "selectPlaylist", "showAddDialog", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ManagePlaylistsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.iptvstream.data.repository.IPTVRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.iptvstream.ui.screens.playlists.ManagePlaylistsState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.iptvstream.ui.screens.playlists.ManagePlaylistsState> state = null;
    
    @javax.inject.Inject()
    public ManagePlaylistsViewModel(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.repository.IPTVRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.iptvstream.ui.screens.playlists.ManagePlaylistsState> getState() {
        return null;
    }
    
    public final void selectPlaylist(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Playlist playlist) {
    }
    
    public final void deletePlaylist(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Playlist playlist) {
    }
    
    public final void editPlaylist(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Playlist playlist) {
    }
    
    public final void showAddDialog() {
    }
    
    public final void hideDialog() {
    }
    
    public final void savePlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
}