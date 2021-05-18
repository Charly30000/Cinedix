package com.example.cinedix.ui.dashboardFragments.entrada;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cinedix.R;
import com.example.cinedix.models.entity.Entrada;
import com.example.cinedix.retrofit.AuthEntradasClient;
import com.example.cinedix.retrofit.AuthEntradasService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntradaFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private List<Entrada> entradasList;
    private EntradaRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private AuthEntradasClient authEntradasClient;
    private AuthEntradasService authEntradasService;

    public EntradaFragment() {
    }

    public static EntradaFragment newInstance(int columnCount) {
        EntradaFragment fragment = new EntradaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entrada_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            retrofitInit();
            loadCarteleraData();

        }
        return view;
    }

    private void retrofitInit() {
        authEntradasClient = AuthEntradasClient.getInstance();
        authEntradasService = authEntradasClient.getAuthEntradasService();
    }

    private void loadCarteleraData() {
        Call<List<Entrada>> call = authEntradasService.getEntradas();
        call.enqueue(new Callback<List<Entrada>>() {
            @Override
            public void onResponse(Call<List<Entrada>> call, Response<List<Entrada>> response) {
                if (response.isSuccessful()) {
                    entradasList = response.body();
                    adapter = new EntradaRecyclerViewAdapter(getActivity(), entradasList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Algo ha ido mal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Entrada>> call, Throwable t) {
                Toast.makeText(getActivity(), "No tienes conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}