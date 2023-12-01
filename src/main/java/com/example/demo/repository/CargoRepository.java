package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cargo;
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{

}
