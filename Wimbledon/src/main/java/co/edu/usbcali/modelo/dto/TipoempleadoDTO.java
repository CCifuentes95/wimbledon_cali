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
public class TipoempleadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoempleadoDTO.class);
    private Long codigotipoempleado;
    private String descripcion;

    public Long getCodigotipoempleado() {
        return codigotipoempleado;
    }

    public void setCodigotipoempleado(Long codigotipoempleado) {
        this.codigotipoempleado = codigotipoempleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
