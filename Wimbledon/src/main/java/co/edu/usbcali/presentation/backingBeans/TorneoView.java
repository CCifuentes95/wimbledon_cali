package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.TorneoDTO;
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
public class TorneoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TorneoView.class);
    private InputText txtCodigoempleado_Empleado;
    private InputText txtCodigotipotorneo_Tipotorneo;
    private InputText txtCodigotorneo;
    private Calendar txtFechafin;
    private Calendar txtFechainicio;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TorneoDTO> data;
    private TorneoDTO selectedTorneo;
    private Torneo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TorneoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TorneoDTO torneoDTO = (TorneoDTO) e.getObject();

            if (txtCodigoempleado_Empleado == null) {
                txtCodigoempleado_Empleado = new InputText();
            }

            txtCodigoempleado_Empleado.setValue(torneoDTO.getCodigoempleado_Empleado());

            if (txtCodigotipotorneo_Tipotorneo == null) {
                txtCodigotipotorneo_Tipotorneo = new InputText();
            }

            txtCodigotipotorneo_Tipotorneo.setValue(torneoDTO.getCodigotipotorneo_Tipotorneo());

            if (txtCodigotorneo == null) {
                txtCodigotorneo = new InputText();
            }

            txtCodigotorneo.setValue(torneoDTO.getCodigotorneo());

            if (txtFechafin == null) {
                txtFechafin = new Calendar();
            }

            txtFechafin.setValue(torneoDTO.getFechafin());

            if (txtFechainicio == null) {
                txtFechainicio = new Calendar();
            }

            txtFechainicio.setValue(torneoDTO.getFechainicio());

            Long codigotorneo = FacesUtils.checkLong(txtCodigotorneo);
            entity = businessDelegatorView.getTorneo(codigotorneo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTorneo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTorneo = null;

        if (txtCodigoempleado_Empleado != null) {
            txtCodigoempleado_Empleado.setValue(null);
            txtCodigoempleado_Empleado.setDisabled(true);
        }

        if (txtCodigotipotorneo_Tipotorneo != null) {
            txtCodigotipotorneo_Tipotorneo.setValue(null);
            txtCodigotipotorneo_Tipotorneo.setDisabled(true);
        }

        if (txtFechafin != null) {
            txtFechafin.setValue(null);
            txtFechafin.setDisabled(true);
        }

        if (txtFechainicio != null) {
            txtFechainicio.setValue(null);
            txtFechainicio.setDisabled(true);
        }

        if (txtCodigotorneo != null) {
            txtCodigotorneo.setValue(null);
            txtCodigotorneo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechafin() {
        Date inputDate = (Date) txtFechafin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechainicio() {
        Date inputDate = (Date) txtFechainicio.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long codigotorneo = FacesUtils.checkLong(txtCodigotorneo);
            entity = (codigotorneo != null)
                ? businessDelegatorView.getTorneo(codigotorneo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigoempleado_Empleado.setDisabled(false);
            txtCodigotipotorneo_Tipotorneo.setDisabled(false);
            txtFechafin.setDisabled(false);
            txtFechainicio.setDisabled(false);
            txtCodigotorneo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFechafin.setValue(entity.getFechafin());
            txtFechafin.setDisabled(false);
            txtFechainicio.setValue(entity.getFechainicio());
            txtFechainicio.setDisabled(false);
            txtCodigoempleado_Empleado.setValue(entity.getEmpleado()
                                                      .getCodigoempleado());
            txtCodigoempleado_Empleado.setDisabled(false);
            txtCodigotipotorneo_Tipotorneo.setValue(entity.getTipotorneo()
                                                          .getCodigotipotorneo());
            txtCodigotipotorneo_Tipotorneo.setDisabled(false);
            txtCodigotorneo.setValue(entity.getCodigotorneo());
            txtCodigotorneo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTorneo = (TorneoDTO) (evt.getComponent().getAttributes()
                                         .get("selectedTorneo"));
        txtFechafin.setValue(selectedTorneo.getFechafin());
        txtFechafin.setDisabled(false);
        txtFechainicio.setValue(selectedTorneo.getFechainicio());
        txtFechainicio.setDisabled(false);
        txtCodigoempleado_Empleado.setValue(selectedTorneo.getCodigoempleado_Empleado());
        txtCodigoempleado_Empleado.setDisabled(false);
        txtCodigotipotorneo_Tipotorneo.setValue(selectedTorneo.getCodigotipotorneo_Tipotorneo());
        txtCodigotipotorneo_Tipotorneo.setDisabled(false);
        txtCodigotorneo.setValue(selectedTorneo.getCodigotorneo());
        txtCodigotorneo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTorneo == null) && (entity == null)) {
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
            entity = new Torneo();

            Long codigotorneo = FacesUtils.checkLong(txtCodigotorneo);

            entity.setCodigotorneo(codigotorneo);
            entity.setFechafin(FacesUtils.checkDate(txtFechafin));
            entity.setFechainicio(FacesUtils.checkDate(txtFechainicio));
            entity.setEmpleado((FacesUtils.checkLong(txtCodigoempleado_Empleado) != null)
                ? businessDelegatorView.getEmpleado(FacesUtils.checkLong(
                        txtCodigoempleado_Empleado)) : null);
            entity.setTipotorneo((FacesUtils.checkLong(
                    txtCodigotipotorneo_Tipotorneo) != null)
                ? businessDelegatorView.getTipotorneo(FacesUtils.checkLong(
                        txtCodigotipotorneo_Tipotorneo)) : null);
            businessDelegatorView.saveTorneo(entity);
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
                Long codigotorneo = new Long(selectedTorneo.getCodigotorneo());
                entity = businessDelegatorView.getTorneo(codigotorneo);
            }

            entity.setFechafin(FacesUtils.checkDate(txtFechafin));
            entity.setFechainicio(FacesUtils.checkDate(txtFechainicio));
            entity.setEmpleado((FacesUtils.checkLong(txtCodigoempleado_Empleado) != null)
                ? businessDelegatorView.getEmpleado(FacesUtils.checkLong(
                        txtCodigoempleado_Empleado)) : null);
            entity.setTipotorneo((FacesUtils.checkLong(
                    txtCodigotipotorneo_Tipotorneo) != null)
                ? businessDelegatorView.getTipotorneo(FacesUtils.checkLong(
                        txtCodigotipotorneo_Tipotorneo)) : null);
            businessDelegatorView.updateTorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTorneo = (TorneoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedTorneo"));

            Long codigotorneo = new Long(selectedTorneo.getCodigotorneo());
            entity = businessDelegatorView.getTorneo(codigotorneo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigotorneo = FacesUtils.checkLong(txtCodigotorneo);
            entity = businessDelegatorView.getTorneo(codigotorneo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTorneo(entity);
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
            selectedTorneo = (TorneoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedTorneo"));

            Long codigotorneo = new Long(selectedTorneo.getCodigotorneo());
            entity = businessDelegatorView.getTorneo(codigotorneo);
            businessDelegatorView.deleteTorneo(entity);
            data.remove(selectedTorneo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigotorneo, Date fechafin,
        Date fechainicio, Long codigoempleado_Empleado,
        Long codigotipotorneo_Tipotorneo) throws Exception {
        try {
            entity.setFechafin(FacesUtils.checkDate(fechafin));
            entity.setFechainicio(FacesUtils.checkDate(fechainicio));
            businessDelegatorView.updateTorneo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TorneoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCodigoempleado_Empleado() {
        return txtCodigoempleado_Empleado;
    }

    public void setTxtCodigoempleado_Empleado(
        InputText txtCodigoempleado_Empleado) {
        this.txtCodigoempleado_Empleado = txtCodigoempleado_Empleado;
    }

    public InputText getTxtCodigotipotorneo_Tipotorneo() {
        return txtCodigotipotorneo_Tipotorneo;
    }

    public void setTxtCodigotipotorneo_Tipotorneo(
        InputText txtCodigotipotorneo_Tipotorneo) {
        this.txtCodigotipotorneo_Tipotorneo = txtCodigotipotorneo_Tipotorneo;
    }

    public Calendar getTxtFechafin() {
        return txtFechafin;
    }

    public void setTxtFechafin(Calendar txtFechafin) {
        this.txtFechafin = txtFechafin;
    }

    public Calendar getTxtFechainicio() {
        return txtFechainicio;
    }

    public void setTxtFechainicio(Calendar txtFechainicio) {
        this.txtFechainicio = txtFechainicio;
    }

    public InputText getTxtCodigotorneo() {
        return txtCodigotorneo;
    }

    public void setTxtCodigotorneo(InputText txtCodigotorneo) {
        this.txtCodigotorneo = txtCodigotorneo;
    }

    public List<TorneoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTorneo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TorneoDTO> torneoDTO) {
        this.data = torneoDTO;
    }

    public TorneoDTO getSelectedTorneo() {
        return selectedTorneo;
    }

    public void setSelectedTorneo(TorneoDTO torneo) {
        this.selectedTorneo = torneo;
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
