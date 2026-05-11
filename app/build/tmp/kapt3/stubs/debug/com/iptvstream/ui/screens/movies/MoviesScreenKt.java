package com.iptvstream.ui.screens.movies;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001as\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u00a2\u0006\u0002\u0010\u0010\u001a\u009e\u0001\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00132`\u0010\u0014\u001a\\\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00010\u00152\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0006\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u001a\u0010\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u0005H\u0002\u00a8\u0006\""}, d2 = {"MovieDetailDialog", "", "movie", "Lcom/iptvstream/data/model/VodStream;", "watchProgress", "", "isFavorite", "", "onDismiss", "Lkotlin/Function0;", "onPlay", "Lkotlin/Function1;", "", "onPlayNew", "onToggleFavorite", "onRemoveProgress", "(Lcom/iptvstream/data/model/VodStream;Ljava/lang/Long;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "MoviesScreen", "viewModel", "Lcom/iptvstream/ui/screens/movies/MoviesViewModel;", "onPlayMovie", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "url", "id", "title", "icon", "onSettingsClick", "currentTab", "Lcom/iptvstream/ui/components/NavTab;", "onTabSelected", "formatDuration", "ms", "app_debug"})
public final class MoviesScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void MoviesScreen(@org.jetbrains.annotations.NotNull()
    com.iptvstream.ui.screens.movies.MoviesViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function4<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onPlayMovie, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSettingsClick, @org.jetbrains.annotations.NotNull()
    com.iptvstream.ui.components.NavTab currentTab, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.iptvstream.ui.components.NavTab, kotlin.Unit> onTabSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MovieDetailDialog(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.VodStream movie, @org.jetbrains.annotations.Nullable()
    java.lang.Long watchProgress, boolean isFavorite, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onPlay, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPlayNew, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onToggleFavorite, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRemoveProgress) {
    }
    
    private static final java.lang.String formatDuration(long ms) {
        return null;
    }
}