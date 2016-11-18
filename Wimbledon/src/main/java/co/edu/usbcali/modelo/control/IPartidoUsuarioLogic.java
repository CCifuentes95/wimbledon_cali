package co.edu.usbcali.modelo.control;

import co.edu.usbcali.modelo.PartidoUsuario;
import co.edu.usbcali.modelo.dto.PartidoUsuarioDTO;

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
public interface IPartidoUsuarioLogic {
    public List<PartidoUsuario> getPartidoUsuario() throws Exception;

    /**
         * Save an new PartidoUsuario entity
         */
    public void savePartidoUsuario(PartidoUsuario entity)
        throws Exception;

    /**
         * Delete an existing PartidoUsuario entity
         *
         */
    public void deletePartidoUsuario(PartidoUsuario entity)
        throws Exception;

    /**
        * Update an existing PartidoUsuario entity
        *
        */
    public void updatePartidoUsuario(PartidoUsuario entity)
        throws Exception;

    /**
         * Load an existing PartidoUsuario entity
         *
         */
    public PartidoUsuario getPartidoUsuario(Long codigopartidoUsuario)
        throws Exception;

    public List<PartidoUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PartidoUsuario> findPagePartidoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPartidoUsuario() throws Exception;

    public List<PartidoUsuarioDTO> getDataPartidoUsuario()
        throws Exception;
}
