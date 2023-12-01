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

import com.example.demo.entity.RolAcceso;
import com.example.demo.serviceImpl.RolAccesoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ROL_ACCESO)
@CrossOrigin(origins = "http://localhost:4200")

public class RolAccesoController {
	
	@Autowired
	private RolAccesoServiceImpl rolAccesoServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<RolAcceso>> listar() {
		try {
            List<RolAcceso> var = rolAccesoServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<RolAcceso> crear(@Valid @RequestBody RolAcceso rolAcceso){
        try {
            RolAcceso _aut = rolAccesoServiceImpl.create(rolAcceso);
            return new ResponseEntity<RolAcceso>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<RolAcceso> getRolAccesoById(@PathVariable("id") Long id){
		Optional<RolAcceso> carData = rolAccesoServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<RolAcceso>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<RolAcceso> delete(@PathVariable("id") Long id){
		try {
			rolAccesoServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody RolAcceso rolAcceso){
		Optional<RolAcceso> carData = rolAccesoServiceImpl.read(id);
        if (carData.isPresent()) {
            RolAcceso dbRolAcceso = carData.get();
            //!ManyToOne's
            dbRolAcceso.setRolAsociado(rolAcceso.getRolAsociado());
            dbRolAcceso.setAccesoAsociado(rolAcceso.getAccesoAsociado());
            return new ResponseEntity<RolAcceso>(rolAccesoServiceImpl.update(dbRolAcceso), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

