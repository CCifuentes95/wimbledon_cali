package co.edu.usbcali.presentation.backingBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ViewScoped
@ManagedBean(name = "mainView")
public class MainView {
    
	private final static Logger log=LoggerFactory.getLogger(MainView.class);
	
	public String crearTorneo() {        
		log.info("Clickeo Crear Torneo");
        return "/XHTML/crearTorneo.xhtml";
    }
	
    public String actualizarTorneo() {        
    	log.info("Clickeo Actualizar Torneo");
        return "/XHTML/main.xhtml";
    }
}
