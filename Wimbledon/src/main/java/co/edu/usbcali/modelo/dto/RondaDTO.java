package co.edu.usbcali.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class RondaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RondaDTO.class);
    private Long codigoronda;
    private String nombre;
    private Double precio;
    private Integer puntos;
    private Long codigotorneo_Torneo;

    public Long getCodigoronda() {
        return codigoronda;
    }

    public void setCodigoronda(Long codigoronda) {
        this.codigoronda = codigoronda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Long getCodigotorneo_Torneo() {
        return codigotorneo_Torneo;
    }

    public void setCodigotorneo_Torneo(Long codigotorneo_Torneo) {
        this.codigotorneo_Torneo = codigotorneo_Torneo;
    }
}
