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

import com.example.demo.entity.Facultad;
import com.example.demo.serviceImpl.FacultadServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_FACULTAD)
@CrossOrigin(origins = "http://localhost:4200")

public class FacultadController {
	
	@Autowired
	private FacultadServiceImpl facultadServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Facultad>> listar() {
		try {
            List<Facultad> var = facultadServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Facultad> crear(@Valid @RequestBody Facultad facultad){
        try {
            Facultad _aut = facultadServiceImpl.create(facultad);
            return new ResponseEntity<Facultad>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Facultad> getFacultadById(@PathVariable("id") Long id){
		Optional<Facultad> carData = facultadServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Facultad>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Facultad> delete(@PathVariable("id") Long id){
		try {
			facultadServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Facultad facultad){
		Optional<Facultad> carData = facultadServiceImpl.read(id);
        if (carData.isPresent()) {
            Facultad dbFacultad = carData.get();
            dbFacultad.setFacultad(facultad.getFacultad());
            //!OneToManys'
			dbFacultad.setEscuelas(facultad.getEscuelas());
            return new ResponseEntity<Facultad>(facultadServiceImpl.update(dbFacultad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

