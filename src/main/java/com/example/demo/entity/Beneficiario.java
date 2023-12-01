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
@Table(name="BENEFICIARIOS")
public class Beneficiario {
	

	@Id
	@Column(name = "BENEFICIARIO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqBeneficiario")
    @SequenceGenerator(name = "seqBeneficiario", allocationSize = 1, sequenceName = "SEQ_BENEFICIARIO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE_BENEFICIARIO")
    private String nombreBeneficiario;
	
	@Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
	
	@Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
	
	@Column(name = "DNI")
    private String dni;
	
	@Column(name = "TELEFONO")
    private String telefono;
	
	@Column(name = "DIRECCION")
    private String direccion;

    //!OneToMany's
    
    @OneToMany(                                         //*Verificado
        mappedBy = "beneficiario",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<ActividadBeneficiario> actividadesBeneficiario;

}
