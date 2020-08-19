package com.example.gopherpost;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.gopherpost.Event;
import com.example.gopherpost.R;
import com.google.android.material.navigation.NavigationView;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
    }

    private void CreateBogusEvents(){
        //normal events

        Event game = new Event(1, "Game Time", "Join up with some virtual friends for games in Coffman!",
                "# GameTime!! \n This is your chance to get together with some friends and have a good time!",
                "Coffman Memorial Union", new GregorianCalendar(), new GregorianCalendar(), "gamer524@umn.edu",
                BitmapFactory.decodeResource(getResources(), R.drawable.game));
        game.setFinished(true);
        game.approvePost();


        Event pingPong = new Event(2, "Ping Pong", "Morning ping pong at the rec center.  Come for a good time.",
                "# Ping Pong is where it's at \n Join up for a game of Ping Pong this Thursday!  And don't forget to bring a friend!!",
                "Rec Center", new GregorianCalendar(), new GregorianCalendar(), "pong0180@umn.edu",
                BitmapFactory.decodeResource(getResources(), R.drawable.ping_pong));
        pingPong.setFinished(true);
        pingPong.approvePost();


        Event pizza = new Event(3, "Pizza Party", "Pizza for free.  Need I say more.",
                "# Free Pizza \n Come visit the Healthy Eating student group in Tate for some free pizza and free advice not to eat it.  *Limit 1/2 slice per person.* ",
                "John T Tate Hall", new GregorianCalendar(), new GregorianCalendar(), "pizza112@umn.edu",
                BitmapFactory.decodeResource(getResources(), R.drawable.pizza));
        pizza.setFinished(true);
        pizza.approvePost();


        Event bowl = new Event(4, "Half off bowling", "Monday night bowling at half price.",
                "Bowling is now half price on Monday morning.  ",
                "Rec Center", new GregorianCalendar(), new GregorianCalendar(), "bowli261@umn.edu",
                BitmapFactory.decodeResource(getResources(), R.drawable.bowling));
        bowl.setFinished(true);
        bowl.approvePost();


        Event research = new Event(5, "Research opportunity", "Earn $25 to participate in research",
                "# Research opportunity \n\n This is a chance to earn up to $25 in Amazon gift cards to participate in a short survey and research opportunity.",
                "Keller 6-8347", new GregorianCalendar(), new GregorianCalendar(), "resea002@umn.edu",
                BitmapFactory.decodeResource(getResources(), R.drawable.study));
        research.setFinished(true);
        research.approvePost();



        // non-approved posts
        Event party = new Event(6, "Party Time", "Party with da bois iykyk",
                "# PARTTYYY TIMMMEEEE \n letss goooo its party time on the lawn Saturday night bring your stuffs and everything",
                "main lawn", new GregorianCalendar(), new GregorianCalendar(), "daboi301@umn.edu",
                BitmapFactory.decodeResource(getResources(), R.drawable.cheers));
        party.setFinished(true);
        party.approvePost();


        Event bowl2 = new Event(7, "Bowling Partner", "Looking for a bowling partner for this Thursday",
                "I pay.  You play.  Contact me at 612-555-7320",
                "Rec Center", new GregorianCalendar(), new GregorianCalendar(), "bowli061@umn.edu",
                BitmapFactory.decodeResource(getResources(), R.drawable.pizza));
        bowl.setFinished(true);
        bowl.approvePost();

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

