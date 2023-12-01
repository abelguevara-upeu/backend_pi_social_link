package com.example.demo.entity;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name="FACULTADES")
public class Facultad {
	

	@Id
	@Column(name = "FACULTAD_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqFacultad")
    @SequenceGenerator(name = "seqFacultad", allocationSize = 1, sequenceName = "SEQ_FACULTAD")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "FACULTAD")
    private String facultad;

    //!OneToMany's
    
    @OneToMany(                                         //*Verificado
        mappedBy = "facultad",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Escuela> escuelas;
	
}
