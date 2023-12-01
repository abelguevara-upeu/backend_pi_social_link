package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Escuela;
import com.example.demo.repository.EscuelaRepository;
import com.example.demo.service.Operaciones;
@Service
public class EscuelaServiceImpl implements Operaciones<Escuela>{
	
	@Autowired
	private EscuelaRepository EscuelaRepository;

	@Override
	public Escuela create(Escuela t) {
		
		return EscuelaRepository.save(t);
	}

	@Override
	public Escuela update(Escuela t) {
		
		return EscuelaRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		EscuelaRepository.deleteById(id);
		
	}

	@Override
	public Optional<Escuela> read(Long id) {
		
		return EscuelaRepository.findById(id);
	}

	@Override
	public List<Escuela> readAll() {
		
		return EscuelaRepository.findAll();
	}

}
