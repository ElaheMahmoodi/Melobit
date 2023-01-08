package com.example.melobit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PlaylistFragment extends Fragment {
    TextView test ;
    String clickType;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        test = view.findViewById(R.id.playlistfragmenttext);
        Bundle bundle = this.getArguments();
        test.setText(bundle.getString("info"));
        clickType = bundle.getString("info");
        categoryHandler(clickType);
    }


    public void categoryHandler(String info){
        if(info.equals("dayTrends") ){
            //apicall
        }

        if(info.equals("artistTrend") ){
            //apicall
        }

        if(info.equals("bestSongDay") ){
            //apicall
        }

        if (info.equals("bestSongWeek") ){
            //apicall
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist,container,false);

    }
}
