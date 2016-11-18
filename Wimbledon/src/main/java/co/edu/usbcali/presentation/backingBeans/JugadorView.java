package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.JugadorDTO;
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
public class JugadorView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(JugadorView.class);
    private InputText txtActivo;
    private InputText txtNombre;
    private InputText txtRanking;
    private InputText txtCodigopais_Pais;
    private InputText txtCodigojugador;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<JugadorDTO> data;
    private JugadorDTO selectedJugador;
    private Jugador entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public JugadorView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            JugadorDTO jugadorDTO = (JugadorDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(jugadorDTO.getActivo());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(jugadorDTO.getNombre());

            if (txtRanking == null) {
                txtRanking = new InputText();
            }

            txtRanking.setValue(jugadorDTO.getRanking());

            if (txtCodigopais_Pais == null) {
                txtCodigopais_Pais = new InputText();
            }

            txtCodigopais_Pais.setValue(jugadorDTO.getCodigopais_Pais());

            if (txtCodigojugador == null) {
                txtCodigojugador = new InputText();
            }

            txtCodigojugador.setValue(jugadorDTO.getCodigojugador());

            Long codigojugador = FacesUtils.checkLong(txtCodigojugador);
            entity = businessDelegatorView.getJugador(codigojugador);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedJugador = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedJugador = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtRanking != null) {
            txtRanking.setValue(null);
            txtRanking.setDisabled(true);
        }

        if (txtCodigopais_Pais != null) {
            txtCodigopais_Pais.setValue(null);
            txtCodigopais_Pais.setDisabled(true);
        }

        if (txtCodigojugador != null) {
            txtCodigojugador.setValue(null);
            txtCodigojugador.setDisabled(false);
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
            Long codigojugador = FacesUtils.checkLong(txtCodigojugador);
            entity = (codigojugador != null)
                ? businessDelegatorView.getJugador(codigojugador) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtNombre.setDisabled(false);
            txtRanking.setDisabled(false);
            txtCodigopais_Pais.setDisabled(false);
            txtCodigojugador.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtRanking.setValue(entity.getRanking());
            txtRanking.setDisabled(false);
            txtCodigopais_Pais.setValue(entity.getPais().getCodigopais());
            txtCodigopais_Pais.setDisabled(false);
            txtCodigojugador.setValue(entity.getCodigojugador());
            txtCodigojugador.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedJugador = (JugadorDTO) (evt.getComponent().getAttributes()
                                           .get("selectedJugador"));
        txtActivo.setValue(selectedJugador.getActivo());
        txtActivo.setDisabled(false);
        txtNombre.setValue(selectedJugador.getNombre());
        txtNombre.setDisabled(false);
        txtRanking.setValue(selectedJugador.getRanking());
        txtRanking.setDisabled(false);
        txtCodigopais_Pais.setValue(selectedJugador.getCodigopais_Pais());
        txtCodigopais_Pais.setDisabled(false);
        txtCodigojugador.setValue(selectedJugador.getCodigojugador());
        txtCodigojugador.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedJugador == null) && (entity == null)) {
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
            entity = new Jugador();

            Long codigojugador = FacesUtils.checkLong(txtCodigojugador);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setCodigojugador(codigojugador);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setRanking(FacesUtils.checkInteger(txtRanking));
            entity.setPais((FacesUtils.checkLong(txtCodigopais_Pais) != null)
                ? businessDelegatorView.getPais(FacesUtils.checkLong(
                        txtCodigopais_Pais)) : null);
            businessDelegatorView.saveJugador(entity);
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
                Long codigojugador = new Long(selectedJugador.getCodigojugador());
                entity = businessDelegatorView.getJugador(codigojugador);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setRanking(FacesUtils.checkInteger(txtRanking));
            entity.setPais((FacesUtils.checkLong(txtCodigopais_Pais) != null)
                ? businessDelegatorView.getPais(FacesUtils.checkLong(
                        txtCodigopais_Pais)) : null);
            businessDelegatorView.updateJugador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedJugador = (JugadorDTO) (evt.getComponent().getAttributes()
                                               .get("selectedJugador"));

            Long codigojugador = new Long(selectedJugador.getCodigojugador());
            entity = businessDelegatorView.getJugador(codigojugador);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigojugador = FacesUtils.checkLong(txtCodigojugador);
            entity = businessDelegatorView.getJugador(codigojugador);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteJugador(entity);
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
            selectedJugador = (JugadorDTO) (evt.getComponent().getAttributes()
                                               .get("selectedJugador"));

            Long codigojugador = new Long(selectedJugador.getCodigojugador());
            entity = businessDelegatorView.getJugador(codigojugador);
            businessDelegatorView.deleteJugador(entity);
            data.remove(selectedJugador);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, Long codigojugador,
        String nombre, Integer ranking, Long codigopais_Pais)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setRanking(FacesUtils.checkInteger(ranking));
            businessDelegatorView.updateJugador(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("JugadorView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtActivo() {
        return txtActivo;
    }

    public void setTxtActivo(InputText txtActivo) {
        this.txtActivo = txtActivo;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtRanking() {
        return txtRanking;
    }

    public void setTxtRanking(InputText txtRanking) {
        this.txtRanking = txtRanking;
    }

    public InputText getTxtCodigopais_Pais() {
        return txtCodigopais_Pais;
    }

    public void setTxtCodigopais_Pais(InputText txtCodigopais_Pais) {
        this.txtCodigopais_Pais = txtCodigopais_Pais;
    }

    public InputText getTxtCodigojugador() {
        return txtCodigojugador;
    }

    public void setTxtCodigojugador(InputText txtCodigojugador) {
        this.txtCodigojugador = txtCodigojugador;
    }

    public List<JugadorDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataJugador();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<JugadorDTO> jugadorDTO) {
        this.data = jugadorDTO;
    }

    public JugadorDTO getSelectedJugador() {
        return selectedJugador;
    }

    public void setSelectedJugador(JugadorDTO jugador) {
        this.selectedJugador = jugador;
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
