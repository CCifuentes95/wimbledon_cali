package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.Jugador;
import co.edu.usbcali.modelo.dto.JugadorDTO;

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
public interface IJugadorLogic {
    public List<Jugador> getJugador() throws Exception;

    /**
         * Save an new Jugador entity
         */
    public void saveJugador(Jugador entity) throws Exception;

    /**
         * Delete an existing Jugador entity
         *
         */
    public void deleteJugador(Jugador entity) throws Exception;

    /**
        * Update an existing Jugador entity
        *
        */
    public void updateJugador(Jugador entity) throws Exception;

    /**
         * Load an existing Jugador entity
         *
         */
    public Jugador getJugador(Long codigojugador) throws Exception;

    public List<Jugador> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Jugador> findPageJugador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberJugador() throws Exception;

    public List<JugadorDTO> getDataJugador() throws Exception;
}
