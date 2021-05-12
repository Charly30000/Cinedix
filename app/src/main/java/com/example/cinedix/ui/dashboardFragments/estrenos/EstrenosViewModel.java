package com.example.cinedix.ui.dashboardFragments.estrenos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstrenosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EstrenosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}