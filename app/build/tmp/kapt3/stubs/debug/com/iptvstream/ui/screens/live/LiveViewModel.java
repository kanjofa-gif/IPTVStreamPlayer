package com.iptvstream.ui.screens.live;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0014"}, d2 = {"Lcom/iptvstream/ui/screens/live/LiveViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/iptvstream/data/repository/IPTVRepository;", "(Lcom/iptvstream/data/repository/IPTVRepository;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/iptvstream/ui/screens/live/LiveState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "filterStreams", "", "loadData", "onSearch", "query", "", "selectCategory", "categoryId", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LiveViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.iptvstream.data.repository.IPTVRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.iptvstream.ui.screens.live.LiveState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.iptvstream.ui.screens.live.LiveState> state = null;
    
    @javax.inject.Inject()
    public LiveViewModel(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.repository.IPTVRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.iptvstream.ui.screens.live.LiveState> getState() {
        return null;
    }
    
    private final void loadData() {
    }
    
    public final void selectCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String categoryId) {
    }
    
    public final void onSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    private final void filterStreams() {
    }
}