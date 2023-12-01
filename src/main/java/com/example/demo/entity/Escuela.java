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
@Table(name="ESCUELAS")
public class Escuela {
	

	@Id
	@Column(name = "ESCUELA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEscuela")
    @SequenceGenerator(name = "seqEscuela", allocationSize = 1, sequenceName = "SEQ_ESCUELA")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "ESCUELA")
    private String escuela;

    //!ManyToOne's
    
    @ManyToOne                                          //*Verificado
    @JoinColumn(name = "FACULTAD_ID", nullable = false)
    private Facultad facultad;

    //!OneToMany's
    //*Aqui se borro proyección

    @OneToMany(                                         //*Verificado
        mappedBy = "escuelaRelacionada",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Semestre> semestres;

}
