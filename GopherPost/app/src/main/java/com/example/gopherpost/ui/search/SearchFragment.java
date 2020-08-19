package com.example.gopherpost.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gopherpost.Event;
import com.example.gopherpost.EventAdapter;
import com.example.gopherpost.R;

import java.util.LinkedList;
import java.util.List;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    List<Event> validEvents;
    List<Event> filteredEvents;
    RecyclerView eventListRecyclerView;
    EventAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final EditText textSearch = root.findViewById(R.id.textSearch);
        textSearch.setText("");
        validEvents = Event.getValidEvents();
        filteredEvents = new LinkedList<>(validEvents);

        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterEvents(textSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterEvents(editable.toString());
            }
        });

        eventListRecyclerView = root.findViewById(R.id.searchRecyclerView);
        eventListRecyclerView.setHasFixedSize(false);
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getBaseContext()));
        adapter = new EventAdapter(this.getContext(), filteredEvents);
        eventListRecyclerView.setAdapter(adapter);

        return root;
    }

    private void filterEvents(String filter){
        filteredEvents = new LinkedList<>();  //reset filteredEvents

        for(Event e : validEvents){
            if(e.getTitle().contains(filter) || e.getLongdesc().contains(filter) || e.getShortdesc().contains(filter)){
                filteredEvents.add(e);  // filtered events = valid events that match filter
            }
        }

        //maybe update adapter, maybe not need to.
        adapter = new EventAdapter(this.getContext(), filteredEvents);
        eventListRecyclerView.setAdapter(adapter);
    }
}