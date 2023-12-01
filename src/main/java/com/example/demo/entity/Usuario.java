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
@Table(name="USUARIOS")
public class Usuario {
	

	@Id
	@Column(name = "USUARIO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
    @SequenceGenerator(name = "seqUsuario", allocationSize = 1, sequenceName = "SEQ_USUARIO")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "USUARIO")
    private String usuario;
	
	@Column(name = "PASSWORD")
    private String password;

    //!OneToOne's
    
    @OneToOne                                           //*Verificado
    @JoinColumn(name = "PERSONA_ID")
    private Persona personaUsuario;

    //!OneToMany's
    
    @OneToMany(                                         //*Verificado
        mappedBy = "usuario",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<RolUsuario> rolesUsuarios;
    
    @OneToMany(                                         //*Verificado
        mappedBy = "usuarioRelacionado",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<AccesoUsuario> accesosUsuarios;
    
}
