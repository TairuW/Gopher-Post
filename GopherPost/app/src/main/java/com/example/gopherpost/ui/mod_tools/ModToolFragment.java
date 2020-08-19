package com.example.gopherpost.ui.mod_tools;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gopherpost.Event;
import com.example.gopherpost.EventActivity;
import com.example.gopherpost.MainActivity;
import com.example.gopherpost.R;
import com.example.gopherpost.ui.empty_stack.EmptyStackFragment;
import com.example.gopherpost.ui.empty_stack.EmptyStackViewModel;
import com.example.gopherpost.ui.home.HomeFragment;
import com.example.gopherpost.ui.my_feed.MyFeedFragment;
import com.example.gopherpost.ui.user_profile.UserProfileFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class ModToolFragment extends Fragment {

    private ModToolViewModel modToolViewModel;

    List<Event> event_list;

    int next_index =0;
    public GregorianCalendar startTime = new GregorianCalendar();
    public GregorianCalendar endTime = new GregorianCalendar();

    TextView title;
    TextView description;
    ImageView image;
    TextView group;
    TextView date;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        modToolViewModel =
                ViewModelProviders.of(this).get(ModToolViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_mod_tools, container, false);

        /*
        event_list = new ArrayList<Event>();
        Event event1, event2, event3;
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.pizza);
        event1 = new Event(1,"Pizza Party", "Come eat pizza with SWE!", "The 3rd anual pizza party to celebrate not passing finals!", "Bruinicks Hall", startTime,endTime, "swe@gmail.com", icon);
        event_list.add(event1);
        setData(event1,root);

        Button denyButton = root.findViewById(R.id.event_deny_button);
        denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                denyEvent(event_list.get(next_index),root,event_list);
            }
        });

        Button acceptButton = root.findViewById(R.id.event_accept_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                approveEvent(event_list.get(next_index),root,event_list);
            }
        });
         */

        final Intent nextMod = new Intent(getActivity(), EventActivity.class);
        final Event next = Event.getNextPostToModerate();
        if(next != null){
            nextMod.putExtra("eid", next.getId());
        }
        if(next != null) {
            startActivity(nextMod);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("That's it!  All pending reviews are complete.");
            builder.setPositiveButton("Hooray!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id){
                    //done.  Go home.
                    Intent goHome = new Intent(root.getContext(), MainActivity.class);
                    startActivity(goHome);
                }
            });
            builder.show();
        }
        return root;
    }

    private void denyEvent(Event event, View view, List<Event> list){
        event.disapprovePost();
        next_index++;
        if (event_list.size()-1 >= next_index) {
            ModToolFragment modToolFragment = new ModToolFragment();
            modToolFragment.setData(list.get(next_index), view);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.event_mod_tools, modToolFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else {
            EmptyStackFragment emptyStackFragment = new EmptyStackFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.event_mod_tools_layout, emptyStackFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

        private void approveEvent(Event event, View view, List<Event> list){
        event.approvePost();
        next_index++;
        if (event_list.size()-1 >= next_index) {
            ModToolFragment modToolFragment = new ModToolFragment();
            modToolFragment.setData(list.get(next_index), view);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.event_mod_tools, modToolFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else {
            EmptyStackFragment emptyStackFragment = new EmptyStackFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.event_mod_tools_layout, emptyStackFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private void setData(Event event, View view){
        title = (TextView) view.findViewById(R.id.event_title);
        title.setText(event.getTitle());
        description = (TextView) view.findViewById((R.id.event_description));
        description.setText(event.getLongdesc());
        image = (ImageView) view.findViewById(R.id.event_image);
        image.setImageBitmap(event.getImage());
        date = (TextView) view.findViewById(R.id.event_date);
        date.setText(event.getStartCal().getTime().toString());
        group = (TextView) view.findViewById(R.id.event_group);
        group.setText(event.getLocation());

    }

}
