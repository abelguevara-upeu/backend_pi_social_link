package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ActividadGrupoAlumno;
import com.example.demo.repository.ActividadGrupoAlumnoRepository;
import com.example.demo.service.Operaciones;
@Service
public class ActividadGrupoAlumnoServiceImpl implements Operaciones<ActividadGrupoAlumno>{
	
	@Autowired
	private ActividadGrupoAlumnoRepository actividadGrupoAlumnoRepository;

	@Override
	public ActividadGrupoAlumno create(ActividadGrupoAlumno t) {
		
		return actividadGrupoAlumnoRepository.save(t);
	}

	@Override
	public ActividadGrupoAlumno update(ActividadGrupoAlumno t) {
		
		return actividadGrupoAlumnoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		actividadGrupoAlumnoRepository.deleteById(id);
		
	}

	@Override
	public Optional<ActividadGrupoAlumno> read(Long id) {
		
		return actividadGrupoAlumnoRepository.findById(id);
	}

	@Override
	public List<ActividadGrupoAlumno> readAll() {
		
		return actividadGrupoAlumnoRepository.findAll();
	}

}
