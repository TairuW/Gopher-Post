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

public class EventFragment2 extends Fragment {

    private EventViewModel mViewModel;

    public static EventFragment2 newInstance() {
        return new EventFragment2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        // TODO: Use the ViewModel
    }

    private void openEvent(Event event){
        EventFragment2 event_fragment = new EventFragment2();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.my_feed_frag,event_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
