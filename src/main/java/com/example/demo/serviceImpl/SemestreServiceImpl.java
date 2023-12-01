package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Semestre;
import com.example.demo.repository.SemestreRepository;
import com.example.demo.service.Operaciones;
@Service
public class SemestreServiceImpl implements Operaciones<Semestre>{
	
	@Autowired
	private SemestreRepository SemestreRepository;

	@Override
	public Semestre create(Semestre t) {
		return SemestreRepository.save(t);
	}

	@Override
	public Semestre update(Semestre t) {
		return SemestreRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		SemestreRepository.deleteById(id);
		
	}

	@Override
	public Optional<Semestre> read(Long id) {
		return SemestreRepository.findById(id);
	}

	@Override
	public List<Semestre> readAll() {
		return SemestreRepository.findAll();
	}

}
