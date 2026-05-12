package com.iptvstream.ui

data class PlayItem(
    val type: String,
    val id: String,
    val url: String,
    val title: String,
    val icon: String
)

object PlayerHolder {
    var playlist: List<PlayItem> = emptyList()
    var currentIndex: Int = 0

    val current: PlayItem? get() = playlist.getOrNull(currentIndex)

    fun setSingle(type: String, id: String, url: String, title: String, icon: String) {
        playlist = listOf(PlayItem(type, id, url, title, icon))
        currentIndex = 0
    }

    fun setPlaylist(items: List<PlayItem>, startIndex: Int) {
        playlist = items
        currentIndex = startIndex.coerceIn(0, items.size - 1)
    }

    fun next(): PlayItem? {
        if (playlist.isEmpty()) return null
        currentIndex = (currentIndex + 1).coerceAtMost(playlist.size - 1)
        return current
    }

    fun previous(): PlayItem? {
        if (playlist.isEmpty()) return null
        currentIndex = (currentIndex - 1).coerceAtLeast(0)
        return current
    }

    fun hasNext() = currentIndex < playlist.size - 1
    fun hasPrevious() = currentIndex > 0
}
