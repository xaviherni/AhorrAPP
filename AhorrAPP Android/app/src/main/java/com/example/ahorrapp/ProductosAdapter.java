package com.example.ahorrapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder> {

    public interface OnProductoSeleccionado {
        void seleccionado(String producto);
    }

    private List<String> productos;
    private OnProductoSeleccionado onSeleccionado;

    public ProductosAdapter() {
        this.productos = new ArrayList<>();
    }

    public List<String> getProductos() {
        return productos;
    }

    public void add(String producto) {
        productos.add(producto);
        notifyDataSetChanged();
    }

    public void setOnProductoSeleccionado(OnProductoSeleccionado onSeleccionado) {
        this.onSeleccionado = onSeleccionado;
    }

    public void remove(String producto) {
        productos.remove(producto);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto, parent, false);
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

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProducto = itemView.findViewById(R.id.tvProducto);
        }

        public void bind(String producto) {
            tvProducto.setText(producto);
            itemView.setOnClickListener(v-> {
                if (onSeleccionado != null) onSeleccionado.seleccionado(producto);
            });
        }

    }
}
