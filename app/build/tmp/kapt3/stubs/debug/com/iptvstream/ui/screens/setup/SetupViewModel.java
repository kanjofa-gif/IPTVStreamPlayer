package com.iptvstream.ui.screens.setup;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0018"}, d2 = {"Lcom/iptvstream/ui/screens/setup/SetupViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/iptvstream/data/repository/IPTVRepository;", "(Lcom/iptvstream/data/repository/IPTVRepository;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/iptvstream/ui/screens/setup/SetupState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "addPlaylist", "", "extractDomain", "", "url", "onNameChanged", "name", "onPasswordChanged", "p", "onUrlChanged", "onUsernameChanged", "u", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SetupViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.iptvstream.data.repository.IPTVRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.iptvstream.ui.screens.setup.SetupState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.iptvstream.ui.screens.setup.SetupState> state = null;
    
    @javax.inject.Inject()
    public SetupViewModel(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.repository.IPTVRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.iptvstream.ui.screens.setup.SetupState> getState() {
        return null;
    }
    
    public final void onUrlChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void onNameChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void onUsernameChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String u) {
    }
    
    public final void onPasswordChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String p) {
    }
    
    public final void addPlaylist() {
    }
    
    private final java.lang.String extractDomain(java.lang.String url) {
        return null;
    }
}