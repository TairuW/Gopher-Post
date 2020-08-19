package com.example.gopherpost.ui.make_post;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.w3c.dom.Text;

import io.noties.markwon.Markwon;

public class MakePostViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MakePostViewModel() {

    }
}
