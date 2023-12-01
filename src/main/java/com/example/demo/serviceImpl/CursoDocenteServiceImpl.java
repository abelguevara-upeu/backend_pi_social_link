package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CursoDocente;
import com.example.demo.repository.CursoDocenteRepository;
import com.example.demo.service.Operaciones;
@Service
public class CursoDocenteServiceImpl implements Operaciones<CursoDocente>{
	
	@Autowired
	private CursoDocenteRepository cursoDocenteRepository;

	@Override
	public CursoDocente create(CursoDocente t) {
		
		return cursoDocenteRepository.save(t);
	}

	@Override
	public CursoDocente update(CursoDocente t) {
		
		return cursoDocenteRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		cursoDocenteRepository.deleteById(id);
		
	}

	@Override
	public Optional<CursoDocente> read(Long id) {
		
		return cursoDocenteRepository.findById(id);
	}

	@Override
	public List<CursoDocente> readAll() {
		
		return cursoDocenteRepository.findAll();
	}

}
