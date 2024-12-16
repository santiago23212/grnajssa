package com.p1.JPA.servicios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p1.JPA.modelos.Cliente;
import com.p1.JPA.respositorios.ClienteRepositorio;

@Service
public class ServicioCliente {

	@Autowired
	ClienteRepositorio clienterep;
	
	public ArrayList<Cliente> GetClientes(){
		return (ArrayList<Cliente>) clienterep.findAll();		
	}
	public Cliente SaveCliente(Cliente cliente) {
		return clienterep.save(cliente);
	}
	public Optional<Cliente> GetClienteId(String Dni) {
		return clienterep.findById(Dni);
	}
	public void DelCliente(String Dni) {
		clienterep.deleteById(Dni);
	}
}

