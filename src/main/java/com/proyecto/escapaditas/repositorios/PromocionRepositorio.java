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

    @Query("SELECT c FROM Promocion c WHERE c.respuesta=null")
    List<Promocion> buscarPromosVigente();

    @Query("SELECT c FROM Promocion c WHERE c.destino=:destino AND c.finicio>:f1 AND c.ffin<:f2 AND c.capacidad>=:cant AND c.respuesta=null")
    List<Promocion> buscarPromo(@Param("destino") String destino,@Param("f1") String f1,@Param("f2") String f2,@Param("cant") int cant);

    @Query("SELECT c FROM Promocion c WHERE codigo_cliente=:codigo AND c.respuesta<>null")
    List<Promocion> buscarPromosCliente(@Param("codigo") Long codigo);

}
