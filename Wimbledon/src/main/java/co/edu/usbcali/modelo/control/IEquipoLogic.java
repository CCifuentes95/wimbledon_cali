package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.Equipo;
import co.edu.usbcali.modelo.dto.EquipoDTO;

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
public interface IEquipoLogic {
    public List<Equipo> getEquipo() throws Exception;

    /**
         * Save an new Equipo entity
         */
    public void saveEquipo(Equipo entity) throws Exception;

    /**
         * Delete an existing Equipo entity
         *
         */
    public void deleteEquipo(Equipo entity) throws Exception;

    /**
        * Update an existing Equipo entity
        *
        */
    public void updateEquipo(Equipo entity) throws Exception;

    /**
         * Load an existing Equipo entity
         *
         */
    public Equipo getEquipo(Long codigoequipo) throws Exception;

    public List<Equipo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Equipo> findPageEquipo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEquipo() throws Exception;

    public List<EquipoDTO> getDataEquipo() throws Exception;
}
