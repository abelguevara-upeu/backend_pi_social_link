package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FuncionAlumno;
import com.example.demo.repository.FuncionAlumnoRepository;
import com.example.demo.service.Operaciones;
@Service
public class FuncionAlumnoServiceImpl implements Operaciones<FuncionAlumno>{
	
	@Autowired
	private FuncionAlumnoRepository funcionAlumnoRepository;

	@Override
	public FuncionAlumno create(FuncionAlumno t) {
		return funcionAlumnoRepository.save(t);
	}

	@Override
	public FuncionAlumno update(FuncionAlumno t) {
		return funcionAlumnoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		funcionAlumnoRepository.deleteById(id);
		
	}

	@Override
	public Optional<FuncionAlumno> read(Long id) {
		return funcionAlumnoRepository.findById(id);
	}

	@Override
	public List<FuncionAlumno> readAll() {
		return funcionAlumnoRepository.findAll();
	}

}
