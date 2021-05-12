package com.example.cinedix.ui.dashboardFragments.cartelera;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CarteleraViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CarteleraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}