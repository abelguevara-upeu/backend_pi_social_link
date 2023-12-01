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
@Table(name="CURSOS")
public class Curso {
	

	@Id
	@Column(name = "CURSO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCurso")
    @SequenceGenerator(name = "seqCurso", allocationSize = 1, sequenceName = "SEQ_CURSO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "CURSO")
    private String curso;
    
    //!ManyToOne's
	
	@ManyToOne											//*Verificado
	@JoinColumn(name = "CICLO_ID", nullable = false)
	private Ciclo ciclo;
    //*Aquí se borró semestre

    //!OneToMany's

    @OneToMany(                                         //*Verificado
        mappedBy = "curso",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<CursoDocente> cursosDocentes;
    
}
