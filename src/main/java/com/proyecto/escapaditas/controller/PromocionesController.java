package com.proyecto.escapaditas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.escapaditas.Constants;
import com.proyecto.escapaditas.entidades.Cliente;
import com.proyecto.escapaditas.entidades.Promocion;
import com.proyecto.escapaditas.jms.JmsProducer;
import com.proyecto.escapaditas.negocio.Negocio;
import com.proyecto.escapaditas.proxy.response.ClienteResponse;
import com.proyecto.escapaditas.repositorios.PromocionRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/api/promocion")
public class PromocionesController {
    @Autowired
    private Negocio negocio;
    Logger logger = LoggerFactory.getLogger(PromocionesController.class);

    ///////////////////////////////////GET/////////////////////////////////////

       //Listar todos las promociones
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

    //Busqueda de Promo destino, fechas y cantidad de pasajeros
    //http://localhost:8888/api/promocion/promo?destino=AREQUIPA&f1=19/10/01&f2=19/10/11&cant=4
    @GetMapping("/promo")
    public List<Promocion> BusquedaPromo(@RequestParam String destino,@RequestParam String f1,@RequestParam String f2,@RequestParam int cant) {
        return negocio.obtenerPromos(destino,f1,f2,cant);
    }

    /*Listar todas las promociones de un cliente
    http://localhost:8888/api/promocion/promoscliente?dni=43695785
    */
    @GetMapping("/promoscliente")
    public List<Promocion> listarPromosCliente(@RequestParam String dni){
        return negocio.obtenerPromosCliente(dni);
    }

    ///////////////////////////////////POST/////////////////////////////////////
/*Registrar promocion
    http://localhost:8888/api/promocion/promocion?dni=43695785
    {
        "nombrepromo":"ARE100",
            "finicio":"19/11/02",
            "ffin":"19/11/15",
            "precio": 399.00,
            "capacidad":5,
            "descripcion":"Arequipa Mistica",
            "destino":"AREQUIPA"
    }
    */
    @PostMapping("/promocion")
    public Promocion registrarPromocionCliente(@RequestParam String dni,@RequestBody Promocion promocion){
        Promocion p;
        try {
            logger.debug("Registrando Promocion");

            p = negocio.registrarPromocion(dni,promocion,"REGISTRADO");

        }catch(Exception e)
        {
            logger.error("Error de registro", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible registrar");
        }
        return  p;
    }


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
