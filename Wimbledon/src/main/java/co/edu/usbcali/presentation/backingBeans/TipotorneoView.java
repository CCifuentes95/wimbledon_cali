package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.TipotorneoDTO;
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
public class TipotorneoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipotorneoView.class);
    private InputText txtDescripcion;
    private InputText txtCodigotipotorneo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipotorneoDTO> data;
    private TipotorneoDTO selectedTipotorneo;
    private Tipotorneo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipotorneoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipotorneoDTO tipotorneoDTO = (TipotorneoDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(tipotorneoDTO.getDescripcion());

            if (txtCodigotipotorneo == null) {
                txtCodigotipotorneo = new InputText();
            }

            txtCodigotipotorneo.setValue(tipotorneoDTO.getCodigotipotorneo());

            Long codigotipotorneo = FacesUtils.checkLong(txtCodigotipotorneo);
            entity = businessDelegatorView.getTipotorneo(codigotipotorneo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipotorneo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipotorneo = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtCodigotipotorneo != null) {
            txtCodigotipotorneo.setValue(null);
            txtCodigotipotorneo.setDisabled(false);
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
            Long codigotipotorneo = FacesUtils.checkLong(txtCodigotipotorneo);
            entity = (codigotipotorneo != null)
                ? businessDelegatorView.getTipotorneo(codigotipotorneo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtCodigotipotorneo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtCodigotipotorneo.setValue(entity.getCodigotipotorneo());
            txtCodigotipotorneo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipotorneo = (TipotorneoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedTipotorneo"));
        txtDescripcion.setValue(selectedTipotorneo.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtCodigotipotorneo.setValue(selectedTipotorneo.getCodigotipotorneo());
        txtCodigotipotorneo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipotorneo == null) && (entity == null)) {
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
            entity = new Tipotorneo();

            Long codigotipotorneo = FacesUtils.checkLong(txtCodigotipotorneo);

            entity.setCodigotipotorneo(codigotipotorneo);
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            businessDelegatorView.saveTipotorneo(entity);
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
                Long codigotipotorneo = new Long(selectedTipotorneo.getCodigotipotorneo());
                entity = businessDelegatorView.getTipotorneo(codigotipotorneo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            businessDelegatorView.updateTipotorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipotorneo = (TipotorneoDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedTipotorneo"));

            Long codigotipotorneo = new Long(selectedTipotorneo.getCodigotipotorneo());
            entity = businessDelegatorView.getTipotorneo(codigotipotorneo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigotipotorneo = FacesUtils.checkLong(txtCodigotipotorneo);
            entity = businessDelegatorView.getTipotorneo(codigotipotorneo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipotorneo(entity);
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
            selectedTipotorneo = (TipotorneoDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedTipotorneo"));

            Long codigotipotorneo = new Long(selectedTipotorneo.getCodigotipotorneo());
            entity = businessDelegatorView.getTipotorneo(codigotipotorneo);
            businessDelegatorView.deleteTipotorneo(entity);
            data.remove(selectedTipotorneo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigotipotorneo, String descripcion)
        throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            businessDelegatorView.updateTipotorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipotorneoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtCodigotipotorneo() {
        return txtCodigotipotorneo;
    }

    public void setTxtCodigotipotorneo(InputText txtCodigotipotorneo) {
        this.txtCodigotipotorneo = txtCodigotipotorneo;
    }

    public List<TipotorneoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipotorneo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipotorneoDTO> tipotorneoDTO) {
        this.data = tipotorneoDTO;
    }

    public TipotorneoDTO getSelectedTipotorneo() {
        return selectedTipotorneo;
    }

    public void setSelectedTipotorneo(TipotorneoDTO tipotorneo) {
        this.selectedTipotorneo = tipotorneo;
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
