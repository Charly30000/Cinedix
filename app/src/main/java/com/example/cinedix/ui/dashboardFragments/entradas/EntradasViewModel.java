package com.example.cinedix.ui.dashboardFragments.entradas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntradasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EntradasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}