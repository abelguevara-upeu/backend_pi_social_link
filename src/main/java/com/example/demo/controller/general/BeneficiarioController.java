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

import com.example.demo.entity.Beneficiario;
import com.example.demo.serviceImpl.BeneficiarioServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_BENEFICIARIO)
@CrossOrigin(origins = "http://localhost:4200")

public class BeneficiarioController {
	
	@Autowired
	private BeneficiarioServiceImpl beneficiarioServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Beneficiario>> listar() {
		try {
            List<Beneficiario> var = beneficiarioServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Beneficiario> crear(@Valid @RequestBody Beneficiario beneficiario){
        try {
            Beneficiario _aut = beneficiarioServiceImpl.create(beneficiario);
            return new ResponseEntity<Beneficiario>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Beneficiario> getBeneficiarioById(@PathVariable("id") Long id){
		Optional<Beneficiario> carData = beneficiarioServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Beneficiario>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Beneficiario> delete(@PathVariable("id") Long id){
		try {
			beneficiarioServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Beneficiario beneficiario){
		Optional<Beneficiario> carData = beneficiarioServiceImpl.read(id);
        if (carData.isPresent()) {
            Beneficiario dbBeneficiario = carData.get();
            dbBeneficiario.setNombreBeneficiario(beneficiario.getNombreBeneficiario());
			dbBeneficiario.setApellidoPaterno(beneficiario.getApellidoPaterno());
			dbBeneficiario.setApellidoMaterno(beneficiario.getApellidoMaterno());
			dbBeneficiario.setDni(beneficiario.getDni());
			dbBeneficiario.setTelefono(beneficiario.getTelefono());
			dbBeneficiario.setDireccion(beneficiario.getDireccion());
            //!OneToMany's
			dbBeneficiario.setActividadesBeneficiario(beneficiario.getActividadesBeneficiario());
            return new ResponseEntity<Beneficiario>(beneficiarioServiceImpl.update(dbBeneficiario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

