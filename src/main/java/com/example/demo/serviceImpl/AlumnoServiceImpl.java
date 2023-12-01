package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alumno;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.service.Operaciones;
@Service
public class AlumnoServiceImpl implements Operaciones<Alumno>{
	
	@Autowired
	private AlumnoRepository AlumnoRepository;

	@Override
	public Alumno create(Alumno t) {
		
		return AlumnoRepository.save(t);
	}

	@Override
	public Alumno update(Alumno t) {
		
		return AlumnoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		AlumnoRepository.deleteById(id);
		
	}

	@Override
	public Optional<Alumno> read(Long id) {
		
		return AlumnoRepository.findById(id);
	}

	@Override
	public List<Alumno> readAll() {
		
		return AlumnoRepository.findAll();
	}

}
