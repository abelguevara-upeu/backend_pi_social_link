package com.example.demo.entity;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="CARGOS")
public class Cargo {
	

	@Id
	@Column(name = "CARGO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCargo")
    @SequenceGenerator(name = "seqCargo", allocationSize = 1, sequenceName = "SEQ_CARGO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE_CARGO")
    private String nombreCargo;
    
    //!OneToMany's

    @OneToMany(                                         //*Verificado
        mappedBy = "cargo",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Docente> docentes;
}
