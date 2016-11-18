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
public class PartidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PartidoDTO.class);
    private Long codigopartido;
    private Date fecha;
    private String ganador;
    private Long codigocancha_Cancha;
    private Long codigoempleado_Empleado;
    private Long codigoronda_Ronda;

    public Long getCodigopartido() {
        return codigopartido;
    }

    public void setCodigopartido(Long codigopartido) {
        this.codigopartido = codigopartido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public Long getCodigocancha_Cancha() {
        return codigocancha_Cancha;
    }

    public void setCodigocancha_Cancha(Long codigocancha_Cancha) {
        this.codigocancha_Cancha = codigocancha_Cancha;
    }

    public Long getCodigoempleado_Empleado() {
        return codigoempleado_Empleado;
    }

    public void setCodigoempleado_Empleado(Long codigoempleado_Empleado) {
        this.codigoempleado_Empleado = codigoempleado_Empleado;
    }

    public Long getCodigoronda_Ronda() {
        return codigoronda_Ronda;
    }

    public void setCodigoronda_Ronda(Long codigoronda_Ronda) {
        this.codigoronda_Ronda = codigoronda_Ronda;
    }
}
