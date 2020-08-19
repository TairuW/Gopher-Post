package com.example.gopherpost.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gopherpost.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    //a list to store all the posters
    List<Poster> posterList;

    //the recyclerview
    RecyclerView recyclerView;

    // the togglebutton
    private ToggleButton toggleButton;

//    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        super.onCreate(savedInstanceState);
        //root.setcontesetContentView(R.layout.fragment_home);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) (root.findViewById(R.id.recyclerView));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getBaseContext()));

        //initializing the posterlist
        posterList = new ArrayList<>();


        //adding some items to our list
        posterList.add(
                new Poster(
                        1,
                        "Bowling",
                        "Bowling game tonight",
                        R.drawable.bowling));

        posterList.add(
                new Poster(
                        2,
                        "Ping Pong",
                        "Ping-pong game this weekend",
                        R.drawable.ping_pong));

        posterList.add(
                new Poster(
                        3,
                        "Pizza",
                        "Here provides free pizza",
                        R.drawable.pizza));

        //creating recyclerview adapter
        PosterAdapter adapter = new PosterAdapter(this.getContext(), posterList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

//        addListenerOnButtonClick();


        return root;
    }

    /*public int getContentView() {
        return contentView;
    }*/
//    public void addListenerOnButtonClick(){
//        //Getting the ToggleButton and Button instance from the layout xml file
//        toggleButton=(ToggleButton) getView().findViewById(R.id.toggleButton);
//        toggleButton.setBackgroundColor(Color.BLACK);
//        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    // The toggle is enabled
//                } else {
//                    // The toggle is disabled
//                }
//            }
//        });
//        //Performing action on button click
//        buttonSubmit.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                StringBuilder result = new StringBuilder();
//                result.append("ToggleButton : ").append(toggleButton.getText());
//                //Displaying the message in toast
//                Toast.makeText(root.getApplicationContext(), result.toString(),Toast.LENGTH_LONG).show();
//            }
//
//        });
//    }
}