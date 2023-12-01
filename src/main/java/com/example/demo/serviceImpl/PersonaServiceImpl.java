package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.service.Operaciones;
@Service
public class PersonaServiceImpl implements Operaciones<Persona>{
	
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Persona create(Persona t) {
		return personaRepository.save(t);
	}

	@Override
	public Persona update(Persona t) {
		return personaRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		personaRepository.deleteById(id);
		
	}

	@Override
	public Optional<Persona> read(Long id) {
		return personaRepository.findById(id);
	}

	@Override
	public List<Persona> readAll() {
		return personaRepository.findAll();
	}

}
