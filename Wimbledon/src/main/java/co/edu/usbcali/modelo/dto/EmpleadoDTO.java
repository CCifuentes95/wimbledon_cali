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
public class EmpleadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EmpleadoDTO.class);
    private String apellido;
    private Long codigoempleado;
    private Long email;
    private String nombre;
    private Long password;
    private Long codigotipoempleado_Tipoempleado;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getCodigoempleado() {
        return codigoempleado;
    }

    public void setCodigoempleado(Long codigoempleado) {
        this.codigoempleado = codigoempleado;
    }

    public Long getEmail() {
        return email;
    }

    public void setEmail(Long email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    public Long getCodigotipoempleado_Tipoempleado() {
        return codigotipoempleado_Tipoempleado;
    }

    public void setCodigotipoempleado_Tipoempleado(
        Long codigotipoempleado_Tipoempleado) {
        this.codigotipoempleado_Tipoempleado = codigotipoempleado_Tipoempleado;
    }
}
