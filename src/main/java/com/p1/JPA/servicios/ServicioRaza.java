package com.p1.JPA.servicios;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.p1.JPA.modelos.Raza;
import com.p1.JPA.respositorios.RazaRepositorio;


@Service
public class ServicioRaza {

    @Autowired
    private RazaRepositorio razaRep;

    public ArrayList<Raza> getRazas() {
        return (ArrayList<Raza>) razaRep.findAll();
    }

    public Raza saveRaza(Raza raza) {
        return razaRep.save(raza);
    }

    public Optional<Raza> getRazaById(Integer id) {
        return razaRep.findById(id);
    }

    public void deleteRaza(Integer id) {
        razaRep.deleteById(id);
    }
}