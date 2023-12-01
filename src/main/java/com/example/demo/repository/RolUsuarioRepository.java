package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.RolUsuario;
@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long>{

}
