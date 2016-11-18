package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.exceptions.*;
import co.edu.usbcali.modelo.*;
import co.edu.usbcali.modelo.dto.ResultadoDTO;
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
public class ResultadoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ResultadoView.class);
    private InputText txtResuadoa;
    private InputText txtResutadob;
    private InputText txtCodigoresultado;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ResultadoDTO> data;
    private ResultadoDTO selectedResultado;
    private Resultado entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ResultadoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ResultadoDTO resultadoDTO = (ResultadoDTO) e.getObject();

            if (txtResuadoa == null) {
                txtResuadoa = new InputText();
            }

            txtResuadoa.setValue(resultadoDTO.getResuadoa());

            if (txtResutadob == null) {
                txtResutadob = new InputText();
            }

            txtResutadob.setValue(resultadoDTO.getResutadob());

            if (txtCodigoresultado == null) {
                txtCodigoresultado = new InputText();
            }

            txtCodigoresultado.setValue(resultadoDTO.getCodigoresultado());

            Long codigoresultado = FacesUtils.checkLong(txtCodigoresultado);
            entity = businessDelegatorView.getResultado(codigoresultado);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedResultado = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedResultado = null;

        if (txtResuadoa != null) {
            txtResuadoa.setValue(null);
            txtResuadoa.setDisabled(true);
        }

        if (txtResutadob != null) {
            txtResutadob.setValue(null);
            txtResutadob.setDisabled(true);
        }

        if (txtCodigoresultado != null) {
            txtCodigoresultado.setValue(null);
            txtCodigoresultado.setDisabled(false);
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
            Long codigoresultado = FacesUtils.checkLong(txtCodigoresultado);
            entity = (codigoresultado != null)
                ? businessDelegatorView.getResultado(codigoresultado) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtResuadoa.setDisabled(false);
            txtResutadob.setDisabled(false);
            txtCodigoresultado.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtResuadoa.setValue(entity.getResuadoa());
            txtResuadoa.setDisabled(false);
            txtResutadob.setValue(entity.getResutadob());
            txtResutadob.setDisabled(false);
            txtCodigoresultado.setValue(entity.getCodigoresultado());
            txtCodigoresultado.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedResultado = (ResultadoDTO) (evt.getComponent().getAttributes()
                                               .get("selectedResultado"));
        txtResuadoa.setValue(selectedResultado.getResuadoa());
        txtResuadoa.setDisabled(false);
        txtResutadob.setValue(selectedResultado.getResutadob());
        txtResutadob.setDisabled(false);
        txtCodigoresultado.setValue(selectedResultado.getCodigoresultado());
        txtCodigoresultado.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedResultado == null) && (entity == null)) {
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
            entity = new Resultado();

            Long codigoresultado = FacesUtils.checkLong(txtCodigoresultado);

            entity.setCodigoresultado(codigoresultado);
            entity.setResuadoa(FacesUtils.checkInteger(txtResuadoa));
            entity.setResutadob(FacesUtils.checkInteger(txtResutadob));
            businessDelegatorView.saveResultado(entity);
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
                Long codigoresultado = new Long(selectedResultado.getCodigoresultado());
                entity = businessDelegatorView.getResultado(codigoresultado);
            }

            entity.setResuadoa(FacesUtils.checkInteger(txtResuadoa));
            entity.setResutadob(FacesUtils.checkInteger(txtResutadob));
            businessDelegatorView.updateResultado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedResultado = (ResultadoDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedResultado"));

            Long codigoresultado = new Long(selectedResultado.getCodigoresultado());
            entity = businessDelegatorView.getResultado(codigoresultado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigoresultado = FacesUtils.checkLong(txtCodigoresultado);
            entity = businessDelegatorView.getResultado(codigoresultado);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteResultado(entity);
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
            selectedResultado = (ResultadoDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedResultado"));

            Long codigoresultado = new Long(selectedResultado.getCodigoresultado());
            entity = businessDelegatorView.getResultado(codigoresultado);
            businessDelegatorView.deleteResultado(entity);
            data.remove(selectedResultado);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigoresultado, Integer resuadoa,
        Integer resutadob) throws Exception {
        try {
            entity.setResuadoa(FacesUtils.checkInteger(resuadoa));
            entity.setResutadob(FacesUtils.checkInteger(resutadob));
            businessDelegatorView.updateResultado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ResultadoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtResuadoa() {
        return txtResuadoa;
    }

    public void setTxtResuadoa(InputText txtResuadoa) {
        this.txtResuadoa = txtResuadoa;
    }

    public InputText getTxtResutadob() {
        return txtResutadob;
    }

    public void setTxtResutadob(InputText txtResutadob) {
        this.txtResutadob = txtResutadob;
    }

    public InputText getTxtCodigoresultado() {
        return txtCodigoresultado;
    }

    public void setTxtCodigoresultado(InputText txtCodigoresultado) {
        this.txtCodigoresultado = txtCodigoresultado;
    }

    public List<ResultadoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataResultado();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ResultadoDTO> resultadoDTO) {
        this.data = resultadoDTO;
    }

    public ResultadoDTO getSelectedResultado() {
        return selectedResultado;
    }

    public void setSelectedResultado(ResultadoDTO resultado) {
        this.selectedResultado = resultado;
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
