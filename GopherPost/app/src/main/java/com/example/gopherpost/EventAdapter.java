package com.example.gopherpost;

import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the posts in a list
    private List<Event> eventList;

    //getting the context and event list with constructor
    public EventAdapter(Context mCtx, List<Event> eventList) {
        this.mCtx = mCtx;
        this.eventList = eventList;
    }

    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.layout_event, null);
        return new EventAdapter.EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventViewHolder eventCard, int position) {
        //getting the event at the specified position
        Event event = eventList.get(position);

        //binding the data with the viewholder views
        eventCard.textViewTitle.setText(event.getTitle());
        eventCard.textViewShortDesc.setText(event.getShortdesc());
        eventCard.imageView.setImageBitmap(event.getImage());


    }


    @Override
    public int getItemCount() { return eventList.size(); }



    class EventViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle, textViewShortDesc;
        public ImageView imageView;

        public EventViewHolder(final View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mCtx, EventActivity.class);
                    Event toSend = eventList.get(getAdapterPosition());
                    intent.putExtra("eid", toSend.getId());
                    mCtx.startActivity(intent);
                }
            });
        }


    }

}
