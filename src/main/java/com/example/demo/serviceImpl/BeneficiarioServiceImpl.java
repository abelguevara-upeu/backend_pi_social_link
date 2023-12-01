package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Beneficiario;
import com.example.demo.repository.BeneficiarioRepository;
import com.example.demo.service.Operaciones;
@Service
public class BeneficiarioServiceImpl implements Operaciones<Beneficiario>{
	
	@Autowired
	private BeneficiarioRepository BeneficiarioRepository;

	@Override
	public Beneficiario create(Beneficiario t) {
		
		return BeneficiarioRepository.save(t);
	}

	@Override
	public Beneficiario update(Beneficiario t) {
		
		return BeneficiarioRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		BeneficiarioRepository.deleteById(id);
		
	}

	@Override
	public Optional<Beneficiario> read(Long id) {
		
		return BeneficiarioRepository.findById(id);
	}

	@Override
	public List<Beneficiario> readAll() {
		
		return BeneficiarioRepository.findAll();
	}

}
