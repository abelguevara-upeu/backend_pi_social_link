package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Ciclo;
import com.example.demo.repository.CicloRepository;
import com.example.demo.service.Operaciones;
@Service
public class CicloServiceImpl implements Operaciones<Ciclo>{
	
	@Autowired
	private CicloRepository CicloRepository;

	@Override
	public Ciclo create(Ciclo t) {
		
		return CicloRepository.save(t);
	}

	@Override
	public Ciclo update(Ciclo t) {
		
		return CicloRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		CicloRepository.deleteById(id);
		
	}

	@Override
	public Optional<Ciclo> read(Long id) {
		
		return CicloRepository.findById(id);
	}

	@Override
	public List<Ciclo> readAll() {
		
		return CicloRepository.findAll();
	}

}
