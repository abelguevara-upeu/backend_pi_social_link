package com.example.demo.entity;

import java.sql.Blob;
import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="ACTIVIDADES")
public class Actividad {
	

	@Id
	@Column(name = "ACTIVIDAD_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqActividad")
    @SequenceGenerator(name = "seqActividad", allocationSize = 1, sequenceName = "SEQ_ACTIVIDAD")
    @Builder.Default
    private Long id=0L;
	
	@NotNull
	@Column(name = "NOMBRE_ACTIVIDAD")
    private String nombreActividad;
	
	@NotNull
	@Column(name = "FECHA_INICIO")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy", timezone = "UTC")
    private Date fechaInicio;
	
	@NotNull
	@Column(name = "FECHA_TERMINO")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy", timezone = "UTC")
    private Date fechaTermino;
	
	@Column(name = "INFORME")
	private Blob informe;

	//!ManyToOne's
	
	@ManyToOne											//*Verificado
	@JoinColumn(name = "PROYECCION_ID", nullable = false)
	private Proyeccion proyeccionReferente;

	//!OneToMany's
	
	@OneToMany(											//*Verificado
		mappedBy = "actividadVinculada",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
    @JsonIgnore
    private Set<ActividadBeneficiario> actividadesBeneficiario;
	
	@OneToMany(											//*Verificado
		mappedBy = "actividadRelacionada",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
    @JsonIgnore
    private Set<ActividadGrupoAlumno> actividadesGruposAlumnos;
}