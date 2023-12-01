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

import com.example.demo.entity.Curso;
import com.example.demo.serviceImpl.CursoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_CURSO)
@CrossOrigin(origins = "http://localhost:4200")

public class CursoController {
	
	@Autowired
	private CursoServiceImpl cursoServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Curso>> listar() {
		try {
            List<Curso> var = cursoServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Curso> crear(@Valid @RequestBody Curso curso){
        try {
            Curso _aut = cursoServiceImpl.create(curso);
            return new ResponseEntity<Curso>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Curso> getCursoById(@PathVariable("id") Long id){
		Optional<Curso> carData = cursoServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Curso>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Curso> delete(@PathVariable("id") Long id){
		try {
			cursoServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Curso curso){
		Optional<Curso> carData = cursoServiceImpl.read(id);
        if (carData.isPresent()) {
            Curso dbCurso = carData.get();
            dbCurso.setCurso(curso.getCurso());
            //!ManyToOne's
            dbCurso.setCiclo(curso.getCiclo());
            //!OneToMany's
			dbCurso.setCursosDocentes(curso.getCursosDocentes());
            
            return new ResponseEntity<Curso>(cursoServiceImpl.update(dbCurso), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

