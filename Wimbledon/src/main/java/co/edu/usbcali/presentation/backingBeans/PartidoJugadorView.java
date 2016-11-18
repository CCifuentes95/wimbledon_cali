package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.PartidoJugadorDTO;
import co.edu.usbcali.presentation.businessDelegate.*;
import co.edu.usbcali.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PartidoJugadorView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PartidoJugadorView.class);
    private InputText txtCodigojugador_Jugador;
    private InputText txtCodigopartido_Partido;
    private InputText txtCodigopartidoJugador;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PartidoJugadorDTO> data;
    private PartidoJugadorDTO selectedPartidoJugador;
    private PartidoJugador entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PartidoJugadorView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PartidoJugadorDTO partidoJugadorDTO = (PartidoJugadorDTO) e.getObject();

            if (txtCodigojugador_Jugador == null) {
                txtCodigojugador_Jugador = new InputText();
            }

            txtCodigojugador_Jugador.setValue(partidoJugadorDTO.getCodigojugador_Jugador());

            if (txtCodigopartido_Partido == null) {
                txtCodigopartido_Partido = new InputText();
            }

            txtCodigopartido_Partido.setValue(partidoJugadorDTO.getCodigopartido_Partido());

            if (txtCodigopartidoJugador == null) {
                txtCodigopartidoJugador = new InputText();
            }

            txtCodigopartidoJugador.setValue(partidoJugadorDTO.getCodigopartidoJugador());

            Long codigopartidoJugador = FacesUtils.checkLong(txtCodigopartidoJugador);
            entity = businessDelegatorView.getPartidoJugador(codigopartidoJugador);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPartidoJugador = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPartidoJugador = null;

        if (txtCodigojugador_Jugador != null) {
            txtCodigojugador_Jugador.setValue(null);
            txtCodigojugador_Jugador.setDisabled(true);
        }

        if (txtCodigopartido_Partido != null) {
            txtCodigopartido_Partido.setValue(null);
            txtCodigopartido_Partido.setDisabled(true);
        }

        if (txtCodigopartidoJugador != null) {
            txtCodigopartidoJugador.setValue(null);
            txtCodigopartidoJugador.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long codigopartidoJugador = FacesUtils.checkLong(txtCodigopartidoJugador);
            entity = (codigopartidoJugador != null)
                ? businessDelegatorView.getPartidoJugador(codigopartidoJugador)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigojugador_Jugador.setDisabled(false);
            txtCodigopartido_Partido.setDisabled(false);
            txtCodigopartidoJugador.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCodigojugador_Jugador.setValue(entity.getJugador()
                                                    .getCodigojugador());
            txtCodigojugador_Jugador.setDisabled(false);
            txtCodigopartido_Partido.setValue(entity.getPartido()
                                                    .getCodigopartido());
            txtCodigopartido_Partido.setDisabled(false);
            txtCodigopartidoJugador.setValue(entity.getCodigopartidoJugador());
            txtCodigopartidoJugador.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPartidoJugador = (PartidoJugadorDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPartidoJugador"));
        txtCodigojugador_Jugador.setValue(selectedPartidoJugador.getCodigojugador_Jugador());
        txtCodigojugador_Jugador.setDisabled(false);
        txtCodigopartido_Partido.setValue(selectedPartidoJugador.getCodigopartido_Partido());
        txtCodigopartido_Partido.setDisabled(false);
        txtCodigopartidoJugador.setValue(selectedPartidoJugador.getCodigopartidoJugador());
        txtCodigopartidoJugador.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPartidoJugador == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new PartidoJugador();

            Long codigopartidoJugador = FacesUtils.checkLong(txtCodigopartidoJugador);

            entity.setCodigopartidoJugador(codigopartidoJugador);
            entity.setJugador((FacesUtils.checkLong(txtCodigojugador_Jugador) != null)
                ? businessDelegatorView.getJugador(FacesUtils.checkLong(
                        txtCodigojugador_Jugador)) : null);
            entity.setPartido((FacesUtils.checkLong(txtCodigopartido_Partido) != null)
                ? businessDelegatorView.getPartido(FacesUtils.checkLong(
                        txtCodigopartido_Partido)) : null);
            businessDelegatorView.savePartidoJugador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long codigopartidoJugador = new Long(selectedPartidoJugador.getCodigopartidoJugador());
                entity = businessDelegatorView.getPartidoJugador(codigopartidoJugador);
            }

            entity.setJugador((FacesUtils.checkLong(txtCodigojugador_Jugador) != null)
                ? businessDelegatorView.getJugador(FacesUtils.checkLong(
                        txtCodigojugador_Jugador)) : null);
            entity.setPartido((FacesUtils.checkLong(txtCodigopartido_Partido) != null)
                ? businessDelegatorView.getPartido(FacesUtils.checkLong(
                        txtCodigopartido_Partido)) : null);
            businessDelegatorView.updatePartidoJugador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPartidoJugador = (PartidoJugadorDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPartidoJugador"));

            Long codigopartidoJugador = new Long(selectedPartidoJugador.getCodigopartidoJugador());
            entity = businessDelegatorView.getPartidoJugador(codigopartidoJugador);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigopartidoJugador = FacesUtils.checkLong(txtCodigopartidoJugador);
            entity = businessDelegatorView.getPartidoJugador(codigopartidoJugador);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePartidoJugador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedPartidoJugador = (PartidoJugadorDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPartidoJugador"));

            Long codigopartidoJugador = new Long(selectedPartidoJugador.getCodigopartidoJugador());
            entity = businessDelegatorView.getPartidoJugador(codigopartidoJugador);
            businessDelegatorView.deletePartidoJugador(entity);
            data.remove(selectedPartidoJugador);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigopartidoJugador,
        Long codigojugador_Jugador, Long codigopartido_Partido)
        throws Exception {
        try {
            businessDelegatorView.updatePartidoJugador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PartidoJugadorView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCodigojugador_Jugador() {
        return txtCodigojugador_Jugador;
    }

    public void setTxtCodigojugador_Jugador(InputText txtCodigojugador_Jugador) {
        this.txtCodigojugador_Jugador = txtCodigojugador_Jugador;
    }

    public InputText getTxtCodigopartido_Partido() {
        return txtCodigopartido_Partido;
    }

    public void setTxtCodigopartido_Partido(InputText txtCodigopartido_Partido) {
        this.txtCodigopartido_Partido = txtCodigopartido_Partido;
    }

    public InputText getTxtCodigopartidoJugador() {
        return txtCodigopartidoJugador;
    }

    public void setTxtCodigopartidoJugador(InputText txtCodigopartidoJugador) {
        this.txtCodigopartidoJugador = txtCodigopartidoJugador;
    }

    public List<PartidoJugadorDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPartidoJugador();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PartidoJugadorDTO> partidoJugadorDTO) {
        this.data = partidoJugadorDTO;
    }

    public PartidoJugadorDTO getSelectedPartidoJugador() {
        return selectedPartidoJugador;
    }

    public void setSelectedPartidoJugador(PartidoJugadorDTO partidoJugador) {
        this.selectedPartidoJugador = partidoJugador;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
