package com.iptvstream.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// ─── Playlist / Credentials ──────────────────────────────────────────────────

@Entity(tableName = "playlists")
data class Playlist(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val url: String,
    val username: String = "",
    val password: String = "",
    val isSelected: Boolean = false,
    val addedAt: Long = System.currentTimeMillis()
)

// ─── Xtream API response models ───────────────────────────────────────────────

data class UserInfo(
    val username: String = "",
    val password: String = "",
    val status: String = "",
    val exp_date: String? = null,
    val max_connections: String = "0",
    val is_trial: String = "0",
    val active_cons: String = "0"
)

data class ServerInfo(
    val url: String = "",
    val port: String = "",
    val https_port: String = "",
    val server_protocol: String = "http",
    val rtmp_port: String = "",
    val timezone: String = "",
    val timestamp_now: Long = 0,
    val time_now: String = ""
)

data class XtreamAuth(
    val user_info: UserInfo,
    val server_info: ServerInfo
)

// ─── Live TV ──────────────────────────────────────────────────────────────────

data class LiveCategory(
    val category_id: String,
    val category_name: String,
    val parent_id: Int = 0
)

data class LiveStream(
    val num: Int = 0,
    val name: String = "",
    val stream_type: String = "live",
    val stream_id: Int = 0,
    val stream_icon: String = "",
    val epg_channel_id: String? = null,
    val added: String = "",
    val category_id: String = "",
    val custom_sid: String? = null,
    val tv_archive: Int = 0,
    val direct_source: String = "",
    val tv_archive_duration: Int = 0
)

// ─── VOD (Movies) ─────────────────────────────────────────────────────────────

data class VodCategory(
    val category_id: String,
    val category_name: String,
    val parent_id: Int = 0
)

data class VodStream(
    val num: Int = 0,
    val name: String = "",
    val stream_type: String = "movie",
    val stream_id: Int = 0,
    val stream_icon: String = "",
    val rating: String = "0",
    val rating_5based: Double = 0.0,
    val added: String = "",
    val category_id: String = "",
    val container_extension: String = "mkv",
    val custom_sid: String? = null,
    val direct_source: String = "",
    val plot: String? = null,
    val cast: String? = null,
    val director: String? = null,
    val genre: String? = null,
    val releaseDate: String? = null,
    val duration: String? = null,
    val youtube_trailer: String? = null
)

data class VodInfo(
    val info: VodInfoDetail? = null,
    val movie_data: VodStream? = null
)

data class VodInfoDetail(
    val kinopoisk_url: String? = null,
    val tmdb_id: String? = null,
    val name: String = "",
    val o_name: String = "",
    val cover_big: String = "",
    val movie_image: String = "",
    val releasedate: String? = null,
    val episode_run_time: String? = null,
    val youtube_trailer: String? = null,
    val director: String = "",
    val actors: String = "",
    val cast: String = "",
    val description: String = "",
    val plot: String = "",
    val age: String = "",
    val country: String = "",
    val genre: String = "",
    val backdrop_path: List<String>? = null,
    val duration_secs: Int = 0,
    val duration: String = "",
    val video: VideoInfo? = null,
    val audio: AudioInfo? = null,
    val bitrate: Int = 0,
    val rating: String = "0"
)

data class VideoInfo(val codec_name: String = "", val width: Int = 0, val height: Int = 0)
data class AudioInfo(val codec_name: String = "", val sample_rate: String = "")

// ─── Series ───────────────────────────────────────────────────────────────────

data class SeriesCategory(
    val category_id: String,
    val category_name: String,
    val parent_id: Int = 0
)

data class Series(
    val num: Int = 0,
    val name: String = "",
    val series_id: Int = 0,
    val cover: String = "",
    val plot: String = "",
    val cast: String = "",
    val director: String = "",
    val genre: String = "",
    val releaseDate: String? = null,
    val last_modified: String? = null,
    val rating: String = "0",
    val rating_5based: Double = 0.0,
    val backdrop_path: List<String>? = null,
    val youtube_trailer: String? = null,
    val episode_run_time: String? = null,
    val category_id: String = "",
    val category_name: String? = null
)

data class SeriesInfo(
    val info: SeriesInfoDetail? = null,
    val episodes: Map<String, List<Episode>>? = null,
    val seasons: List<Season>? = null
)

data class SeriesInfoDetail(
    val name: String = "",
    val cover: String = "",
    val plot: String = "",
    val cast: String = "",
    val director: String = "",
    val genre: String = "",
    val releaseDate: String? = null,
    val last_modified: String? = null,
    val rating: String = "",
    val rating_5based: Double = 0.0,
    val backdrop_path: List<String>? = null,
    val youtube_trailer: String? = null,
    val episode_run_time: String? = null,
    val category_id: String? = null
)

data class Season(
    val air_date: String? = null,
    val episode_count: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val season_number: Int = 0,
    val vote_average: Double = 0.0,
    val cover: String? = null,
    val cover_big: String? = null
)

data class Episode(
    val id: String = "",
    val episode_num: Int = 0,
    val title: String = "",
    val container_extension: String = "mkv",
    val info: EpisodeInfo? = null,
    val custom_sid: String? = null,
    val added: String = "",
    val season: Int = 0,
    val direct_source: String = ""
)

data class EpisodeInfo(
    val tmdb_id: Int? = null,
    val releasedate: String? = null,
    val plot: String? = null,
    val duration_secs: Int = 0,
    val duration: String = "",
    val bitrate: Int = 0,
    val rating: Double = 0.0,
    val movie_image: String = ""
)

// ─── EPG ─────────────────────────────────────────────────────────────────────

data class EpgListing(
    val id: String = "",
    val epg_id: String = "",
    val title: String = "",
    val lang: String = "",
    val start: String = "",
    val end: String = "",
    val description: String = "",
    val channel_id: String = "",
    val start_timestamp: Long = 0,
    val stop_timestamp: Long = 0,
    val now_playing: Int = 0,
    val has_archive: Int = 0
)

data class EpgResponse(
    val epg_listings: List<EpgListing> = emptyList()
)

// ─── Watch Progress ───────────────────────────────────────────────────────────

@Entity(tableName = "watch_progress")
data class WatchProgress(
    @PrimaryKey val streamId: String,
    val streamName: String,
    val streamIcon: String,
    val streamUrl: String,
    val streamType: String,
    val positionMs: Long = 0,
    val durationMs: Long = 0,
    val watchedAt: Long = System.currentTimeMillis()
)

// ─── Favorites ────────────────────────────────────────────────────────────────

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val streamId: String,
    val streamName: String,
    val streamIcon: String,
    val streamType: String,
    val categoryId: String = "",
    val addedAt: Long = System.currentTimeMillis()
)
