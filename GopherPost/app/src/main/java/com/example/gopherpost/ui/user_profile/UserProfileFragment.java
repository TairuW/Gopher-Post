package com.example.gopherpost.ui.user_profile;

import android.os.Bundle;
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
import android.widget.Button;
import com.example.gopherpost.ui.event_page.EventFragment;

import com.example.gopherpost.R;
import com.example.gopherpost.ui.event_page.EventFragment2;
import com.example.gopherpost.ui.event_page.EventFragment3;

public class UserProfileFragment extends Fragment {

    private UserProfileViewModel userProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileViewModel =
                ViewModelProviders.of(this).get(UserProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user_profile, container, false);
        final Button followButton = root.findViewById(R.id.button_follow);
        ImageButton event3cButton = root.findViewById(R.id.imageButton3c);
        ImageButton event2aButton = root.findViewById(R.id.imageButton2a);
        ImageButton event2bButton = root.findViewById(R.id.imageButton2b);
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                if (followButton.getText() == "Follow") {
                    followButton.setText("Following!");
                } else {
                    followButton.setText("Follow");
                }
            }
        });

        event3cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                EventFragment event1_fragment = new EventFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,event1_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        event2aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                EventFragment2 event1_fragment = new EventFragment2();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,event1_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        event2bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                EventFragment3 event1_fragment = new EventFragment3();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,event1_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


//        final TextView textView = root.findViewById(R.id.text_user_profile);
//        userProfileViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }
}