package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cargo;
import com.example.demo.repository.CargoRepository;
import com.example.demo.service.Operaciones;
@Service
public class CargoServiceImpl implements Operaciones<Cargo>{
	
	@Autowired
	private CargoRepository CargoRepository;

	@Override
	public Cargo create(Cargo t) {
		
		return CargoRepository.save(t);
	}

	@Override
	public Cargo update(Cargo t) {
		
		return CargoRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		CargoRepository.deleteById(id);
		
	}

	@Override
	public Optional<Cargo> read(Long id) {
		
		return CargoRepository.findById(id);
	}

	@Override
	public List<Cargo> readAll() {
		
		return CargoRepository.findAll();
	}

}
