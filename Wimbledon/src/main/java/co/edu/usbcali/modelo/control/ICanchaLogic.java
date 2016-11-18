package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.Cancha;
import co.edu.usbcali.modelo.dto.CanchaDTO;

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
public interface ICanchaLogic {
    public List<Cancha> getCancha() throws Exception;

    /**
         * Save an new Cancha entity
         */
    public void saveCancha(Cancha entity) throws Exception;

    /**
         * Delete an existing Cancha entity
         *
         */
    public void deleteCancha(Cancha entity) throws Exception;

    /**
        * Update an existing Cancha entity
        *
        */
    public void updateCancha(Cancha entity) throws Exception;

    /**
         * Load an existing Cancha entity
         *
         */
    public Cancha getCancha(Long codigocancha) throws Exception;

    public List<Cancha> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cancha> findPageCancha(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCancha() throws Exception;

    public List<CanchaDTO> getDataCancha() throws Exception;
}
