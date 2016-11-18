package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.EmpleadoDTO;
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
public class EmpleadoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EmpleadoView.class);
    private InputText txtApellido;
    private InputText txtEmail;
    private InputText txtNombre;
    private InputText txtPassword;
    private InputText txtCodigotipoempleado_Tipoempleado;
    private InputText txtCodigoempleado;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<EmpleadoDTO> data;
    private EmpleadoDTO selectedEmpleado;
    private Empleado entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public EmpleadoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            EmpleadoDTO empleadoDTO = (EmpleadoDTO) e.getObject();

            if (txtApellido == null) {
                txtApellido = new InputText();
            }

            txtApellido.setValue(empleadoDTO.getApellido());

            if (txtEmail == null) {
                txtEmail = new InputText();
            }

            txtEmail.setValue(empleadoDTO.getEmail());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(empleadoDTO.getNombre());

            if (txtPassword == null) {
                txtPassword = new InputText();
            }

            txtPassword.setValue(empleadoDTO.getPassword());

            if (txtCodigotipoempleado_Tipoempleado == null) {
                txtCodigotipoempleado_Tipoempleado = new InputText();
            }

            txtCodigotipoempleado_Tipoempleado.setValue(empleadoDTO.getCodigotipoempleado_Tipoempleado());

            if (txtCodigoempleado == null) {
                txtCodigoempleado = new InputText();
            }

            txtCodigoempleado.setValue(empleadoDTO.getCodigoempleado());

            Long codigoempleado = FacesUtils.checkLong(txtCodigoempleado);
            entity = businessDelegatorView.getEmpleado(codigoempleado);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedEmpleado = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedEmpleado = null;

        if (txtApellido != null) {
            txtApellido.setValue(null);
            txtApellido.setDisabled(true);
        }

        if (txtEmail != null) {
            txtEmail.setValue(null);
            txtEmail.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtPassword != null) {
            txtPassword.setValue(null);
            txtPassword.setDisabled(true);
        }

        if (txtCodigotipoempleado_Tipoempleado != null) {
            txtCodigotipoempleado_Tipoempleado.setValue(null);
            txtCodigotipoempleado_Tipoempleado.setDisabled(true);
        }

        if (txtCodigoempleado != null) {
            txtCodigoempleado.setValue(null);
            txtCodigoempleado.setDisabled(false);
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
            Long codigoempleado = FacesUtils.checkLong(txtCodigoempleado);
            entity = (codigoempleado != null)
                ? businessDelegatorView.getEmpleado(codigoempleado) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtApellido.setDisabled(false);
            txtEmail.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPassword.setDisabled(false);
            txtCodigotipoempleado_Tipoempleado.setDisabled(false);
            txtCodigoempleado.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtApellido.setValue(entity.getApellido());
            txtApellido.setDisabled(false);
            txtEmail.setValue(entity.getEmail());
            txtEmail.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPassword.setValue(entity.getPassword());
            txtPassword.setDisabled(false);
            txtCodigotipoempleado_Tipoempleado.setValue(entity.getTipoempleado()
                                                              .getCodigotipoempleado());
            txtCodigotipoempleado_Tipoempleado.setDisabled(false);
            txtCodigoempleado.setValue(entity.getCodigoempleado());
            txtCodigoempleado.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedEmpleado = (EmpleadoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedEmpleado"));
        txtApellido.setValue(selectedEmpleado.getApellido());
        txtApellido.setDisabled(false);
        txtEmail.setValue(selectedEmpleado.getEmail());
        txtEmail.setDisabled(false);
        txtNombre.setValue(selectedEmpleado.getNombre());
        txtNombre.setDisabled(false);
        txtPassword.setValue(selectedEmpleado.getPassword());
        txtPassword.setDisabled(false);
        txtCodigotipoempleado_Tipoempleado.setValue(selectedEmpleado.getCodigotipoempleado_Tipoempleado());
        txtCodigotipoempleado_Tipoempleado.setDisabled(false);
        txtCodigoempleado.setValue(selectedEmpleado.getCodigoempleado());
        txtCodigoempleado.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedEmpleado == null) && (entity == null)) {
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
            entity = new Empleado();

            Long codigoempleado = FacesUtils.checkLong(txtCodigoempleado);

            entity.setApellido(FacesUtils.checkString(txtApellido));
            entity.setCodigoempleado(codigoempleado);
            entity.setEmail(FacesUtils.checkLong(txtEmail));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPassword(FacesUtils.checkLong(txtPassword));
            entity.setTipoempleado((FacesUtils.checkLong(
                    txtCodigotipoempleado_Tipoempleado) != null)
                ? businessDelegatorView.getTipoempleado(FacesUtils.checkLong(
                        txtCodigotipoempleado_Tipoempleado)) : null);
            businessDelegatorView.saveEmpleado(entity);
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
                Long codigoempleado = new Long(selectedEmpleado.getCodigoempleado());
                entity = businessDelegatorView.getEmpleado(codigoempleado);
            }

            entity.setApellido(FacesUtils.checkString(txtApellido));
            entity.setEmail(FacesUtils.checkLong(txtEmail));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPassword(FacesUtils.checkLong(txtPassword));
            entity.setTipoempleado((FacesUtils.checkLong(
                    txtCodigotipoempleado_Tipoempleado) != null)
                ? businessDelegatorView.getTipoempleado(FacesUtils.checkLong(
                        txtCodigotipoempleado_Tipoempleado)) : null);
            businessDelegatorView.updateEmpleado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedEmpleado = (EmpleadoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedEmpleado"));

            Long codigoempleado = new Long(selectedEmpleado.getCodigoempleado());
            entity = businessDelegatorView.getEmpleado(codigoempleado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigoempleado = FacesUtils.checkLong(txtCodigoempleado);
            entity = businessDelegatorView.getEmpleado(codigoempleado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteEmpleado(entity);
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
            selectedEmpleado = (EmpleadoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedEmpleado"));

            Long codigoempleado = new Long(selectedEmpleado.getCodigoempleado());
            entity = businessDelegatorView.getEmpleado(codigoempleado);
            businessDelegatorView.deleteEmpleado(entity);
            data.remove(selectedEmpleado);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String apellido, Long codigoempleado,
        Long email, String nombre, Long password,
        Long codigotipoempleado_Tipoempleado) throws Exception {
        try {
            entity.setApellido(FacesUtils.checkString(apellido));
            entity.setEmail(FacesUtils.checkLong(email));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setPassword(FacesUtils.checkLong(password));
            businessDelegatorView.updateEmpleado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("EmpleadoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(InputText txtApellido) {
        this.txtApellido = txtApellido;
    }

    public InputText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(InputText txtEmail) {
        this.txtEmail = txtEmail;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(InputText txtPassword) {
        this.txtPassword = txtPassword;
    }

    public InputText getTxtCodigotipoempleado_Tipoempleado() {
        return txtCodigotipoempleado_Tipoempleado;
    }

    public void setTxtCodigotipoempleado_Tipoempleado(
        InputText txtCodigotipoempleado_Tipoempleado) {
        this.txtCodigotipoempleado_Tipoempleado = txtCodigotipoempleado_Tipoempleado;
    }

    public InputText getTxtCodigoempleado() {
        return txtCodigoempleado;
    }

    public void setTxtCodigoempleado(InputText txtCodigoempleado) {
        this.txtCodigoempleado = txtCodigoempleado;
    }

    public List<EmpleadoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataEmpleado();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<EmpleadoDTO> empleadoDTO) {
        this.data = empleadoDTO;
    }

    public EmpleadoDTO getSelectedEmpleado() {
        return selectedEmpleado;
    }

    public void setSelectedEmpleado(EmpleadoDTO empleado) {
        this.selectedEmpleado = empleado;
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
