package com.example.gopherpost.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.gopherpost.R;
import com.example.gopherpost.ui.my_feed.MyFeedFragment;

public class SignInFragment extends Fragment {

    private com.example.gopherpost.ui.home.SignInViewModel signInView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        signInView =
                ViewModelProviders.of(this).get(com.example.gopherpost.ui.home.SignInViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sign_in, container, false);
        Button newGameButton = (Button) root.findViewById(R.id.sign_in_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                swapFragment();
            }
        });

        return root;
    }

    private void swapFragment(){
        MyFeedFragment feed_fragment = new MyFeedFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.sign_in_frag,feed_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}