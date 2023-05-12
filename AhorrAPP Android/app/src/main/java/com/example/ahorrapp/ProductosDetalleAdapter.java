package com.example.ahorrapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ProductosDetalleAdapter extends RecyclerView.Adapter<ProductosDetalleAdapter.ProductoViewHolder> {


    private List<Producto> productos;

    public ProductosDetalleAdapter() {
        this.productos = new ArrayList<>();
    }

    public void add(Producto producto) {
        productos.add(producto);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_detalle, parent, false);
        return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.bind(productos.get(position));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProducto;
        private TextView tvPrecio;


        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProducto = itemView.findViewById(R.id.tvProducto);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }

        public void bind(Producto producto) {
            tvProducto.setText(producto.getNombre());
            NumberFormat format = NumberFormat.getCurrencyInstance();
            format.setMaximumFractionDigits(2);
            format.setCurrency(Currency.getInstance("EUR"));
            tvPrecio.setText(format.format(producto.getPrecio()));
        }

    }
}
