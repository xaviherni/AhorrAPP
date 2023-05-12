package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Producto_repositorio extends JpaRepository<Producto,Long>{

	List<Producto> findByNombreOrderByPrecioAsc(String nombre);

	@Query("SELECT DISTINCT p.nombre FROM Producto p")
	List<String> findDistinctByNombre();
	
}
