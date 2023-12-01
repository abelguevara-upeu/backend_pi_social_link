package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Null;
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
@Table(name="ACTIVIDADES_ALUMNOS")
public class ActividadGrupoAlumno {
	

	@Id
	@Column(name = "ACTIVIDAD_ALUMNO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqActividad_Alumno")
    @SequenceGenerator(name = "seqActividad_Alumno", allocationSize = 1, sequenceName = "SEQ_ACTIVIDAD_ALUMNO")
    @Builder.Default
    private Long id = 0L;
    
    @Null
    @Column(name = "ASISTENCIA")
	private Integer asistencia;

    //!ManyToOne's
    
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "ACTIVIDAD_ID", nullable = false)
    private Actividad actividadRelacionada;
    
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "GRUPO_ALUMNO_ID", nullable = false)
    private GrupoAlumno grupoAlumno;

}
