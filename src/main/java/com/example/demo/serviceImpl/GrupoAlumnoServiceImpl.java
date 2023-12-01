package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.GrupoAlumno;
import com.example.demo.repository.GrupoAlumnoRepository;
import com.example.demo.service.Operaciones;
@Service
public class GrupoAlumnoServiceImpl implements Operaciones<GrupoAlumno>{
	
	@Autowired
	private GrupoAlumnoRepository grupoAlumnoRepository;

	@Override
	public GrupoAlumno create(GrupoAlumno t) {
		return grupoAlumnoRepository.save(t);
	}

	@Override
	public GrupoAlumno update(GrupoAlumno t) {
		return grupoAlumnoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		grupoAlumnoRepository.deleteById(id);
		
	}

	@Override
	public Optional<GrupoAlumno> read(Long id) {
		return grupoAlumnoRepository.findById(id);
	}

	@Override
	public List<GrupoAlumno> readAll() {
		return grupoAlumnoRepository.findAll();
	}

}
