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
public class JugadorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(JugadorDTO.class);
    private String activo;
    private Long codigojugador;
    private String nombre;
    private Integer ranking;
    private Long codigopais_Pais;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Long getCodigojugador() {
        return codigojugador;
    }

    public void setCodigojugador(Long codigojugador) {
        this.codigojugador = codigojugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Long getCodigopais_Pais() {
        return codigopais_Pais;
    }

    public void setCodigopais_Pais(Long codigopais_Pais) {
        this.codigopais_Pais = codigopais_Pais;
    }
}
