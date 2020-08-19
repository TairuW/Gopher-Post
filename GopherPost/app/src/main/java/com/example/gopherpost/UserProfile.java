package com.example.gopherpost;

import android.graphics.drawable.Drawable;

public class UserProfile {
    private int id;
    private String name;
    private int score;
    //    private double price;

    // private int image;

    public UserProfile(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    //public int getImage() {
        //return image;
    //}
}
