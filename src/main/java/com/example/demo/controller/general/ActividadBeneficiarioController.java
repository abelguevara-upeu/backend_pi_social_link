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

import com.example.demo.entity.ActividadBeneficiario;
import com.example.demo.serviceImpl.ActividadBeneficiarioServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_ACTIVIDAD_BENEFICIARIO)
@CrossOrigin(origins = "http://localhost:4200")

public class ActividadBeneficiarioController {
	
	@Autowired
	private ActividadBeneficiarioServiceImpl actvidadBeneficiarioServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<ActividadBeneficiario>> listar() {
		try {
            List<ActividadBeneficiario> var = actvidadBeneficiarioServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<ActividadBeneficiario> crear(@Valid @RequestBody ActividadBeneficiario actvidadBeneficiario){
        try {
            ActividadBeneficiario _aut = actvidadBeneficiarioServiceImpl.create(actvidadBeneficiario);
            return new ResponseEntity<ActividadBeneficiario>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ActividadBeneficiario> getActividadBeneficiarioById(@PathVariable("id") Long id){
		Optional<ActividadBeneficiario> carData = actvidadBeneficiarioServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<ActividadBeneficiario>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ActividadBeneficiario> delete(@PathVariable("id") Long id){
		try {
			actvidadBeneficiarioServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody ActividadBeneficiario actividadBeneficiario){
		Optional<ActividadBeneficiario> carData = actvidadBeneficiarioServiceImpl.read(id);
        if (carData.isPresent()) {
            ActividadBeneficiario dbActividadBeneficiario = carData.get();
            dbActividadBeneficiario.setAsistencia(actividadBeneficiario.getAsistencia());
            //!ManyToOne's
            dbActividadBeneficiario.setBeneficiario(actividadBeneficiario.getBeneficiario());
            dbActividadBeneficiario.setActividadVinculada(actividadBeneficiario.getActividadVinculada());
            return new ResponseEntity<ActividadBeneficiario>(actvidadBeneficiarioServiceImpl.update(dbActividadBeneficiario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

