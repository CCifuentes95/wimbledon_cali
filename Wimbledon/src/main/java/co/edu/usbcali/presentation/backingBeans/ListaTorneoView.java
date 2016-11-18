package co.edu.usbcali.presentation.backingBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.modelo.Torneo;
import co.edu.usbcali.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class ListaTorneoView {
    
    @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ListaTorneoView.class);
    
    private List<Torneo> losTorneos;
    
    private Torneo torneoSeleccionado;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ListaTorneoView() {
        super();
    }
    
    @PostConstruct
    public void init(){
    	
    }
    
    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

	public List<Torneo> getLosTorneos() {
		
		try {
			if(losTorneos == null){
				losTorneos = businessDelegatorView.getTorneo();
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return losTorneos;
	}

	public void setLosTorneos(List<Torneo> losTorneos) {
		this.losTorneos = losTorneos;
	}

	public Torneo getTorneoSeleccionado() {
		return torneoSeleccionado;
	}

	public void setTorneoSeleccionado(Torneo torneoSeleccionado) {
		this.torneoSeleccionado = torneoSeleccionado;
	}
    
	public String openSelected(){
		FacesUtils.putinSession("Torneo", torneoSeleccionado);
		
		return "/XHTML/actualizarTorneo.xhtml";
	}
    
	public String regresar() {        
        return "/XHTML/main.xhtml";
    }
    
    
}
