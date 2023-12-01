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

import com.example.demo.entity.Semestre;
import com.example.demo.serviceImpl.SemestreServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_SEMESTRE)
@CrossOrigin(origins = "http://localhost:4200")

public class SemestreController {
	
	@Autowired
	private SemestreServiceImpl semestreServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Semestre>> listar() {
		try {
            List<Semestre> var = semestreServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Semestre> crear(@Valid @RequestBody Semestre semestre){
        try {
            Semestre _aut = semestreServiceImpl.create(semestre);
            return new ResponseEntity<Semestre>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/get/{id}")
	public ResponseEntity<Semestre> getSemestreById(@PathVariable("id") Long id){
		Optional<Semestre> carData = semestreServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Semestre>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Semestre> delete(@PathVariable("id") Long id){
		try {
			semestreServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Semestre semestre){
		Optional<Semestre> carData = semestreServiceImpl.read(id);
        if (carData.isPresent()) {
            Semestre dbSemestre = carData.get();
            dbSemestre.setSemestre(semestre.getSemestre());
			//!ManyToOne's
			dbSemestre.setEscuelaRelacionada(semestre.getEscuelaRelacionada());
			//!OneToMany's
			dbSemestre.setCiclos(semestre.getCiclos());
            return new ResponseEntity<Semestre>(semestreServiceImpl.update(dbSemestre), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

