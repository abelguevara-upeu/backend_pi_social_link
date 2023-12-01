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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name="ALUMNOS")
public class Alumno {
	

	@Id
	@Column(name = "ALUMNO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAlumno")
    @SequenceGenerator(name = "seqAlumno", allocationSize = 1, sequenceName = "SEQ_ALUMNO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "CODIGO")
    private String codigo;
    
    //!OneToOne's
    
    @OneToOne                                           //*Verificado
    @JoinColumn(name = "PERSONA_ID")
    private Persona personaAlumno;

    //!ManyToOne's

    //*Aquí se borró ciclo

    @ManyToOne                                          //*Verificado
    @JoinColumn(name = "FUNCION_ALUMNO_ID", nullable = true)
    private FuncionAlumno funcionAlumno;

    //!OneToMany's
    
    @OneToMany(                                         //*Verificado
        mappedBy = "alumnoRelacionado",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<GrupoAlumno> gruposAlumnos;

    @OneToMany(                                         //*Verificado
        mappedBy = "alumno",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<CursoDocenteAlumno> cursosDocentesAlumnos;

}
