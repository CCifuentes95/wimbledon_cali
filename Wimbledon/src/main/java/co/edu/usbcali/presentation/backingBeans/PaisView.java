package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.PaisDTO;
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
public class PaisView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PaisView.class);
    private InputText txtNombre;
    private InputText txtCodigopais;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PaisDTO> data;
    private PaisDTO selectedPais;
    private Pais entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PaisView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PaisDTO paisDTO = (PaisDTO) e.getObject();

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(paisDTO.getNombre());

            if (txtCodigopais == null) {
                txtCodigopais = new InputText();
            }

            txtCodigopais.setValue(paisDTO.getCodigopais());

            Long codigopais = FacesUtils.checkLong(txtCodigopais);
            entity = businessDelegatorView.getPais(codigopais);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPais = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPais = null;

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtCodigopais != null) {
            txtCodigopais.setValue(null);
            txtCodigopais.setDisabled(false);
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
            Long codigopais = FacesUtils.checkLong(txtCodigopais);
            entity = (codigopais != null)
                ? businessDelegatorView.getPais(codigopais) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtNombre.setDisabled(false);
            txtCodigopais.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtCodigopais.setValue(entity.getCodigopais());
            txtCodigopais.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPais = (PaisDTO) (evt.getComponent().getAttributes()
                                     .get("selectedPais"));
        txtNombre.setValue(selectedPais.getNombre());
        txtNombre.setDisabled(false);
        txtCodigopais.setValue(selectedPais.getCodigopais());
        txtCodigopais.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPais == null) && (entity == null)) {
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
            entity = new Pais();

            Long codigopais = FacesUtils.checkLong(txtCodigopais);

            entity.setCodigopais(codigopais);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.savePais(entity);
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
                Long codigopais = new Long(selectedPais.getCodigopais());
                entity = businessDelegatorView.getPais(codigopais);
            }

            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updatePais(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPais = (PaisDTO) (evt.getComponent().getAttributes()
                                         .get("selectedPais"));

            Long codigopais = new Long(selectedPais.getCodigopais());
            entity = businessDelegatorView.getPais(codigopais);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigopais = FacesUtils.checkLong(txtCodigopais);
            entity = businessDelegatorView.getPais(codigopais);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePais(entity);
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
            selectedPais = (PaisDTO) (evt.getComponent().getAttributes()
                                         .get("selectedPais"));

            Long codigopais = new Long(selectedPais.getCodigopais());
            entity = businessDelegatorView.getPais(codigopais);
            businessDelegatorView.deletePais(entity);
            data.remove(selectedPais);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigopais, String nombre)
        throws Exception {
        try {
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updatePais(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PaisView").requestRender();
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

    public InputText getTxtCodigopais() {
        return txtCodigopais;
    }

    public void setTxtCodigopais(InputText txtCodigopais) {
        this.txtCodigopais = txtCodigopais;
    }

    public List<PaisDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPais();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PaisDTO> paisDTO) {
        this.data = paisDTO;
    }

    public PaisDTO getSelectedPais() {
        return selectedPais;
    }

    public void setSelectedPais(PaisDTO pais) {
        this.selectedPais = pais;
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
