package com.example.melobit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MelobitAPI {
    @GET("song/top/day/0/100")
    Call<Song> getSong();
}
