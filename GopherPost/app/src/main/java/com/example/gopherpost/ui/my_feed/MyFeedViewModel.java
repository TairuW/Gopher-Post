package com.example.gopherpost.ui.my_feed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyFeedViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyFeedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My Feed fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}