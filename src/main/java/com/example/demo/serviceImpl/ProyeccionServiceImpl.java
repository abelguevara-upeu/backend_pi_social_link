package com.example.demo.serviceImpl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Proyeccion;
import com.example.demo.repository.ProyeccionRepository;
import com.example.demo.service.ProyeccionService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ProyeccionServiceImpl implements ProyeccionService{
	
	@Autowired
	private ProyeccionRepository proyeccionRepository;
	private EntityManager entityManager;

	@Override
	public Proyeccion create(Proyeccion t) {
		return proyeccionRepository.save(t);
	}

	@Override
	public Proyeccion update(Proyeccion t) {
		return proyeccionRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		proyeccionRepository.deleteById(id);
		
	}

	@Override
	public Proyeccion read(Long id) {
		return proyeccionRepository.findById(id).get();
	}

	@Override
	public List<Proyeccion> readAll() {
		List<Proyeccion> libros = proyeccionRepository.findAll();
        libros.sort(Comparator.comparing(Proyeccion::getId));
        return libros;
	}

	@Override
    @Transactional
    public void reiniciarSecuencia(String tabla, String secuencia, Long idABorrar) {
        entityManager.createNativeQuery(
                "BEGIN " +
                        "CONTROL.reiniciar_secuencia(:tabla, :secuencia, :idABorrar); " +
                        "END;")
                .setParameter("tabla", tabla)
                .setParameter("secuencia", secuencia)
                .setParameter("idABorrar", idABorrar)
                .executeUpdate();
    }
	
}
