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

        return "/XHTML/crearTorneo.xhtml";
    }
	
    public String actualizarTorneo() {        

        return "/XHTML/actualizarTorneo.xhtml";
    }
}
