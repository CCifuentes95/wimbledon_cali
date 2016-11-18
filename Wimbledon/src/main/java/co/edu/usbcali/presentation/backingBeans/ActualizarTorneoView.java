package co.edu.usbcali.presentation.backingBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class ActualizarTorneoView {
    
    @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ActualizarTorneoView.class);
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ActualizarTorneoView() {
        super();
    }
    
    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }
    
    public String regresar() {
    	FacesUtils.putinSession("Torneo", null);
        return "/XHTML/main.xhtml";
    }
    
    
    
}
