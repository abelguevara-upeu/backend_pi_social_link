package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TipoProyeccion;
import com.example.demo.repository.TipoProyeccionRepository;
import com.example.demo.service.Operaciones;
@Service
public class TipoProyeccionServiceImpl implements Operaciones<TipoProyeccion>{
	
	@Autowired
	private TipoProyeccionRepository tipoRepository;

	@Override
	public TipoProyeccion create(TipoProyeccion t) {
		return tipoRepository.save(t);
	}

	@Override
	public TipoProyeccion update(TipoProyeccion t) {
		return tipoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		tipoRepository.deleteById(id);
		
	}

	@Override
	public Optional<TipoProyeccion> read(Long id) {
		return tipoRepository.findById(id);
	}

	@Override
	public List<TipoProyeccion> readAll() {
		return tipoRepository.findAll();
	}

}
