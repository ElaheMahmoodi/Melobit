package com.example.melobit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MelobitAPI {
    @GET("song/new/0/11")
    Call<Song> getDayTrendSong();
    @GET("artist/trending/0/4")
    Call<Artist> getBestArtists();
    @GET("song/top/day/0/100")
    Call<Song> getBestWeekSong();
    @GET("song/top/day/0/100")
    Call<Song> getBestDaySong();
}
