package com.example.cinedix.ui.dashboardFragments.cartelera;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cinedix.R;
import com.example.cinedix.models.entity.Pelicula;
import com.example.cinedix.retrofit.AuthPeliculasClient;
import com.example.cinedix.retrofit.AuthPeliculasService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarteleraFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    List<Pelicula> peliculasList;
    CarteleraRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    AuthPeliculasService authPeliculasService;
    AuthPeliculasClient authPeliculasClient;

    public CarteleraFragment() {
    }

    public static CarteleraFragment newInstance(int columnCount) {
        CarteleraFragment fragment = new CarteleraFragment();
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
        View view = inflater.inflate(R.layout.fragment_cartelera_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numColumnas = (int) dpWidth / 160;
                recyclerView.setLayoutManager(new GridLayoutManager(context, numColumnas));
            }
            retrofitInit();
            loadCarteleraData();

        }
        return view;
    }

    private void retrofitInit() {
        authPeliculasClient = AuthPeliculasClient.getInstance();
        authPeliculasService = authPeliculasClient.getAuthPeliculasService();
    }

    private void loadCarteleraData() {
        Call<List<Pelicula>> call = authPeliculasService.getPeliculasCartelera();
        call.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if (response.isSuccessful()) {
                    peliculasList = response.body();
                    adapter = new CarteleraRecyclerViewAdapter(getActivity(), peliculasList);
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(getActivity(), "Algo ha ido mal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Toast.makeText(getActivity(), "No hay conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });


    }
}