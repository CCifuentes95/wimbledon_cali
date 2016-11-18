package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.EquipoDTO;
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
public class EquipoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EquipoView.class);
    private InputText txtNombre;
    private InputText txtCodigojugador_Jugador;
    private InputText txtCodigoequipo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<EquipoDTO> data;
    private EquipoDTO selectedEquipo;
    private Equipo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public EquipoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            EquipoDTO equipoDTO = (EquipoDTO) e.getObject();

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(equipoDTO.getNombre());

            if (txtCodigojugador_Jugador == null) {
                txtCodigojugador_Jugador = new InputText();
            }

            txtCodigojugador_Jugador.setValue(equipoDTO.getCodigojugador_Jugador());

            if (txtCodigoequipo == null) {
                txtCodigoequipo = new InputText();
            }

            txtCodigoequipo.setValue(equipoDTO.getCodigoequipo());

            Long codigoequipo = FacesUtils.checkLong(txtCodigoequipo);
            entity = businessDelegatorView.getEquipo(codigoequipo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedEquipo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedEquipo = null;

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtCodigojugador_Jugador != null) {
            txtCodigojugador_Jugador.setValue(null);
            txtCodigojugador_Jugador.setDisabled(true);
        }

        if (txtCodigoequipo != null) {
            txtCodigoequipo.setValue(null);
            txtCodigoequipo.setDisabled(false);
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
            Long codigoequipo = FacesUtils.checkLong(txtCodigoequipo);
            entity = (codigoequipo != null)
                ? businessDelegatorView.getEquipo(codigoequipo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtNombre.setDisabled(false);
            txtCodigojugador_Jugador.setDisabled(false);
            txtCodigoequipo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtCodigojugador_Jugador.setValue(entity.getJugador()
                                                    .getCodigojugador());
            txtCodigojugador_Jugador.setDisabled(false);
            txtCodigoequipo.setValue(entity.getCodigoequipo());
            txtCodigoequipo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedEquipo = (EquipoDTO) (evt.getComponent().getAttributes()
                                         .get("selectedEquipo"));
        txtNombre.setValue(selectedEquipo.getNombre());
        txtNombre.setDisabled(false);
        txtCodigojugador_Jugador.setValue(selectedEquipo.getCodigojugador_Jugador());
        txtCodigojugador_Jugador.setDisabled(false);
        txtCodigoequipo.setValue(selectedEquipo.getCodigoequipo());
        txtCodigoequipo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedEquipo == null) && (entity == null)) {
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
            entity = new Equipo();

            Long codigoequipo = FacesUtils.checkLong(txtCodigoequipo);

            entity.setCodigoequipo(codigoequipo);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setJugador((FacesUtils.checkLong(txtCodigojugador_Jugador) != null)
                ? businessDelegatorView.getJugador(FacesUtils.checkLong(
                        txtCodigojugador_Jugador)) : null);
            businessDelegatorView.saveEquipo(entity);
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
                Long codigoequipo = new Long(selectedEquipo.getCodigoequipo());
                entity = businessDelegatorView.getEquipo(codigoequipo);
            }

            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setJugador((FacesUtils.checkLong(txtCodigojugador_Jugador) != null)
                ? businessDelegatorView.getJugador(FacesUtils.checkLong(
                        txtCodigojugador_Jugador)) : null);
            businessDelegatorView.updateEquipo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedEquipo = (EquipoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedEquipo"));

            Long codigoequipo = new Long(selectedEquipo.getCodigoequipo());
            entity = businessDelegatorView.getEquipo(codigoequipo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigoequipo = FacesUtils.checkLong(txtCodigoequipo);
            entity = businessDelegatorView.getEquipo(codigoequipo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteEquipo(entity);
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
            selectedEquipo = (EquipoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedEquipo"));

            Long codigoequipo = new Long(selectedEquipo.getCodigoequipo());
            entity = businessDelegatorView.getEquipo(codigoequipo);
            businessDelegatorView.deleteEquipo(entity);
            data.remove(selectedEquipo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigoequipo, String nombre,
        Long codigojugador_Jugador) throws Exception {
        try {
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updateEquipo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("EquipoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtCodigojugador_Jugador() {
        return txtCodigojugador_Jugador;
    }

    public void setTxtCodigojugador_Jugador(InputText txtCodigojugador_Jugador) {
        this.txtCodigojugador_Jugador = txtCodigojugador_Jugador;
    }

    public InputText getTxtCodigoequipo() {
        return txtCodigoequipo;
    }

    public void setTxtCodigoequipo(InputText txtCodigoequipo) {
        this.txtCodigoequipo = txtCodigoequipo;
    }

    public List<EquipoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataEquipo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<EquipoDTO> equipoDTO) {
        this.data = equipoDTO;
    }

    public EquipoDTO getSelectedEquipo() {
        return selectedEquipo;
    }

    public void setSelectedEquipo(EquipoDTO equipo) {
        this.selectedEquipo = equipo;
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
