package com.example.melobit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.myViewHolder> {
    private Song songList;

    public SongAdapter(Song songList){
        this.songList= songList;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView title ;
        TextView downloadcounts ;

        public myViewHolder(final View view){
        super(view);
        title = view.findViewById(R.id.songtitle);
        downloadcounts = view.findViewById(R.id.downloadscount);



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
        String downloadcounts = songList.getResults().get(position).getDownloadCount();
        holder.title.setText(title);
        holder.downloadcounts.setText(downloadcounts);
    }

    @Override
    public int getItemCount() {
        return songList.getResults().size();
    }
}
