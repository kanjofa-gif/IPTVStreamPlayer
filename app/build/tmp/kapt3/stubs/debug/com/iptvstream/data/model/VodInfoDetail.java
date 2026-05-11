package com.iptvstream.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b:\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u008b\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u001fJ\u000b\u0010=\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\t\u0010?\u001a\u00020\u0003H\u00c6\u0003J\t\u0010@\u001a\u00020\u0003H\u00c6\u0003J\t\u0010A\u001a\u00020\u0003H\u00c6\u0003J\t\u0010B\u001a\u00020\u0003H\u00c6\u0003J\t\u0010C\u001a\u00020\u0003H\u00c6\u0003J\t\u0010D\u001a\u00020\u0003H\u00c6\u0003J\t\u0010E\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010F\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015H\u00c6\u0003J\t\u0010G\u001a\u00020\u0017H\u00c6\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u001aH\u00c6\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u001cH\u00c6\u0003J\t\u0010L\u001a\u00020\u0017H\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\t\u0010O\u001a\u00020\u0003H\u00c6\u0003J\t\u0010P\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u008f\u0002\u0010U\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010Y\u001a\u00020\u0017H\u00d6\u0001J\t\u0010Z\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0019\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u001d\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010!R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010!R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010!R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010!R\u0011\u0010\u0018\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010!R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010(R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010!R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010!R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010!R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010!R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010!R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010!R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010!R\u0011\u0010\u001e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010!R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010!R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010!R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010!\u00a8\u0006["}, d2 = {"Lcom/iptvstream/data/model/VodInfoDetail;", "", "kinopoisk_url", "", "tmdb_id", "name", "o_name", "cover_big", "movie_image", "releasedate", "episode_run_time", "youtube_trailer", "director", "actors", "cast", "description", "plot", "age", "country", "genre", "backdrop_path", "", "duration_secs", "", "duration", "video", "Lcom/iptvstream/data/model/VideoInfo;", "audio", "Lcom/iptvstream/data/model/AudioInfo;", "bitrate", "rating", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILjava/lang/String;Lcom/iptvstream/data/model/VideoInfo;Lcom/iptvstream/data/model/AudioInfo;ILjava/lang/String;)V", "getActors", "()Ljava/lang/String;", "getAge", "getAudio", "()Lcom/iptvstream/data/model/AudioInfo;", "getBackdrop_path", "()Ljava/util/List;", "getBitrate", "()I", "getCast", "getCountry", "getCover_big", "getDescription", "getDirector", "getDuration", "getDuration_secs", "getEpisode_run_time", "getGenre", "getKinopoisk_url", "getMovie_image", "getName", "getO_name", "getPlot", "getRating", "getReleasedate", "getTmdb_id", "getVideo", "()Lcom/iptvstream/data/model/VideoInfo;", "getYoutube_trailer", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class VodInfoDetail {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String kinopoisk_url = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String tmdb_id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String o_name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String cover_big = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String movie_image = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String releasedate = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String episode_run_time = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String youtube_trailer = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String director = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String actors = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String cast = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String plot = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String age = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String country = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String genre = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<java.lang.String> backdrop_path = null;
    private final int duration_secs = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String duration = null;
    @org.jetbrains.annotations.Nullable()
    private final com.iptvstream.data.model.VideoInfo video = null;
    @org.jetbrains.annotations.Nullable()
    private final com.iptvstream.data.model.AudioInfo audio = null;
    private final int bitrate = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String rating = null;
    
    public VodInfoDetail(@org.jetbrains.annotations.Nullable()
    java.lang.String kinopoisk_url, @org.jetbrains.annotations.Nullable()
    java.lang.String tmdb_id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String o_name, @org.jetbrains.annotations.NotNull()
    java.lang.String cover_big, @org.jetbrains.annotations.NotNull()
    java.lang.String movie_image, @org.jetbrains.annotations.Nullable()
    java.lang.String releasedate, @org.jetbrains.annotations.Nullable()
    java.lang.String episode_run_time, @org.jetbrains.annotations.Nullable()
    java.lang.String youtube_trailer, @org.jetbrains.annotations.NotNull()
    java.lang.String director, @org.jetbrains.annotations.NotNull()
    java.lang.String actors, @org.jetbrains.annotations.NotNull()
    java.lang.String cast, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String plot, @org.jetbrains.annotations.NotNull()
    java.lang.String age, @org.jetbrains.annotations.NotNull()
    java.lang.String country, @org.jetbrains.annotations.NotNull()
    java.lang.String genre, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> backdrop_path, int duration_secs, @org.jetbrains.annotations.NotNull()
    java.lang.String duration, @org.jetbrains.annotations.Nullable()
    com.iptvstream.data.model.VideoInfo video, @org.jetbrains.annotations.Nullable()
    com.iptvstream.data.model.AudioInfo audio, int bitrate, @org.jetbrains.annotations.NotNull()
    java.lang.String rating) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getKinopoisk_url() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTmdb_id() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getO_name() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCover_big() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMovie_image() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getReleasedate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEpisode_run_time() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getYoutube_trailer() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDirector() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getActors() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCast() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlot() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCountry() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGenre() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getBackdrop_path() {
        return null;
    }
    
    public final int getDuration_secs() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDuration() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.iptvstream.data.model.VideoInfo getVideo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.iptvstream.data.model.AudioInfo getAudio() {
        return null;
    }
    
    public final int getBitrate() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRating() {
        return null;
    }
    
    public VodInfoDetail() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component18() {
        return null;
    }
    
    public final int component19() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.iptvstream.data.model.VideoInfo component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.iptvstream.data.model.AudioInfo component22() {
        return null;
    }
    
    public final int component23() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iptvstream.data.model.VodInfoDetail copy(@org.jetbrains.annotations.Nullable()
    java.lang.String kinopoisk_url, @org.jetbrains.annotations.Nullable()
    java.lang.String tmdb_id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String o_name, @org.jetbrains.annotations.NotNull()
    java.lang.String cover_big, @org.jetbrains.annotations.NotNull()
    java.lang.String movie_image, @org.jetbrains.annotations.Nullable()
    java.lang.String releasedate, @org.jetbrains.annotations.Nullable()
    java.lang.String episode_run_time, @org.jetbrains.annotations.Nullable()
    java.lang.String youtube_trailer, @org.jetbrains.annotations.NotNull()
    java.lang.String director, @org.jetbrains.annotations.NotNull()
    java.lang.String actors, @org.jetbrains.annotations.NotNull()
    java.lang.String cast, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String plot, @org.jetbrains.annotations.NotNull()
    java.lang.String age, @org.jetbrains.annotations.NotNull()
    java.lang.String country, @org.jetbrains.annotations.NotNull()
    java.lang.String genre, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> backdrop_path, int duration_secs, @org.jetbrains.annotations.NotNull()
    java.lang.String duration, @org.jetbrains.annotations.Nullable()
    com.iptvstream.data.model.VideoInfo video, @org.jetbrains.annotations.Nullable()
    com.iptvstream.data.model.AudioInfo audio, int bitrate, @org.jetbrains.annotations.NotNull()
    java.lang.String rating) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}