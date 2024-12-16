package com.p1.JPA.respositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p1.JPA.modelos.Cliente;
import com.p1.JPA.modelos.Porcino;

public interface PorcinoRepositorio extends JpaRepository<Porcino,Integer>{
	 List<Porcino> findByCliente(Cliente cliente);
}
