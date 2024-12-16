package com.p1.JPA.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p1.JPA.modelos.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,String>{
 //proporciona los metodos del crud generando las consulas de SQL
}
