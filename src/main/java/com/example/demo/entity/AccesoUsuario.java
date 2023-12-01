package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="ACCESOS_USUARIOS")
public class AccesoUsuario {
	@Id
	@Column(name = "ACCESO_USUARIO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAcceso_Usuario")
    @SequenceGenerator(name = "seqAcceso_Usuario", allocationSize = 1, sequenceName = "SEQ_Acceso_Usuario")
    @Builder.Default
    private Long id = 0L;

    //!ManyToOne 's
    
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Usuario usuarioRelacionado;
    
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "ACCESO_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Acceso acceso;
}