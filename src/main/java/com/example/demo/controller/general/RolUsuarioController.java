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

import com.example.demo.entity.RolUsuario;
import com.example.demo.serviceImpl.RolUsuarioServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ROL_USUARIO)
@CrossOrigin(origins = "http://localhost:4200")

public class RolUsuarioController {
	
	@Autowired
	private RolUsuarioServiceImpl rolUsuarioServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<RolUsuario>> listar() {
		try {
            List<RolUsuario> var = rolUsuarioServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<RolUsuario> crear(@Valid @RequestBody RolUsuario rolUsuario){
        try {
            RolUsuario _aut = rolUsuarioServiceImpl.create(rolUsuario);
            return new ResponseEntity<RolUsuario>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<RolUsuario> getRolUsuarioById(@PathVariable("id") Long id){
		Optional<RolUsuario> carData = rolUsuarioServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<RolUsuario>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<RolUsuario> delete(@PathVariable("id") Long id){
		try {
			rolUsuarioServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody RolUsuario rolUsuario){
		Optional<RolUsuario> carData = rolUsuarioServiceImpl.read(id);
        if (carData.isPresent()) {
            RolUsuario dbRolUsuario = carData.get();
            //!ManyToOne's
            dbRolUsuario.setRol(rolUsuario.getRol());
            dbRolUsuario.setUsuario(rolUsuario.getUsuario());
            return new ResponseEntity<RolUsuario>(rolUsuarioServiceImpl.update(dbRolUsuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

