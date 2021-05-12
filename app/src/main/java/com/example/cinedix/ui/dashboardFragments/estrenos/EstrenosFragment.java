package com.example.cinedix.ui.dashboardFragments.estrenos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinedix.R;

public class EstrenosFragment extends Fragment {

    private EstrenosViewModel estrenosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        estrenosViewModel =
                new ViewModelProvider(this).get(EstrenosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_estrenos, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        estrenosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}