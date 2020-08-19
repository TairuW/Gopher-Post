package com.example.gopherpost.ui.leaderboards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gopherpost.R;
import com.example.gopherpost.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);


        //a list to store all the products
        List<UserProfile> userList;

            // Lookup the recyclerview in activity layout
            RecyclerView leaderboard = (RecyclerView) findViewById(R.id.leaderboardView);

            // Initialize users
            //initializing the userlist
            userList = new ArrayList<>();

            //adding some items to our list
            userList.add(
                    new UserProfile(
                            1,
                            "Billy Bob",
                            200));

            userList.add(
                    new UserProfile(
                            1,
                            "Goldy Gopher",
                            200));

            userList.add(
                    new UserProfile(
                            1,
                            "Jimmy John",
                            200));

            // Create adapter passing in the sample user data
            LeaderboadAdapter adapter = new LeaderboadAdapter(userList);
            // Attach the adapter to the recyclerview to populate items
            leaderboard.setAdapter(adapter);
            // Set layout manager to position the items
            leaderboard.setLayoutManager(new LinearLayoutManager(this));
            // That's all!
        }
    }
