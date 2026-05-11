package com.iptvstream.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\nH\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\nH\'J\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/iptvstream/data/api/WatchProgressDao;", "", "deleteProgress", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProgress", "Lcom/iptvstream/data/model/WatchProgress;", "getRecentLive", "Lkotlinx/coroutines/flow/Flow;", "", "getRecentlyWatched", "saveProgress", "progress", "(Lcom/iptvstream/data/model/WatchProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface WatchProgressDao {
    
    @androidx.room.Query(value = "SELECT * FROM watch_progress ORDER BY watchedAt DESC LIMIT 20")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.iptvstream.data.model.WatchProgress>> getRecentlyWatched();
    
    @androidx.room.Query(value = "SELECT * FROM watch_progress WHERE streamId = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.iptvstream.data.model.WatchProgress> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveProgress(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.WatchProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM watch_progress WHERE streamId = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM watch_progress WHERE streamType = \'live\' ORDER BY watchedAt DESC LIMIT 10")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.iptvstream.data.model.WatchProgress>> getRecentLive();
}