package com.example.cinedix.ui.dashboardFragments.entradas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinedix.R;

public class EntradasFragment extends Fragment {

    private EntradasViewModel entradasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        entradasViewModel =
                new ViewModelProvider(this).get(EntradasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_entradas, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        entradasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}