package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.service.Operaciones;
@Service
public class DocenteServiceImpl implements Operaciones<Docente>{
	
	@Autowired
	private DocenteRepository DocenteRepository;

	@Override
	public Docente create(Docente t) {
		
		return DocenteRepository.save(t);
	}

	@Override
	public Docente update(Docente t) {
		
		return DocenteRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		DocenteRepository.deleteById(id);
		
	}

	@Override
	public Optional<Docente> read(Long id) {
		
		return DocenteRepository.findById(id);
	}

	@Override
	public List<Docente> readAll() {
		
		return DocenteRepository.findAll();
	}

}
