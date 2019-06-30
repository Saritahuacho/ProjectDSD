package com.proyecto.escapaditas.negocio;

import com.proyecto.escapaditas.entidades.Cliente;
import com.proyecto.escapaditas.entidades.Promocion;
import com.proyecto.escapaditas.repositorios.ClienteRepositorio;
import com.proyecto.escapaditas.repositorios.PromocionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;
import java.util.Optional;

@Service
public class Negocio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private PromocionRepositorio promocionRepositorio;
    private PromocionRepositorio promocionRepositorio1;

    ////////////////////////////////////////METODOS PARA CLIENTES////////////////////////////////////////
    public Cliente registrar(Cliente cliente){
        return clienteRepositorio.save(cliente);
    }

    public Cliente actualizarCliente(Cliente cliente) throws Exception{

        Cliente c = clienteRepositorio.findById(cliente.getCodigo()).
                orElseThrow(() -> new Exception("Cliente no existe"));

        if (cliente.getNombre()!=null) c.setNombre(cliente.getNombre());
        if (cliente.getApaterno()!=null)c.setApaterno(cliente.getApaterno());
        if (cliente.getAmaterno()!=null)c.setAmaterno(cliente.getAmaterno());
        if (cliente.getDni()!=null)c.setDni(cliente.getDni());
        if (cliente.getCelular()!=null)c.setCelular(cliente.getCelular());
        if (cliente.getFnacimiento()!=null)c.setFnacimiento(cliente.getFnacimiento());
        if (cliente.getSexo()!=null)c.setSexo(cliente.getSexo());
        if (cliente.getDireccion()!=null)c.setDireccion(cliente.getDireccion());
        if (cliente.getCorreo()!=null)c.setCorreo(cliente.getCorreo());
        if (cliente.getContrasena()!=null)c.setContrasena(cliente.getContrasena());
        return  clienteRepositorio.save(c);
    }

    public List<Cliente> obtenerClientes(){
        return (List<Cliente>)clienteRepositorio.findAll();
    }

    public Cliente obtenerClienteId(String id){
        return clienteRepositorio.buscarCliente(id);
    }

    ////////////////////////////////////////METODOS PARA PROMOCIONES////////////////////////////////////////

    public Promocion registrarPromocion(String dni, Promocion promocion){
        Cliente c =  clienteRepositorio.buscarCliente(dni);
        if (c!=null) {
            promocion.setCliente(c);
            return promocionRepositorio.save(promocion);
        }
        return null;
    }

    public List<Promocion> obtenerPromociones(){
        return (List<Promocion>)promocionRepositorio.findAll();
    }

    public List<Promocion> obtenerPromocionesDestino() {
        List<Promocion> promociones = obtenerPromociones();
        List<Promocion> promocionesfiltro = null;
        for(Promocion p:promociones){
            if(p.getDestino().contentEquals("CUZCO")) {
                System.out.println("OK");
                promocionesfiltro.add(p);
                //System.out.println("Descripcion: "+p.getDescripcion()+" F.Inicio: " + p.getFinicio() + " F.Fin: " + p.getFfin() + " Precio: " + p.getPrecio() );
            }
        }
        return promocionesfiltro;
    }
}