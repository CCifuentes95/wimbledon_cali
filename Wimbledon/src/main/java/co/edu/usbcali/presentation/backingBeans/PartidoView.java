package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.PartidoDTO;
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
public class PartidoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PartidoView.class);
    private InputText txtGanador;
    private InputText txtCodigocancha_Cancha;
    private InputText txtCodigoempleado_Empleado;
    private InputText txtCodigoronda_Ronda;
    private InputText txtCodigopartido;
    private Calendar txtFecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PartidoDTO> data;
    private PartidoDTO selectedPartido;
    private Partido entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PartidoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PartidoDTO partidoDTO = (PartidoDTO) e.getObject();

            if (txtGanador == null) {
                txtGanador = new InputText();
            }

            txtGanador.setValue(partidoDTO.getGanador());

            if (txtCodigocancha_Cancha == null) {
                txtCodigocancha_Cancha = new InputText();
            }

            txtCodigocancha_Cancha.setValue(partidoDTO.getCodigocancha_Cancha());

            if (txtCodigoempleado_Empleado == null) {
                txtCodigoempleado_Empleado = new InputText();
            }

            txtCodigoempleado_Empleado.setValue(partidoDTO.getCodigoempleado_Empleado());

            if (txtCodigoronda_Ronda == null) {
                txtCodigoronda_Ronda = new InputText();
            }

            txtCodigoronda_Ronda.setValue(partidoDTO.getCodigoronda_Ronda());

            if (txtCodigopartido == null) {
                txtCodigopartido = new InputText();
            }

            txtCodigopartido.setValue(partidoDTO.getCodigopartido());

            if (txtFecha == null) {
                txtFecha = new Calendar();
            }

            txtFecha.setValue(partidoDTO.getFecha());

            Long codigopartido = FacesUtils.checkLong(txtCodigopartido);
            entity = businessDelegatorView.getPartido(codigopartido);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPartido = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPartido = null;

        if (txtGanador != null) {
            txtGanador.setValue(null);
            txtGanador.setDisabled(true);
        }

        if (txtCodigocancha_Cancha != null) {
            txtCodigocancha_Cancha.setValue(null);
            txtCodigocancha_Cancha.setDisabled(true);
        }

        if (txtCodigoempleado_Empleado != null) {
            txtCodigoempleado_Empleado.setValue(null);
            txtCodigoempleado_Empleado.setDisabled(true);
        }

        if (txtCodigoronda_Ronda != null) {
            txtCodigoronda_Ronda.setValue(null);
            txtCodigoronda_Ronda.setDisabled(true);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(true);
        }

        if (txtCodigopartido != null) {
            txtCodigopartido.setValue(null);
            txtCodigopartido.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFecha() {
        Date inputDate = (Date) txtFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long codigopartido = FacesUtils.checkLong(txtCodigopartido);
            entity = (codigopartido != null)
                ? businessDelegatorView.getPartido(codigopartido) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtGanador.setDisabled(false);
            txtCodigocancha_Cancha.setDisabled(false);
            txtCodigoempleado_Empleado.setDisabled(false);
            txtCodigoronda_Ronda.setDisabled(false);
            txtFecha.setDisabled(false);
            txtCodigopartido.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtGanador.setValue(entity.getGanador());
            txtGanador.setDisabled(false);
            txtCodigocancha_Cancha.setValue(entity.getCancha().getCodigocancha());
            txtCodigocancha_Cancha.setDisabled(false);
            txtCodigoempleado_Empleado.setValue(entity.getEmpleado()
                                                      .getCodigoempleado());
            txtCodigoempleado_Empleado.setDisabled(false);
            txtCodigoronda_Ronda.setValue(entity.getRonda().getCodigoronda());
            txtCodigoronda_Ronda.setDisabled(false);
            txtCodigopartido.setValue(entity.getCodigopartido());
            txtCodigopartido.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPartido = (PartidoDTO) (evt.getComponent().getAttributes()
                                           .get("selectedPartido"));
        txtFecha.setValue(selectedPartido.getFecha());
        txtFecha.setDisabled(false);
        txtGanador.setValue(selectedPartido.getGanador());
        txtGanador.setDisabled(false);
        txtCodigocancha_Cancha.setValue(selectedPartido.getCodigocancha_Cancha());
        txtCodigocancha_Cancha.setDisabled(false);
        txtCodigoempleado_Empleado.setValue(selectedPartido.getCodigoempleado_Empleado());
        txtCodigoempleado_Empleado.setDisabled(false);
        txtCodigoronda_Ronda.setValue(selectedPartido.getCodigoronda_Ronda());
        txtCodigoronda_Ronda.setDisabled(false);
        txtCodigopartido.setValue(selectedPartido.getCodigopartido());
        txtCodigopartido.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPartido == null) && (entity == null)) {
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
            entity = new Partido();

            Long codigopartido = FacesUtils.checkLong(txtCodigopartido);

            entity.setCodigopartido(codigopartido);
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setGanador(FacesUtils.checkString(txtGanador));
            entity.setCancha((FacesUtils.checkLong(txtCodigocancha_Cancha) != null)
                ? businessDelegatorView.getCancha(FacesUtils.checkLong(
                        txtCodigocancha_Cancha)) : null);
            entity.setEmpleado((FacesUtils.checkLong(txtCodigoempleado_Empleado) != null)
                ? businessDelegatorView.getEmpleado(FacesUtils.checkLong(
                        txtCodigoempleado_Empleado)) : null);
            entity.setRonda((FacesUtils.checkLong(txtCodigoronda_Ronda) != null)
                ? businessDelegatorView.getRonda(FacesUtils.checkLong(
                        txtCodigoronda_Ronda)) : null);
            businessDelegatorView.savePartido(entity);
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
                Long codigopartido = new Long(selectedPartido.getCodigopartido());
                entity = businessDelegatorView.getPartido(codigopartido);
            }

            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setGanador(FacesUtils.checkString(txtGanador));
            entity.setCancha((FacesUtils.checkLong(txtCodigocancha_Cancha) != null)
                ? businessDelegatorView.getCancha(FacesUtils.checkLong(
                        txtCodigocancha_Cancha)) : null);
            entity.setEmpleado((FacesUtils.checkLong(txtCodigoempleado_Empleado) != null)
                ? businessDelegatorView.getEmpleado(FacesUtils.checkLong(
                        txtCodigoempleado_Empleado)) : null);
            entity.setRonda((FacesUtils.checkLong(txtCodigoronda_Ronda) != null)
                ? businessDelegatorView.getRonda(FacesUtils.checkLong(
                        txtCodigoronda_Ronda)) : null);
            businessDelegatorView.updatePartido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPartido = (PartidoDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPartido"));

            Long codigopartido = new Long(selectedPartido.getCodigopartido());
            entity = businessDelegatorView.getPartido(codigopartido);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigopartido = FacesUtils.checkLong(txtCodigopartido);
            entity = businessDelegatorView.getPartido(codigopartido);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePartido(entity);
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
            selectedPartido = (PartidoDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPartido"));

            Long codigopartido = new Long(selectedPartido.getCodigopartido());
            entity = businessDelegatorView.getPartido(codigopartido);
            businessDelegatorView.deletePartido(entity);
            data.remove(selectedPartido);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigopartido, Date fecha,
        String ganador, Long codigocancha_Cancha, Long codigoempleado_Empleado,
        Long codigoronda_Ronda) throws Exception {
        try {
            entity.setFecha(FacesUtils.checkDate(fecha));
            entity.setGanador(FacesUtils.checkString(ganador));
            businessDelegatorView.updatePartido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PartidoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtGanador() {
        return txtGanador;
    }

    public void setTxtGanador(InputText txtGanador) {
        this.txtGanador = txtGanador;
    }

    public InputText getTxtCodigocancha_Cancha() {
        return txtCodigocancha_Cancha;
    }

    public void setTxtCodigocancha_Cancha(InputText txtCodigocancha_Cancha) {
        this.txtCodigocancha_Cancha = txtCodigocancha_Cancha;
    }

    public InputText getTxtCodigoempleado_Empleado() {
        return txtCodigoempleado_Empleado;
    }

    public void setTxtCodigoempleado_Empleado(
        InputText txtCodigoempleado_Empleado) {
        this.txtCodigoempleado_Empleado = txtCodigoempleado_Empleado;
    }

    public InputText getTxtCodigoronda_Ronda() {
        return txtCodigoronda_Ronda;
    }

    public void setTxtCodigoronda_Ronda(InputText txtCodigoronda_Ronda) {
        this.txtCodigoronda_Ronda = txtCodigoronda_Ronda;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public InputText getTxtCodigopartido() {
        return txtCodigopartido;
    }

    public void setTxtCodigopartido(InputText txtCodigopartido) {
        this.txtCodigopartido = txtCodigopartido;
    }

    public List<PartidoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPartido();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PartidoDTO> partidoDTO) {
        this.data = partidoDTO;
    }

    public PartidoDTO getSelectedPartido() {
        return selectedPartido;
    }

    public void setSelectedPartido(PartidoDTO partido) {
        this.selectedPartido = partido;
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
