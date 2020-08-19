package com.example.gopherpost.ui.my_feed;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gopherpost.R;
import com.example.gopherpost.ui.user_profile.UserProfileFragment;
import com.example.gopherpost.ui.event_page.EventFragment;
import com.example.gopherpost.ui.search.SearchFragment;

public class MyFeedFragment extends Fragment {
    private MyFeedViewModel myFeedViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myFeedViewModel =
                ViewModelProviders.of(this).get(MyFeedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_feed, container, false);
        ImageButton userprofileButton = root.findViewById(R.id.imageButton1);
        ImageButton event1Button = root.findViewById(R.id.imageButton1a);
        userprofileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                UserProfileFragment UP_fragment = new UserProfileFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,UP_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        event1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                EventFragment event1_fragment = new EventFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,event1_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



        return root;
    }
    private void swapFragment(){

    }
}