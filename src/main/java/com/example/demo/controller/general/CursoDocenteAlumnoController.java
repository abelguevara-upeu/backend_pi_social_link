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

import com.example.demo.entity.CursoDocenteAlumno;
import com.example.demo.serviceImpl.CursoDocenteAlumnoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_CURSO_DOCENTE_ALUMNO)
@CrossOrigin(origins = "http://localhost:4200")

public class CursoDocenteAlumnoController {
	
	@Autowired
	private CursoDocenteAlumnoServiceImpl cursoDocenteAlumnoServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<CursoDocenteAlumno>> listar() {
		try {
            List<CursoDocenteAlumno> var = cursoDocenteAlumnoServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/save")
    public ResponseEntity<CursoDocenteAlumno> crear(@Valid @RequestBody CursoDocenteAlumno cursoDocenteAlumno){
        try {
            CursoDocenteAlumno _aut = cursoDocenteAlumnoServiceImpl.create(cursoDocenteAlumno);
            return new ResponseEntity<CursoDocenteAlumno>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/get/{id}")
	public ResponseEntity<CursoDocenteAlumno> getCursoDocenteAlumnoById(@PathVariable("id") Long id){
		Optional<CursoDocenteAlumno> carData = cursoDocenteAlumnoServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<CursoDocenteAlumno>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CursoDocenteAlumno> delete(@PathVariable("id") Long id){
		try {
			cursoDocenteAlumnoServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody CursoDocenteAlumno cursoDocenteAlumno){
		Optional<CursoDocenteAlumno> carData = cursoDocenteAlumnoServiceImpl.read(id);
        if (carData.isPresent()) {
            CursoDocenteAlumno dbCursoDocenteAlumno = carData.get();
            //!ManyToOne's
            dbCursoDocenteAlumno.setAlumno(cursoDocenteAlumno.getAlumno());
            dbCursoDocenteAlumno.setCursoDocenteAsignado(cursoDocenteAlumno.getCursoDocenteAsignado());
            return new ResponseEntity<CursoDocenteAlumno>(cursoDocenteAlumnoServiceImpl.update(dbCursoDocenteAlumno), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

