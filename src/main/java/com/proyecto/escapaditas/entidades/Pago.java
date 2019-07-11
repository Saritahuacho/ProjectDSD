package com.proyecto.escapaditas.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_VENTA")
public class Pago implements Serializable {
    private  static final long serialVersionUID = 43695785L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_VENTA")
    private Long codigo;
    private String dni;
    private String nombrepromo;
    private double precio;
    private String respuesta;

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombrepromo() {
        return nombrepromo;
    }

    public void setNombrepromo(String nombrepromo) {
        this.nombrepromo = nombrepromo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
