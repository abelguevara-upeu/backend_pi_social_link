package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Grupo;
import com.example.demo.repository.GrupoRepository;
import com.example.demo.service.Operaciones;
@Service
public class GrupoServiceImpl implements Operaciones<Grupo>{
	
	@Autowired
	private GrupoRepository GrupoRepository;

	@Override
	public Grupo create(Grupo t) {
		return GrupoRepository.save(t);
	}

	@Override
	public Grupo update(Grupo t) {
		return GrupoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		GrupoRepository.deleteById(id);
		
	}

	@Override
	public Optional<Grupo> read(Long id) {
		return GrupoRepository.findById(id);
	}

	@Override
	public List<Grupo> readAll() {
		return GrupoRepository.findAll();
	}

}
