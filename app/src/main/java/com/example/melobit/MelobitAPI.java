package com.example.melobit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MelobitAPI {
    @GET("song/new/0/11")
    Call<Song> getDayTrendSong();
    @GET("artist/trending/0/4")
    Call<Artist> getBestArtists();
    @GET("song/top/day/0/100")
    Call<Song> getBestWeekSong();
    @GET("song/top/week/0/100")
    Call<Song> getBestDaySong();

    @GET("search/query/{name}/0/50")
    Call<SearchResult> searchSongs(@Path("name") String name);

}
