package co.edu.usbcali.presentation.backingBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.modelo.Partido;
import co.edu.usbcali.modelo.Ronda;
import co.edu.usbcali.modelo.Torneo;
import co.edu.usbcali.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class ListaRondasView {
    
    private static final Logger log = LoggerFactory.getLogger(ListaRondasView.class);
    
    private List<Ronda> lasRondas;
    
    private Ronda RondaSeleccionada;
    
    private Torneo torneoSeleccionado;
    
    private Partido partidoSeleccionado;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ListaRondasView() {
        super();
    }
    
    @PostConstruct
    public void init(){
    	this.torneoSeleccionado = (Torneo) FacesUtils.getfromSession("Torneo");
    }
    
    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

	
	public List<Ronda> getLasRondas() {
		try {
			if(lasRondas == null){
				lasRondas = businessDelegatorView.findRondasByTorneo(torneoSeleccionado.getCodigotorneo());
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return lasRondas;
	}

	public void setLasRondas(List<Ronda> lasRondas) {
		this.lasRondas = lasRondas;
	}

	public Ronda getRondaSeleccionada() {
		return RondaSeleccionada;
	}

	public void setRondaSeleccionada(Ronda rondaSeleccionada) {
		RondaSeleccionada = rondaSeleccionada;
	}

	public Torneo getTorneoSeleccionado() {
		return torneoSeleccionado;
	}

	public void setTorneoSeleccionado(Torneo torneoSeleccionado) {
		this.torneoSeleccionado = torneoSeleccionado;
	} 
	
	public Partido getPartidoSeleccionado() {
		return partidoSeleccionado;
	}

	public void setPartidoSeleccionado(Partido partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

	public String openSelected(){
		FacesUtils.putinSession("Partido", partidoSeleccionado);
		
		return "/XHTML/actualizarTorneo.xhtml";
	}
    
	public String regresar() {
		FacesUtils.putinSession("Torneo", null);		
		FacesUtils.putinSession("Partido", null);
        return "/XHTML/listaTorneos.xhtml";
    }
    
    
}
