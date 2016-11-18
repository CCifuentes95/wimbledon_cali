package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.PartidoResultadoDTO;
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
public class PartidoResultadoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PartidoResultadoView.class);
    private InputText txtCodigopartido_Partido;
    private InputText txtCodigoresultado_Resultado;
    private InputText txtCodigopatridoResultado;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PartidoResultadoDTO> data;
    private PartidoResultadoDTO selectedPartidoResultado;
    private PartidoResultado entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PartidoResultadoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PartidoResultadoDTO partidoResultadoDTO = (PartidoResultadoDTO) e.getObject();

            if (txtCodigopartido_Partido == null) {
                txtCodigopartido_Partido = new InputText();
            }

            txtCodigopartido_Partido.setValue(partidoResultadoDTO.getCodigopartido_Partido());

            if (txtCodigoresultado_Resultado == null) {
                txtCodigoresultado_Resultado = new InputText();
            }

            txtCodigoresultado_Resultado.setValue(partidoResultadoDTO.getCodigoresultado_Resultado());

            if (txtCodigopatridoResultado == null) {
                txtCodigopatridoResultado = new InputText();
            }

            txtCodigopatridoResultado.setValue(partidoResultadoDTO.getCodigopatridoResultado());

            Long codigopatridoResultado = FacesUtils.checkLong(txtCodigopatridoResultado);
            entity = businessDelegatorView.getPartidoResultado(codigopatridoResultado);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPartidoResultado = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPartidoResultado = null;

        if (txtCodigopartido_Partido != null) {
            txtCodigopartido_Partido.setValue(null);
            txtCodigopartido_Partido.setDisabled(true);
        }

        if (txtCodigoresultado_Resultado != null) {
            txtCodigoresultado_Resultado.setValue(null);
            txtCodigoresultado_Resultado.setDisabled(true);
        }

        if (txtCodigopatridoResultado != null) {
            txtCodigopatridoResultado.setValue(null);
            txtCodigopatridoResultado.setDisabled(false);
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
            Long codigopatridoResultado = FacesUtils.checkLong(txtCodigopatridoResultado);
            entity = (codigopatridoResultado != null)
                ? businessDelegatorView.getPartidoResultado(codigopatridoResultado)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigopartido_Partido.setDisabled(false);
            txtCodigoresultado_Resultado.setDisabled(false);
            txtCodigopatridoResultado.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCodigopartido_Partido.setValue(entity.getPartido()
                                                    .getCodigopartido());
            txtCodigopartido_Partido.setDisabled(false);
            txtCodigoresultado_Resultado.setValue(entity.getResultado()
                                                        .getCodigoresultado());
            txtCodigoresultado_Resultado.setDisabled(false);
            txtCodigopatridoResultado.setValue(entity.getCodigopatridoResultado());
            txtCodigopatridoResultado.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPartidoResultado = (PartidoResultadoDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPartidoResultado"));
        txtCodigopartido_Partido.setValue(selectedPartidoResultado.getCodigopartido_Partido());
        txtCodigopartido_Partido.setDisabled(false);
        txtCodigoresultado_Resultado.setValue(selectedPartidoResultado.getCodigoresultado_Resultado());
        txtCodigoresultado_Resultado.setDisabled(false);
        txtCodigopatridoResultado.setValue(selectedPartidoResultado.getCodigopatridoResultado());
        txtCodigopatridoResultado.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPartidoResultado == null) && (entity == null)) {
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
            entity = new PartidoResultado();

            Long codigopatridoResultado = FacesUtils.checkLong(txtCodigopatridoResultado);

            entity.setCodigopatridoResultado(codigopatridoResultado);
            entity.setPartido((FacesUtils.checkLong(txtCodigopartido_Partido) != null)
                ? businessDelegatorView.getPartido(FacesUtils.checkLong(
                        txtCodigopartido_Partido)) : null);
            entity.setResultado((FacesUtils.checkLong(
                    txtCodigoresultado_Resultado) != null)
                ? businessDelegatorView.getResultado(FacesUtils.checkLong(
                        txtCodigoresultado_Resultado)) : null);
            businessDelegatorView.savePartidoResultado(entity);
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
                Long codigopatridoResultado = new Long(selectedPartidoResultado.getCodigopatridoResultado());
                entity = businessDelegatorView.getPartidoResultado(codigopatridoResultado);
            }

            entity.setPartido((FacesUtils.checkLong(txtCodigopartido_Partido) != null)
                ? businessDelegatorView.getPartido(FacesUtils.checkLong(
                        txtCodigopartido_Partido)) : null);
            entity.setResultado((FacesUtils.checkLong(
                    txtCodigoresultado_Resultado) != null)
                ? businessDelegatorView.getResultado(FacesUtils.checkLong(
                        txtCodigoresultado_Resultado)) : null);
            businessDelegatorView.updatePartidoResultado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPartidoResultado = (PartidoResultadoDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedPartidoResultado"));

            Long codigopatridoResultado = new Long(selectedPartidoResultado.getCodigopatridoResultado());
            entity = businessDelegatorView.getPartidoResultado(codigopatridoResultado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigopatridoResultado = FacesUtils.checkLong(txtCodigopatridoResultado);
            entity = businessDelegatorView.getPartidoResultado(codigopatridoResultado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePartidoResultado(entity);
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
            selectedPartidoResultado = (PartidoResultadoDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedPartidoResultado"));

            Long codigopatridoResultado = new Long(selectedPartidoResultado.getCodigopatridoResultado());
            entity = businessDelegatorView.getPartidoResultado(codigopatridoResultado);
            businessDelegatorView.deletePartidoResultado(entity);
            data.remove(selectedPartidoResultado);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigopatridoResultado,
        Long codigopartido_Partido, Long codigoresultado_Resultado)
        throws Exception {
        try {
            businessDelegatorView.updatePartidoResultado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PartidoResultadoView").requestRender();
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

    public InputText getTxtCodigoresultado_Resultado() {
        return txtCodigoresultado_Resultado;
    }

    public void setTxtCodigoresultado_Resultado(
        InputText txtCodigoresultado_Resultado) {
        this.txtCodigoresultado_Resultado = txtCodigoresultado_Resultado;
    }

    public InputText getTxtCodigopatridoResultado() {
        return txtCodigopatridoResultado;
    }

    public void setTxtCodigopatridoResultado(
        InputText txtCodigopatridoResultado) {
        this.txtCodigopatridoResultado = txtCodigopatridoResultado;
    }

    public List<PartidoResultadoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPartidoResultado();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PartidoResultadoDTO> partidoResultadoDTO) {
        this.data = partidoResultadoDTO;
    }

    public PartidoResultadoDTO getSelectedPartidoResultado() {
        return selectedPartidoResultado;
    }

    public void setSelectedPartidoResultado(
        PartidoResultadoDTO partidoResultado) {
        this.selectedPartidoResultado = partidoResultado;
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
