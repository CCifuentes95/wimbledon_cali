package co.edu.usbcali.modelo.control;

import co.edu.usbcali.dataaccess.dao.*;
import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.PartidoDTO;
import co.edu.usbcali.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("PartidoLogic")
public class PartidoLogic implements IPartidoLogic {
    private static final Logger log = LoggerFactory.getLogger(PartidoLogic.class);

    /**
     * DAO injected by Spring that manages Partido entities
     *
     */
    @Autowired
    private IPartidoDAO partidoDAO;

    /**
    * DAO injected by Spring that manages PartidoJugador entities
    *
    */
    @Autowired
    private IPartidoJugadorDAO partidoJugadorDAO;

    /**
    * DAO injected by Spring that manages PartidoResultado entities
    *
    */
    @Autowired
    private IPartidoResultadoDAO partidoResultadoDAO;

    /**
    * DAO injected by Spring that manages PartidoUsuario entities
    *
    */
    @Autowired
    private IPartidoUsuarioDAO partidoUsuarioDAO;

    /**
    * Logic injected by Spring that manages Cancha entities
    *
    */
    @Autowired
    ICanchaLogic logicCancha1;

    /**
    * Logic injected by Spring that manages Empleado entities
    *
    */
    @Autowired
    IEmpleadoLogic logicEmpleado2;

    /**
    * Logic injected by Spring that manages Ronda entities
    *
    */
    @Autowired
    IRondaLogic logicRonda3;

    @Transactional(readOnly = true)
    public List<Partido> getPartido() throws Exception {
        log.debug("finding all Partido instances");

        List<Partido> list = new ArrayList<Partido>();

        try {
            list = partidoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Partido failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Partido");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePartido(Partido entity) throws Exception {
        log.debug("saving Partido instance");

        try {
            if (entity.getCancha() == null) {
                throw new ZMessManager().new ForeignException("cancha");
            }

            if (entity.getEmpleado() == null) {
                throw new ZMessManager().new ForeignException("empleado");
            }

            if (entity.getRonda() == null) {
                throw new ZMessManager().new ForeignException("ronda");
            }

            if (entity.getCodigopartido() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigopartido");
            }

            if (entity.getFecha() == null) {
                throw new ZMessManager().new EmptyFieldException("fecha");
            }

            if (entity.getGanador() == null) {
                throw new ZMessManager().new EmptyFieldException("ganador");
            }

            if ((entity.getGanador() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGanador(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("ganador");
            }

            if (entity.getCancha().getCodigocancha() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigocancha_Cancha");
            }

            if (entity.getEmpleado().getCodigoempleado() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigoempleado_Empleado");
            }

            if (entity.getRonda().getCodigoronda() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigoronda_Ronda");
            }

            if (getPartido(entity.getCodigopartido()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            partidoDAO.save(entity);

            log.debug("save Partido successful");
        } catch (Exception e) {
            log.error("save Partido failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePartido(Partido entity) throws Exception {
        log.debug("deleting Partido instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Partido");
        }

        if (entity.getCodigopartido() == null) {
            throw new ZMessManager().new EmptyFieldException("codigopartido");
        }

        List<PartidoJugador> partidoJugadors = null;
        List<PartidoResultado> partidoResultados = null;
        List<PartidoUsuario> partidoUsuarios = null;

        try {
            partidoJugadors = partidoJugadorDAO.findByProperty("partido.codigopartido",
                    entity.getCodigopartido());

            if (Utilities.validationsList(partidoJugadors) == true) {
                throw new ZMessManager().new DeletingException(
                    "partidoJugadors");
            }

            partidoResultados = partidoResultadoDAO.findByProperty("partido.codigopartido",
                    entity.getCodigopartido());

            if (Utilities.validationsList(partidoResultados) == true) {
                throw new ZMessManager().new DeletingException(
                    "partidoResultados");
            }

            partidoUsuarios = partidoUsuarioDAO.findByProperty("partido.codigopartido",
                    entity.getCodigopartido());

            if (Utilities.validationsList(partidoUsuarios) == true) {
                throw new ZMessManager().new DeletingException(
                    "partidoUsuarios");
            }

            partidoDAO.delete(entity);

            log.debug("delete Partido successful");
        } catch (Exception e) {
            log.error("delete Partido failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePartido(Partido entity) throws Exception {
        log.debug("updating Partido instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Partido");
            }

            if (entity.getCancha() == null) {
                throw new ZMessManager().new ForeignException("cancha");
            }

            if (entity.getEmpleado() == null) {
                throw new ZMessManager().new ForeignException("empleado");
            }

            if (entity.getRonda() == null) {
                throw new ZMessManager().new ForeignException("ronda");
            }

            if (entity.getCodigopartido() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigopartido");
            }

            if (entity.getFecha() == null) {
                throw new ZMessManager().new EmptyFieldException("fecha");
            }

            if (entity.getGanador() == null) {
                throw new ZMessManager().new EmptyFieldException("ganador");
            }

            if ((entity.getGanador() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getGanador(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("ganador");
            }

            if (entity.getCancha().getCodigocancha() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigocancha_Cancha");
            }

            if (entity.getEmpleado().getCodigoempleado() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigoempleado_Empleado");
            }

            if (entity.getRonda().getCodigoronda() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigoronda_Ronda");
            }

            partidoDAO.update(entity);

            log.debug("update Partido successful");
        } catch (Exception e) {
            log.error("update Partido failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<PartidoDTO> getDataPartido() throws Exception {
        try {
            List<Partido> partido = partidoDAO.findAll();

            List<PartidoDTO> partidoDTO = new ArrayList<PartidoDTO>();

            for (Partido partidoTmp : partido) {
                PartidoDTO partidoDTO2 = new PartidoDTO();

                partidoDTO2.setCodigopartido(partidoTmp.getCodigopartido());
                partidoDTO2.setFecha(partidoTmp.getFecha());
                partidoDTO2.setGanador((partidoTmp.getGanador() != null)
                    ? partidoTmp.getGanador() : null);

                if (partidoTmp.getCancha() != null) {
                    partidoDTO2.setCodigocancha_Cancha(partidoTmp.getCancha()
                                                                 .getCodigocancha());
                } else {
                    partidoDTO2.setCodigocancha_Cancha(null);
                }

                if (partidoTmp.getEmpleado() != null) {
                    partidoDTO2.setCodigoempleado_Empleado(partidoTmp.getEmpleado()
                                                                     .getCodigoempleado());
                } else {
                    partidoDTO2.setCodigoempleado_Empleado(null);
                }

                if (partidoTmp.getRonda() != null) {
                    partidoDTO2.setCodigoronda_Ronda(partidoTmp.getRonda()
                                                               .getCodigoronda());
                } else {
                    partidoDTO2.setCodigoronda_Ronda(null);
                }

                partidoDTO.add(partidoDTO2);
            }

            return partidoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Partido getPartido(Long codigopartido) throws Exception {
        log.debug("getting Partido instance");

        Partido entity = null;

        try {
            entity = partidoDAO.findById(codigopartido);
        } catch (Exception e) {
            log.error("get Partido failed", e);
            throw new ZMessManager().new FindingException("Partido");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Partido> findPagePartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Partido> entity = null;

        try {
            entity = partidoDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Partido Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPartido() throws Exception {
        Long entity = null;

        try {
            entity = partidoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Partido Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Partido> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Partido> list = new ArrayList<Partido>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = partidoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
