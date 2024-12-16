package com.p1.JPA.modelos;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name="porcino")
public class Porcino {

    @Id
    @SequenceGenerator(name="porcino_sequence",
    sequenceName="porcino_sequence",
    allocationSize=2)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
    generator="porcino_sequence")
    @Column(name="id_porcino",
    updatable=false)
    private Integer id;

    @Column(name="edad",
            nullable=false)
    @NonNull
    private Integer edad;

    @Column(name="peso",
            nullable=false)
    @NonNull
    private Float peso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_raza",
                nullable=false)
    @NonNull
    private Raza raza;

    @ManyToOne
    @JoinColumn(name="dni_cliente",
                nullable=false)
    @NonNull
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, 
               cascade = CascadeType.ALL, 
               mappedBy = "porcino")
    @JsonManagedReference
    private Set<Alimento> alimentos = new HashSet<>();

    public void addAlimento(Alimento alimento) {
        this.alimentos.add(alimento);
    }
}
