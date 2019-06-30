package com.proyecto.escapaditas.repositorios;

import com.proyecto.escapaditas.entidades.Cliente;
import com.proyecto.escapaditas.entidades.Promocion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PromocionRepositorio extends CrudRepository<Promocion, Long> {
    @Query("SELECT c FROM Promocion c WHERE c.nombrepromo=:nombrepromo")
    Promocion buscarPromocion(@Param("nombrepromo") String nombrepromo);


    @Query("SELECT c FROM Promocion c WHERE c.destino=:destino")
    List<Promocion> buscarPromocionDestino(@Param("destino") String destino);

}
