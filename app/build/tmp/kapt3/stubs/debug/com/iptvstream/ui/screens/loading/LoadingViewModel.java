package com.iptvstream.ui.screens.loading;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lcom/iptvstream/ui/screens/loading/LoadingViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/iptvstream/data/repository/IPTVRepository;", "(Lcom/iptvstream/data/repository/IPTVRepository;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/iptvstream/ui/screens/loading/LoadingState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadData", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LoadingViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.iptvstream.data.repository.IPTVRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.iptvstream.ui.screens.loading.LoadingState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.iptvstream.ui.screens.loading.LoadingState> state = null;
    
    @javax.inject.Inject()
    public LoadingViewModel(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.repository.IPTVRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.iptvstream.ui.screens.loading.LoadingState> getState() {
        return null;
    }
    
    public final void loadData() {
    }
}