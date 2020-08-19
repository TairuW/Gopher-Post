package com.example.gopherpost.ui.mod_tools;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModToolViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ModToolViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mod tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
