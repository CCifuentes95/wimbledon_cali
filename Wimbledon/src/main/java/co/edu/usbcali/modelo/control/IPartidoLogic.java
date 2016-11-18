package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.Partido;
import co.edu.usbcali.modelo.dto.PartidoDTO;

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
public interface IPartidoLogic {
    public List<Partido> getPartido() throws Exception;

    /**
         * Save an new Partido entity
         */
    public void savePartido(Partido entity) throws Exception;

    /**
         * Delete an existing Partido entity
         *
         */
    public void deletePartido(Partido entity) throws Exception;

    /**
        * Update an existing Partido entity
        *
        */
    public void updatePartido(Partido entity) throws Exception;

    /**
         * Load an existing Partido entity
         *
         */
    public Partido getPartido(Long codigopartido) throws Exception;

    public List<Partido> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Partido> findPagePartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPartido() throws Exception;

    public List<PartidoDTO> getDataPartido() throws Exception;
}
