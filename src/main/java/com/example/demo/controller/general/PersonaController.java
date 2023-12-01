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

import com.example.demo.entity.Persona;
import com.example.demo.serviceImpl.PersonaServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_PERSONA)
@CrossOrigin(origins = "http://localhost:4200")

public class PersonaController {
	
	@Autowired
	private PersonaServiceImpl personaServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Persona>> listar() {
		try {
            List<Persona> var = personaServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Persona> crear(@Valid @RequestBody Persona persona){
        try {
            Persona _aut = personaServiceImpl.create(persona);
            return new ResponseEntity<Persona>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Persona> getPersonaById(@PathVariable("id") Long id){
		Optional<Persona> carData = personaServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Persona>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Persona> delete(@PathVariable("id") Long id){
		try {
			personaServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Persona persona){
		Optional<Persona> carData = personaServiceImpl.read(id);
        if (carData.isPresent()) {
            Persona dbPersona = carData.get();
            dbPersona.setNombre(persona.getNombre());
			dbPersona.setApellidoPaterno(persona.getApellidoPaterno());
			dbPersona.setApellidoMaterno(persona.getApellidoMaterno());
			dbPersona.setDni(persona.getDni());
			//!OneToOne's
			dbPersona.setDocente(persona.getDocente());
			dbPersona.setAlumno(persona.getAlumno());
			dbPersona.setUsuario(persona.getUsuario());
            return new ResponseEntity<Persona>(personaServiceImpl.update(dbPersona), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

