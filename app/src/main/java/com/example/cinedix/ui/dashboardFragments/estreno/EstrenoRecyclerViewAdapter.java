package com.example.cinedix.ui.dashboardFragments.estreno;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cinedix.R;
import com.example.cinedix.common.Constantes;
import com.example.cinedix.models.entity.Pelicula;

import java.util.List;

public class EstrenoRecyclerViewAdapter extends RecyclerView.Adapter<EstrenoRecyclerViewAdapter.ViewHolder> {

    private final List<Pelicula> mValues;
    private Context ctx;

    public EstrenoRecyclerViewAdapter(Context context, List<Pelicula> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_estreno, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTituloEstreno.setText(holder.mItem.getNombre());
        String rutaImagen = holder.mItem.getRutaImagen();
        if (!rutaImagen.equals("")) {
            Glide.with(ctx)
                    .load(Constantes.BASE_URL + "uploads/" + rutaImagen)
                    .into(holder.ivEstreno);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivEstreno;
        public final TextView tvTituloEstreno;
        public Pelicula mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivEstreno = (ImageView) view.findViewById(R.id.ivEstreno);
            tvTituloEstreno = (TextView) view.findViewById(R.id.tvTituloEstreno);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}