package com.proyecto.escapaditas.controller;

import com.proyecto.escapaditas.entidades.Cliente;
import com.proyecto.escapaditas.entidades.Promocion;
import com.proyecto.escapaditas.negocio.Negocio;
import com.proyecto.escapaditas.repositorios.PromocionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PromocionesRest {
    @Autowired
    private Negocio negocio;

    //GET
    @GetMapping("/")
    public String index(){
        return "Hello World";
    }


    //http://localhost:8080/api/clientes
    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return negocio.obtenerClientes();
    }

    @GetMapping("/clientesDni")
    public Cliente clientePorDni(@RequestParam String id){
        return negocio.obtenerClienteId(id);
    }


    //http://localhost:8080/api/promociones
    @GetMapping("/promociones")
    public List<Promocion> listarPromocionesDestino() {
        return negocio.obtenerPromocionesDestino();
    }
    //POST
    //http://localhost:8080/api/cliente
    @PostMapping("/cliente")
    public Cliente registrar(@RequestBody Cliente cliente){
        return negocio.registrar(cliente);
    }


}
