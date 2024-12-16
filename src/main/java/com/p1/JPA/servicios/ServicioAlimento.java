package com.p1.JPA.servicios;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.p1.JPA.modelos.Alimento;
import com.p1.JPA.respositorios.AlimentoRepositorio;

@Service
public class ServicioAlimento {

    @Autowired
    private AlimentoRepositorio alimentoRep;

    public ArrayList<Alimento> getAlimentos() {
        return (ArrayList<Alimento>) alimentoRep.findAll();
    }

    public Alimento saveAlimento(Alimento alimento) {
        return alimentoRep.save(alimento);
    }

    public Optional<Alimento> getAlimentoById(Integer id) {
        return alimentoRep.findById(id);
    }

    public void deleteAlimento(Integer id) {
        alimentoRep.deleteById(id);
    }
}
