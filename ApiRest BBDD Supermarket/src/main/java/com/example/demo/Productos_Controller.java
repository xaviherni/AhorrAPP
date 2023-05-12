package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Productos_Controller {

	@Autowired
	private Producto_repositorio repos;
	
	@GetMapping(
			value = "/productos/{nombre}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Producto> buscar_nombre(@PathVariable String nombre){
		
		return repos.findByNombreOrderByPrecioAsc(nombre);
		
	}
	

	@GetMapping(
			value = "/productos",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> buscar_distintos(){
		
		return repos.findDistinctByNombre();
		
	}

}
