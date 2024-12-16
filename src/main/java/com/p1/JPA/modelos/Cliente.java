package com.p1.JPA.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @Column(unique = true, name="dni_cliente",
           updatable=false)
    @NonNull
    private String dni;

    @Column(name="nombre",
            nullable=false)
    @NonNull
    private String nombre;

    @Column(name="apellido",
            nullable=false)
    @NonNull
    private String apellido;

    @Column(name="direccion",
            nullable=false)
    @NonNull
    private String direccion;

    @Column(name="telefono",
            nullable=false)
    @NonNull
    private String telefono;
}
