package com.example.melobit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.myViewHolder> {
    private Song songList;

    public SongAdapter(Song songList){
        this.songList= songList;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView title ;
        TextView songArtist ;
        ImageView artistPic;
        CardView songItem;
        public myViewHolder(final View view){
        super(view);
        title = view.findViewById(R.id.songtitle);
        songArtist = view.findViewById(R.id.songArtist);
        artistPic = view.findViewById(R.id.artistpic);
        songItem = view.findViewById(R.id.songItem);


        }
    }

    @NonNull
    @Override
    public SongAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_info,parent,false);
        return new myViewHolder(itemview);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull SongAdapter.myViewHolder holder, int position) {
        String title = songList.getResults().get(position).getTitle();
        String songArtist = songList.getResults().get(position).getAlbum().getArtists().get(0).getFullName();
        //TODO make Song class able to deliver URL to Picasso in order to load image into imageview
        String picAddress = songList.getResults().get(position).getImage().getThumbnail().getUrl();
        Picasso.get().load(picAddress).into(holder.artistPic);
        holder.title.setText(title);
        holder.songArtist.setText(songArtist);

        holder.songItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Hello", Toast.LENGTH_SHORT).show();
//               TODO address ahang grefte shavad
                Fragment musicFragment = new MusicFragment();
                Bundle bundle  = new Bundle();
                bundle.putString("URL", songList.getResults().get(position).getAudio().getMedium().getUrl());
                musicFragment.setArguments(bundle);
                Toast.makeText(view.getContext(), songList.getResults().get(position).getAudio().getMedium().getUrl(), Toast.LENGTH_SHORT).show();
                ((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       musicFragment ).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.getResults().size();
    }

}
