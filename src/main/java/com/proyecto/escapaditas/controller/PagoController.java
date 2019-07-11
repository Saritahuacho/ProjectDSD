package com.proyecto.escapaditas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.escapaditas.entidades.Pago;
import com.proyecto.escapaditas.jms.JmsProducer;

@RestController
@RequestMapping("/api/pago")
public class PagoController {
    @Autowired
    private JmsProducer jmsProducer;


    //Pagar promocion
    //http://localhost:8888/api/pago/pagar
    @PostMapping("/pagar")
    public Pago enviar(@RequestBody Pago pago) {
        ObjectMapper mapper = new ObjectMapper();
        //Object a JSON en String
        String jsonString=null;
        try {
            jsonString = mapper.writeValueAsString(pago);
            //se envía a la cola en String
            jmsProducer.send(jsonString);
            pago.setRespuesta("Enviado a cola!");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            pago.setRespuesta("Error en trama, no se envía!");
        }
        return pago;
    }

    /*
        {
    	"dni":"43695785",
    	"nombrepromo":"ARE01",
        "precio": 399.00
    }
     */

}
