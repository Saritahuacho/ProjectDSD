package com.proyecto.escapaditas.repositorios;

import com.proyecto.escapaditas.entidades.Pago;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PagoRepositorio extends CrudRepository<Pago, Long> {

}