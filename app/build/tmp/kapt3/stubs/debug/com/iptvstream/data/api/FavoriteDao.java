package com.iptvstream.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u000f\u001a\u00020\fH\'J\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u0011"}, d2 = {"Lcom/iptvstream/data/api/FavoriteDao;", "", "addFavorite", "", "favorite", "Lcom/iptvstream/data/model/Favorite;", "(Lcom/iptvstream/data/model/Favorite;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFavorites", "Lkotlinx/coroutines/flow/Flow;", "", "getFavorite", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFavoritesByType", "type", "removeFavorite", "app_debug"})
@androidx.room.Dao()
public abstract interface FavoriteDao {
    
    @androidx.room.Query(value = "SELECT * FROM favorites ORDER BY addedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.iptvstream.data.model.Favorite>> getAllFavorites();
    
    @androidx.room.Query(value = "SELECT * FROM favorites WHERE streamType = :type ORDER BY addedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.iptvstream.data.model.Favorite>> getFavoritesByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type);
    
    @androidx.room.Query(value = "SELECT * FROM favorites WHERE streamId = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.iptvstream.data.model.Favorite> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addFavorite(@org.jetbrains.annotations.NotNull()
    com.iptvstream.data.model.Favorite favorite, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM favorites WHERE streamId = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}