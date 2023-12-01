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
import jakarta.validation.constraints.NotNull;
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
@Table(name="GRUPOS")
public class Grupo {
	

	@Id
	@Column(name = "GRUPO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGrupo")
    @SequenceGenerator(name = "seqGrupo", allocationSize = 1, sequenceName = "SEQ_GRUPO")
    @Builder.Default
    private Long id=0L;
	
    @NotNull
	@Column(name = "GRUPO")
    private String grupo;

    //*Aquí se borro proyección
    
    //!OneToMany's
    
    @OneToMany(                                         //*Verificado
        mappedBy = "grupoRelacionado",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<GrupoAlumno> gruposAlumnos;
}