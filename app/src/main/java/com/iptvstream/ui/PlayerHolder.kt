package com.iptvstream.ui

object PlayerHolder {
    var type: String = ""
    var id: String = ""
    var url: String = ""
    var title: String = ""
    var icon: String = ""

    fun set(type: String, id: String, url: String, title: String, icon: String) {
        this.type = type
        this.id = id
        this.url = url
        this.title = title
        this.icon = icon
    }
}
