package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.PartidoUsuarioDTO;
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
public class PartidoUsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PartidoUsuarioView.class);
    private InputText txtCodigopartido_Partido;
    private InputText txtCodigousuario_Usuario;
    private InputText txtCodigopartidoUsuario;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PartidoUsuarioDTO> data;
    private PartidoUsuarioDTO selectedPartidoUsuario;
    private PartidoUsuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PartidoUsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PartidoUsuarioDTO partidoUsuarioDTO = (PartidoUsuarioDTO) e.getObject();

            if (txtCodigopartido_Partido == null) {
                txtCodigopartido_Partido = new InputText();
            }

            txtCodigopartido_Partido.setValue(partidoUsuarioDTO.getCodigopartido_Partido());

            if (txtCodigousuario_Usuario == null) {
                txtCodigousuario_Usuario = new InputText();
            }

            txtCodigousuario_Usuario.setValue(partidoUsuarioDTO.getCodigousuario_Usuario());

            if (txtCodigopartidoUsuario == null) {
                txtCodigopartidoUsuario = new InputText();
            }

            txtCodigopartidoUsuario.setValue(partidoUsuarioDTO.getCodigopartidoUsuario());

            Long codigopartidoUsuario = FacesUtils.checkLong(txtCodigopartidoUsuario);
            entity = businessDelegatorView.getPartidoUsuario(codigopartidoUsuario);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPartidoUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPartidoUsuario = null;

        if (txtCodigopartido_Partido != null) {
            txtCodigopartido_Partido.setValue(null);
            txtCodigopartido_Partido.setDisabled(true);
        }

        if (txtCodigousuario_Usuario != null) {
            txtCodigousuario_Usuario.setValue(null);
            txtCodigousuario_Usuario.setDisabled(true);
        }

        if (txtCodigopartidoUsuario != null) {
            txtCodigopartidoUsuario.setValue(null);
            txtCodigopartidoUsuario.setDisabled(false);
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
            Long codigopartidoUsuario = FacesUtils.checkLong(txtCodigopartidoUsuario);
            entity = (codigopartidoUsuario != null)
                ? businessDelegatorView.getPartidoUsuario(codigopartidoUsuario)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigopartido_Partido.setDisabled(false);
            txtCodigousuario_Usuario.setDisabled(false);
            txtCodigopartidoUsuario.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCodigopartido_Partido.setValue(entity.getPartido()
                                                    .getCodigopartido());
            txtCodigopartido_Partido.setDisabled(false);
            txtCodigousuario_Usuario.setValue(entity.getUsuario()
                                                    .getCodigousuario());
            txtCodigousuario_Usuario.setDisabled(false);
            txtCodigopartidoUsuario.setValue(entity.getCodigopartidoUsuario());
            txtCodigopartidoUsuario.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPartidoUsuario = (PartidoUsuarioDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPartidoUsuario"));
        txtCodigopartido_Partido.setValue(selectedPartidoUsuario.getCodigopartido_Partido());
        txtCodigopartido_Partido.setDisabled(false);
        txtCodigousuario_Usuario.setValue(selectedPartidoUsuario.getCodigousuario_Usuario());
        txtCodigousuario_Usuario.setDisabled(false);
        txtCodigopartidoUsuario.setValue(selectedPartidoUsuario.getCodigopartidoUsuario());
        txtCodigopartidoUsuario.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPartidoUsuario == null) && (entity == null)) {
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
            entity = new PartidoUsuario();

            Long codigopartidoUsuario = FacesUtils.checkLong(txtCodigopartidoUsuario);

            entity.setCodigopartidoUsuario(codigopartidoUsuario);
            entity.setPartido((FacesUtils.checkLong(txtCodigopartido_Partido) != null)
                ? businessDelegatorView.getPartido(FacesUtils.checkLong(
                        txtCodigopartido_Partido)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtCodigousuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtCodigousuario_Usuario)) : null);
            businessDelegatorView.savePartidoUsuario(entity);
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
                Long codigopartidoUsuario = new Long(selectedPartidoUsuario.getCodigopartidoUsuario());
                entity = businessDelegatorView.getPartidoUsuario(codigopartidoUsuario);
            }

            entity.setPartido((FacesUtils.checkLong(txtCodigopartido_Partido) != null)
                ? businessDelegatorView.getPartido(FacesUtils.checkLong(
                        txtCodigopartido_Partido)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtCodigousuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtCodigousuario_Usuario)) : null);
            businessDelegatorView.updatePartidoUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPartidoUsuario = (PartidoUsuarioDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPartidoUsuario"));

            Long codigopartidoUsuario = new Long(selectedPartidoUsuario.getCodigopartidoUsuario());
            entity = businessDelegatorView.getPartidoUsuario(codigopartidoUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigopartidoUsuario = FacesUtils.checkLong(txtCodigopartidoUsuario);
            entity = businessDelegatorView.getPartidoUsuario(codigopartidoUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePartidoUsuario(entity);
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
            selectedPartidoUsuario = (PartidoUsuarioDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPartidoUsuario"));

            Long codigopartidoUsuario = new Long(selectedPartidoUsuario.getCodigopartidoUsuario());
            entity = businessDelegatorView.getPartidoUsuario(codigopartidoUsuario);
            businessDelegatorView.deletePartidoUsuario(entity);
            data.remove(selectedPartidoUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigopartidoUsuario,
        Long codigopartido_Partido, Long codigousuario_Usuario)
        throws Exception {
        try {
            businessDelegatorView.updatePartidoUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PartidoUsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCodigopartido_Partido() {
        return txtCodigopartido_Partido;
    }

    public void setTxtCodigopartido_Partido(InputText txtCodigopartido_Partido) {
        this.txtCodigopartido_Partido = txtCodigopartido_Partido;
    }

    public InputText getTxtCodigousuario_Usuario() {
        return txtCodigousuario_Usuario;
    }

    public void setTxtCodigousuario_Usuario(InputText txtCodigousuario_Usuario) {
        this.txtCodigousuario_Usuario = txtCodigousuario_Usuario;
    }

    public InputText getTxtCodigopartidoUsuario() {
        return txtCodigopartidoUsuario;
    }

    public void setTxtCodigopartidoUsuario(InputText txtCodigopartidoUsuario) {
        this.txtCodigopartidoUsuario = txtCodigopartidoUsuario;
    }

    public List<PartidoUsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPartidoUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PartidoUsuarioDTO> partidoUsuarioDTO) {
        this.data = partidoUsuarioDTO;
    }

    public PartidoUsuarioDTO getSelectedPartidoUsuario() {
        return selectedPartidoUsuario;
    }

    public void setSelectedPartidoUsuario(PartidoUsuarioDTO partidoUsuario) {
        this.selectedPartidoUsuario = partidoUsuario;
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
