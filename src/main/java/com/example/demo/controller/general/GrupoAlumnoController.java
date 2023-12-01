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

import com.example.demo.entity.GrupoAlumno;
import com.example.demo.serviceImpl.GrupoAlumnoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_GRUPO_ALUMNO)
@CrossOrigin(origins = "http://localhost:4200")

public class GrupoAlumnoController {
	
	@Autowired
	private GrupoAlumnoServiceImpl grupoAlumnoServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<GrupoAlumno>> listar() {
		try {
            List<GrupoAlumno> var = grupoAlumnoServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<GrupoAlumno> crear(@Valid @RequestBody GrupoAlumno grupoAlumno){
        try {
            GrupoAlumno _aut = grupoAlumnoServiceImpl.create(grupoAlumno);
            return new ResponseEntity<GrupoAlumno>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<GrupoAlumno> getGrupoAlumnoById(@PathVariable("id") Long id){
		Optional<GrupoAlumno> carData = grupoAlumnoServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<GrupoAlumno>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GrupoAlumno> delete(@PathVariable("id") Long id){
		try {
			grupoAlumnoServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody GrupoAlumno grupoAlumno){
		Optional<GrupoAlumno> carData = grupoAlumnoServiceImpl.read(id);
        if (carData.isPresent()) {
            GrupoAlumno dbGrupoAlumno = carData.get();
			//!ManyToOne's
			dbGrupoAlumno.setGrupoRelacionado(grupoAlumno.getGrupoRelacionado());
            dbGrupoAlumno.setAlumnoRelacionado(grupoAlumno.getAlumnoRelacionado());
			//!OneToMany's
			dbGrupoAlumno.setActividadesGruposAlumnos(grupoAlumno.getActividadesGruposAlumnos());
            return new ResponseEntity<GrupoAlumno>(grupoAlumnoServiceImpl.update(dbGrupoAlumno), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

