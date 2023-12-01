package com.example.demo.controller.general;

import static com.example.demo.commons.GlobalConstans.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Actividad;
import com.example.demo.serviceImpl.ActividadServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ACTIVIDAD)
@CrossOrigin(origins = "http://localhost:4200")

public class ActividadController {
	
	@Autowired
	private ActividadServiceImpl actividadServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Actividad>> listar() {
		try {
			List<Actividad> car = actividadServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				return new ResponseEntity<>(car, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Actividad> crear(@Valid @RequestBody Actividad actividad){
        try {
            Actividad _aut = actividadServiceImpl.create(actividad);
            return new ResponseEntity<Actividad>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Actividad> getActividadById(@PathVariable("id") Long id){
		Optional<Actividad> carData = actividadServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<Actividad>(carData.get(), HttpStatus.OK);
		} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Actividad> delete(@PathVariable("id") Long id){
		try {
			actividadServiceImpl.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Actividad actividad){
		Optional<Actividad> carData = actividadServiceImpl.read(id);
		if (carData.isPresent()) {
			Actividad dbActividad = carData.get();
			dbActividad.setNombreActividad(actividad.getNombreActividad());
			dbActividad.setFechaInicio(actividad.getFechaInicio());
			dbActividad.setFechaTermino(actividad.getFechaTermino());
			dbActividad.setInforme(actividad.getInforme());
			dbActividad.setProyeccionReferente(actividad.getProyeccionReferente());
			//!OneToMany's
			dbActividad.setActividadesBeneficiario(actividad.getActividadesBeneficiario());
			dbActividad.setActividadesGruposAlumnos(actividad.getActividadesGruposAlumnos());
			return new ResponseEntity<Actividad>(actividadServiceImpl.update(dbActividad), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
