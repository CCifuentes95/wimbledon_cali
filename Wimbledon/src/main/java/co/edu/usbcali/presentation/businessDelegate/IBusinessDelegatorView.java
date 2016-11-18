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

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<Cancha> getCancha() throws Exception;

    public void saveCancha(Cancha entity) throws Exception;

    public void deleteCancha(Cancha entity) throws Exception;

    public void updateCancha(Cancha entity) throws Exception;

    public Cancha getCancha(Long codigocancha) throws Exception;

    public List<Cancha> findByCriteriaInCancha(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cancha> findPageCancha(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCancha() throws Exception;

    public List<CanchaDTO> getDataCancha() throws Exception;

    public List<Empleado> getEmpleado() throws Exception;

    public void saveEmpleado(Empleado entity) throws Exception;

    public void deleteEmpleado(Empleado entity) throws Exception;

    public void updateEmpleado(Empleado entity) throws Exception;

    public Empleado getEmpleado(Long codigoempleado) throws Exception;

    public List<Empleado> findByCriteriaInEmpleado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Empleado> findPageEmpleado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEmpleado() throws Exception;

    public List<EmpleadoDTO> getDataEmpleado() throws Exception;

    public List<Equipo> getEquipo() throws Exception;

    public void saveEquipo(Equipo entity) throws Exception;

    public void deleteEquipo(Equipo entity) throws Exception;

    public void updateEquipo(Equipo entity) throws Exception;

    public Equipo getEquipo(Long codigoequipo) throws Exception;

    public List<Equipo> findByCriteriaInEquipo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Equipo> findPageEquipo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEquipo() throws Exception;

    public List<EquipoDTO> getDataEquipo() throws Exception;

    public List<Jugador> getJugador() throws Exception;

    public void saveJugador(Jugador entity) throws Exception;

    public void deleteJugador(Jugador entity) throws Exception;

    public void updateJugador(Jugador entity) throws Exception;

    public Jugador getJugador(Long codigojugador) throws Exception;

    public List<Jugador> findByCriteriaInJugador(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Jugador> findPageJugador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberJugador() throws Exception;

    public List<JugadorDTO> getDataJugador() throws Exception;

    public List<Pais> getPais() throws Exception;

    public void savePais(Pais entity) throws Exception;

    public void deletePais(Pais entity) throws Exception;

    public void updatePais(Pais entity) throws Exception;

    public Pais getPais(Long codigopais) throws Exception;

    public List<Pais> findByCriteriaInPais(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pais> findPagePais(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPais() throws Exception;

    public List<PaisDTO> getDataPais() throws Exception;

    public List<Partido> getPartido() throws Exception;

    public void savePartido(Partido entity) throws Exception;

    public void deletePartido(Partido entity) throws Exception;

    public void updatePartido(Partido entity) throws Exception;

    public Partido getPartido(Long codigopartido) throws Exception;

    public List<Partido> findByCriteriaInPartido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Partido> findPagePartido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPartido() throws Exception;

    public List<PartidoDTO> getDataPartido() throws Exception;

    public List<PartidoJugador> getPartidoJugador() throws Exception;

    public void savePartidoJugador(PartidoJugador entity)
        throws Exception;

    public void deletePartidoJugador(PartidoJugador entity)
        throws Exception;

    public void updatePartidoJugador(PartidoJugador entity)
        throws Exception;

    public PartidoJugador getPartidoJugador(Long codigopartidoJugador)
        throws Exception;

    public List<PartidoJugador> findByCriteriaInPartidoJugador(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<PartidoJugador> findPagePartidoJugador(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPartidoJugador() throws Exception;

    public List<PartidoJugadorDTO> getDataPartidoJugador()
        throws Exception;

    public List<PartidoResultado> getPartidoResultado()
        throws Exception;

    public void savePartidoResultado(PartidoResultado entity)
        throws Exception;

    public void deletePartidoResultado(PartidoResultado entity)
        throws Exception;

    public void updatePartidoResultado(PartidoResultado entity)
        throws Exception;

    public PartidoResultado getPartidoResultado(Long codigopatridoResultado)
        throws Exception;

    public List<PartidoResultado> findByCriteriaInPartidoResultado(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<PartidoResultado> findPagePartidoResultado(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPartidoResultado() throws Exception;

    public List<PartidoResultadoDTO> getDataPartidoResultado()
        throws Exception;

    public List<PartidoUsuario> getPartidoUsuario() throws Exception;

    public void savePartidoUsuario(PartidoUsuario entity)
        throws Exception;

    public void deletePartidoUsuario(PartidoUsuario entity)
        throws Exception;

    public void updatePartidoUsuario(PartidoUsuario entity)
        throws Exception;

    public PartidoUsuario getPartidoUsuario(Long codigopartidoUsuario)
        throws Exception;

    public List<PartidoUsuario> findByCriteriaInPartidoUsuario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<PartidoUsuario> findPagePartidoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPartidoUsuario() throws Exception;

    public List<PartidoUsuarioDTO> getDataPartidoUsuario()
        throws Exception;

    public List<Resultado> getResultado() throws Exception;

    public void saveResultado(Resultado entity) throws Exception;

    public void deleteResultado(Resultado entity) throws Exception;

    public void updateResultado(Resultado entity) throws Exception;

    public Resultado getResultado(Long codigoresultado)
        throws Exception;

    public List<Resultado> findByCriteriaInResultado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Resultado> findPageResultado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberResultado() throws Exception;

    public List<ResultadoDTO> getDataResultado() throws Exception;

    public List<Ronda> getRonda() throws Exception;

    public void saveRonda(Ronda entity) throws Exception;

    public void deleteRonda(Ronda entity) throws Exception;

    public void updateRonda(Ronda entity) throws Exception;

    public Ronda getRonda(Long codigoronda) throws Exception;

    public List<Ronda> findByCriteriaInRonda(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Ronda> findPageRonda(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRonda() throws Exception;

    public List<RondaDTO> getDataRonda() throws Exception;

    public List<Tipoempleado> getTipoempleado() throws Exception;

    public void saveTipoempleado(Tipoempleado entity) throws Exception;

    public void deleteTipoempleado(Tipoempleado entity)
        throws Exception;

    public void updateTipoempleado(Tipoempleado entity)
        throws Exception;

    public Tipoempleado getTipoempleado(Long codigotipoempleado)
        throws Exception;

    public List<Tipoempleado> findByCriteriaInTipoempleado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tipoempleado> findPageTipoempleado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoempleado() throws Exception;

    public List<TipoempleadoDTO> getDataTipoempleado()
        throws Exception;

    public List<Tipotorneo> getTipotorneo() throws Exception;

    public void saveTipotorneo(Tipotorneo entity) throws Exception;

    public void deleteTipotorneo(Tipotorneo entity) throws Exception;

    public void updateTipotorneo(Tipotorneo entity) throws Exception;

    public Tipotorneo getTipotorneo(Long codigotipotorneo)
        throws Exception;

    public List<Tipotorneo> findByCriteriaInTipotorneo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tipotorneo> findPageTipotorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipotorneo() throws Exception;

    public List<TipotorneoDTO> getDataTipotorneo() throws Exception;

    public List<Torneo> getTorneo() throws Exception;

    public void saveTorneo(Torneo entity) throws Exception;

    public void deleteTorneo(Torneo entity) throws Exception;

    public void updateTorneo(Torneo entity) throws Exception;

    public Torneo getTorneo(Long codigotorneo) throws Exception;

    public List<Torneo> findByCriteriaInTorneo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Torneo> findPageTorneo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTorneo() throws Exception;

    public List<TorneoDTO> getDataTorneo() throws Exception;

    public List<Usuario> getUsuario() throws Exception;

    public void saveUsuario(Usuario entity) throws Exception;

    public void deleteUsuario(Usuario entity) throws Exception;

    public void updateUsuario(Usuario entity) throws Exception;

    public Usuario getUsuario(Long codigousuario) throws Exception;

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;
}
