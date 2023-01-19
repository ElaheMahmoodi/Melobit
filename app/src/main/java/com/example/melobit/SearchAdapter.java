package com.example.melobit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.myViewHolder>{
    private SearchResult result;

    public SearchAdapter(SearchResult result){
        this.result= result;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result,parent,false);
        return new SearchAdapter.myViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
         if(result.getResults().get(position).getType().equals("song") ){
            holder.resultTitle.setText(result.getResults().get(position).getSong().getTitle());
            holder.resultDes.setText("آهنگ");
            Picasso.get().load(result.getResults().get(position).getSong().getImage().getThumbnail().getUrl()).into(holder.resultImg);
         }
        if(result.getResults().get(position).getType().equals("artist") ){
            holder.resultTitle.setText(result.getResults().get(position).getArtist().getFullName());
            holder.resultDes.setText("خواننده");
            Picasso.get().load(result.getResults().get(position).getArtist().getImage().getThumbnail().getUrl()).into(holder.resultImg);
        }

    }

    @Override
    public int getItemCount() {
        return result.getResults().size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView resultTitle ;
        TextView resultDes ;
        ImageView resultImg;
    public myViewHolder(final View view){
        super(view);
        resultTitle = view.findViewById(R.id.resultTitle);
        resultDes = view.findViewById(R.id.resultdes);
        resultImg = view.findViewById(R.id.resultPic);
    }

}

}
