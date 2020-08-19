package com.example.gopherpost.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gopherpost.R;

import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the posts in a list
    private List<Poster> posterList;

    //getting the context and poster list with constructor
    public PosterAdapter(Context mCtx, List<Poster> posterList) {
        this.mCtx = mCtx;
        this.posterList = posterList;
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.layout_posters, null);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        //getting the poster of the specified position
        Poster poster = posterList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(poster.getTitle());
        holder.textViewShortDesc.setText(poster.getShortdesc());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(poster.getImage()));
    }


    @Override
    public int getItemCount() {
        return posterList.size();
    }


    class PosterViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle, textViewShortDesc;
        public ImageView imageView;

        public PosterViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
