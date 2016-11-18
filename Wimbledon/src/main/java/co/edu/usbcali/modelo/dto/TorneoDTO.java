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
public class TorneoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TorneoDTO.class);
    private Long codigotorneo;
    private Date fechafin;
    private Date fechainicio;
    private Long codigoempleado_Empleado;
    private Long codigotipotorneo_Tipotorneo;

    public Long getCodigotorneo() {
        return codigotorneo;
    }

    public void setCodigotorneo(Long codigotorneo) {
        this.codigotorneo = codigotorneo;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Long getCodigoempleado_Empleado() {
        return codigoempleado_Empleado;
    }

    public void setCodigoempleado_Empleado(Long codigoempleado_Empleado) {
        this.codigoempleado_Empleado = codigoempleado_Empleado;
    }

    public Long getCodigotipotorneo_Tipotorneo() {
        return codigotipotorneo_Tipotorneo;
    }

    public void setCodigotipotorneo_Tipotorneo(Long codigotipotorneo_Tipotorneo) {
        this.codigotipotorneo_Tipotorneo = codigotipotorneo_Tipotorneo;
    }
}
