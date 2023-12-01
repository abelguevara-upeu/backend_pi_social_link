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

import com.example.demo.entity.Docente;
import com.example.demo.serviceImpl.DocenteServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_DOCENTE)
@CrossOrigin(origins = "http://localhost:4200")

public class DocenteController {
	
	@Autowired
	private DocenteServiceImpl docenteServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Docente>> listar() {
		try {
            List<Docente> var = docenteServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Docente> crear(@Valid @RequestBody Docente docente){
        try {
            Docente _aut = docenteServiceImpl.create(docente);
            return new ResponseEntity<Docente>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Docente> getDocenteById(@PathVariable("id") Long id){
		Optional<Docente> carData = docenteServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Docente>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Docente> delete(@PathVariable("id") Long id){
		try {
			docenteServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Docente docente){
		Optional<Docente> carData = docenteServiceImpl.read(id);
        if (carData.isPresent()) {
            Docente dbDocente = carData.get();
            //!OneToOne's
            dbDocente.setPersonaDocente(docente.getPersonaDocente());
            //!ManyToOne's
			dbDocente.setCargo(docente.getCargo());
            //!OneToMany's
			dbDocente.setCursosDocentes(docente.getCursosDocentes());
            return new ResponseEntity<Docente>(docenteServiceImpl.update(dbDocente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

