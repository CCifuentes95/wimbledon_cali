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
public class PartidoJugadorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PartidoJugadorDTO.class);
    private Long codigopartidoJugador;
    private Long codigojugador_Jugador;
    private Long codigopartido_Partido;

    public Long getCodigopartidoJugador() {
        return codigopartidoJugador;
    }

    public void setCodigopartidoJugador(Long codigopartidoJugador) {
        this.codigopartidoJugador = codigopartidoJugador;
    }

    public Long getCodigojugador_Jugador() {
        return codigojugador_Jugador;
    }

    public void setCodigojugador_Jugador(Long codigojugador_Jugador) {
        this.codigojugador_Jugador = codigojugador_Jugador;
    }

    public Long getCodigopartido_Partido() {
        return codigopartido_Partido;
    }

    public void setCodigopartido_Partido(Long codigopartido_Partido) {
        this.codigopartido_Partido = codigopartido_Partido;
    }
}
