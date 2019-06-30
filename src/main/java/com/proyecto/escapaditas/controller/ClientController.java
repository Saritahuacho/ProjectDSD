package com.proyecto.escapaditas.controller;

import com.proyecto.escapaditas.entidades.Cliente;
import com.proyecto.escapaditas.entidades.Promocion;
import com.proyecto.escapaditas.negocio.Negocio;
import com.proyecto.escapaditas.repositorios.PromocionRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClientController {
    @Autowired
    private Negocio negocio;
    Logger logger = LoggerFactory.getLogger(ClientController.class);

    //GET
    @GetMapping("/")
    public String index(){
        return "Hello World";
    }

    ///////////////////////////////////GET/////////////////////////////////////

    //Listar todos los clientes
    //http://localhost:8080/api/cliente/clientes
    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return negocio.obtenerClientes();
    }

    @GetMapping("/clientesDni")
    public Cliente clientePorDni(@RequestParam String id){
        return negocio.obtenerClienteId(id);
    }


    ///////////////////////////////////POST/////////////////////////////////////

    //Registrar cliente
    //http://localhost:8080/api/cliente/cliente
    @PostMapping("/cliente")
    public Cliente registrar(@RequestBody Cliente cliente){
        return negocio.registrar(cliente);
    }

    ///////////////////////////////////PUT/////////////////////////////////////

    //Actualizar datos cliente
    //http://localhost:8080/api/cliente/updateclient
    @PutMapping("/updateclient")
    public Cliente actualizarCliente(@RequestBody Cliente cliente){
        Cliente c;
        try {
            logger.debug("Actualizando Cliente");
            c = negocio.actualizarCliente(cliente);
        }catch(Exception e)
        {
            logger.error("Error de actualizacion", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible actualizar");
        }
        return  c;
    }

}
