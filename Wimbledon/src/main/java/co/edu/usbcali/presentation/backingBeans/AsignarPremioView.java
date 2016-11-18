package co.edu.usbcali.presentation.backingBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.modelo.Ronda;
import co.edu.usbcali.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.utilities.FacesUtils;

@ManagedBean
@ViewScoped
public class AsignarPremioView implements Serializable {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AsignarPremioView.class);

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private List<SelectItem> lasRondasItems;
	private InputText txtAsignarPremio;
	private SelectOneMenu somRondasTorneo;
	private CommandButton btnAsignar;
	private CommandButton btnLimpiar;

	public String asignarPremioPorRonda() {

		try {
			Ronda ronda = businessDelegatorView.getRonda(Long.parseLong(somRondasTorneo.getValue().toString().trim()));
			ronda.setCodigoronda(ronda.getCodigoronda());
			ronda.setNombre(ronda.getNombre());
			ronda.setPrecio(Double.parseDouble(txtAsignarPremio.getValue().toString().trim()));
			businessDelegatorView.updateRonda(ronda);
			limpiar();
			FacesUtils.addInfoMessage("Se ha asignado el premio con éxito");
		} catch (NumberFormatException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
	//comitd

	public String limpiar() {
		somRondasTorneo.setValue("-1");
		txtAsignarPremio.resetValue();
		return "";
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public void setLasRondasItems(List<SelectItem> lasRondasItems) {
		this.lasRondasItems = lasRondasItems;
	}

	public List<SelectItem> getLasRondasItems() {
		try {
			if (lasRondasItems == null) {
				List<Ronda> listaRondas = businessDelegatorView.getRonda();
				lasRondasItems = new ArrayList<SelectItem>();

				for (Ronda ronda : listaRondas) {
					lasRondasItems.add(new SelectItem(ronda.getCodigoronda(), ronda.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return lasRondasItems;
	}

	public InputText getTxtAsignarPremio() {
		return txtAsignarPremio;
	}

	public void setTxtAsignarPremio(InputText txtAsignarPremio) {
		this.txtAsignarPremio = txtAsignarPremio;
	}

	public SelectOneMenu getSomRondasTorneo() {
		return somRondasTorneo;
	}

	public void setSomRondasTorneo(SelectOneMenu somRondasTorneo) {
		this.somRondasTorneo = somRondasTorneo;
	}

	public CommandButton getBtnAsignar() {
		return btnAsignar;
	}

	public void setBtnAsignar(CommandButton btnAsignar) {
		this.btnAsignar = btnAsignar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
}
