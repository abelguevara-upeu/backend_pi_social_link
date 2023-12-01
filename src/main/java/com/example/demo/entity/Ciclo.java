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
@Table(name="CICLOS")
public class Ciclo {
	

	@Id
	@Column(name = "CICLO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCiclo")
    @SequenceGenerator(name = "seqCiclo", allocationSize = 1, sequenceName = "SEQ_CICLO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "CICLO")
    private String ciclo;

	//!ManyToOne's
	
	@ManyToOne											//*Verificado
	@JoinColumn(name = "SEMESTRE_ID", nullable = false)
	private Semestre semestreRelacionado;

	//!OneToMany's

	@OneToMany(                                         //*Verificado
        mappedBy = "ciclo",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Curso> cursos;

	//*Se borró proyección

	//*Se borró alumno

}
