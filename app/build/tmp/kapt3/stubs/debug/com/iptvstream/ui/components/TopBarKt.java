package com.iptvstream.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001aJ\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007\u001a\u00b0\u0001\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00072\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001a,\u0010#\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020&H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\'\u0010(\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006)"}, d2 = {"DrawerMenuItem", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "isSelected", "", "onClick", "Lkotlin/Function0;", "IPTVTopBar", "currentTab", "Lcom/iptvstream/ui/components/NavTab;", "onTabSelected", "Lkotlin/Function1;", "onSettingsClick", "onContinueWatchingClick", "modifier", "Landroidx/compose/ui/Modifier;", "SettingsDrawer", "isVisible", "userInfo", "Lcom/iptvstream/data/model/UserInfo;", "expDate", "maxConnections", "isTrial", "onDismiss", "onAccount", "onRefresh", "onManagePlaylists", "onManageCategories", "onSettings", "onWhatsNew", "onMobileApp", "onNotes", "SubscriptionRow", "value", "valueColor", "Landroidx/compose/ui/graphics/Color;", "SubscriptionRow-mxwnekA", "(Ljava/lang/String;Ljava/lang/String;J)V", "app_debug"})
public final class TopBarKt {
    
    @androidx.compose.runtime.Composable()
    public static final void IPTVTopBar(@org.jetbrains.annotations.NotNull()
    com.iptvstream.ui.components.NavTab currentTab, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.iptvstream.ui.components.NavTab, kotlin.Unit> onTabSelected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSettingsClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onContinueWatchingClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SettingsDrawer(boolean isVisible, @org.jetbrains.annotations.Nullable()
    com.iptvstream.data.model.UserInfo userInfo, @org.jetbrains.annotations.NotNull()
    java.lang.String expDate, @org.jetbrains.annotations.NotNull()
    java.lang.String maxConnections, boolean isTrial, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAccount, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRefresh, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onManagePlaylists, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onManageCategories, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSettings, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onWhatsNew, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onMobileApp, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNotes) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DrawerMenuItem(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.vector.ImageVector icon, @org.jetbrains.annotations.NotNull()
    java.lang.String label, boolean isSelected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}