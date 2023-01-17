package com.example.melobit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.myViewHolder> {
    private Artist artist;

    public ArtistAdapter(Artist artist){
        this.artist= artist;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_info,parent,false);
        return new myViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String fullName = artist.getResults().get(position).getFullName();
        String followers = artist.getResults().get(position).getFollowersCount();
        String picAddress = artist.getResults().get(position).getImage().getThumbnail().getUrl();
        Picasso.get().load(picAddress).into(holder.artistPic);
        holder.artistFullName.setText(fullName);
        holder.artistFollower.setText(followers);
    }

    @Override
    public int getItemCount() {
        return artist.getResults().size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView artistFullName ;
        TextView artistFollower ;
        ImageView artistPic;
        public myViewHolder(final View view){
            super(view);
            artistFullName = view.findViewById(R.id.artistFullname);
            artistFollower = view.findViewById(R.id.artistFollower);
            artistPic = view.findViewById(R.id.artistPicture);

        }
    }




}
