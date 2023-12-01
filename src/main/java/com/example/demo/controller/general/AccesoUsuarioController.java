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

import com.example.demo.entity.AccesoUsuario;
import com.example.demo.serviceImpl.AccesoUsuarioServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ACCESO_USUARIO)
@CrossOrigin(origins = "http://localhost:4200")

public class AccesoUsuarioController {
	
	@Autowired
	private AccesoUsuarioServiceImpl accesoUsuarioServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<AccesoUsuario>> listar() {
		try {
            List<AccesoUsuario> var = accesoUsuarioServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<AccesoUsuario> crear(@Valid @RequestBody AccesoUsuario accesoUsuario){
        try {
            AccesoUsuario _aut = accesoUsuarioServiceImpl.create(accesoUsuario);
            return new ResponseEntity<AccesoUsuario>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<AccesoUsuario> getAccesoUsuarioById(@PathVariable("id") Long id){
		Optional<AccesoUsuario> carData = accesoUsuarioServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<AccesoUsuario>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AccesoUsuario> delete(@PathVariable("id") Long id){
		try {
			accesoUsuarioServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody AccesoUsuario accesoUsuario){
		Optional<AccesoUsuario> carData = accesoUsuarioServiceImpl.read(id);
        if (carData.isPresent()) {
            AccesoUsuario dbAccesoUsuario = carData.get();
            //!ManyToOne's
            dbAccesoUsuario.setUsuarioRelacionado(accesoUsuario.getUsuarioRelacionado());
			dbAccesoUsuario.setAcceso(accesoUsuario.getAcceso());
            return new ResponseEntity<AccesoUsuario>(accesoUsuarioServiceImpl.update(dbAccesoUsuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

