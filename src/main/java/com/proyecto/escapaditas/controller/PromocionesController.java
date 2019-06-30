package com.proyecto.escapaditas.controller;

import com.proyecto.escapaditas.entidades.Cliente;
import com.proyecto.escapaditas.entidades.Promocion;
import com.proyecto.escapaditas.negocio.Negocio;
import com.proyecto.escapaditas.repositorios.PromocionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promocion")
public class PromocionesController {
    @Autowired
    private Negocio negocio;

    ///////////////////////////////////GET/////////////////////////////////////
    @GetMapping("/")
    public String index(){
        return "Hello World";
    }

     //http://localhost:8080/api/promocion/promociones
    @GetMapping("/promociones")
    public List<Promocion> listarPromocionesDestino() {
        return negocio.obtenerPromocionesDestino();
    }

    ///////////////////////////////////POST/////////////////////////////////////

    ///////////////////////////////////PUT/////////////////////////////////////

}
