package co.edu.usbcali.presentation.backingBeans;

import co.edu.usbcali.utilities.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ViewScoped
@ManagedBean(name = "mainView")
public class MainView {
    
	public String crearTorneo() {        

        return "/XHTML/crearTorneo.xhtml";
    }
	
    public String actualizarTorneo() {        

        return "/XHTML/main.xhtml";
    }
}
