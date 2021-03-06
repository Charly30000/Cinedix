package com.example.cinedix.ui.dashboardFragments.entrada;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cinedix.R;
import com.example.cinedix.common.Constantes;
import com.example.cinedix.models.entity.Entrada;
import com.example.cinedix.models.entity.SitiosOcupado;
import com.example.cinedix.ui.QrCodeActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

public class EntradaRecyclerViewAdapter extends RecyclerView.Adapter<EntradaRecyclerViewAdapter.ViewHolder> {

    private final List<Entrada> mValues;
    private Context ctx;

    public EntradaRecyclerViewAdapter(Context context, List<Entrada> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_entrada, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvEntradaPelicula.setText(holder.mItem.getPelicula());
        holder.tvHoraPelicula.setText(holder.mItem.getHoraPelicula());
        holder.tvEntradaCine.setText(holder.mItem.getCine());
        String sitiosOcupadosString = "";
        List<SitiosOcupado> sitiosOcupados = holder.mItem.getSitiosOcupados();
        for (SitiosOcupado s: sitiosOcupados) {
            sitiosOcupadosString += "Asiento numero: " + s.getSitioOcupado() +
                    (s.equals(sitiosOcupados.get(sitiosOcupados.size() - 1)) ? "" : "\n");
        }
        holder.tvEntradaSitiosOcupados.setText(sitiosOcupadosString);
        String codigo = holder.mItem.getCodigo();
        holder.tvEntradaCodigo.setText("Codigo: " + codigo);
        holder.tvEntradaEstado.setText("Estado: " + holder.mItem.getEstado());
        switch (holder.mItem.getEstado()) {
            case Constantes.ENTRADA_PAGADO:
                holder.tvEntradaEstado.setTextColor(Color.GREEN);
                break;
            case Constantes.ENTRADA_PROCESANDO:
                holder.tvEntradaEstado.setTextColor(0xFFF39C12);
                break;
            case Constantes.ENTRADA_CANCELADO:
                holder.tvEntradaEstado.setTextColor(Color.RED);
                break;
        }
        //QrCode
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(codigo, BarcodeFormat.QR_CODE, 350, 350);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            holder.ivEntradaQRCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        holder.ivEntradaQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, QrCodeActivity.class);
                i.putExtra("codigo", holder.mItem.getCodigo());
                ctx.startActivity(i);
            }
        });

        holder.cardViewEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mItem.getEstado().equals(Constantes.ENTRADA_PROCESANDO)) {
                    Uri link = Uri.parse(Constantes.BASE_URL_BANCO +
                            "cinedix/transaccion/sendtobank?codigo=" + codigo);
                    Intent i = new Intent(Intent.ACTION_VIEW, link);
                    ctx.startActivity(i);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvEntradaPelicula;
        public final TextView tvHoraPelicula;
        public final TextView tvEntradaCine;
        public final TextView tvEntradaSitiosOcupados;
        public final TextView tvEntradaCodigo;
        public final TextView tvEntradaEstado;
        public final ImageView ivEntradaQRCode;
        public final CardView cardViewEntrada;
        public Entrada mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvEntradaPelicula = view.findViewById(R.id.tvEntradaPelicula);
            tvHoraPelicula = view.findViewById(R.id.tvHoraPelicula);
            tvEntradaCine = view.findViewById(R.id.tvEntradaCine);
            tvEntradaSitiosOcupados = view.findViewById(R.id.tvEntradaSitiosOcupados);
            tvEntradaCodigo = view.findViewById(R.id.tvEntradaCodigo);
            tvEntradaEstado = view.findViewById(R.id.tvEntradaEstado);
            ivEntradaQRCode = view.findViewById(R.id.ivEntradaQRCode);
            cardViewEntrada = view.findViewById(R.id.cardViewEntrada);

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}