package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.RondaDTO;
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
public class RondaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RondaView.class);
    private InputText txtNombre;
    private InputText txtPrecio;
    private InputText txtPuntos;
    private InputText txtCodigotorneo_Torneo;
    private InputText txtCodigoronda;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RondaDTO> data;
    private RondaDTO selectedRonda;
    private Ronda entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RondaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RondaDTO rondaDTO = (RondaDTO) e.getObject();

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(rondaDTO.getNombre());

            if (txtPrecio == null) {
                txtPrecio = new InputText();
            }

            txtPrecio.setValue(rondaDTO.getPrecio());

            if (txtPuntos == null) {
                txtPuntos = new InputText();
            }

            txtPuntos.setValue(rondaDTO.getPuntos());

            if (txtCodigotorneo_Torneo == null) {
                txtCodigotorneo_Torneo = new InputText();
            }

            txtCodigotorneo_Torneo.setValue(rondaDTO.getCodigotorneo_Torneo());

            if (txtCodigoronda == null) {
                txtCodigoronda = new InputText();
            }

            txtCodigoronda.setValue(rondaDTO.getCodigoronda());

            Long codigoronda = FacesUtils.checkLong(txtCodigoronda);
            entity = businessDelegatorView.getRonda(codigoronda);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRonda = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRonda = null;

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtPrecio != null) {
            txtPrecio.setValue(null);
            txtPrecio.setDisabled(true);
        }

        if (txtPuntos != null) {
            txtPuntos.setValue(null);
            txtPuntos.setDisabled(true);
        }

        if (txtCodigotorneo_Torneo != null) {
            txtCodigotorneo_Torneo.setValue(null);
            txtCodigotorneo_Torneo.setDisabled(true);
        }

        if (txtCodigoronda != null) {
            txtCodigoronda.setValue(null);
            txtCodigoronda.setDisabled(false);
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
            Long codigoronda = FacesUtils.checkLong(txtCodigoronda);
            entity = (codigoronda != null)
                ? businessDelegatorView.getRonda(codigoronda) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtNombre.setDisabled(false);
            txtPrecio.setDisabled(false);
            txtPuntos.setDisabled(false);
            txtCodigotorneo_Torneo.setDisabled(false);
            txtCodigoronda.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPrecio.setValue(entity.getPrecio());
            txtPrecio.setDisabled(false);
            txtPuntos.setValue(entity.getPuntos());
            txtPuntos.setDisabled(false);
            txtCodigotorneo_Torneo.setValue(entity.getTorneo().getCodigotorneo());
            txtCodigotorneo_Torneo.setDisabled(false);
            txtCodigoronda.setValue(entity.getCodigoronda());
            txtCodigoronda.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRonda = (RondaDTO) (evt.getComponent().getAttributes()
                                       .get("selectedRonda"));
        txtNombre.setValue(selectedRonda.getNombre());
        txtNombre.setDisabled(false);
        txtPrecio.setValue(selectedRonda.getPrecio());
        txtPrecio.setDisabled(false);
        txtPuntos.setValue(selectedRonda.getPuntos());
        txtPuntos.setDisabled(false);
        txtCodigotorneo_Torneo.setValue(selectedRonda.getCodigotorneo_Torneo());
        txtCodigotorneo_Torneo.setDisabled(false);
        txtCodigoronda.setValue(selectedRonda.getCodigoronda());
        txtCodigoronda.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRonda == null) && (entity == null)) {
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
            entity = new Ronda();

            Long codigoronda = FacesUtils.checkLong(txtCodigoronda);

            entity.setCodigoronda(codigoronda);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPrecio(FacesUtils.checkDouble(txtPrecio));
            entity.setPuntos(FacesUtils.checkInteger(txtPuntos));
            entity.setTorneo((FacesUtils.checkLong(txtCodigotorneo_Torneo) != null)
                ? businessDelegatorView.getTorneo(FacesUtils.checkLong(
                        txtCodigotorneo_Torneo)) : null);
            businessDelegatorView.saveRonda(entity);
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
                Long codigoronda = new Long(selectedRonda.getCodigoronda());
                entity = businessDelegatorView.getRonda(codigoronda);
            }

            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPrecio(FacesUtils.checkDouble(txtPrecio));
            entity.setPuntos(FacesUtils.checkInteger(txtPuntos));
            entity.setTorneo((FacesUtils.checkLong(txtCodigotorneo_Torneo) != null)
                ? businessDelegatorView.getTorneo(FacesUtils.checkLong(
                        txtCodigotorneo_Torneo)) : null);
            businessDelegatorView.updateRonda(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRonda = (RondaDTO) (evt.getComponent().getAttributes()
                                           .get("selectedRonda"));

            Long codigoronda = new Long(selectedRonda.getCodigoronda());
            entity = businessDelegatorView.getRonda(codigoronda);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigoronda = FacesUtils.checkLong(txtCodigoronda);
            entity = businessDelegatorView.getRonda(codigoronda);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRonda(entity);
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
            selectedRonda = (RondaDTO) (evt.getComponent().getAttributes()
                                           .get("selectedRonda"));

            Long codigoronda = new Long(selectedRonda.getCodigoronda());
            entity = businessDelegatorView.getRonda(codigoronda);
            businessDelegatorView.deleteRonda(entity);
            data.remove(selectedRonda);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigoronda, String nombre,
        Double precio, Integer puntos, Long codigotorneo_Torneo)
        throws Exception {
        try {
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setPrecio(FacesUtils.checkDouble(precio));
            entity.setPuntos(FacesUtils.checkInteger(puntos));
            businessDelegatorView.updateRonda(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RondaView").requestRender();
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

    public InputText getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(InputText txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public InputText getTxtPuntos() {
        return txtPuntos;
    }

    public void setTxtPuntos(InputText txtPuntos) {
        this.txtPuntos = txtPuntos;
    }

    public InputText getTxtCodigotorneo_Torneo() {
        return txtCodigotorneo_Torneo;
    }

    public void setTxtCodigotorneo_Torneo(InputText txtCodigotorneo_Torneo) {
        this.txtCodigotorneo_Torneo = txtCodigotorneo_Torneo;
    }

    public InputText getTxtCodigoronda() {
        return txtCodigoronda;
    }

    public void setTxtCodigoronda(InputText txtCodigoronda) {
        this.txtCodigoronda = txtCodigoronda;
    }

    public List<RondaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRonda();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RondaDTO> rondaDTO) {
        this.data = rondaDTO;
    }

    public RondaDTO getSelectedRonda() {
        return selectedRonda;
    }

    public void setSelectedRonda(RondaDTO ronda) {
        this.selectedRonda = ronda;
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
