package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.Ronda;
import co.edu.usbcali.modelo.dto.RondaDTO;

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
public interface IRondaLogic {
    public List<Ronda> getRonda() throws Exception;

    /**
         * Save an new Ronda entity
         */
    public void saveRonda(Ronda entity) throws Exception;

    /**
         * Delete an existing Ronda entity
         *
         */
    public void deleteRonda(Ronda entity) throws Exception;

    /**
        * Update an existing Ronda entity
        *
        */
    public void updateRonda(Ronda entity) throws Exception;

    /**
         * Load an existing Ronda entity
         *
         */
    public Ronda getRonda(Long codigoronda) throws Exception;

    public List<Ronda> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Ronda> findPageRonda(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRonda() throws Exception;

    public List<RondaDTO> getDataRonda() throws Exception;
    
    public List<Ronda> findRondasByTorneo(Long idTorneo) throws Exception;
}
