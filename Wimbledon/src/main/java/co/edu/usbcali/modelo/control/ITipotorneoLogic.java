package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.Tipotorneo;
import co.edu.usbcali.modelo.dto.TipotorneoDTO;

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
public interface ITipotorneoLogic {
    public List<Tipotorneo> getTipotorneo() throws Exception;

    /**
         * Save an new Tipotorneo entity
         */
    public void saveTipotorneo(Tipotorneo entity) throws Exception;

    /**
         * Delete an existing Tipotorneo entity
         *
         */
    public void deleteTipotorneo(Tipotorneo entity) throws Exception;

    /**
        * Update an existing Tipotorneo entity
        *
        */
    public void updateTipotorneo(Tipotorneo entity) throws Exception;

    /**
         * Load an existing Tipotorneo entity
         *
         */
    public Tipotorneo getTipotorneo(Long codigotipotorneo)
        throws Exception;

    public List<Tipotorneo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tipotorneo> findPageTipotorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipotorneo() throws Exception;

    public List<TipotorneoDTO> getDataTipotorneo() throws Exception;
}
