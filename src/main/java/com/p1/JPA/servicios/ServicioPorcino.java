package com.p1.JPA.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p1.JPA.modelos.Cliente;
import com.p1.JPA.modelos.Porcino;
import com.p1.JPA.respositorios.PorcinoRepositorio;

@Service
public class ServicioPorcino {

    @Autowired
    private PorcinoRepositorio porcinoRep;

    public ArrayList<Porcino> getPorcinos() {
        return (ArrayList<Porcino>) porcinoRep.findAll();
    }

    public Porcino savePorcino(Porcino porcino) {
        return porcinoRep.save(porcino);
    }

    public Optional<Porcino> getPorcinoById(Integer id) {
        return porcinoRep.findById(id);
    }

    public void deletePorcino(Integer id) {
        porcinoRep.deleteById(id);
    }

	public List<Porcino> GetMascotaCliente(Cliente cliente) {
		return porcinoRep.findByCliente(cliente);
	}
}
