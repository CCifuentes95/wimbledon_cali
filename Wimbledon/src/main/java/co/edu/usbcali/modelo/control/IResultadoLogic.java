package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.Resultado;
import co.edu.usbcali.modelo.dto.ResultadoDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IResultadoLogic {
    public List<Resultado> getResultado() throws Exception;

    /**
         * Save an new Resultado entity
         */
    public void saveResultado(Resultado entity) throws Exception;

    /**
         * Delete an existing Resultado entity
         *
         */
    public void deleteResultado(Resultado entity) throws Exception;

    /**
        * Update an existing Resultado entity
        *
        */
    public void updateResultado(Resultado entity) throws Exception;

    /**
         * Load an existing Resultado entity
         *
         */
    public Resultado getResultado(Long codigoresultado)
        throws Exception;

    public List<Resultado> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Resultado> findPageResultado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberResultado() throws Exception;

    public List<ResultadoDTO> getDataResultado() throws Exception;
}
