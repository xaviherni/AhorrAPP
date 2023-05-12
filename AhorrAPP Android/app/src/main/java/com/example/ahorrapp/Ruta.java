package com.example.ahorrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ruta extends AppCompatActivity {

    private ProductosDetalleAdapter adLista1 = new ProductosDetalleAdapter();
    private ProductosDetalleAdapter adLista2 = new ProductosDetalleAdapter();
    private ProductosDetalleAdapter adLista3 = new ProductosDetalleAdapter();

    private String [] supers = new String[] {"Dia", "Carrefour", "Alimerka"};

    private List<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);

        lista = getIntent().getExtras().getStringArrayList("lista");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvSuper1 = findViewById(R.id.tvSuper1);
        TextView tvSuper2 = findViewById(R.id.tvSuper2);
        TextView tvSuper3 = findViewById(R.id.tvSuper3);
        tvSuper1.setText(supers[0]);
        tvSuper2.setText(supers[1]);
        tvSuper3.setText(supers[2]);


        RecyclerView rvLista1 = findViewById(R.id.rvLista1);
        RecyclerView rvLista2 = findViewById(R.id.rvLista2);
        RecyclerView rvLista3 = findViewById(R.id.rvLista3);

        rvLista1.setAdapter(adLista1);
        rvLista2.setAdapter(adLista2);
        rvLista3.setAdapter(adLista3);

        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        LinearLayoutManager llm3 = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(this,
                llm1.getOrientation());
        DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(this,
                llm2.getOrientation());
        DividerItemDecoration dividerItemDecoration3 = new DividerItemDecoration(this,
                llm3.getOrientation());
        rvLista1.setLayoutManager(llm1);
        rvLista1.addItemDecoration(dividerItemDecoration1);
        rvLista2.setLayoutManager(llm2);
        rvLista2.addItemDecoration(dividerItemDecoration2);
        rvLista3.setLayoutManager(llm3);
        rvLista3.addItemDecoration(dividerItemDecoration3);

        ClienteRest cr = ClienteRest.create();
        for (String producto: lista) {
            cr.getProductosPorNombre(producto).enqueue(new Callback<List<Producto>>() {
                @Override
                public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                    productosRecibidos(response.body());
                }

                @Override
                public void onFailure(Call<List<Producto>> call, Throwable t) {
                    Log.w(Ruta.this.getClass().getName(), "Error al obtener productos", t);
                    Toast.makeText(Ruta.this, "No se pudieron obtener los productos", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnSuper1 = findViewById(R.id.btnSuper1);
        btnSuper1.setOnClickListener(v -> irASuper(0));
        Button btnSuper2 = findViewById(R.id.btnSuper2);
        btnSuper2.setOnClickListener(v -> irASuper(1));
        Button btnSuper3 = findViewById(R.id.btnSuper3);
        btnSuper3.setOnClickListener(v -> irASuper(2));

    }

    private void irASuper(int i) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + supers[i]));
        startActivity(intent);
    }

    private void productosRecibidos(List<Producto> productos) {
        Producto masBarato = null;
        for (Producto p: productos) {
            if (masBarato == null || masBarato.getPrecio() > p.getPrecio()) {
                masBarato = p;
            }
        }

        switch (masBarato.getSupermercado()) {
            case "Dia":
                adLista1.add(masBarato);
                break;
            case "Carrefour":
                adLista2.add(masBarato);
                break;
            case "Alimerka":
                adLista3.add(masBarato);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemClaro) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        return super.onOptionsItemSelected(item);
    }
}


