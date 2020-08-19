package com.example.gopherpost.ui.empty_stack;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmptyStackViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public EmptyStackViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No remaining events to approve");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
