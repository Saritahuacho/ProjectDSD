package com.proyecto.escapaditas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.escapaditas.entidades.Cliente;
import com.proyecto.escapaditas.entidades.Promocion;
import com.proyecto.escapaditas.jms.JmsProducer;
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
    //Listar todos las promociones vigentes
    //http://localhost:8080/api/promocion/promociones
    @GetMapping("/promociones")
    public List<Promocion> listarPromociones(){
        return negocio.obtenerPromociones();
    }

    //Listar todos las promociones vigentes
    //http://localhost:8080/api/promocion/promovigente
    @GetMapping("/promovigente")
    public List<Promocion> listarPromoVigentes(){
        return negocio.obtenerPromosVigente();
    }

    //Buscar promociones por nombre
    //http://localhost:8080/api/promocion/promonombre?id=ARE01
    @GetMapping("/promonombre")
    public Promocion promoPorNombre(@RequestParam String id){
        return negocio.obtenerPromoNombre(id);
    }

    //Listar promociones por destino
    //http://localhost:8080/api/promocion/promodestino?id=AREQUIPA
    @GetMapping("/promodestino")
    public List<Promocion> listarPromocionesDestino(@RequestParam String id) {
        return negocio.obtenerPromoDestino(id);
    }

    //Listar promociones por destino
    //http://localhost:8080/api/promocion/promodestino?id=AREQUIPA
    @GetMapping("/promo")
    public List<Promocion> BusquedaPromo(@RequestParam String destino,@RequestParam String f1,@RequestParam String f2) {
        return negocio.obtenerPromos(destino,f1,f2);
    }

    ///////////////////////////////////POST/////////////////////////////////////

   /* @PostMapping("/enviar")
    public Promocion enviar(@RequestBody Promocion promocion) {
        ObjectMapper mapper = new ObjectMapper();
        //Object a JSON en String
        String jsonString=null;
        try {
            jsonString = mapper.writeValueAsString(promocion);
            //se envía a la cola en String
            jmsProducer.send(jsonString);
            promocion.setRespuesta("Enviado a cola!");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            promocion.setRespuesta("Error en trama, no se envía!");
        }
        return promocion;
    }*/

    ///////////////////////////////////PUT/////////////////////////////////////

}
