package com.example.melobit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
        public myViewHolder(final View view){
        super(view);
        title = view.findViewById(R.id.songtitle);
        songArtist = view.findViewById(R.id.songArtist);
        artistPic = view.findViewById(R.id.artistpic);



        }
    }

    @NonNull
    @Override
    public SongAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_info,parent,false);
        return new myViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.myViewHolder holder, int position) {
        String title = songList.getResults().get(position).getTitle();
        String songArtist = songList.getResults().get(position).getAlbum().getArtists().get(0).getFullName();
        //TODO make Song class able to deliver URL to Picasso in order to load image into imageview
        String picAddress = songList.getResults().get(position).getImage().getThumbnail().getUrl();
        Picasso.get().load(picAddress).into(holder.artistPic);
        holder.title.setText(title);
        holder.songArtist.setText(songArtist);
    }

    @Override
    public int getItemCount() {
        return songList.getResults().size();
    }
}
