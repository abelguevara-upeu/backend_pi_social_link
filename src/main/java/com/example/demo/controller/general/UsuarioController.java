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

import com.example.demo.entity.Usuario;
import com.example.demo.serviceImpl.UsuarioServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_USUARIO)
@CrossOrigin(origins = "http://localhost:4200")

public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Usuario>> listar() {
		try {
            List<Usuario> var = usuarioServiceImpl.readAll();
                if (var.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(var, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario){
        try {
            Usuario _aut = usuarioServiceImpl.create(usuario);
            return new ResponseEntity<Usuario>(_aut, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id){
		Optional<Usuario> carData = usuarioServiceImpl.read(id);
        if (carData.isPresent()) {
            return new ResponseEntity<Usuario>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable("id") Long id){
		try {
			usuarioServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario){
		Optional<Usuario> carData = usuarioServiceImpl.read(id);
        if (carData.isPresent()) {
            Usuario dbUsuario = carData.get();
            dbUsuario.setUsuario(usuario.getUsuario());
            dbUsuario.setPassword(usuario.getPassword());
            //!OneToOne's
            dbUsuario.setPersonaUsuario(usuario.getPersonaUsuario());
            //!OneToMany's
            dbUsuario.setRolesUsuarios(usuario.getRolesUsuarios());
            dbUsuario.setAccesosUsuarios(usuario.getAccesosUsuarios());
            return new ResponseEntity<Usuario>(usuarioServiceImpl.update(dbUsuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

