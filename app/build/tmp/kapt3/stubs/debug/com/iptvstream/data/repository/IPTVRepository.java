package com.iptvstream.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00ba\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u0000 h2\u00020\u0001:\u0001hB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J \u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0017J \u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0017J \u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0017J\u0016\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0017H\u0002J\u0012\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0%0$J\u0012\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130%0$J\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00170$J\f\u0010(\u001a\b\u0012\u0004\u0012\u00020)0$J,\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b,\u0010-J\u0018\u0010.\u001a\u0004\u0018\u00010\r2\u0006\u0010 \u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010!J\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0%0$2\u0006\u00100\u001a\u00020\u0017J\u001a\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u0017020$2\u0006\u00100\u001a\u00020\u0017J*\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040%0\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b5\u0010\u0015J6\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070%0\u00102\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u0017H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b9\u0010:J\u000e\u0010;\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170$J\u0018\u0010<\u001a\u0004\u0018\u00010=2\u0006\u0010 \u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010!J\u0012\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0%0$J\u0012\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0%0$J\u0010\u0010@\u001a\u0004\u0018\u00010\u0013H\u0086@\u00a2\u0006\u0002\u0010AJ6\u0010B\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0%0\u00102\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u0017H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bD\u0010:J*\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0%0\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bG\u0010\u0015J,\u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010J\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bK\u0010-J*\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020M0%0\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bN\u0010\u0015J,\u0010O\u001a\b\u0012\u0004\u0012\u00020P0\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010Q\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bR\u0010-J6\u0010S\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020T0%0\u00102\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u0017H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bU\u0010:J\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u001a0$J\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u001a0$J\u0016\u0010X\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010Y\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010!J\u0016\u0010Z\u001a\u00020\u000b2\u0006\u0010[\u001a\u00020=H\u0086@\u00a2\u0006\u0002\u0010\\J\u0016\u0010]\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010^J\u0016\u0010_\u001a\u00020\b2\u0006\u0010`\u001a\u00020)H\u0086@\u00a2\u0006\u0002\u0010aJ\u0018\u0010b\u001a\u00020\b2\b\u0010c\u001a\u0004\u0018\u00010\u0017H\u0086@\u00a2\u0006\u0002\u0010!J\u0016\u0010d\u001a\u00020\b2\u0006\u0010`\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010^J\u0016\u0010e\u001a\u00020\b2\u0006\u0010`\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010^J\u001e\u0010f\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u00172\u0006\u00108\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010gR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006i"}, d2 = {"Lcom/iptvstream/data/repository/IPTVRepository;", "", "api", "Lcom/iptvstream/data/api/XtreamApiService;", "db", "Lcom/iptvstream/data/api/AppDatabase;", "dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "(Lcom/iptvstream/data/api/XtreamApiService;Lcom/iptvstream/data/api/AppDatabase;Landroidx/datastore/core/DataStore;)V", "addFavorite", "", "favorite", "Lcom/iptvstream/data/model/Favorite;", "(Lcom/iptvstream/data/model/Favorite;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authenticate", "Lkotlin/Result;", "Lcom/iptvstream/data/model/XtreamAuth;", "playlist", "Lcom/iptvstream/data/model/Playlist;", "authenticate-gIAlu-s", "(Lcom/iptvstream/data/model/Playlist;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildApiUrl", "", "buildSeriesUrl", "streamId", "", "ext", "buildStreamUrl", "buildVodUrl", "deletePlaylist", "deleteProgress", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateAppId", "getAllFavorites", "Lkotlinx/coroutines/flow/Flow;", "", "getAllPlaylists", "getAppId", "getAutoNextEpisode", "", "getEpg", "Lcom/iptvstream/data/model/EpgResponse;", "getEpg-0E7RQCE", "(Lcom/iptvstream/data/model/Playlist;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFavorite", "getFavoritesByType", "type", "getHiddenCategories", "", "getLiveCategories", "Lcom/iptvstream/data/model/LiveCategory;", "getLiveCategories-gIAlu-s", "getLiveStreams", "Lcom/iptvstream/data/model/LiveStream;", "categoryId", "getLiveStreams-0E7RQCE", "(Lcom/iptvstream/data/model/Playlist;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getParentalPin", "getProgress", "Lcom/iptvstream/data/model/WatchProgress;", "getRecentLive", "getRecentlyWatched", "getSelectedPlaylist", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSeries", "Lcom/iptvstream/data/model/Series;", "getSeries-0E7RQCE", "getSeriesCategories", "Lcom/iptvstream/data/model/SeriesCategory;", "getSeriesCategories-gIAlu-s", "getSeriesInfo", "Lcom/iptvstream/data/model/SeriesInfo;", "seriesId", "getSeriesInfo-0E7RQCE", "getVodCategories", "Lcom/iptvstream/data/model/VodCategory;", "getVodCategories-gIAlu-s", "getVodInfo", "Lcom/iptvstream/data/model/VodInfo;", "vodId", "getVodInfo-0E7RQCE", "getVodStreams", "Lcom/iptvstream/data/model/VodStream;", "getVodStreams-0E7RQCE", "getWatchFromMin", "getWatchToMin", "insertPlaylist", "removeFavorite", "saveProgress", "progress", "(Lcom/iptvstream/data/model/WatchProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectPlaylist", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAutoNextEpisode", "value", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setParentalPin", "pin", "setWatchFromMin", "setWatchToMin", "toggleCategoryVisibility", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class IPTVRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.iptvstream.data.api.XtreamApiService api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.iptvstream.data.api.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> dataStore = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.util.Set<java.lang.String>> HIDDEN_CATEGORIES_LIVE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.util.Set<java.lang.String>> HIDDEN_CATEGORIES_MOVIES = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.util.Set<java.lang.String>> HIDDEN_CATEGORIES_SERIES = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> CATEGORY_ORDER_LIVE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> CATEGORY_ORDER_MOVIES = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> CATEGORY_ORDER_SERIES = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> AUTO_NEXT_EPISODE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> WATCH_FROM_MIN = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> WATCH_TO_MIN = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PARENTAL_PIN = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> APP_ID = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.iptvstream.data.repository.IPTVRepository.Companion Companion = null;
    
    @javax.inject.Inject()
    public IPTVRepository(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.api.XtreamApiService api, @org.jetbrains.annotations.NotNull()
    com.iptvstream.data.api.AppDatabase db, @org.jetbrains.annotations.NotNull()
    androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> dataStore) {
        super();
    }
    
    private final java.lang.String buildApiUrl(com.iptvstream.data.model.Playlist playlist) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildStreamUrl(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Playlist playlist, int streamId, @org.jetbrains.annotations.NotNull()
    java.lang.String ext) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildVodUrl(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Playlist playlist, int streamId, @org.jetbrains.annotations.NotNull()
    java.lang.String ext) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildSeriesUrl(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Playlist playlist, int streamId, @org.jetbrains.annotations.NotNull()
    java.lang.String ext) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.iptvstream.data.model.Playlist>> getAllPlaylists() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSelectedPlaylist(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.iptvstream.data.model.Playlist> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPlaylist(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Playlist playlist, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePlaylist(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Playlist playlist, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object selectPlaylist(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.iptvstream.data.model.WatchProgress>> getRecentlyWatched() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.iptvstream.data.model.WatchProgress>> getRecentLive() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveProgress(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.WatchProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.iptvstream.data.model.WatchProgress> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.iptvstream.data.model.Favorite>> getAllFavorites() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.iptvstream.data.model.Favorite>> getFavoritesByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.iptvstream.data.model.Favorite> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addFavorite(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Favorite favorite, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.Set<java.lang.String>> getHiddenCategories(@org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleCategoryVisibility(@org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getAutoNextEpisode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setAutoNextEpisode(boolean value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getWatchFromMin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getWatchToMin() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setWatchFromMin(int value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setWatchToMin(int value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getParentalPin() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setParentalPin(@org.jetbrains.annotations.Nullable()
    java.lang.String pin, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.datastore.preferences.core.Preferences> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getAppId() {
        return null;
    }
    
    private final java.lang.String generateAppId() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00120\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00120\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00120\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0007R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0007\u00a8\u0006\u001f"}, d2 = {"Lcom/iptvstream/data/repository/IPTVRepository$Companion;", "", "()V", "APP_ID", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getAPP_ID", "()Landroidx/datastore/preferences/core/Preferences$Key;", "AUTO_NEXT_EPISODE", "", "getAUTO_NEXT_EPISODE", "CATEGORY_ORDER_LIVE", "getCATEGORY_ORDER_LIVE", "CATEGORY_ORDER_MOVIES", "getCATEGORY_ORDER_MOVIES", "CATEGORY_ORDER_SERIES", "getCATEGORY_ORDER_SERIES", "HIDDEN_CATEGORIES_LIVE", "", "getHIDDEN_CATEGORIES_LIVE", "HIDDEN_CATEGORIES_MOVIES", "getHIDDEN_CATEGORIES_MOVIES", "HIDDEN_CATEGORIES_SERIES", "getHIDDEN_CATEGORIES_SERIES", "PARENTAL_PIN", "getPARENTAL_PIN", "WATCH_FROM_MIN", "", "getWATCH_FROM_MIN", "WATCH_TO_MIN", "getWATCH_TO_MIN", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.util.Set<java.lang.String>> getHIDDEN_CATEGORIES_LIVE() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.util.Set<java.lang.String>> getHIDDEN_CATEGORIES_MOVIES() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.util.Set<java.lang.String>> getHIDDEN_CATEGORIES_SERIES() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getCATEGORY_ORDER_LIVE() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getCATEGORY_ORDER_MOVIES() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getCATEGORY_ORDER_SERIES() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getAUTO_NEXT_EPISODE() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getWATCH_FROM_MIN() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getWATCH_TO_MIN() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPARENTAL_PIN() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getAPP_ID() {
            return null;
        }
    }
}