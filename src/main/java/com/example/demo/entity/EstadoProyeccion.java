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
@Table(name="ESTADOS_PROYECCION")
public class EstadoProyeccion {


	@Id
	@Column(name = "ESTADO_PROYECCION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEstado_Proyeccion")
    @SequenceGenerator(name = "seqEstado_Proyeccion", allocationSize = 1, sequenceName = "SEQ_ESTADO_PROYECCION")
    @Builder.Default
    private Long id=0L;
	
	@NotNull
	@Column(name = "ESTADO_PROYECCION")
    private String estadoProyeccion;

	//!OneToMany's
	
	@OneToMany(											//*Verificado
		mappedBy = "estadoProyeccion",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JsonIgnore
	private Set<Proyeccion> proyecciones;

}