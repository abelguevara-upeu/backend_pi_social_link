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

import com.example.demo.entity.Alumno;
import com.example.demo.serviceImpl.AlumnoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ALUMNO)
@CrossOrigin(origins = "http://localhost:4200")

public class AlumnoController {
	
	@Autowired
	private AlumnoServiceImpl alumnoServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Alumno>> listar() {
		try {
            List<Alumno> var = alumnoServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Alumno> crear(@Valid @RequestBody Alumno alumno){
        try {
            Alumno _aut = alumnoServiceImpl.create(alumno);
            return new ResponseEntity<Alumno>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Alumno> getAlumnoById(@PathVariable("id") Long id){
		Optional<Alumno> carData = alumnoServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Alumno>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Alumno> delete(@PathVariable("id") Long id){
		try {
			alumnoServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Alumno alumno){
		Optional<Alumno> carData = alumnoServiceImpl.read(id);
        if (carData.isPresent()) {
            Alumno dbAlumno = carData.get();
            dbAlumno.setCodigo(alumno.getCodigo());
            //!OneToOne's
			dbAlumno.setPersonaAlumno(alumno.getPersonaAlumno());
            //!ManyToOne's
			dbAlumno.setFuncionAlumno(alumno.getFuncionAlumno());
            //!OneToMany's
			dbAlumno.setGruposAlumnos(alumno.getGruposAlumnos());
            dbAlumno.setCursosDocentesAlumnos(alumno.getCursosDocentesAlumnos());
            return new ResponseEntity<Alumno>(alumnoServiceImpl.update(dbAlumno), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

