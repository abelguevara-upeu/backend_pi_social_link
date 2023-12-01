package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AccesoUsuario;
import com.example.demo.repository.AccesoUsuarioRepository;
import com.example.demo.service.Operaciones;
@Service
public class AccesoUsuarioServiceImpl implements Operaciones<AccesoUsuario>{
	
	@Autowired
	private AccesoUsuarioRepository accesoUsuarioRepository;

	@Override
	public AccesoUsuario create(AccesoUsuario t) {
		
		return accesoUsuarioRepository.save(t);
	}

	@Override
	public AccesoUsuario update(AccesoUsuario t) {
		
		return accesoUsuarioRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		accesoUsuarioRepository.deleteById(id);
		
	}

	@Override
	public Optional<AccesoUsuario> read(Long id) {
		
		return accesoUsuarioRepository.findById(id);
	}

	@Override
	public List<AccesoUsuario> readAll() {
		
		return accesoUsuarioRepository.findAll();
	}

}
