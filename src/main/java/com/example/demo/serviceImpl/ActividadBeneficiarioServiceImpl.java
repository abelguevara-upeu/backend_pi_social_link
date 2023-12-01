package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ActividadBeneficiario;
import com.example.demo.repository.ActividadBeneficiarioRepository;
import com.example.demo.service.Operaciones;
@Service
public class ActividadBeneficiarioServiceImpl implements Operaciones<ActividadBeneficiario>{
	
	@Autowired
	private ActividadBeneficiarioRepository actividadBeneficiarioRepository;

	@Override
	public ActividadBeneficiario create(ActividadBeneficiario t) {
		
		return actividadBeneficiarioRepository.save(t);
	}

	@Override
	public ActividadBeneficiario update(ActividadBeneficiario t) {
		
		return actividadBeneficiarioRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		
		actividadBeneficiarioRepository.deleteById(id);
		
	}

	@Override
	public Optional<ActividadBeneficiario> read(Long id) {
		
		return actividadBeneficiarioRepository.findById(id);
	}

	@Override
	public List<ActividadBeneficiario> readAll() {
		
		return actividadBeneficiarioRepository.findAll();
	}

}
