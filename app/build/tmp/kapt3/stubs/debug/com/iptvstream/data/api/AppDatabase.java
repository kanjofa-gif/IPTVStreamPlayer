package com.iptvstream.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/iptvstream/data/api/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "favoriteDao", "Lcom/iptvstream/data/api/FavoriteDao;", "playlistDao", "Lcom/iptvstream/data/api/PlaylistDao;", "watchProgressDao", "Lcom/iptvstream/data/api/WatchProgressDao;", "app_debug"})
@androidx.room.Database(entities = {com.iptvstream.data.model.Playlist.class, com.iptvstream.data.model.WatchProgress.class, com.iptvstream.data.model.Favorite.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.iptvstream.data.api.PlaylistDao playlistDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.iptvstream.data.api.WatchProgressDao watchProgressDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.iptvstream.data.api.FavoriteDao favoriteDao();
}