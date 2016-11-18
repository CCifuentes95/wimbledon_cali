package co.edu.usbcali.presentation.backingBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.presentation.businessDelegate.IBusinessDelegatorView;


@ManagedBean
@ViewScoped
public class CrearTorneoView {
    
    private static final Logger log = LoggerFactory.getLogger(CrearTorneoView.class);
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CrearTorneoView() {
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
        return "/XHTML/main.xhtml";
    }
    
}
