package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RolUsuario;
import com.example.demo.repository.RolUsuarioRepository;
import com.example.demo.service.Operaciones;
@Service
public class RolUsuarioServiceImpl implements Operaciones<RolUsuario>{
	
	@Autowired
	private RolUsuarioRepository rolUsuarioRepository;

	@Override
	public RolUsuario create(RolUsuario t) {
		return rolUsuarioRepository.save(t);
	}

	@Override
	public RolUsuario update(RolUsuario t) {
		return rolUsuarioRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		rolUsuarioRepository.deleteById(id);
		
	}

	@Override
	public Optional<RolUsuario> read(Long id) {
		return rolUsuarioRepository.findById(id);
	}

	@Override
	public List<RolUsuario> readAll() {
		return rolUsuarioRepository.findAll();
	}

}
