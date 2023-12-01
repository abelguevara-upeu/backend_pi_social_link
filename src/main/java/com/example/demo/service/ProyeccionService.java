package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Proyeccion;

public interface ProyeccionService {
	Proyeccion create(Proyeccion proy);
	Proyeccion update(Proyeccion proy);
	void delete(Long id);
	Proyeccion read(Long id);
	List<Proyeccion> readAll();
	void reiniciarSecuencia(String tabla, String secuencia, Long idABorrar);

}

