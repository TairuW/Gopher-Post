package com.example.gopherpost.ui.leaderboards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gopherpost.R;
import com.example.gopherpost.UserProfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardsFragment extends Fragment {

    private LeaderboardsViewModel leaderboardsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_leaderboards, container, false);
        //a list to store all the products
        List<UserProfile> userList;

        // Lookup the recyclerview in activity layout
        RecyclerView leaderboard = (RecyclerView) root.findViewById(R.id.leaderboardView);
        leaderboard.setLayoutManager(new LinearLayoutManager(getActivity()));

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
                        2,
                        "Goldy Gopher",
                        400));

        userList.add(
                new UserProfile(
                        3,
                        "Jimmy John",
                        500));

        userList.add(
                new UserProfile(
                        4,
                        "Sam Smith",
                        100));

        userList.add(
                new UserProfile(
                        5,
                        "Bill Sampson",
                        70000));

        userList.add(
                new UserProfile(
                        6,
                        "Hannah Montana",
                        900));

        userList.add(
                new UserProfile(
                        7,
                        "Tooth Fairy",
                        100));

        userList.add(
                new UserProfile(
                        8,
                        "Santa Claus",
                        100));

        userList.add(
                new UserProfile(
                        9,
                        "Sam Smith",
                        8907));

        userList.add(
                new UserProfile(
                        10,
                        "PJ Fleck",
                        100000));

        Collections.sort(userList, new Comparator<UserProfile>() {
            @Override
            public int compare(UserProfile o1, UserProfile o2) {
                UserProfile p1 = (UserProfile) o1;
                UserProfile p2 = (UserProfile) o2;
                if (p1.getScore() < p2.getScore()){
                    return 1;
                } else if (p1.getScore() > p2.getScore()){
                    return -1;
                } else {
                    return 0;
                }
            }
        });



        // Create adapter passing in the sample user data
        LeaderboadAdapter adapter = new LeaderboadAdapter(userList);
        // Attach the adapter to the recyclerview to populate items
        leaderboard.setAdapter(adapter);

        leaderboard.setItemAnimator(new DefaultItemAnimator());
        // That's all!

        return root;
    }
}