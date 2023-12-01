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
@Table(name="ROLES")
public class Rol {
	

	@Id
	@Column(name = "ROL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRol")
    @SequenceGenerator(name = "seqRol", allocationSize = 1, sequenceName = "SEQ_ROL")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "ROL")
    private String rol;

    //!OneToMany's
    @OneToMany(                                         //*Verificado
        mappedBy = "rol",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<RolUsuario> rolesUsuarios;

    @OneToMany(                                         //*Verificado
        mappedBy = "rolAsociado",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<RolAcceso> rolesAccesos;
}
