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
public class PartidoUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PartidoUsuarioDTO.class);
    private Long codigopartidoUsuario;
    private Long codigopartido_Partido;
    private Long codigousuario_Usuario;

    public Long getCodigopartidoUsuario() {
        return codigopartidoUsuario;
    }

    public void setCodigopartidoUsuario(Long codigopartidoUsuario) {
        this.codigopartidoUsuario = codigopartidoUsuario;
    }

    public Long getCodigopartido_Partido() {
        return codigopartido_Partido;
    }

    public void setCodigopartido_Partido(Long codigopartido_Partido) {
        this.codigopartido_Partido = codigopartido_Partido;
    }

    public Long getCodigousuario_Usuario() {
        return codigousuario_Usuario;
    }

    public void setCodigousuario_Usuario(Long codigousuario_Usuario) {
        this.codigousuario_Usuario = codigousuario_Usuario;
    }
}
