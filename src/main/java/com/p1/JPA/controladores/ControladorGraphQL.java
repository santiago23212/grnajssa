package com.p1.JPA.controladores;

import com.p1.JPA.modelos.Alimento;
import com.p1.JPA.modelos.Cliente;
import com.p1.JPA.modelos.Porcino;
import com.p1.JPA.modelos.Raza;
import com.p1.JPA.servicios.ServicioAlimento;
import com.p1.JPA.servicios.ServicioCliente;
import com.p1.JPA.servicios.ServicioPorcino;
import com.p1.JPA.servicios.ServicioRaza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ControladorGraphQL {

    @Autowired
    private ServicioPorcino servicioPorcino;

    @Autowired
    private ServicioCliente servicioCliente;

    @Autowired
    private ServicioRaza servicioRaza;

    @Autowired
    private ServicioAlimento servicioAlimento;

    // ------------------------------
    // QUERIES
    // ------------------------------

    @QueryMapping
    public List<Cliente> getClientes() {
        return servicioCliente.GetClientes();
    }

    @QueryMapping
    public Cliente getCliente(@Argument String dni) {
        return servicioCliente.GetClienteId(dni).orElse(null);
    }

    @QueryMapping
    public List<Porcino> getPorcinos() {
        return servicioPorcino.getPorcinos();
    }

    @QueryMapping
    public Porcino getPorcino(@Argument Integer id) {
        return servicioPorcino.getPorcinoById(id).orElse(null);
    }

    @QueryMapping
    public List<Alimento> getAlimentos() {
        return servicioAlimento.getAlimentos();
    }

    @QueryMapping
    public Alimento getAlimento(@Argument Integer id) {
        return servicioAlimento.getAlimentoById(id).orElse(null);
    }

    @QueryMapping
    public List<Raza> getRazas() {
        return servicioRaza.getRazas();
    }

    @QueryMapping
    public Raza getRaza(@Argument Integer id) {
        return servicioRaza.getRazaById(id).orElse(null);
    }

    // ------------------------------
    // MUTATIONS - CREAR
    // ------------------------------

    @MutationMapping
    public Cliente createCliente(
            @Argument String dni,
            @Argument String nombre,
            @Argument String apellido,
            @Argument String direccion,
            @Argument String telefono) {
        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        return servicioCliente.SaveCliente(cliente);
    }

    @MutationMapping
    public Porcino createPorcino(
            @Argument Integer edad,
            @Argument Float peso,
            @Argument Integer razaId,
            @Argument String clienteDni) {
        Raza raza = servicioRaza.getRazaById(razaId)
                .orElseThrow(() -> new RuntimeException("Raza no encontrada"));
        Cliente cliente = servicioCliente.GetClienteId(clienteDni)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Porcino porcino = new Porcino();
        porcino.setEdad(edad);
        porcino.setPeso(peso);
        porcino.setRaza(raza);
        porcino.setCliente(cliente);
        return servicioPorcino.savePorcino(porcino);
    }

    @MutationMapping
    public Alimento createAlimento(
            @Argument String descripcion,
            @Argument String dosis,
            @Argument Integer porcinoId) {
        Porcino porcino = servicioPorcino.getPorcinoById(porcinoId)
                .orElseThrow(() -> new RuntimeException("Porcino no encontrado"));

        Alimento alimento = new Alimento();
        alimento.setDescripcion(descripcion);
        alimento.setDosis(dosis);
        alimento.setPorcino(porcino);
        return servicioAlimento.saveAlimento(alimento);
    }

    @MutationMapping
    public Raza createRaza(@Argument String nombre) {
        Raza raza = new Raza();
        raza.setNombre(nombre);
        return servicioRaza.saveRaza(raza);
    }

    // ------------------------------
    // MUTATIONS - ACTUALIZAR
    // ------------------------------

    @MutationMapping
    public Cliente updateCliente(
            @Argument String dni,
            @Argument String nombre,
            @Argument String apellido,
            @Argument String direccion,
            @Argument String telefono) {
        Cliente cliente = servicioCliente.GetClienteId(dni)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if (nombre != null) cliente.setNombre(nombre);
        if (apellido != null) cliente.setApellido(apellido);
        if (direccion != null) cliente.setDireccion(direccion);
        if (telefono != null) cliente.setTelefono(telefono);

        return servicioCliente.SaveCliente(cliente);
    }

    @MutationMapping
    public Porcino updatePorcino(
            @Argument Integer id,
            @Argument Integer edad,
            @Argument Float peso,
            @Argument Integer razaId,
            @Argument String clienteDni) {
        Porcino porcino = servicioPorcino.getPorcinoById(id)
                .orElseThrow(() -> new RuntimeException("Porcino no encontrado"));

        if (edad != null) porcino.setEdad(edad);
        if (peso != null) porcino.setPeso(peso);
        if (razaId != null) {
            Raza raza = servicioRaza.getRazaById(razaId)
                    .orElseThrow(() -> new RuntimeException("Raza no encontrada"));
            porcino.setRaza(raza);
        }
        if (clienteDni != null) {
            Cliente cliente = servicioCliente.GetClienteId(clienteDni)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            porcino.setCliente(cliente);
        }

        return servicioPorcino.savePorcino(porcino);
    }

    @MutationMapping
    public Alimento updateAlimento(
            @Argument Integer id,
            @Argument String descripcion,
            @Argument String dosis,
            @Argument Integer porcinoId) {
        Alimento alimento = servicioAlimento.getAlimentoById(id)
                .orElseThrow(() -> new RuntimeException("Alimento no encontrado"));

        if (descripcion != null) alimento.setDescripcion(descripcion);
        if (dosis != null) alimento.setDosis(dosis);
        if (porcinoId != null) {
            Porcino porcino = servicioPorcino.getPorcinoById(porcinoId)
                    .orElseThrow(() -> new RuntimeException("Porcino no encontrado"));
            alimento.setPorcino(porcino);
        }

        return servicioAlimento.saveAlimento(alimento);
    }

    @MutationMapping
    public Raza updateRaza(@Argument Integer id, @Argument String nombre) {
        Raza raza = servicioRaza.getRazaById(id)
                .orElseThrow(() -> new RuntimeException("Raza no encontrada"));

        if (nombre != null) raza.setNombre(nombre);

        return servicioRaza.saveRaza(raza);
    }

    // ------------------------------
    // MUTATIONS - ELIMINAR
    // ------------------------------

    @MutationMapping
    public Boolean deleteCliente(@Argument String dni) {
        servicioCliente.DelCliente(dni);
        return true;
    }

    @MutationMapping
    public Boolean deletePorcino(@Argument Integer id) {
        servicioPorcino.deletePorcino(id);
        return true;
    }

    @MutationMapping
    public Boolean deleteAlimento(@Argument Integer id) {
        servicioAlimento.deleteAlimento(id);
        return true;
    }

    @MutationMapping
    public Boolean deleteRaza(@Argument Integer id) {
        servicioRaza.deleteRaza(id);
        return true;
    }
}
