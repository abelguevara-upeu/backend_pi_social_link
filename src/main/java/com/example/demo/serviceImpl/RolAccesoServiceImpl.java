package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RolAcceso;
import com.example.demo.repository.RolAccesoRepository;
import com.example.demo.service.Operaciones;
@Service
public class RolAccesoServiceImpl implements Operaciones<RolAcceso>{
	
	@Autowired
	private RolAccesoRepository rolAccesoRepository;

	@Override
	public RolAcceso create(RolAcceso t) {
		return rolAccesoRepository.save(t);
	}

	@Override
	public RolAcceso update(RolAcceso t) {
		return rolAccesoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		rolAccesoRepository.deleteById(id);
		
	}

	@Override
	public Optional<RolAcceso> read(Long id) {
		return rolAccesoRepository.findById(id);
	}

	@Override
	public List<RolAcceso> readAll() {
		return rolAccesoRepository.findAll();
	}

}
