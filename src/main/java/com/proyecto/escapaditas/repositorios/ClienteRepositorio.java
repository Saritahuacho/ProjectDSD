package com.proyecto.escapaditas.repositorios;

import com.proyecto.escapaditas.entidades.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClienteRepositorio extends CrudRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.dni=:dni")
    Cliente buscarCliente(@Param("dni") String dni);
}
