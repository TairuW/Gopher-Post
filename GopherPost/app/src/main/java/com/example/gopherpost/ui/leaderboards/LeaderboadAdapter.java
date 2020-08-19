package com.example.gopherpost.ui.leaderboards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gopherpost.R;
import com.example.gopherpost.UserProfile;

import java.util.List;

public class LeaderboadAdapter extends
        RecyclerView.Adapter<LeaderboadAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView userNameTextView;
        public TextView userScoretextView;
        public ImageView userPicImageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            userNameTextView = (TextView) itemView.findViewById(R.id.userName);
            userScoretextView = (TextView) itemView.findViewById(R.id.userScore);
        }
    }

    // Store a member variable for the user profiles
    private List<UserProfile> mUsers;

    // Pass in the contact array into the constructor
    public LeaderboadAdapter(List<UserProfile> users) {
        mUsers = users;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public LeaderboadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.leader_board_user_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(LeaderboadAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        UserProfile user = mUsers.get(position);
        // Set item views based on your views and data model
        TextView user_name = viewHolder.userNameTextView;
        user_name.setText(user.getName());
        TextView user_score = viewHolder.userScoretextView;
        user_score.setText(Integer.toString(user.getScore()));

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
