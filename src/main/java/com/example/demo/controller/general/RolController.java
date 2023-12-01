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

import com.example.demo.entity.Rol;
import com.example.demo.serviceImpl.RolServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ROL)
@CrossOrigin(origins = "http://localhost:4200")

public class RolController {
	
	@Autowired
	private RolServiceImpl rolServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Rol>> listar() {
		try {
            List<Rol> var = rolServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Rol> crear(@Valid @RequestBody Rol rol){
        try {
            Rol _aut = rolServiceImpl.create(rol);
            return new ResponseEntity<Rol>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Rol> getRolById(@PathVariable("id") Long id){
		Optional<Rol> carData = rolServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Rol>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Rol> delete(@PathVariable("id") Long id){
		try {
			rolServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Rol rol){
		Optional<Rol> carData = rolServiceImpl.read(id);
        if (carData.isPresent()) {
            Rol dbRol = carData.get();
            dbRol.setRol(rol.getRol());
			//!OneToMany's
			dbRol.setRolesUsuarios(rol.getRolesUsuarios());
			dbRol.setRolesAccesos(rol.getRolesAccesos());
            return new ResponseEntity<Rol>(rolServiceImpl.update(dbRol), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

