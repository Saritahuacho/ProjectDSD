package com.proyecto.escapaditas.controller;

import com.proyecto.escapaditas.Constants;
import com.proyecto.escapaditas.entidades.Cliente;
import com.proyecto.escapaditas.entidades.Promocion;
import com.proyecto.escapaditas.negocio.Negocio;
import com.proyecto.escapaditas.proxy.ClienteProxy;
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

@RestController // controller que responde para un request
@RequestMapping("/api/cliente") //nos nos indica donse esta el origen del controller para llamar
public class ClientController {
    @Autowired // el F de sprint boot inicia la clase
    private Negocio negocio;
    Logger logger = LoggerFactory.getLogger(ClientController.class); //framewor para escribir log

    private ClienteProxy proxy; //objeto para comunicacion entre web service y la BD

    public ClientController(){  // constructor de un objeto de Cliente controller
        proxy = new ClienteProxy(negocio); // Genera un nuevo objeto de tipo ClienteProxy
    }

    ///////////////////////////////////GET/////////////////////////////////////

    //Listar todos los clientes
    //http://localhost:8080/api/cliente/clientes
    @GetMapping("/clientes")
    public ClienteResponse listarClientes(){//metodo que retorna un objeto de tipo ClienteResponse

        ClienteResponse response = new ClienteResponse(Constants.ERROR_CODE_OK,
                Constants.ERROR_MESSAGE_OK,
                new ArrayList<>());

        try{
            response.setData(negocio.obtenerClientes());
        }catch (Exception e){
            response.setErrorCode(Constants.ERROR_CODE_FAIL);
            response.setErrorMessage(Constants.ERROR_MESSAGE_FAIL);
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.ALL, e.getLocalizedMessage());
        }

        return response;
    }

    //Listar todas las promociones de un cliente
    //http://localhost:8080/api/cliente/promoscliente
    @GetMapping("/promoscliente")
    public List<Promocion> listarPromosCliente(@RequestParam String id){
        return negocio.obtenerPromosCliente(id);
    }

    //Buscar clientes por DNI
    //http://localhost:8080/api/cliente/clientedni?id=11111111
    @GetMapping("/clientedni")
    public ClienteResponse clientePorDni(@RequestParam String id){

        ClienteResponse response = new ClienteResponse(Constants.ERROR_CODE_OK,
                Constants.ERROR_MESSAGE_OK,
                new ArrayList<>());

        try{
            List<Cliente> result = new ArrayList<>();
            result.add(negocio.obtenerClienteId(id));
            response.setData(result);
        }catch (Exception e){
            response.setErrorCode(Constants.ERROR_CODE_FAIL);
            response.setErrorMessage(Constants.ERROR_MESSAGE_FAIL);
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.ALL, e.getLocalizedMessage());
        }

        return response;
    }


    ///////////////////////////////////POST/////////////////////////////////////

    //Registrar cliente
    //http://localhost:8080/api/cliente/RegistrarCliente
    @PostMapping("/Registrarcliente")
    public ClienteResponse registrar(@RequestBody Cliente cliente){

        ClienteResponse response = new ClienteResponse(Constants.ERROR_CODE_OK,
                Constants.ERROR_MESSAGE_OK,
                new ArrayList<>());

        try{
            List<Cliente> result = new ArrayList<>();
            result.add(negocio.registrar(cliente));
            response.setData(result);
        }catch (Exception e){
            response.setErrorCode(Constants.ERROR_CODE_FAIL);
            response.setErrorMessage(Constants.ERROR_MESSAGE_FAIL);
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.ALL, e.getLocalizedMessage());
        }

        return response;
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
