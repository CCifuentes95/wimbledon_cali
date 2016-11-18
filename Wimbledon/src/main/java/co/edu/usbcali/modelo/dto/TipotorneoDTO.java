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
public class TipotorneoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipotorneoDTO.class);
    private Long codigotipotorneo;
    private String descripcion;

    public Long getCodigotipotorneo() {
        return codigotipotorneo;
    }

    public void setCodigotipotorneo(Long codigotipotorneo) {
        this.codigotipotorneo = codigotipotorneo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
