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
public class PartidoResultadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PartidoResultadoDTO.class);
    private Long codigopatridoResultado;
    private Long codigopartido_Partido;
    private Long codigoresultado_Resultado;

    public Long getCodigopatridoResultado() {
        return codigopatridoResultado;
    }

    public void setCodigopatridoResultado(Long codigopatridoResultado) {
        this.codigopatridoResultado = codigopatridoResultado;
    }

    public Long getCodigopartido_Partido() {
        return codigopartido_Partido;
    }

    public void setCodigopartido_Partido(Long codigopartido_Partido) {
        this.codigopartido_Partido = codigopartido_Partido;
    }

    public Long getCodigoresultado_Resultado() {
        return codigoresultado_Resultado;
    }

    public void setCodigoresultado_Resultado(Long codigoresultado_Resultado) {
        this.codigoresultado_Resultado = codigoresultado_Resultado;
    }
}
