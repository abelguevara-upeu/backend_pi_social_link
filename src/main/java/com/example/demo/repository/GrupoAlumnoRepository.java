package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GrupoAlumno;
@Repository
public interface GrupoAlumnoRepository extends JpaRepository<GrupoAlumno, Long>{

}
