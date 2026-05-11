package com.iptvstream.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001aX\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0003H\u0007\u001aI\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00190\u00182\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00010\u0018\u00a2\u0006\u0002\b\u001bH\u0007\u001a\b\u0010\u001c\u001a\u00020\u0001H\u0007\u001ab\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010!\u001a#\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00032\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007\u00a2\u0006\u0002\b\u001bH\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006%"}, d2 = {"CategoryListItem", "", "name", "", "isSelected", "", "onClick", "Lkotlin/Function0;", "ChannelCard", "icon", "modifier", "Landroidx/compose/ui/Modifier;", "width", "Landroidx/compose/ui/unit/Dp;", "height", "ChannelCard-gv0btCI", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;FF)V", "EmptyState", "message", "HorizontalScrollRow", "T", "items", "", "key", "Lkotlin/Function1;", "", "itemContent", "Landroidx/compose/runtime/Composable;", "LoadingState", "MovieCard", "rating", "year", "MovieCard-AWlRVLg", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;FF)V", "SectionRow", "title", "content", "app_debug"})
public final class MediaCardsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SectionRow(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> content) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final <T extends java.lang.Object>void HorizontalScrollRow(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> items, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, ? extends java.lang.Object> key, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> itemContent) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CategoryListItem(@org.jetbrains.annotations.NotNull()
    java.lang.String name, boolean isSelected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EmptyState(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LoadingState() {
    }
}