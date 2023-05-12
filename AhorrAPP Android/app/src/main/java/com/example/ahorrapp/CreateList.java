package com.example.ahorrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateList extends AppCompatActivity {

    private ProductosAdapter adProductos = new ProductosAdapter();
    private ProductosAdapter adLista = new ProductosAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvProductos = findViewById(R.id.rvProductos);
        RecyclerView rvLista = findViewById(R.id.rvLista);

        rvProductos.setAdapter(adProductos);
        rvLista.setAdapter(adLista);

        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(rvProductos.getContext(),
                llm1.getOrientation());
        DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(rvProductos.getContext(),
                llm1.getOrientation());
        rvProductos.setLayoutManager(llm1);
        rvProductos.addItemDecoration(dividerItemDecoration1);
        rvLista.setLayoutManager(llm2);
        rvLista.addItemDecoration(dividerItemDecoration2);

        ClienteRest cr = ClienteRest.create();
        cr.getNombresProductos().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                productosRecibidos(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.w(CreateList.this.getClass().getName(), "Error al obtener productos", t);
                Toast.makeText(CreateList.this,"No se pudieron obtener los productos", Toast.LENGTH_SHORT).show();
            }
        });

        adProductos.setOnProductoSeleccionado(this::seleccionadoEnProductos);
        adLista.setOnProductoSeleccionado(this::seleccionadoEnLista);

        Button btnGenerarRuta = findViewById(R.id.btnGenerarRuta);
        btnGenerarRuta.setOnClickListener(this::generarRuta);
    }

    private void generarRuta(View view) {
        Intent i = new Intent(this, Ruta.class);
        i.putStringArrayListExtra("lista", new ArrayList<>(adLista.getProductos()));
        startActivity(i);
    }

    private void productosRecibidos(List<String> productos) {
        for (String p: productos) {
            adProductos.add(p);
        }
    }

    private void seleccionadoEnProductos(String producto) {
        adProductos.remove(producto);
        adLista.add(producto);
    }

    private void seleccionadoEnLista(String producto) {
        adProductos.add(producto);
        adLista.remove(producto);
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