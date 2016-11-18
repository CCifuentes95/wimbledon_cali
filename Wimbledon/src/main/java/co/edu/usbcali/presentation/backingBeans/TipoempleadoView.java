package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.TipoempleadoDTO;
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
public class TipoempleadoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoempleadoView.class);
    private InputText txtDescripcion;
    private InputText txtCodigotipoempleado;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoempleadoDTO> data;
    private TipoempleadoDTO selectedTipoempleado;
    private Tipoempleado entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipoempleadoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipoempleadoDTO tipoempleadoDTO = (TipoempleadoDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(tipoempleadoDTO.getDescripcion());

            if (txtCodigotipoempleado == null) {
                txtCodigotipoempleado = new InputText();
            }

            txtCodigotipoempleado.setValue(tipoempleadoDTO.getCodigotipoempleado());

            Long codigotipoempleado = FacesUtils.checkLong(txtCodigotipoempleado);
            entity = businessDelegatorView.getTipoempleado(codigotipoempleado);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoempleado = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoempleado = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtCodigotipoempleado != null) {
            txtCodigotipoempleado.setValue(null);
            txtCodigotipoempleado.setDisabled(false);
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
            Long codigotipoempleado = FacesUtils.checkLong(txtCodigotipoempleado);
            entity = (codigotipoempleado != null)
                ? businessDelegatorView.getTipoempleado(codigotipoempleado) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtCodigotipoempleado.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtCodigotipoempleado.setValue(entity.getCodigotipoempleado());
            txtCodigotipoempleado.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoempleado = (TipoempleadoDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedTipoempleado"));
        txtDescripcion.setValue(selectedTipoempleado.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtCodigotipoempleado.setValue(selectedTipoempleado.getCodigotipoempleado());
        txtCodigotipoempleado.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoempleado == null) && (entity == null)) {
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
            entity = new Tipoempleado();

            Long codigotipoempleado = FacesUtils.checkLong(txtCodigotipoempleado);

            entity.setCodigotipoempleado(codigotipoempleado);
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            businessDelegatorView.saveTipoempleado(entity);
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
                Long codigotipoempleado = new Long(selectedTipoempleado.getCodigotipoempleado());
                entity = businessDelegatorView.getTipoempleado(codigotipoempleado);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            businessDelegatorView.updateTipoempleado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoempleado = (TipoempleadoDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedTipoempleado"));

            Long codigotipoempleado = new Long(selectedTipoempleado.getCodigotipoempleado());
            entity = businessDelegatorView.getTipoempleado(codigotipoempleado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigotipoempleado = FacesUtils.checkLong(txtCodigotipoempleado);
            entity = businessDelegatorView.getTipoempleado(codigotipoempleado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoempleado(entity);
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
            selectedTipoempleado = (TipoempleadoDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedTipoempleado"));

            Long codigotipoempleado = new Long(selectedTipoempleado.getCodigotipoempleado());
            entity = businessDelegatorView.getTipoempleado(codigotipoempleado);
            businessDelegatorView.deleteTipoempleado(entity);
            data.remove(selectedTipoempleado);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigotipoempleado,
        String descripcion) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            businessDelegatorView.updateTipoempleado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoempleadoView").requestRender();
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

    public InputText getTxtCodigotipoempleado() {
        return txtCodigotipoempleado;
    }

    public void setTxtCodigotipoempleado(InputText txtCodigotipoempleado) {
        this.txtCodigotipoempleado = txtCodigotipoempleado;
    }

    public List<TipoempleadoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoempleado();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoempleadoDTO> tipoempleadoDTO) {
        this.data = tipoempleadoDTO;
    }

    public TipoempleadoDTO getSelectedTipoempleado() {
        return selectedTipoempleado;
    }

    public void setSelectedTipoempleado(TipoempleadoDTO tipoempleado) {
        this.selectedTipoempleado = tipoempleado;
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
