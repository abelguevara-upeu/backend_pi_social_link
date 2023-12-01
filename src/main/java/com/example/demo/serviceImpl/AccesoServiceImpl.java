package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Acceso;
import com.example.demo.repository.AccesoRepository;
import com.example.demo.service.Operaciones;
@Service
public class AccesoServiceImpl implements Operaciones<Acceso>{
	
	@Autowired
	private AccesoRepository accesoRepository;

	@Override
	public Acceso create(Acceso t) {
		
		return accesoRepository.save(t);
	}

	@Override
	public Acceso update(Acceso t) {
		
		return accesoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		accesoRepository.deleteById(id);
		
	}

	@Override
	public Optional<Acceso> read(Long id) {
		
		return accesoRepository.findById(id);
	}

	@Override
	public List<Acceso> readAll() {
		
		return accesoRepository.findAll();
	}

}
