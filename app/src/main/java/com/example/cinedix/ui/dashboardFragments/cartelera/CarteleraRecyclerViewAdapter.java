package com.example.cinedix.ui.dashboardFragments.cartelera;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cinedix.R;
import com.example.cinedix.common.Constantes;
import com.example.cinedix.models.entity.Pelicula;
import com.example.cinedix.ui.BuyPeliculaActivity;

import java.util.List;

public class CarteleraRecyclerViewAdapter extends RecyclerView.Adapter<CarteleraRecyclerViewAdapter.ViewHolder> {

    private final List<Pelicula> mValues;
    private Context ctx;

    public CarteleraRecyclerViewAdapter(Context context, List<Pelicula> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cartelera, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvTituloCartelera.setText(holder.mItem.getNombre());
        String rutaImagen = holder.mItem.getRutaImagen();
        if (!rutaImagen.equals("")) {
            Glide.with(ctx)
                    .load(Constantes.BASE_URL + "uploads/" + rutaImagen)
                    .into(holder.ivCartelera);
        }

        holder.cvCarteleraPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, BuyPeliculaActivity.class);
                i.putExtra("pelicula", holder.mItem);
                ctx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivCartelera;
        public final TextView tvTituloCartelera;
        public final CardView cvCarteleraPelicula;
        public Pelicula mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivCartelera = (ImageView) view.findViewById(R.id.ivCartelera);
            tvTituloCartelera = (TextView) view.findViewById(R.id.tvTituloCartelera);
            cvCarteleraPelicula = (CardView) view.findViewById(R.id.cvCarteleraPelicula);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}