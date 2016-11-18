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
public class ResultadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ResultadoDTO.class);
    private Long codigoresultado;
    private Integer resuadoa;
    private Integer resutadob;

    public Long getCodigoresultado() {
        return codigoresultado;
    }

    public void setCodigoresultado(Long codigoresultado) {
        this.codigoresultado = codigoresultado;
    }

    public Integer getResuadoa() {
        return resuadoa;
    }

    public void setResuadoa(Integer resuadoa) {
        this.resuadoa = resuadoa;
    }

    public Integer getResutadob() {
        return resutadob;
    }

    public void setResutadob(Integer resutadob) {
        this.resutadob = resutadob;
    }
}
