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

import com.example.demo.entity.TipoProyeccion;
import com.example.demo.serviceImpl.TipoProyeccionServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_TIPO_PROYECCION)
@CrossOrigin(origins = "http://localhost:4200")

public class TipoProyeccionController {
	
	@Autowired
	private TipoProyeccionServiceImpl tipoProyeccionServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<TipoProyeccion>> listar() {
		try {
            List<TipoProyeccion> var = tipoProyeccionServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<TipoProyeccion> crear(@Valid @RequestBody TipoProyeccion tipoProyeccion){
        try {
            TipoProyeccion _aut = tipoProyeccionServiceImpl.create(tipoProyeccion);
            return new ResponseEntity<TipoProyeccion>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<TipoProyeccion> getTipoProyeccionById(@PathVariable("id") Long id){
		Optional<TipoProyeccion> carData = tipoProyeccionServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<TipoProyeccion>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TipoProyeccion> delete(@PathVariable("id") Long id){
		try {
			tipoProyeccionServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody TipoProyeccion tipoProyeccion){
		Optional<TipoProyeccion> carData = tipoProyeccionServiceImpl.read(id);
        if (carData.isPresent()) {
            TipoProyeccion dbTipoProyeccion = carData.get();
            dbTipoProyeccion.setTipoProyeccion(tipoProyeccion.getTipoProyeccion());
            //!OneToMany's
            dbTipoProyeccion.setProyecciones(tipoProyeccion.getProyecciones());
            return new ResponseEntity<TipoProyeccion>(tipoProyeccionServiceImpl.update(dbTipoProyeccion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

