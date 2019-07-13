/*
package com.proyecto.escapaditas.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.proyecto.escapaditas.entidades.Pago;
import com.proyecto.escapaditas.negocio.Negocio;


@Component
public class JmsConsumer {

    @Autowired
    private Negocio negocio;

    @JmsListener(destination="${jms.destino}")
    public void miMensaje(String mensajeJson) {
        System.out.println("Recibido:" + mensajeJson);
        //mensajeJSON a Objeto Auto
        ObjectMapper mapper = new ObjectMapper();
        try {
            Pago pago =  mapper.readValue(mensajeJson, Pago.class);
            pago.setRespuesta("Registrar a Tabla");
            System.out.println(mensajeJson);
            Pago respuesta = negocio.grabar(pago);//registra en la base de  datos
            if (respuesta==null) {
                System.out.println("No se pudo registrar");
            }
            else {
                System.out.println("Registrado ok!");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            System.out.println("No se pudo registrar");
        }
    }

}

*/
