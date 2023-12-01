package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Actividad;
import com.example.demo.repository.ActividadRepository;
import com.example.demo.service.Operaciones;
@Service
public class ActividadServiceImpl implements Operaciones<Actividad>{
	
	@Autowired
	private ActividadRepository ActividadRepository;

	@Override
	public Actividad create(Actividad t) {
		
		return ActividadRepository.save(t);
	}

	@Override
	public Actividad update(Actividad t) {
		
		return ActividadRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		ActividadRepository.deleteById(id);
		
	}

	@Override
	public Optional<Actividad> read(Long id) {
		
		return ActividadRepository.findById(id);
	}

	@Override
	public List<Actividad> readAll() {
		
		return ActividadRepository.findAll();
	}

}
