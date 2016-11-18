package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.CanchaDTO;
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
public class CanchaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CanchaView.class);
    private InputText txtDiametro;
    private InputText txtDireccion;
    private InputText txtCodigocancha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CanchaDTO> data;
    private CanchaDTO selectedCancha;
    private Cancha entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CanchaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            CanchaDTO canchaDTO = (CanchaDTO) e.getObject();

            if (txtDiametro == null) {
                txtDiametro = new InputText();
            }

            txtDiametro.setValue(canchaDTO.getDiametro());

            if (txtDireccion == null) {
                txtDireccion = new InputText();
            }

            txtDireccion.setValue(canchaDTO.getDireccion());

            if (txtCodigocancha == null) {
                txtCodigocancha = new InputText();
            }

            txtCodigocancha.setValue(canchaDTO.getCodigocancha());

            Long codigocancha = FacesUtils.checkLong(txtCodigocancha);
            entity = businessDelegatorView.getCancha(codigocancha);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCancha = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCancha = null;

        if (txtDiametro != null) {
            txtDiametro.setValue(null);
            txtDiametro.setDisabled(true);
        }

        if (txtDireccion != null) {
            txtDireccion.setValue(null);
            txtDireccion.setDisabled(true);
        }

        if (txtCodigocancha != null) {
            txtCodigocancha.setValue(null);
            txtCodigocancha.setDisabled(false);
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
            Long codigocancha = FacesUtils.checkLong(txtCodigocancha);
            entity = (codigocancha != null)
                ? businessDelegatorView.getCancha(codigocancha) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDiametro.setDisabled(false);
            txtDireccion.setDisabled(false);
            txtCodigocancha.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDiametro.setValue(entity.getDiametro());
            txtDiametro.setDisabled(false);
            txtDireccion.setValue(entity.getDireccion());
            txtDireccion.setDisabled(false);
            txtCodigocancha.setValue(entity.getCodigocancha());
            txtCodigocancha.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCancha = (CanchaDTO) (evt.getComponent().getAttributes()
                                         .get("selectedCancha"));
        txtDiametro.setValue(selectedCancha.getDiametro());
        txtDiametro.setDisabled(false);
        txtDireccion.setValue(selectedCancha.getDireccion());
        txtDireccion.setDisabled(false);
        txtCodigocancha.setValue(selectedCancha.getCodigocancha());
        txtCodigocancha.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCancha == null) && (entity == null)) {
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
            entity = new Cancha();

            Long codigocancha = FacesUtils.checkLong(txtCodigocancha);

            entity.setCodigocancha(codigocancha);
            entity.setDiametro(FacesUtils.checkString(txtDiametro));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            businessDelegatorView.saveCancha(entity);
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
                Long codigocancha = new Long(selectedCancha.getCodigocancha());
                entity = businessDelegatorView.getCancha(codigocancha);
            }

            entity.setDiametro(FacesUtils.checkString(txtDiametro));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            businessDelegatorView.updateCancha(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCancha = (CanchaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCancha"));

            Long codigocancha = new Long(selectedCancha.getCodigocancha());
            entity = businessDelegatorView.getCancha(codigocancha);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigocancha = FacesUtils.checkLong(txtCodigocancha);
            entity = businessDelegatorView.getCancha(codigocancha);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCancha(entity);
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
            selectedCancha = (CanchaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCancha"));

            Long codigocancha = new Long(selectedCancha.getCodigocancha());
            entity = businessDelegatorView.getCancha(codigocancha);
            businessDelegatorView.deleteCancha(entity);
            data.remove(selectedCancha);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigocancha, String diametro,
        String direccion) throws Exception {
        try {
            entity.setDiametro(FacesUtils.checkString(diametro));
            entity.setDireccion(FacesUtils.checkString(direccion));
            businessDelegatorView.updateCancha(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CanchaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDiametro() {
        return txtDiametro;
    }

    public void setTxtDiametro(InputText txtDiametro) {
        this.txtDiametro = txtDiametro;
    }

    public InputText getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(InputText txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public InputText getTxtCodigocancha() {
        return txtCodigocancha;
    }

    public void setTxtCodigocancha(InputText txtCodigocancha) {
        this.txtCodigocancha = txtCodigocancha;
    }

    public List<CanchaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCancha();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CanchaDTO> canchaDTO) {
        this.data = canchaDTO;
    }

    public CanchaDTO getSelectedCancha() {
        return selectedCancha;
    }

    public void setSelectedCancha(CanchaDTO cancha) {
        this.selectedCancha = cancha;
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
