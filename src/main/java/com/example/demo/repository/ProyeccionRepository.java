package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Proyeccion;
@Repository
public interface ProyeccionRepository extends JpaRepository<Proyeccion, Long>{
    
}
