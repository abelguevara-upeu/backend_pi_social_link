package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EstadoProyeccion;
@Repository
public interface EstadoProyeccionRepository extends JpaRepository<EstadoProyeccion, Long>{

}
