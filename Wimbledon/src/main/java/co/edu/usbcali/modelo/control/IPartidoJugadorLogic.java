package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.PartidoJugador;
import co.edu.usbcali.modelo.dto.PartidoJugadorDTO;

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
public interface IPartidoJugadorLogic {
    public List<PartidoJugador> getPartidoJugador() throws Exception;

    /**
         * Save an new PartidoJugador entity
         */
    public void savePartidoJugador(PartidoJugador entity)
        throws Exception;

    /**
         * Delete an existing PartidoJugador entity
         *
         */
    public void deletePartidoJugador(PartidoJugador entity)
        throws Exception;

    /**
        * Update an existing PartidoJugador entity
        *
        */
    public void updatePartidoJugador(PartidoJugador entity)
        throws Exception;

    /**
         * Load an existing PartidoJugador entity
         *
         */
    public PartidoJugador getPartidoJugador(Long codigopartidoJugador)
        throws Exception;

    public List<PartidoJugador> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PartidoJugador> findPagePartidoJugador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPartidoJugador() throws Exception;

    public List<PartidoJugadorDTO> getDataPartidoJugador()
        throws Exception;
}
