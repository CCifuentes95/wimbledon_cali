package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.Tipoempleado;
import co.edu.usbcali.modelo.dto.TipoempleadoDTO;

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
public interface ITipoempleadoLogic {
    public List<Tipoempleado> getTipoempleado() throws Exception;

    /**
         * Save an new Tipoempleado entity
         */
    public void saveTipoempleado(Tipoempleado entity) throws Exception;

    /**
         * Delete an existing Tipoempleado entity
         *
         */
    public void deleteTipoempleado(Tipoempleado entity)
        throws Exception;

    /**
        * Update an existing Tipoempleado entity
        *
        */
    public void updateTipoempleado(Tipoempleado entity)
        throws Exception;

    /**
         * Load an existing Tipoempleado entity
         *
         */
    public Tipoempleado getTipoempleado(Long codigotipoempleado)
        throws Exception;

    public List<Tipoempleado> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tipoempleado> findPageTipoempleado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoempleado() throws Exception;

    public List<TipoempleadoDTO> getDataTipoempleado()
        throws Exception;
}
