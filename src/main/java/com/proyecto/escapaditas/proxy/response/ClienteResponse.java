package com.proyecto.escapaditas.proxy.response;

import com.proyecto.escapaditas.entidades.Cliente;

import java.util.List;

public class ClienteResponse extends BaseResponse {

    private List<Cliente> data;

    public ClienteResponse(String errorCode, String errorMessage, List<Cliente> data) {
        super(errorCode, errorMessage);

        this.data = data;
    }

    public List<Cliente> getData() {
        return data;
    }

    public void setData(List<Cliente> data) {
        this.data = data;
    }
}
