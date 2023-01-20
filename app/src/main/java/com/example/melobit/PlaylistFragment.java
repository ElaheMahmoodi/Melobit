package com.example.melobit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaylistFragment extends Fragment {
    String clickType;
    Retrofit retrofit;
    MelobitAPI melobitAPI;
    RecyclerView recyclerView;
    Call<Song> daytrendsCall ;
    Call<Artist> artistCall ;
    Call<Song> bestSongDayCall ;
    Call<Song> bestSongWeekCall ;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.songRecyclerView);

        Bundle bundle = this.getArguments();
        clickType = bundle.getString("info");


        retrofit = new Retrofit.Builder()
                .baseUrl("https://api-beta.melobit.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         melobitAPI = retrofit.create(MelobitAPI.class);

         daytrendsCall =  melobitAPI.getDayTrendSong();
         artistCall = melobitAPI.getBestArtists();
         bestSongDayCall = melobitAPI.getBestDaySong();
         bestSongWeekCall = melobitAPI.getBestWeekSong();
        categoryHandler(clickType);
    }

    public void categoryHandler(String info){
        if(info.equals("dayTrends") ){
            daytrendsCall.enqueue(new Callback<Song>() {
                @Override
                public void onResponse(Call<Song> call, Response<Song> response) {
                    SongAdapter songAdapter = new SongAdapter(response.body());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(songAdapter);
                }

                @Override
                public void onFailure(Call<Song> call, Throwable t) {

                }
            });
        }

        if(info.equals("artistTrend") ){
            artistCall.enqueue(new Callback<Artist>() {
                @Override
                public void onResponse(Call<Artist> call, Response<Artist> response) {
                    ArtistAdapter artistAdapter = new ArtistAdapter(response.body());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(artistAdapter);
                }

                @Override
                public void onFailure(Call<Artist> call, Throwable t) {

                }
            });
        }

        if(info.equals("bestSongDay") ){
            bestSongDayCall.enqueue(new Callback<Song>() {
                @Override
                public void onResponse(Call<Song> call, Response<Song> response) {
                    SongAdapter songAdapter = new SongAdapter(response.body());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(songAdapter);
                }

                @Override
                public void onFailure(Call<Song> call, Throwable t) {

                }
            });
        }

        if (info.equals("bestSongWeek") ){
            bestSongWeekCall.enqueue(new Callback<Song>() {
                @Override
                public void onResponse(Call<Song> call, Response<Song> response) {
                    SongAdapter songAdapter = new SongAdapter(response.body());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(songAdapter);
                }

                @Override
                public void onFailure(Call<Song> call, Throwable t) {

                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist,container,false);
    }
}
