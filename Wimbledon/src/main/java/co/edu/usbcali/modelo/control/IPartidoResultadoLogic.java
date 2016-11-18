package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.PartidoResultado;
import co.edu.usbcali.modelo.dto.PartidoResultadoDTO;

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
public interface IPartidoResultadoLogic {
    public List<PartidoResultado> getPartidoResultado()
        throws Exception;

    /**
         * Save an new PartidoResultado entity
         */
    public void savePartidoResultado(PartidoResultado entity)
        throws Exception;

    /**
         * Delete an existing PartidoResultado entity
         *
         */
    public void deletePartidoResultado(PartidoResultado entity)
        throws Exception;

    /**
        * Update an existing PartidoResultado entity
        *
        */
    public void updatePartidoResultado(PartidoResultado entity)
        throws Exception;

    /**
         * Load an existing PartidoResultado entity
         *
         */
    public PartidoResultado getPartidoResultado(Long codigopatridoResultado)
        throws Exception;

    public List<PartidoResultado> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PartidoResultado> findPagePartidoResultado(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPartidoResultado() throws Exception;

    public List<PartidoResultadoDTO> getDataPartidoResultado()
        throws Exception;
}
