package com.p1.JPA.controladores;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p1.JPA.modelos.Cliente;
import com.p1.JPA.modelos.Porcino;
import com.p1.JPA.modelos.Alimento;
import com.p1.JPA.servicios.ServicioCliente;
import com.thoughtworks.xstream.XStream;

@RestController
@RequestMapping("/Cliente")
public class ControladorCliente {
    
    @Autowired
    ServicioCliente clientesS;    
    
    @GetMapping()
    public ArrayList<Cliente> ObtenerClientes(){
        return clientesS.GetClientes();
    }
    
    @PostMapping()
    public Cliente GuardarCliente(@RequestBody Cliente cliente) {
        return this.clientesS.SaveCliente(cliente);
    }

    @GetMapping(path = "/{dni}")
    public Optional<Cliente> ObtenerClientePorDni(@PathVariable("dni") String dni) {
        return this.clientesS.GetClienteId(dni);
    }
    
    @PutMapping(path = "/{dni}")
    public Cliente ActualizarClientePorId(@PathVariable("dni") String dni, @RequestBody Cliente cliente) {
    	return this.clientesS.SaveCliente(cliente);
    }

    @DeleteMapping(path = "/{dni}")
    public String EliminarClientePorDni(@PathVariable("dni") String dni) {
        boolean ok = false;
        try {
            clientesS.DelCliente(dni);
            ok = true;
        } catch (Exception e) {
            ok = false;
        }

        if (ok) {
            return "Cliente con DNI " + dni + " fue eliminado.";
        } else {
            return "No se pudo eliminar el cliente con DNI " + dni;
        }
    }
 // Endpoint para generar XML con XStream
    @GetMapping("/generarXML/{dni}")
    public ResponseEntity<String> obtenerClienteConMascotasYMedicamentosEnXml(@PathVariable String dni) {
        Optional<Cliente> clienteOpt = clientesS.GetClienteId(dni);

        if (clienteOpt.isPresent()) {
            try {
                // Crear una instancia de XStream
                XStream xstream = new XStream();

                // Configurar XStream para procesar los objetos de Cliente, Mascota y Medicamento
                xstream.alias("cliente", Cliente.class);
                xstream.alias("procino", Porcino.class);
                xstream.alias("alimento", Alimento.class);

                // Configurar alias para que las colecciones no sean mostradas como "list" o "set"
                xstream.addImplicitCollection(Cliente.class, "mascotas");
                xstream.addImplicitCollection(Porcino.class, "Alimentos");

                // Convertir el objeto Cliente a XML
                String xml = xstream.toXML(clienteOpt.get());

                // Configurar los headers para que sea descargado como un archivo XML
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cliente_" + dni + ".xml");

                // Retornar el archivo XML generado
                return new ResponseEntity<>(xml, headers, HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
