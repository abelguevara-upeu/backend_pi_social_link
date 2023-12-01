package com.example.demo.controller.general;

import static com.example.demo.commons.GlobalConstans.*;

import java.util.List;

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

import com.example.demo.entity.Proyeccion;
import com.example.demo.service.ProyeccionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_PROYECCION)
@CrossOrigin(origins = "http://localhost:4200")

public class ProyeccionController {
	
	@Autowired
	private ProyeccionService proyeccionService;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Proyeccion>> listar() {
		try {
            List<Proyeccion> var = proyeccionService.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Proyeccion> crear(@Valid @RequestBody Proyeccion proyeccion){
        try {
            Proyeccion _aut = proyeccionService.create(proyeccion);
            return new ResponseEntity<Proyeccion>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Proyeccion> listar2(@PathVariable("id") long id) {
		Proyeccion lbr = proyeccionService.read(id);
		if (lbr!=null) {
			return new ResponseEntity<>(lbr, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Proyeccion> delete(@PathVariable("id") Long id){
		try {
			proyeccionService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<Proyeccion> updateTutorial(@PathVariable("id") long id, @RequestBody Proyeccion l) {
	    Proyeccion dbproyeccion = proyeccionService.read(id);
	    if (dbproyeccion!=null) {
	    	dbproyeccion.setId(l.getId());
			dbproyeccion.setNombreProyeccion(l.getNombreProyeccion());
			dbproyeccion.setDescripcion(l.getDescripcion());
			dbproyeccion.setEjeEstrategico(l.getEjeEstrategico());
			dbproyeccion.setObjetivo(l.getObjetivo());
			dbproyeccion.setZonaIntervencion(l.getZonaIntervencion());
			dbproyeccion.setArchivoAdjunto(l.getArchivoAdjunto());
			dbproyeccion.setFechaInicio(l.getFechaInicio());
			dbproyeccion.setFechaTermino(l.getFechaTermino());
			//!ManyToOne's
			dbproyeccion.setEstadoProyeccion(l.getEstadoProyeccion());
			dbproyeccion.setTipoProyeccion(l.getTipoProyeccion());
			dbproyeccion.setCursoDocente(l.getCursoDocente());
			//!OneToMany's
			dbproyeccion.setActividades(l.getActividades());
	      return new ResponseEntity<>(proyeccionService.create(dbproyeccion), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
