package com.example.gopherpost;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.gopherpost.ui.make_post.MakePostFragment;
import com.example.gopherpost.ui.make_post.MakePostViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import io.noties.markwon.Markwon;

import static android.app.PendingIntent.getActivity;

public class EventActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        //Toolbar toolbar = findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int eventId = intent.getIntExtra("eid", -1);
        final Event eventToShow = Event.getEvent(eventId);

        ImageView image = findViewById(R.id.event_image);
        image.setImageBitmap(eventToShow.getImage());

        TextView title = findViewById(R.id.event_title);
        title.setText(eventToShow.getTitle());

        TextView dateTime = findViewById(R.id.event_date);

        //display time
        GregorianCalendar c1 = eventToShow.getStartCal();
        String amPm1 = " AM  through  ";
        int h1 = c1.get(Calendar.HOUR_OF_DAY);
        if(h1 > 12){ amPm1 = " PM  through  "; h1-=12; }
        GregorianCalendar c2 = eventToShow.getEndCal();
        String amPm2 = " AM";
        int h2 = c2.get(Calendar.HOUR_OF_DAY);
        if(h2 > 12){ amPm1 = " PM"; h2-=12; }

        String inclusiveDates = c1.get(Calendar.MONTH) + "/" + c1.get(Calendar.DAY_OF_MONTH) + "/"
                + c1.get(Calendar.YEAR) + " at " + h1 + ":" + c1.get(Calendar.MINUTE) + amPm1
                + c2.get(Calendar.MONTH) + "/" + c2.get(Calendar.DAY_OF_MONTH) + "/"
                + c2.get(Calendar.YEAR) + " at " + h2 + ":" + c2.get(Calendar.MINUTE) + amPm2;
        dateTime.setText(inclusiveDates);

        // location
        TextView location = findViewById(R.id.event_location);
        location.setText(eventToShow.getLocation());

        // description
        TextView description = findViewById(R.id.event_description);
        final Markwon markwon = Markwon.create(this);
        markwon.setMarkdown(description, eventToShow.getLongdesc());


        // mod tools
        Button modAccept = findViewById(R.id.btnAcceptMod);
        Button modDecline = findViewById(R.id.btnDenyMod);

        if(eventToShow.needsModeration()){
            modAccept.setVisibility(View.VISIBLE);
            modDecline.setVisibility(View.VISIBLE);

            eventToShow.disapprovePost();
            final Intent nextMod = new Intent(this.getBaseContext(), EventActivity.class);
            final Event next = Event.getNextPostToModerate();
            if(next != null){
                nextMod.putExtra("eid", next.getId());
            }


            modAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    eventToShow.approvePost();
                    if(next != null) {
                        startActivity(nextMod);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setMessage("That's it!  All pending reviews are complete.");
                        builder.setPositiveButton("Hooray!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){
                                //done.  Go home.
                                Intent goHome = new Intent(view.getContext(), MainActivity.class);
                                startActivity(goHome);
                            }
                        });
                        builder.show();
                    }
                }
            });

            modDecline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    eventToShow.disapprovePost();
                    if(next != null) {
                        startActivity(nextMod);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setMessage("That's it!  All pending reviews are complete.");
                        builder.setPositiveButton("Hooray!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){
                                //done.  Go home.
                                Intent goHome = new Intent(view.getContext(), MainActivity.class);
                                startActivity(goHome);
                            }
                        });
                        builder.show();
                    }
                }
            });
        } else { //no moderation needed
            modAccept.setVisibility(View.GONE);
            modDecline.setVisibility(View.GONE);
        }

        /*
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_search, R.id.nav_my_feed,
                R.id.nav_leaderboards, R.id.nav_my_profile, R.id.nav_make_post,
                R.id.nav_mod_tools)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //create events for testing
        CreateBogusEvents();

         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}