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

import com.example.demo.entity.EstadoProyeccion;
import com.example.demo.serviceImpl.EstadoProyeccionServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ESTADO_PROYECCION)
@CrossOrigin(origins = "http://localhost:4200")

public class EstadoProyeccionController {
	
	@Autowired
	private EstadoProyeccionServiceImpl estadoProyeccionServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<EstadoProyeccion>> listar() {
		try {
            List<EstadoProyeccion> var = estadoProyeccionServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<EstadoProyeccion> crear(@Valid @RequestBody EstadoProyeccion estadoProyeccion){
        try {
            EstadoProyeccion _aut = estadoProyeccionServiceImpl.create(estadoProyeccion);
            return new ResponseEntity<EstadoProyeccion>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<EstadoProyeccion> getEstadoProyeccionById(@PathVariable("id") Long id){
		Optional<EstadoProyeccion> carData = estadoProyeccionServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<EstadoProyeccion>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EstadoProyeccion> delete(@PathVariable("id") Long id){
		try {
			estadoProyeccionServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody EstadoProyeccion estadoProyeccion){
		Optional<EstadoProyeccion> carData = estadoProyeccionServiceImpl.read(id);
        if (carData.isPresent()) {
            EstadoProyeccion dbEstadoProyeccion = carData.get();
            dbEstadoProyeccion.setEstadoProyeccion(estadoProyeccion.getEstadoProyeccion());
            //!OneToMany's
			dbEstadoProyeccion.setProyecciones(estadoProyeccion.getProyecciones());
            return new ResponseEntity<EstadoProyeccion>(estadoProyeccionServiceImpl.update(dbEstadoProyeccion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

