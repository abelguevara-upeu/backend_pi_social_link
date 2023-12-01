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

import com.example.demo.entity.Ciclo;
import com.example.demo.serviceImpl.CicloServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_CICLO)
@CrossOrigin(origins = "http://localhost:4200")

public class CicloController {
	
	@Autowired
	private CicloServiceImpl cicloServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Ciclo>> listar() {
		try {
            List<Ciclo> var = cicloServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Ciclo> crear(@Valid @RequestBody Ciclo ciclo){
        try {
            Ciclo _aut = cicloServiceImpl.create(ciclo);
            return new ResponseEntity<Ciclo>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Ciclo> getCicloById(@PathVariable("id") Long id){
		Optional<Ciclo> carData = cicloServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Ciclo>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Ciclo> delete(@PathVariable("id") Long id){
		try {
			cicloServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Ciclo ciclo){
		Optional<Ciclo> carData = cicloServiceImpl.read(id);
        if (carData.isPresent()) {
            Ciclo dbCiclo = carData.get();
            dbCiclo.setCiclo(ciclo.getCiclo());
            //!ManyToOne's
			dbCiclo.setSemestreRelacionado(ciclo.getSemestreRelacionado());
            //!OneToMany's
            dbCiclo.setCursos(ciclo.getCursos());
            return new ResponseEntity<Ciclo>(cicloServiceImpl.update(dbCiclo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

