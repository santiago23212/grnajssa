package com.p1.JPA.modelos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name="alimento")
public class Alimento {

    @Id
    @SequenceGenerator(name="alimento_sequence",
    sequenceName="alimento_sequence",
    allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
    generator="alimento_sequence")
    @Column(name="id_alimento",
    updatable=false)
    private Integer id;

    @Column(name="descripcion",
            nullable=false)
    @NonNull
    private String descripcion;

    @Column(name="dosis",
            nullable=false)
    @NonNull
    private String dosis;

    @ManyToOne(fetch = FetchType.LAZY, 
               optional = false)
    @JoinColumn(name = "id_porcino", 
                nullable = false)
    @NonNull
    @JsonManagedReference
    //@JsonBackReference
    private Porcino porcino;
}