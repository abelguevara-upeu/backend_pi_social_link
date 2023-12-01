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
@Table(name="SEMESTRES")
public class Semestre {
	

	@Id
	@Column(name = "SEMESTRE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSemestre")
    @SequenceGenerator(name = "seqSemestre", allocationSize = 1, sequenceName = "SEQ_SEMESTRE")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "SEMESTRE")
    private String semestre;

    //!ManyToOne's
    
    @ManyToOne                                          //*Verificado
    @JoinColumn(name = "ESCUELA_ID", nullable = false)
    private Escuela escuelaRelacionada;

    //!OneToMany's
    
    @OneToMany(                                         //*Verificado
        mappedBy = "semestreRelacionado",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Ciclo> ciclos;

    //*Aquí borro curso-docente

}
