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

import com.example.demo.entity.ActividadGrupoAlumno;
import com.example.demo.serviceImpl.ActividadGrupoAlumnoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ACTIVIDAD_GRUPO_ALUMNO)
@CrossOrigin(origins = "http://localhost:4200")

public class ActividadGrupoAlumnoController {
	
	@Autowired
	private ActividadGrupoAlumnoServiceImpl actividadGrupoAlumnoServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<ActividadGrupoAlumno>> listar() {
		try {
			List<ActividadGrupoAlumno> car = actividadGrupoAlumnoServiceImpl.readAll();
			if (car.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				return new ResponseEntity<>(car, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<ActividadGrupoAlumno> crear(@Valid @RequestBody ActividadGrupoAlumno actividadGrupoAlumno){
        try {
            ActividadGrupoAlumno _aut = actividadGrupoAlumnoServiceImpl.create(actividadGrupoAlumno);
            return new ResponseEntity<ActividadGrupoAlumno>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ActividadGrupoAlumno> getActividadGrupoAlumnoById(@PathVariable("id") Long id){
		Optional<ActividadGrupoAlumno> carData = actividadGrupoAlumnoServiceImpl.read(id);
		if (carData.isPresent()) {
			return new ResponseEntity<ActividadGrupoAlumno>(carData.get(), HttpStatus.OK);
		} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ActividadGrupoAlumno> delete(@PathVariable("id") Long id){
		try {
			actividadGrupoAlumnoServiceImpl.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody ActividadGrupoAlumno actividadGrupoAlumno){
		Optional<ActividadGrupoAlumno> carData = actividadGrupoAlumnoServiceImpl.read(id);
		if (carData.isPresent()) {
			ActividadGrupoAlumno dbActividadGrupoAlumno = carData.get();
			dbActividadGrupoAlumno.setAsistencia(actividadGrupoAlumno.getAsistencia());
			//!ManyToOne's
            dbActividadGrupoAlumno.setActividadRelacionada(actividadGrupoAlumno.getActividadRelacionada());
			dbActividadGrupoAlumno.setGrupoAlumno(actividadGrupoAlumno.getGrupoAlumno());
			return new ResponseEntity<ActividadGrupoAlumno>(actividadGrupoAlumnoServiceImpl.update(dbActividadGrupoAlumno), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
