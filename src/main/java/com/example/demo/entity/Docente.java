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
@Table(name="DOCENTES")
public class Docente {
	

	@Id
	@Column(name = "DOCENTE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDocente")
    @SequenceGenerator(name = "seqDocente", allocationSize = 1, sequenceName = "SEQ_DOCENTE")
    @Builder.Default
    private Long id=0L;

    //!OneToOne's
    
    @OneToOne                                           //*Verificado
    @JoinColumn(name = "PERSONA_ID")
    private Persona personaDocente;

    //!ManyToOne's
    
    @ManyToOne                                          //*Verificado
    @JoinColumn(name = "CARGO_ID", nullable = true)
    private Cargo cargo;

    //!OneToMany's
    
    @OneToMany(                                         //*Verificado
        mappedBy = "docente",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<CursoDocente> cursosDocentes;
    
}
