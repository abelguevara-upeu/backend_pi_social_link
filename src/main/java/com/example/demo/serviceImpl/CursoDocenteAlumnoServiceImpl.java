package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CursoDocenteAlumno;
import com.example.demo.repository.CursoDocenteAlumnoRepository;
import com.example.demo.service.Operaciones;
@Service
public class CursoDocenteAlumnoServiceImpl implements Operaciones<CursoDocenteAlumno>{
	
	@Autowired
	private CursoDocenteAlumnoRepository cursoDocenteAlumnoRepository;

	@Override
	public CursoDocenteAlumno create(CursoDocenteAlumno t) {
		return cursoDocenteAlumnoRepository.save(t);
	}

	@Override
	public CursoDocenteAlumno update(CursoDocenteAlumno t) {
		return cursoDocenteAlumnoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		cursoDocenteAlumnoRepository.deleteById(id);
		
	}

	@Override
	public Optional<CursoDocenteAlumno> read(Long id) {
		return cursoDocenteAlumnoRepository.findById(id);
	}

	@Override
	public List<CursoDocenteAlumno> readAll() {
		return cursoDocenteAlumnoRepository.findAll();
	}

}
