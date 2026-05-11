package com.iptvstream.data.api

import androidx.room.*
import com.iptvstream.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlists ORDER BY addedAt DESC")
    fun getAllPlaylists(): Flow<List<Playlist>>

    @Query("SELECT * FROM playlists WHERE isSelected = 1 LIMIT 1")
    suspend fun getSelectedPlaylist(): Playlist?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: Playlist)

    @Update
    suspend fun updatePlaylist(playlist: Playlist)

    @Delete
    suspend fun deletePlaylist(playlist: Playlist)

    @Query("UPDATE playlists SET isSelected = 0")
    suspend fun clearAllSelections()

    @Query("UPDATE playlists SET isSelected = 1 WHERE id = :id")
    suspend fun selectPlaylist(id: Int)
}

@Dao
interface WatchProgressDao {
    @Query("SELECT * FROM watch_progress ORDER BY watchedAt DESC LIMIT 20")
    fun getRecentlyWatched(): Flow<List<WatchProgress>>

    @Query("SELECT * FROM watch_progress WHERE streamId = :id LIMIT 1")
    suspend fun getProgress(id: String): WatchProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: WatchProgress)

    @Query("DELETE FROM watch_progress WHERE streamId = :id")
    suspend fun deleteProgress(id: String)

    @Query("SELECT * FROM watch_progress WHERE streamType = 'live' ORDER BY watchedAt DESC LIMIT 10")
    fun getRecentLive(): Flow<List<WatchProgress>>
}

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY addedAt DESC")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE streamType = :type ORDER BY addedAt DESC")
    fun getFavoritesByType(type: String): Flow<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE streamId = :id LIMIT 1")
    suspend fun getFavorite(id: String): Favorite?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE streamId = :id")
    suspend fun removeFavorite(id: String)
}

@Database(
    entities = [Playlist::class, WatchProgress::class, Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    abstract fun watchProgressDao(): WatchProgressDao
    abstract fun favoriteDao(): FavoriteDao
}
