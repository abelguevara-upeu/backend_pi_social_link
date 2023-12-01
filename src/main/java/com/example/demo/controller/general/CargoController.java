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

import com.example.demo.entity.Cargo;
import com.example.demo.serviceImpl.CargoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_CARGO)
@CrossOrigin(origins = "http://localhost:4200")

public class CargoController {
	
	@Autowired
	private CargoServiceImpl cargoServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Cargo>> listar() {
		try {
            List<Cargo> var = cargoServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Cargo> crear(@Valid @RequestBody Cargo cargo){
        try {
            Cargo _aut = cargoServiceImpl.create(cargo);
            return new ResponseEntity<Cargo>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Cargo> getCargoById(@PathVariable("id") Long id){
		Optional<Cargo> carData = cargoServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Cargo>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Cargo> delete(@PathVariable("id") Long id){
		try {
			cargoServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Cargo cargo){
		Optional<Cargo> carData = cargoServiceImpl.read(id);
        if (carData.isPresent()) {
            Cargo dbCargo = carData.get();
            dbCargo.setNombreCargo(cargo.getNombreCargo());
            //!OneToMany's
			dbCargo.setDocentes(cargo.getDocentes());
            return new ResponseEntity<Cargo>(cargoServiceImpl.update(dbCargo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

