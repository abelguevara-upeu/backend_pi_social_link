package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Rol;
import com.example.demo.repository.RolRepository;
import com.example.demo.service.Operaciones;
@Service
public class RolServiceImpl implements Operaciones<Rol>{
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public Rol create(Rol t) {
		return rolRepository.save(t);
	}

	@Override
	public Rol update(Rol t) {
		return rolRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		rolRepository.deleteById(id);
		
	}

	@Override
	public Optional<Rol> read(Long id) {
		return rolRepository.findById(id);
	}

	@Override
	public List<Rol> readAll() {
		return rolRepository.findAll();
	}

}
