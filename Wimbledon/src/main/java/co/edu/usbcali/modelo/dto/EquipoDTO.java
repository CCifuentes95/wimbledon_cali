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
public class EquipoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EquipoDTO.class);
    private Long codigoequipo;
    private String nombre;
    private Long codigojugador_Jugador;

    public Long getCodigoequipo() {
        return codigoequipo;
    }

    public void setCodigoequipo(Long codigoequipo) {
        this.codigoequipo = codigoequipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCodigojugador_Jugador() {
        return codigojugador_Jugador;
    }

    public void setCodigojugador_Jugador(Long codigojugador_Jugador) {
        this.codigojugador_Jugador = codigojugador_Jugador;
    }
}
