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

import com.example.demo.entity.FuncionAlumno;
import com.example.demo.serviceImpl.FuncionAlumnoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_FUNCION_ALUMNO)
@CrossOrigin(origins = "http://localhost:4200")

public class FuncionAlumnoController {
	
	@Autowired
	private FuncionAlumnoServiceImpl funcionAlumnoServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<FuncionAlumno>> listar() {
		try {
            List<FuncionAlumno> var = funcionAlumnoServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<FuncionAlumno> crear(@Valid @RequestBody FuncionAlumno funcionAlumno){
        try {
            FuncionAlumno _aut = funcionAlumnoServiceImpl.create(funcionAlumno);
            return new ResponseEntity<FuncionAlumno>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<FuncionAlumno> getFuncionAlumnoById(@PathVariable("id") Long id){
		Optional<FuncionAlumno> carData = funcionAlumnoServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<FuncionAlumno>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<FuncionAlumno> delete(@PathVariable("id") Long id){
		try {
			funcionAlumnoServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody FuncionAlumno funcionAlumno){
		Optional<FuncionAlumno> carData = funcionAlumnoServiceImpl.read(id);
        if (carData.isPresent()) {
            FuncionAlumno dbFuncionAlumno = carData.get();
            dbFuncionAlumno.setFuncionAlumno(funcionAlumno.getFuncionAlumno());
            //!OneToMany's
			dbFuncionAlumno.setAlumnos(funcionAlumno.getAlumnos());
            return new ResponseEntity<FuncionAlumno>(funcionAlumnoServiceImpl.update(dbFuncionAlumno), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

