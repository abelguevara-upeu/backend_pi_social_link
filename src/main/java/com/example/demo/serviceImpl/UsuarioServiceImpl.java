package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.Operaciones;
@Service
public class UsuarioServiceImpl implements Operaciones<Usuario>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario create(Usuario t) {
		return usuarioRepository.save(t);
	}

	@Override
	public Usuario update(Usuario t) {
		return usuarioRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
		
	}

	@Override
	public Optional<Usuario> read(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public List<Usuario> readAll() {
		return usuarioRepository.findAll();
	}

}
