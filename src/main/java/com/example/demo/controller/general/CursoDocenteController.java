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

import com.example.demo.entity.CursoDocente;
import com.example.demo.serviceImpl.CursoDocenteServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_CURSO_DOCENTE)
@CrossOrigin(origins = "http://localhost:4200")

public class CursoDocenteController {
	
	@Autowired
	private CursoDocenteServiceImpl cursoDocenteServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<CursoDocente>> listar() {
		try {
            List<CursoDocente> var = cursoDocenteServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/save")
    public ResponseEntity<CursoDocente> crear(@Valid @RequestBody CursoDocente cursoDocente){
        try {
            CursoDocente _aut = cursoDocenteServiceImpl.create(cursoDocente);
            return new ResponseEntity<CursoDocente>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/get/{id}")
	public ResponseEntity<CursoDocente> getCursoDocenteById(@PathVariable("id") Long id){
		Optional<CursoDocente> carData = cursoDocenteServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<CursoDocente>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CursoDocente> delete(@PathVariable("id") Long id){
		try {
			cursoDocenteServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody CursoDocente cursoDocente){
		Optional<CursoDocente> carData = cursoDocenteServiceImpl.read(id);
        if (carData.isPresent()) {
            CursoDocente dbCursoDocente = carData.get();
            //!ManyToOne's
            dbCursoDocente.setCurso(cursoDocente.getCurso());
			dbCursoDocente.setDocente(cursoDocente.getDocente());
            //!OneToMany's
            dbCursoDocente.setCursoDocenteAlumnos(cursoDocente.getCursoDocenteAlumnos());
            dbCursoDocente.setProyecciones(cursoDocente.getProyecciones());
            return new ResponseEntity<CursoDocente>(cursoDocenteServiceImpl.update(dbCursoDocente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}