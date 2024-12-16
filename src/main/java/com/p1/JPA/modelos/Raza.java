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
@Table(name="raza")
public class Raza {

    @Id
    @SequenceGenerator(name="raza_sequence",
    sequenceName="raza_sequence",
    allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
    generator="raza_sequence")
    @Column(name="id_raza",
    updatable=false)
    private Integer id;

    @Column(name="nombre_raza",
            nullable=false)
    @NonNull
    private String nombre;
}