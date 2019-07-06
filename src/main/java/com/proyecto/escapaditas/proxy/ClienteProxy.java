package com.proyecto.escapaditas.proxy;

import com.proyecto.escapaditas.Constants;
import com.proyecto.escapaditas.negocio.Negocio;
import com.proyecto.escapaditas.proxy.response.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ClienteProxy {

    @Autowired
    private Negocio negocio;

    public ClienteProxy(Negocio negocio){
        this.negocio = negocio;
    }

    public ClienteResponse obtenerTodosClientes(){

        ClienteResponse response = new ClienteResponse(Constants.ERROR_CODE_OK,
                Constants.ERROR_MESSAGE_OK,
                new ArrayList<>());

        try{
            response.setData(negocio.obtenerClientes());
        }catch (Exception e){
            response.setErrorCode(Constants.ERROR_CODE_FAIL);
            response.setErrorMessage(Constants.ERROR_MESSAGE_FAIL);
            Logger.getLogger(getClass().getName()).log(Level.ALL, e.getLocalizedMessage());
        }

        return response;
    }
}
