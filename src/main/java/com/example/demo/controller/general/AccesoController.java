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

import com.example.demo.entity.Acceso;
import com.example.demo.serviceImpl.AccesoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ACCESO)
@CrossOrigin(origins = "http://localhost:4200")

public class AccesoController {
	
	@Autowired
	private AccesoServiceImpl accesoServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Acceso>> listar() {
		try {
            List<Acceso> var = accesoServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Acceso> crear(@Valid @RequestBody Acceso acceso){
        try {
            Acceso _aut = accesoServiceImpl.create(acceso);
            return new ResponseEntity<Acceso>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Acceso> getAccesoById(@PathVariable("id") Long id){
		Optional<Acceso> carData = accesoServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Acceso>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Acceso> delete(@PathVariable("id") Long id){
		try {
			accesoServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Acceso acceso){
		Optional<Acceso> carData = accesoServiceImpl.read(id);
        if (carData.isPresent()) {
            Acceso dbAcceso = carData.get();
            dbAcceso.setAcceso(acceso.getAcceso());
            dbAcceso.setImg(acceso.getImg());
            dbAcceso.setUrl(acceso.getUrl());
            dbAcceso.setEstado(acceso.getEstado());
            //!ManyToOne's
            dbAcceso.setSubAcceso(acceso.getSubAcceso());
            //!OneToMany's
            dbAcceso.setAccesosUsuarios(acceso.getAccesosUsuarios());
            dbAcceso.setRolesAccesos(acceso.getRolesAccesos());
            return new ResponseEntity<Acceso>(accesoServiceImpl.update(dbAcceso), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

