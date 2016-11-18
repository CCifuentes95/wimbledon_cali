package co.edu.usbcali.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.modelo.Cancha;
import co.edu.usbcali.modelo.Empleado;
import co.edu.usbcali.modelo.Jugador;
import co.edu.usbcali.modelo.Partido;
import co.edu.usbcali.modelo.PartidoJugador;
import co.edu.usbcali.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class ActualizarTorneoView {
    
    private static final Logger log = LoggerFactory.getLogger(ActualizarTorneoView.class);
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    private SelectOneMenu somArbitros;
    private List<SelectItem> lasArbitrosItems;
    
    private SelectOneMenu somCanchas;
    private List<SelectItem> lasCanchas;
    
    private Partido partido;
    private Calendar txtCalendar;
    private Date fecha;
    
    private List<PartidoJugador> losPartidosJugador;
    private Jugador juagadorA;
    private Jugador juagadorB;

    public ActualizarTorneoView() {
        super();
    }
    
    @PostConstruct
    public void init(){
    	this.partido = (Partido) FacesUtils.getfromSession("Partido"); 	
    	this.losPartidosJugador = new ArrayList<>();
    	

    		for (PartidoJugador partidoJugador : partido.getPartidoJugadors()) {
    			losPartidosJugador.add(partidoJugador);
			}
    		
    		juagadorA = losPartidosJugador.get(0).getJugador();
    		juagadorB = losPartidosJugador.get(1).getJugador();
    }
    
    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }
        
    public SelectOneMenu getSomArbitros() {
		return somArbitros;
	}

	public void setSomArbitros(SelectOneMenu somArbitros) {
		this.somArbitros = somArbitros;
	}

	public List<SelectItem> getLasArbitrosItems() {
		try {
			if (lasArbitrosItems == null) {
				List<Empleado> listaArbitros = businessDelegatorView.findArbitros(3L);
				lasArbitrosItems = new ArrayList<SelectItem>();
				
				for (Empleado empleado : listaArbitros) {
					lasArbitrosItems.add(new SelectItem(empleado.getCodigoempleado(), empleado.getNombre()));
				}				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return lasArbitrosItems;
	}
	
	

	public SelectOneMenu getSomCanchas() {
		return somCanchas;
	}

	public void setSomCanchas(SelectOneMenu somCanchas) {
		this.somCanchas = somCanchas;
	}

	public List<SelectItem> getLasCanchas() {
		
		try {
			if (lasCanchas == null) {
				List<Cancha> listaCanchas = businessDelegatorView.getCancha();
				lasCanchas = new ArrayList<SelectItem>();
				
				for (Cancha cancha : listaCanchas) {
					lasCanchas.add(new SelectItem(cancha.getCodigocancha(),cancha.getDireccion()));
				}		
								
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return lasCanchas;
	}

	public void setLasCanchas(List<SelectItem> lasCanchas) {
		this.lasCanchas = lasCanchas;
	}

	public void setLasArbitrosItems(List<SelectItem> lasArbitrosItems) {
		this.lasArbitrosItems = lasArbitrosItems;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Calendar getTxtCalendar() {
		return txtCalendar;
	}

	public void setTxtCalendar(Calendar txtCalendar) {
		this.txtCalendar = txtCalendar;
	}
	
	public List<PartidoJugador> getLosPartidosJugador() {
		return losPartidosJugador;
	}

	public void setLosPartidosJugador(List<PartidoJugador> losPartidosJugador) {
		this.losPartidosJugador = losPartidosJugador;
	}

	public Jugador getJuagadorA() {
		return juagadorA;
	}

	public void setJuagadorA(Jugador juagadorA) {
		this.juagadorA = juagadorA;
	}

	public Jugador getJuagadorB() {
		return juagadorB;
	}

	public void setJuagadorB(Jugador juagadorB) {
		this.juagadorB = juagadorB;
	}
	
	//---------------------------------------------------------------
	
	
	public void actualizar(){
		
		try {
			
			if (somArbitros.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un arbitro");				
			}
			
			if (somCanchas.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione una cancha");				
			}
			
			if(partido.getFecha().equals("")|| partido.getFecha() == null){
				throw new Exception("Se necesita una fecha de partido");
			}
			
			Cancha cancha = businessDelegatorView.getCancha(Long.parseLong(somCanchas.getValue().toString()));
			Empleado empleado = businessDelegatorView.getEmpleado(Long.parseLong(somArbitros.getValue().toString()));
			
			
			partido.setCancha(cancha);
			partido.setEmpleado(empleado);
			partido.setFecha(partido.getFecha());
			
			businessDelegatorView.updatePartido(partido);
			FacesUtils.addInfoMessage("El partido se ha modificado con exito");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
	}

	public void limpiar(){
		somArbitros.setValue("-1");
		somCanchas.setValue("-1");
		partido.setFecha(null);
	}

	public String regresar() {
		FacesUtils.putinSession("Partido", null);
        return "/XHTML/listaRondas.xhtml";
    }
    
    
    
}
