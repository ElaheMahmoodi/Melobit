package com.example.melobit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    ImageView dayTrendMusics;
    ImageView artistTrend ;
    ImageView bestSongDay ;
    ImageView bestSongWeek ;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         dayTrendMusics = (ImageView) getView().findViewById(R.id.dayTrend);
         artistTrend = (ImageView) getView().findViewById(R.id.artistTrend);
         bestSongDay = (ImageView) getView().findViewById(R.id.bestSongDay);
         bestSongWeek = (ImageView) getView().findViewById(R.id.bestSongWeek);

         dayTrendMusics.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                Fragment playListFragment = new PlaylistFragment();
                Bundle bundle  = new Bundle();
                bundle.putString("info","dayTrends");
                playListFragment.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        playListFragment).commit();
             }
         });

         artistTrend.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Fragment playListFragment = new PlaylistFragment();
                 Bundle bundle  = new Bundle();
                 bundle.putString("info","artistTrend");
                 playListFragment.setArguments(bundle);
                 getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                         playListFragment).commit();
             }
         });

         bestSongDay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Fragment playListFragment = new PlaylistFragment();
                 Bundle bundle  = new Bundle();
                 bundle.putString("info","bestSongDay");
                 playListFragment.setArguments(bundle);
                 getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                         playListFragment).commit();
             }
         });

         bestSongWeek.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Fragment playListFragment = new PlaylistFragment();
                 Bundle bundle  = new Bundle();
                 bundle.putString("info","bestSongWeek");
                 playListFragment.setArguments(bundle);
                 getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                         playListFragment).commit();
             }
         });
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }
}
