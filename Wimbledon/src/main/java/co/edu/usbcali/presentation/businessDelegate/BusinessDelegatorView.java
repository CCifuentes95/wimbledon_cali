package co.edu.usbcali.presentation.businessDelegate;

import co.edu.usbcali.modelo.Cancha;
import co.edu.usbcali.modelo.Empleado;
import co.edu.usbcali.modelo.Equipo;
import co.edu.usbcali.modelo.Jugador;
import co.edu.usbcali.modelo.Pais;
import co.edu.usbcali.modelo.Partido;
import co.edu.usbcali.modelo.PartidoJugador;
import co.edu.usbcali.modelo.PartidoResultado;
import co.edu.usbcali.modelo.PartidoUsuario;
import co.edu.usbcali.modelo.Resultado;
import co.edu.usbcali.modelo.Ronda;
import co.edu.usbcali.modelo.Tipoempleado;
import co.edu.usbcali.modelo.Tipotorneo;
import co.edu.usbcali.modelo.Torneo;
import co.edu.usbcali.modelo.Usuario;
import co.edu.usbcali.modelo.control.CanchaLogic;
import co.edu.usbcali.modelo.control.EmpleadoLogic;
import co.edu.usbcali.modelo.control.EquipoLogic;
import co.edu.usbcali.modelo.control.ICanchaLogic;
import co.edu.usbcali.modelo.control.IEmpleadoLogic;
import co.edu.usbcali.modelo.control.IEquipoLogic;
import co.edu.usbcali.modelo.control.IJugadorLogic;
import co.edu.usbcali.modelo.control.IPaisLogic;
import co.edu.usbcali.modelo.control.IPartidoJugadorLogic;
import co.edu.usbcali.modelo.control.IPartidoLogic;
import co.edu.usbcali.modelo.control.IPartidoResultadoLogic;
import co.edu.usbcali.modelo.control.IPartidoUsuarioLogic;
import co.edu.usbcali.modelo.control.IResultadoLogic;
import co.edu.usbcali.modelo.control.IRondaLogic;
import co.edu.usbcali.modelo.control.ITipoempleadoLogic;
import co.edu.usbcali.modelo.control.ITipotorneoLogic;
import co.edu.usbcali.modelo.control.ITorneoLogic;
import co.edu.usbcali.modelo.control.IUsuarioLogic;
import co.edu.usbcali.modelo.control.JugadorLogic;
import co.edu.usbcali.modelo.control.PaisLogic;
import co.edu.usbcali.modelo.control.PartidoJugadorLogic;
import co.edu.usbcali.modelo.control.PartidoLogic;
import co.edu.usbcali.modelo.control.PartidoResultadoLogic;
import co.edu.usbcali.modelo.control.PartidoUsuarioLogic;
import co.edu.usbcali.modelo.control.ResultadoLogic;
import co.edu.usbcali.modelo.control.RondaLogic;
import co.edu.usbcali.modelo.control.TipoempleadoLogic;
import co.edu.usbcali.modelo.control.TipotorneoLogic;
import co.edu.usbcali.modelo.control.TorneoLogic;
import co.edu.usbcali.modelo.control.UsuarioLogic;
import co.edu.usbcali.modelo.dto.CanchaDTO;
import co.edu.usbcali.modelo.dto.EmpleadoDTO;
import co.edu.usbcali.modelo.dto.EquipoDTO;
import co.edu.usbcali.modelo.dto.JugadorDTO;
import co.edu.usbcali.modelo.dto.PaisDTO;
import co.edu.usbcali.modelo.dto.PartidoDTO;
import co.edu.usbcali.modelo.dto.PartidoJugadorDTO;
import co.edu.usbcali.modelo.dto.PartidoResultadoDTO;
import co.edu.usbcali.modelo.dto.PartidoUsuarioDTO;
import co.edu.usbcali.modelo.dto.ResultadoDTO;
import co.edu.usbcali.modelo.dto.RondaDTO;
import co.edu.usbcali.modelo.dto.TipoempleadoDTO;
import co.edu.usbcali.modelo.dto.TipotorneoDTO;
import co.edu.usbcali.modelo.dto.TorneoDTO;
import co.edu.usbcali.modelo.dto.UsuarioDTO;
import co.edu.usbcali.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private ICanchaLogic canchaLogic;
    @Autowired
    private IEmpleadoLogic empleadoLogic;
    @Autowired
    private IEquipoLogic equipoLogic;
    @Autowired
    private IJugadorLogic jugadorLogic;
    @Autowired
    private IPaisLogic paisLogic;
    @Autowired
    private IPartidoLogic partidoLogic;
    @Autowired
    private IPartidoJugadorLogic partidoJugadorLogic;
    @Autowired
    private IPartidoResultadoLogic partidoResultadoLogic;
    @Autowired
    private IPartidoUsuarioLogic partidoUsuarioLogic;
    @Autowired
    private IResultadoLogic resultadoLogic;
    @Autowired
    private IRondaLogic rondaLogic;
    @Autowired
    private ITipoempleadoLogic tipoempleadoLogic;
    @Autowired
    private ITipotorneoLogic tipotorneoLogic;
    @Autowired
    private ITorneoLogic torneoLogic;
    @Autowired
    private IUsuarioLogic usuarioLogic;

    public List<Cancha> getCancha() throws Exception {
        return canchaLogic.getCancha();
    }

    public void saveCancha(Cancha entity) throws Exception {
        canchaLogic.saveCancha(entity);
    }

    public void deleteCancha(Cancha entity) throws Exception {
        canchaLogic.deleteCancha(entity);
    }

    public void updateCancha(Cancha entity) throws Exception {
        canchaLogic.updateCancha(entity);
    }

    public Cancha getCancha(Long codigocancha) throws Exception {
        Cancha cancha = null;

        try {
            cancha = canchaLogic.getCancha(codigocancha);
        } catch (Exception e) {
            throw e;
        }

        return cancha;
    }

    public List<Cancha> findByCriteriaInCancha(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return canchaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Cancha> findPageCancha(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return canchaLogic.findPageCancha(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberCancha() throws Exception {
        return canchaLogic.findTotalNumberCancha();
    }

    public List<CanchaDTO> getDataCancha() throws Exception {
        return canchaLogic.getDataCancha();
    }

    public List<Empleado> getEmpleado() throws Exception {
        return empleadoLogic.getEmpleado();
    }

    public void saveEmpleado(Empleado entity) throws Exception {
        empleadoLogic.saveEmpleado(entity);
    }

    public void deleteEmpleado(Empleado entity) throws Exception {
        empleadoLogic.deleteEmpleado(entity);
    }

    public void updateEmpleado(Empleado entity) throws Exception {
        empleadoLogic.updateEmpleado(entity);
    }

    public Empleado getEmpleado(Long codigoempleado) throws Exception {
        Empleado empleado = null;

        try {
            empleado = empleadoLogic.getEmpleado(codigoempleado);
        } catch (Exception e) {
            throw e;
        }

        return empleado;
    }

    public List<Empleado> findByCriteriaInEmpleado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return empleadoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Empleado> findPageEmpleado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return empleadoLogic.findPageEmpleado(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberEmpleado() throws Exception {
        return empleadoLogic.findTotalNumberEmpleado();
    }

    public List<EmpleadoDTO> getDataEmpleado() throws Exception {
        return empleadoLogic.getDataEmpleado();
    }

    public List<Equipo> getEquipo() throws Exception {
        return equipoLogic.getEquipo();
    }

    public void saveEquipo(Equipo entity) throws Exception {
        equipoLogic.saveEquipo(entity);
    }

    public void deleteEquipo(Equipo entity) throws Exception {
        equipoLogic.deleteEquipo(entity);
    }

    public void updateEquipo(Equipo entity) throws Exception {
        equipoLogic.updateEquipo(entity);
    }

    public Equipo getEquipo(Long codigoequipo) throws Exception {
        Equipo equipo = null;

        try {
            equipo = equipoLogic.getEquipo(codigoequipo);
        } catch (Exception e) {
            throw e;
        }

        return equipo;
    }

    public List<Equipo> findByCriteriaInEquipo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return equipoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Equipo> findPageEquipo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return equipoLogic.findPageEquipo(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberEquipo() throws Exception {
        return equipoLogic.findTotalNumberEquipo();
    }

    public List<EquipoDTO> getDataEquipo() throws Exception {
        return equipoLogic.getDataEquipo();
    }

    public List<Jugador> getJugador() throws Exception {
        return jugadorLogic.getJugador();
    }

    public void saveJugador(Jugador entity) throws Exception {
        jugadorLogic.saveJugador(entity);
    }

    public void deleteJugador(Jugador entity) throws Exception {
        jugadorLogic.deleteJugador(entity);
    }

    public void updateJugador(Jugador entity) throws Exception {
        jugadorLogic.updateJugador(entity);
    }

    public Jugador getJugador(Long codigojugador) throws Exception {
        Jugador jugador = null;

        try {
            jugador = jugadorLogic.getJugador(codigojugador);
        } catch (Exception e) {
            throw e;
        }

        return jugador;
    }

    public List<Jugador> findByCriteriaInJugador(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return jugadorLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Jugador> findPageJugador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return jugadorLogic.findPageJugador(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberJugador() throws Exception {
        return jugadorLogic.findTotalNumberJugador();
    }

    public List<JugadorDTO> getDataJugador() throws Exception {
        return jugadorLogic.getDataJugador();
    }

    public List<Pais> getPais() throws Exception {
        return paisLogic.getPais();
    }

    public void savePais(Pais entity) throws Exception {
        paisLogic.savePais(entity);
    }

    public void deletePais(Pais entity) throws Exception {
        paisLogic.deletePais(entity);
    }

    public void updatePais(Pais entity) throws Exception {
        paisLogic.updatePais(entity);
    }

    public Pais getPais(Long codigopais) throws Exception {
        Pais pais = null;

        try {
            pais = paisLogic.getPais(codigopais);
        } catch (Exception e) {
            throw e;
        }

        return pais;
    }

    public List<Pais> findByCriteriaInPais(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return paisLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Pais> findPagePais(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return paisLogic.findPagePais(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberPais() throws Exception {
        return paisLogic.findTotalNumberPais();
    }

    public List<PaisDTO> getDataPais() throws Exception {
        return paisLogic.getDataPais();
    }

    public List<Partido> getPartido() throws Exception {
        return partidoLogic.getPartido();
    }

    public void savePartido(Partido entity) throws Exception {
        partidoLogic.savePartido(entity);
    }

    public void deletePartido(Partido entity) throws Exception {
        partidoLogic.deletePartido(entity);
    }

    public void updatePartido(Partido entity) throws Exception {
        partidoLogic.updatePartido(entity);
    }

    public Partido getPartido(Long codigopartido) throws Exception {
        Partido partido = null;

        try {
            partido = partidoLogic.getPartido(codigopartido);
        } catch (Exception e) {
            throw e;
        }

        return partido;
    }

    public List<Partido> findByCriteriaInPartido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return partidoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Partido> findPagePartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return partidoLogic.findPagePartido(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPartido() throws Exception {
        return partidoLogic.findTotalNumberPartido();
    }

    public List<PartidoDTO> getDataPartido() throws Exception {
        return partidoLogic.getDataPartido();
    }

    public List<PartidoJugador> getPartidoJugador() throws Exception {
        return partidoJugadorLogic.getPartidoJugador();
    }

    public void savePartidoJugador(PartidoJugador entity)
        throws Exception {
        partidoJugadorLogic.savePartidoJugador(entity);
    }

    public void deletePartidoJugador(PartidoJugador entity)
        throws Exception {
        partidoJugadorLogic.deletePartidoJugador(entity);
    }

    public void updatePartidoJugador(PartidoJugador entity)
        throws Exception {
        partidoJugadorLogic.updatePartidoJugador(entity);
    }

    public PartidoJugador getPartidoJugador(Long codigopartidoJugador)
        throws Exception {
        PartidoJugador partidoJugador = null;

        try {
            partidoJugador = partidoJugadorLogic.getPartidoJugador(codigopartidoJugador);
        } catch (Exception e) {
            throw e;
        }

        return partidoJugador;
    }

    public List<PartidoJugador> findByCriteriaInPartidoJugador(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return partidoJugadorLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PartidoJugador> findPagePartidoJugador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return partidoJugadorLogic.findPagePartidoJugador(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPartidoJugador() throws Exception {
        return partidoJugadorLogic.findTotalNumberPartidoJugador();
    }

    public List<PartidoJugadorDTO> getDataPartidoJugador()
        throws Exception {
        return partidoJugadorLogic.getDataPartidoJugador();
    }

    public List<PartidoResultado> getPartidoResultado()
        throws Exception {
        return partidoResultadoLogic.getPartidoResultado();
    }

    public void savePartidoResultado(PartidoResultado entity)
        throws Exception {
        partidoResultadoLogic.savePartidoResultado(entity);
    }

    public void deletePartidoResultado(PartidoResultado entity)
        throws Exception {
        partidoResultadoLogic.deletePartidoResultado(entity);
    }

    public void updatePartidoResultado(PartidoResultado entity)
        throws Exception {
        partidoResultadoLogic.updatePartidoResultado(entity);
    }

    public PartidoResultado getPartidoResultado(Long codigopatridoResultado)
        throws Exception {
        PartidoResultado partidoResultado = null;

        try {
            partidoResultado = partidoResultadoLogic.getPartidoResultado(codigopatridoResultado);
        } catch (Exception e) {
            throw e;
        }

        return partidoResultado;
    }

    public List<PartidoResultado> findByCriteriaInPartidoResultado(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return partidoResultadoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<PartidoResultado> findPagePartidoResultado(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return partidoResultadoLogic.findPagePartidoResultado(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPartidoResultado() throws Exception {
        return partidoResultadoLogic.findTotalNumberPartidoResultado();
    }

    public List<PartidoResultadoDTO> getDataPartidoResultado()
        throws Exception {
        return partidoResultadoLogic.getDataPartidoResultado();
    }

    public List<PartidoUsuario> getPartidoUsuario() throws Exception {
        return partidoUsuarioLogic.getPartidoUsuario();
    }

    public void savePartidoUsuario(PartidoUsuario entity)
        throws Exception {
        partidoUsuarioLogic.savePartidoUsuario(entity);
    }

    public void deletePartidoUsuario(PartidoUsuario entity)
        throws Exception {
        partidoUsuarioLogic.deletePartidoUsuario(entity);
    }

    public void updatePartidoUsuario(PartidoUsuario entity)
        throws Exception {
        partidoUsuarioLogic.updatePartidoUsuario(entity);
    }

    public PartidoUsuario getPartidoUsuario(Long codigopartidoUsuario)
        throws Exception {
        PartidoUsuario partidoUsuario = null;

        try {
            partidoUsuario = partidoUsuarioLogic.getPartidoUsuario(codigopartidoUsuario);
        } catch (Exception e) {
            throw e;
        }

        return partidoUsuario;
    }

    public List<PartidoUsuario> findByCriteriaInPartidoUsuario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return partidoUsuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<PartidoUsuario> findPagePartidoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return partidoUsuarioLogic.findPagePartidoUsuario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPartidoUsuario() throws Exception {
        return partidoUsuarioLogic.findTotalNumberPartidoUsuario();
    }

    public List<PartidoUsuarioDTO> getDataPartidoUsuario()
        throws Exception {
        return partidoUsuarioLogic.getDataPartidoUsuario();
    }

    public List<Resultado> getResultado() throws Exception {
        return resultadoLogic.getResultado();
    }

    public void saveResultado(Resultado entity) throws Exception {
        resultadoLogic.saveResultado(entity);
    }

    public void deleteResultado(Resultado entity) throws Exception {
        resultadoLogic.deleteResultado(entity);
    }

    public void updateResultado(Resultado entity) throws Exception {
        resultadoLogic.updateResultado(entity);
    }

    public Resultado getResultado(Long codigoresultado)
        throws Exception {
        Resultado resultado = null;

        try {
            resultado = resultadoLogic.getResultado(codigoresultado);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }

    public List<Resultado> findByCriteriaInResultado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return resultadoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Resultado> findPageResultado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return resultadoLogic.findPageResultado(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberResultado() throws Exception {
        return resultadoLogic.findTotalNumberResultado();
    }

    public List<ResultadoDTO> getDataResultado() throws Exception {
        return resultadoLogic.getDataResultado();
    }

    public List<Ronda> getRonda() throws Exception {
        return rondaLogic.getRonda();
    }

    public void saveRonda(Ronda entity) throws Exception {
        rondaLogic.saveRonda(entity);
    }

    public void deleteRonda(Ronda entity) throws Exception {
        rondaLogic.deleteRonda(entity);
    }

    public void updateRonda(Ronda entity) throws Exception {
        rondaLogic.updateRonda(entity);
    }

    public Ronda getRonda(Long codigoronda) throws Exception {
        Ronda ronda = null;

        try {
            ronda = rondaLogic.getRonda(codigoronda);
        } catch (Exception e) {
            throw e;
        }

        return ronda;
    }

    public List<Ronda> findByCriteriaInRonda(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return rondaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Ronda> findPageRonda(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return rondaLogic.findPageRonda(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberRonda() throws Exception {
        return rondaLogic.findTotalNumberRonda();
    }

    public List<RondaDTO> getDataRonda() throws Exception {
        return rondaLogic.getDataRonda();
    }

    public List<Tipoempleado> getTipoempleado() throws Exception {
        return tipoempleadoLogic.getTipoempleado();
    }

    public void saveTipoempleado(Tipoempleado entity) throws Exception {
        tipoempleadoLogic.saveTipoempleado(entity);
    }

    public void deleteTipoempleado(Tipoempleado entity)
        throws Exception {
        tipoempleadoLogic.deleteTipoempleado(entity);
    }

    public void updateTipoempleado(Tipoempleado entity)
        throws Exception {
        tipoempleadoLogic.updateTipoempleado(entity);
    }

    public Tipoempleado getTipoempleado(Long codigotipoempleado)
        throws Exception {
        Tipoempleado tipoempleado = null;

        try {
            tipoempleado = tipoempleadoLogic.getTipoempleado(codigotipoempleado);
        } catch (Exception e) {
            throw e;
        }

        return tipoempleado;
    }

    public List<Tipoempleado> findByCriteriaInTipoempleado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return tipoempleadoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Tipoempleado> findPageTipoempleado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipoempleadoLogic.findPageTipoempleado(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoempleado() throws Exception {
        return tipoempleadoLogic.findTotalNumberTipoempleado();
    }

    public List<TipoempleadoDTO> getDataTipoempleado()
        throws Exception {
        return tipoempleadoLogic.getDataTipoempleado();
    }

    public List<Tipotorneo> getTipotorneo() throws Exception {
        return tipotorneoLogic.getTipotorneo();
    }

    public void saveTipotorneo(Tipotorneo entity) throws Exception {
        tipotorneoLogic.saveTipotorneo(entity);
    }

    public void deleteTipotorneo(Tipotorneo entity) throws Exception {
        tipotorneoLogic.deleteTipotorneo(entity);
    }

    public void updateTipotorneo(Tipotorneo entity) throws Exception {
        tipotorneoLogic.updateTipotorneo(entity);
    }

    public Tipotorneo getTipotorneo(Long codigotipotorneo)
        throws Exception {
        Tipotorneo tipotorneo = null;

        try {
            tipotorneo = tipotorneoLogic.getTipotorneo(codigotipotorneo);
        } catch (Exception e) {
            throw e;
        }

        return tipotorneo;
    }

    public List<Tipotorneo> findByCriteriaInTipotorneo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return tipotorneoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Tipotorneo> findPageTipotorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipotorneoLogic.findPageTipotorneo(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipotorneo() throws Exception {
        return tipotorneoLogic.findTotalNumberTipotorneo();
    }

    public List<TipotorneoDTO> getDataTipotorneo() throws Exception {
        return tipotorneoLogic.getDataTipotorneo();
    }

    public List<Torneo> getTorneo() throws Exception {
        return torneoLogic.getTorneo();
    }

    public void saveTorneo(Torneo entity) throws Exception {
        torneoLogic.saveTorneo(entity);
    }

    public void deleteTorneo(Torneo entity) throws Exception {
        torneoLogic.deleteTorneo(entity);
    }

    public void updateTorneo(Torneo entity) throws Exception {
        torneoLogic.updateTorneo(entity);
    }

    public Torneo getTorneo(Long codigotorneo) throws Exception {
        Torneo torneo = null;

        try {
            torneo = torneoLogic.getTorneo(codigotorneo);
        } catch (Exception e) {
            throw e;
        }

        return torneo;
    }

    public List<Torneo> findByCriteriaInTorneo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return torneoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Torneo> findPageTorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return torneoLogic.findPageTorneo(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberTorneo() throws Exception {
        return torneoLogic.findTotalNumberTorneo();
    }

    public List<TorneoDTO> getDataTorneo() throws Exception {
        return torneoLogic.getDataTorneo();
    }

    public List<Usuario> getUsuario() throws Exception {
        return usuarioLogic.getUsuario();
    }

    public void saveUsuario(Usuario entity) throws Exception {
        usuarioLogic.saveUsuario(entity);
    }

    public void deleteUsuario(Usuario entity) throws Exception {
        usuarioLogic.deleteUsuario(entity);
    }

    public void updateUsuario(Usuario entity) throws Exception {
        usuarioLogic.updateUsuario(entity);
    }

    public Usuario getUsuario(Long codigousuario) throws Exception {
        Usuario usuario = null;

        try {
            usuario = usuarioLogic.getUsuario(codigousuario);
        } catch (Exception e) {
            throw e;
        }

        return usuario;
    }

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usuarioLogic.findPageUsuario(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsuario() throws Exception {
        return usuarioLogic.findTotalNumberUsuario();
    }

    public List<UsuarioDTO> getDataUsuario() throws Exception {
        return usuarioLogic.getDataUsuario();
    }

	public List<Ronda> findRondasByTorneo(Long idTorneo) throws Exception {
		return rondaLogic.findRondasByTorneo(idTorneo);
	}
}
