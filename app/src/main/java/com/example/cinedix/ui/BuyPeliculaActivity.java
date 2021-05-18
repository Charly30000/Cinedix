package com.example.cinedix.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cinedix.R;
import com.example.cinedix.common.Constantes;
import com.example.cinedix.models.entity.Pelicula;
import com.example.cinedix.models.entity.SesionPelicula;
import com.example.cinedix.models.entity.SesionPeliculaRequest;
import com.example.cinedix.models.entity.SitiosOcupado;
import com.example.cinedix.retrofit.AuthSesionPeliculaClient;
import com.example.cinedix.retrofit.AuthSesionPeliculaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyPeliculaActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivPoster;
    private TextView tvTitulo, tvDescripcion;
    private Spinner spinnerSeleccionarCine, spinnerSeleccionarHora;
    private LinearLayout linearLayoutCheckboxes;
    private Button btnSubmit;

    private Pelicula pelicula;
    private List<SesionPelicula> sesionPeliculaList;
    private String[] sesionPeliculaArray;
    private String[] sesionPeliculaHorasArray;
    private List<Integer> asientosSeleccionados;

    private AuthSesionPeliculaClient authSesionPeliculaClient;
    private AuthSesionPeliculaService authSesionPeliculaService;

    private static final String SELECCIONAR_CINE = "---Selecciona tu cine---";
    private static final String SELECCIONAR_HORA = "---Selecciona la hora---";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pelicula);

        findViews();

        Bundle b = this.getIntent().getExtras();
        pelicula = (Pelicula) b.get("pelicula");

        retrofitInit();
        putData(pelicula);
        asientosSeleccionados = new ArrayList<>();
        listeners();
    }

    private void listeners() {
        spinnerSeleccionarHora.setEnabled(false);
        btnSubmit.setOnClickListener(this);
        // Listeners spinners
        spinnerSeleccionarCine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getItemAtPosition(position).equals(SELECCIONAR_CINE)) {
                    List<String> horasCine = new ArrayList<>();
                    horasCine.add(SELECCIONAR_HORA);
                    for (SesionPelicula sp : sesionPeliculaList) {
                        if (sp.getCine().getNombre().equals(sesionPeliculaArray[position])) {
                            horasCine.add(sp.getHoraPelicula());
                        }
                    }
                    sesionPeliculaHorasArray = new String[horasCine.size()];
                    int index = 0;
                    for(String s : horasCine) {
                        sesionPeliculaHorasArray[index] = s;
                        index++;
                    }

                    ArrayAdapter adapter = new ArrayAdapter<String>(BuyPeliculaActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, sesionPeliculaHorasArray);
                    spinnerSeleccionarHora.setAdapter(adapter);
                    spinnerSeleccionarHora.setEnabled(true);

                } else {
                    spinnerSeleccionarHora.setEnabled(false);
                    asientosSeleccionados.clear();
                    linearLayoutCheckboxes.removeAllViewsInLayout();
                    linearLayoutCheckboxes.requestLayout();
                    linearLayoutCheckboxes.invalidate();
                    asientosSeleccionados.clear();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerSeleccionarHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Se borran todos los checkboxes del linear layout...
                linearLayoutCheckboxes.removeAllViewsInLayout();
                linearLayoutCheckboxes.requestLayout();
                linearLayoutCheckboxes.invalidate();
                asientosSeleccionados.clear();
                if (!parent.getItemAtPosition(position).equals(SELECCIONAR_HORA)) {
                    SesionPelicula sesionPelicula = null;
                    for (SesionPelicula sp : sesionPeliculaList) {
                        if (sp.getCine().getNombre().equals(spinnerSeleccionarCine.getSelectedItem()) &&
                                sp.getHoraPelicula().equals(spinnerSeleccionarHora.getSelectedItem())) {
                            sesionPelicula = sp;
                            break;
                        }
                    }
                    for (int i = 1; i <= sesionPelicula.getSitiosTotales(); i++) {
                        boolean sitioOcupado = false;
                        for (SitiosOcupado so : sesionPelicula.getSitiosOcupados()) {
                            if (so.getSitioOcupado().equals(i)) {
                                sitioOcupado = true;
                                break;
                            }
                        }
                        if (!sitioOcupado) {
                            CheckBox cb = new CheckBox(BuyPeliculaActivity.this);
                            cb.setId(i);
                            cb.setText("Asiento numero " + i);
                            cb.setTextSize(18);
                            cb.setOnClickListener(BuyPeliculaActivity.this);
                            linearLayoutCheckboxes.addView(cb);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void putData(Pelicula p) {
        tvTitulo.setText(p.getNombre());
        tvDescripcion.setText(p.getDescripcion());
        if (!p.getRutaImagen().equals("")) {
            Glide.with(this)
                    .load(Constantes.BASE_URL + "uploads/" + p.getRutaImagen())
                    .into(ivPoster);
        }

        Call<List<SesionPelicula>> call = authSesionPeliculaService.getSesionesPeliculas(pelicula.getId());
        call.enqueue(new Callback<List<SesionPelicula>>() {
            @Override
            public void onResponse(Call<List<SesionPelicula>> call, Response<List<SesionPelicula>> response) {
                if (response.isSuccessful()) {
                    sesionPeliculaList = response.body();
                    List<String> listAux = new ArrayList<>();
                    listAux.add(SELECCIONAR_CINE);
                    for (SesionPelicula sp : sesionPeliculaList) {
                        if (!listAux.contains(sp.getCine().getNombre())) {
                            listAux.add(sp.getCine().getNombre());
                        }
                    }
                    sesionPeliculaArray = new String[listAux.size()];
                    int index = 0;
                    for (String s : listAux) {
                        sesionPeliculaArray[index] = s;
                        index++;
                    }

                    ArrayAdapter adapter = new ArrayAdapter<String>(BuyPeliculaActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, sesionPeliculaArray);
                    spinnerSeleccionarCine.setAdapter(adapter);
                } else {
                    Toast.makeText(BuyPeliculaActivity.this,
                            "Algo ha ido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<SesionPelicula>> call, Throwable t) {
                Toast.makeText(BuyPeliculaActivity.this,
                        "Lo sentimos, no tienes conexion a internet", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void retrofitInit() {
        authSesionPeliculaClient = AuthSesionPeliculaClient.getInstance();
        authSesionPeliculaService = authSesionPeliculaClient.getAuthPeliculasService();
    }

    private void findViews() {
        ivPoster = findViewById(R.id.ivPoster);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        spinnerSeleccionarCine = findViewById(R.id.spinnerSeleccionarCine);
        spinnerSeleccionarHora = findViewById(R.id.spinnerSeleccionarHora);
        linearLayoutCheckboxes = findViewById(R.id.linearLayoutCheckboxes);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSubmit:
                comprarEntrada();
                break;
            default:
                listenerCheckbox(v, id);
                break;
        }
    }

    private void comprarEntrada() {
        SesionPeliculaRequest entrada = null;
        if (!asientosSeleccionados.isEmpty()) {
            for (SesionPelicula sp : sesionPeliculaList) {
                if (sp.getCine().getNombre().equals(spinnerSeleccionarCine.getSelectedItem()) &&
                        sp.getHoraPelicula().equals(spinnerSeleccionarHora.getSelectedItem())) {
                    entrada = new SesionPeliculaRequest(sp.getId(), asientosSeleccionados);
                }
            }
            Call<SesionPeliculaRequest> call = authSesionPeliculaService.comprarEntrada(entrada);
            call.enqueue(new Callback<SesionPeliculaRequest>() {
                @Override
                public void onResponse(Call<SesionPeliculaRequest> call, Response<SesionPeliculaRequest> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(BuyPeliculaActivity.this, "Pelicula comprada, revise sus entradas\nMuchas gracias!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(BuyPeliculaActivity.this, "Ha ocurrido un error inesperado, vuelva a intentarlo por favor", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<SesionPeliculaRequest> call, Throwable t) {
                    Toast.makeText(BuyPeliculaActivity.this, "No tienes conexion a internet", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Debes de seleccionar algun asiento", Toast.LENGTH_SHORT).show();
        }

    }

    private void listenerCheckbox(View v, int id) {
        boolean checked = ((CheckBox) v).isChecked();
        String aux = ((CheckBox) v).getText().toString().split(" ")[2];
        Integer asiento = Integer.parseInt(aux);
        if (checked) {
            asientosSeleccionados.add(asiento);
        } else {
            asientosSeleccionados.remove(new Integer(asiento));
        }
    }
}