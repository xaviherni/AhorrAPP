package com.example.ahorrapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClienteRest {

    @GET("productos/{nombre}")
    Call<List<Producto>> getProductosPorNombre(@Path("nombre") String nombre);

    @GET("productos")
    Call<List<String>> getNombresProductos();

    public static ClienteRest create() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8080")
                .build();

        return retrofit.create(ClienteRest.class);
    }
}
