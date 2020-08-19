package com.example.gopherpost.ui.event_page;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gopherpost.Event;
import com.example.gopherpost.R;

public class EventFragment extends Fragment {

    private EventViewModel mViewModel;

    public static EventFragment newInstance() {
        return new EventFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        // TODO: Use the ViewModel
    }

    private void openEvent(Event event){
        EventFragment event_fragment = new EventFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.my_feed_frag,event_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
