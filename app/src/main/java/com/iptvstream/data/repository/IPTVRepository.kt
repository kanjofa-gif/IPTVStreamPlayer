package com.iptvstream.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.iptvstream.data.api.*
import com.iptvstream.data.model.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IPTVRepository @Inject constructor(
    private val api: XtreamApiService,
    private val db: AppDatabase,
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val HIDDEN_CATEGORIES_LIVE = stringSetPreferencesKey("hidden_cats_live")
        val HIDDEN_CATEGORIES_MOVIES = stringSetPreferencesKey("hidden_cats_movies")
        val HIDDEN_CATEGORIES_SERIES = stringSetPreferencesKey("hidden_cats_series")
        val CATEGORY_ORDER_LIVE = stringPreferencesKey("cat_order_live")
        val CATEGORY_ORDER_MOVIES = stringPreferencesKey("cat_order_movies")
        val CATEGORY_ORDER_SERIES = stringPreferencesKey("cat_order_series")
        val AUTO_NEXT_EPISODE = booleanPreferencesKey("auto_next_episode")
        val WATCH_FROM_MIN = intPreferencesKey("watch_from_min")
        val WATCH_TO_MIN = intPreferencesKey("watch_to_min")
        val PARENTAL_PIN = stringPreferencesKey("parental_pin")
        val APP_ID = stringPreferencesKey("app_id")
    }

    private fun buildApiUrl(playlist: Playlist): String {
        val base = playlist.url.trimEnd('/')
        return "$base/player_api.php"
    }

    fun buildStreamUrl(playlist: Playlist, streamId: Int, ext: String = "ts"): String {
        val base = playlist.url.trimEnd('/')
        return "$base/live/${playlist.username}/${playlist.password}/$streamId.$ext"
    }

    fun buildVodUrl(playlist: Playlist, streamId: Int, ext: String = "mkv"): String {
        val base = playlist.url.trimEnd('/')
        return "$base/movie/${playlist.username}/${playlist.password}/$streamId.$ext"
    }

    fun buildSeriesUrl(playlist: Playlist, streamId: Int, ext: String = "mkv"): String {
        val base = playlist.url.trimEnd('/')
        return "$base/series/${playlist.username}/${playlist.password}/$streamId.$ext"
    }

    suspend fun authenticate(playlist: Playlist): Result<XtreamAuth> = runCatching {
        api.authenticate(buildApiUrl(playlist), playlist.username, playlist.password)
    }

    suspend fun getLiveCategories(playlist: Playlist): Result<List<LiveCategory>> = runCatching {
        api.getLiveCategories(buildApiUrl(playlist), playlist.username, playlist.password)
    }

    suspend fun getLiveStreams(playlist: Playlist, categoryId: String? = null): Result<List<LiveStream>> = runCatching {
        api.getLiveStreams(buildApiUrl(playlist), playlist.username, playlist.password, categoryId = categoryId)
    }

    suspend fun getVodCategories(playlist: Playlist): Result<List<VodCategory>> = runCatching {
        api.getVodCategories(buildApiUrl(playlist), playlist.username, playlist.password)
    }

    suspend fun getVodStreams(playlist: Playlist, categoryId: String? = null): Result<List<VodStream>> = runCatching {
        api.getVodStreams(buildApiUrl(playlist), playlist.username, playlist.password, categoryId = categoryId)
    }

    suspend fun getVodInfo(playlist: Playlist, vodId: Int): Result<VodInfo> = runCatching {
        api.getVodInfo(buildApiUrl(playlist), playlist.username, playlist.password, vodId = vodId)
    }

    suspend fun getSeriesCategories(playlist: Playlist): Result<List<SeriesCategory>> = runCatching {
        api.getSeriesCategories(buildApiUrl(playlist), playlist.username, playlist.password)
    }

    suspend fun getSeries(playlist: Playlist, categoryId: String? = null): Result<List<Series>> = runCatching {
        api.getSeries(buildApiUrl(playlist), playlist.username, playlist.password, categoryId = categoryId)
    }

    suspend fun getSeriesInfo(playlist: Playlist, seriesId: Int): Result<SeriesInfo> = runCatching {
        api.getSeriesInfo(buildApiUrl(playlist), playlist.username, playlist.password, seriesId = seriesId)
    }

    suspend fun getEpg(playlist: Playlist, streamId: Int): Result<EpgResponse> = runCatching {
        api.getShortEpg(buildApiUrl(playlist), playlist.username, playlist.password, streamId = streamId)
    }

    fun getAllPlaylists() = db.playlistDao().getAllPlaylists()
    suspend fun getSelectedPlaylist() = db.playlistDao().getSelectedPlaylist()
    suspend fun insertPlaylist(playlist: Playlist) = db.playlistDao().insertPlaylist(playlist)
    suspend fun deletePlaylist(playlist: Playlist) = db.playlistDao().deletePlaylist(playlist)
    suspend fun selectPlaylist(id: Int) {
        db.playlistDao().clearAllSelections()
        db.playlistDao().selectPlaylist(id)
    }

    fun getRecentlyWatched() = db.watchProgressDao().getRecentlyWatched()
    fun getRecentLive() = db.watchProgressDao().getRecentLive()
    suspend fun saveProgress(progress: WatchProgress) = db.watchProgressDao().saveProgress(progress)
    suspend fun getProgress(id: String) = db.watchProgressDao().getProgress(id)
    suspend fun deleteProgress(id: String) = db.watchProgressDao().deleteProgress(id)

    fun getAllFavorites() = db.favoriteDao().getAllFavorites()
    fun getFavoritesByType(type: String) = db.favoriteDao().getFavoritesByType(type)
    suspend fun getFavorite(id: String) = db.favoriteDao().getFavorite(id)
    suspend fun addFavorite(favorite: Favorite) = db.favoriteDao().addFavorite(favorite)
    suspend fun removeFavorite(id: String) = db.favoriteDao().removeFavorite(id)

    fun getHiddenCategories(type: String): Flow<Set<String>> = dataStore.data.map {
        when (type) {
            "live" -> it[HIDDEN_CATEGORIES_LIVE] ?: emptySet()
            "movies" -> it[HIDDEN_CATEGORIES_MOVIES] ?: emptySet()
            else -> it[HIDDEN_CATEGORIES_SERIES] ?: emptySet()
        }
    }

    suspend fun toggleCategoryVisibility(type: String, categoryId: String) {
        val key = when (type) {
            "live" -> HIDDEN_CATEGORIES_LIVE
            "movies" -> HIDDEN_CATEGORIES_MOVIES
            else -> HIDDEN_CATEGORIES_SERIES
        }
        dataStore.edit { prefs ->
            val current = prefs[key] ?: emptySet()
            prefs[key] = if (categoryId in current) current - categoryId else current + categoryId
        }
    }

    fun getAutoNextEpisode(): Flow<Boolean> = dataStore.data.map { it[AUTO_NEXT_EPISODE] ?: true }
    suspend fun setAutoNextEpisode(value: Boolean) = dataStore.edit { it[AUTO_NEXT_EPISODE] = value }

    fun getWatchFromMin(): Flow<Int> = dataStore.data.map { it[WATCH_FROM_MIN] ?: 0 }
    fun getWatchToMin(): Flow<Int> = dataStore.data.map { it[WATCH_TO_MIN] ?: 5 }
    suspend fun setWatchFromMin(value: Int) = dataStore.edit { it[WATCH_FROM_MIN] = value }
    suspend fun setWatchToMin(value: Int) = dataStore.edit { it[WATCH_TO_MIN] = value }

    fun getParentalPin(): Flow<String?> = dataStore.data.map { it[PARENTAL_PIN] }
    suspend fun setParentalPin(pin: String?) = dataStore.edit {
        if (pin == null) it.remove(PARENTAL_PIN) else it[PARENTAL_PIN] = pin
    }

    fun getAppId(): Flow<String> = dataStore.data.map {
        it[APP_ID] ?: generateAppId().also { id -> dataStore.edit { p -> p[APP_ID] = id } }
    }

    private fun generateAppId(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..32).map { chars.random() }.joinToString("")
    }
}
