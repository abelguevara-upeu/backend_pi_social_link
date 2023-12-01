package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EstadoProyeccion;
import com.example.demo.repository.EstadoProyeccionRepository;
import com.example.demo.service.Operaciones;
@Service
public class EstadoProyeccionServiceImpl implements Operaciones<EstadoProyeccion>{
	
	@Autowired
	private EstadoProyeccionRepository EstadoRepository;

	@Override
	public EstadoProyeccion create(EstadoProyeccion t) {
		
		return EstadoRepository.save(t);
	}

	@Override
	public EstadoProyeccion update(EstadoProyeccion t) {
		
		return EstadoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		EstadoRepository.deleteById(id);
		
	}

	@Override
	public Optional<EstadoProyeccion> read(Long id) {
		
		return EstadoRepository.findById(id);
	}

	@Override
	public List<EstadoProyeccion> readAll() {
		
		return EstadoRepository.findAll();
	}

}
