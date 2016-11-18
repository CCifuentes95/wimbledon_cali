package co.edu.usbcali.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.modelo.Cancha;
import co.edu.usbcali.modelo.Empleado;
import co.edu.usbcali.modelo.Partido;
import co.edu.usbcali.modelo.Ronda;
import co.edu.usbcali.modelo.control.ICanchaLogic;
import co.edu.usbcali.modelo.control.IEmpleadoLogic;
import co.edu.usbcali.modelo.control.IPartidoLogic;
import co.edu.usbcali.modelo.control.IRondaLogic;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class CasosDeUsoTest {
	
	private final static Logger log=LoggerFactory.getLogger(CasosDeUsoTest.class);
		
	@Autowired
	private IPartidoLogic partidoLogic;
	
	@Autowired
	private ICanchaLogic canchaLogic;
	
	@Autowired
	private IEmpleadoLogic empleadoLogic;
	
	@Autowired
	private IRondaLogic rondaLogic;
	
	//@Test
	public void testA() {
		Partido partido = null;
		Cancha cancha = null;
		try {
			partido = partidoLogic.getPartido(2L);
			cancha = canchaLogic.getCancha(4L);
			
			log.info("Codigo Cancha Anterior: "+partido.getCancha().getCodigocancha());
			partido.setCancha(cancha);
			log.info("Codigo Cancha Actual: "+partido.getCancha().getCodigocancha());
			
			partidoLogic.updatePartido(partido);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}		
	}
	
	//@Test
	public void testB() {
		Partido partido = null;
		Empleado empleado = null;
		try {
			partido = partidoLogic.getPartido(2L);
			empleado = empleadoLogic.getEmpleado(3L);
			
			log.info("Codigo Arbitro Anterior: "+partido.getEmpleado().getCodigoempleado());
			partido.setEmpleado(empleado);
			log.info("Codigo Actual: "+partido.getEmpleado().getCodigoempleado());
			
			partidoLogic.updatePartido(partido);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}		
	}
	
	//@Test
	public void testC() {
		Partido partido = null;

		try {
			partido = partidoLogic.getPartido(2L);
			
			log.info("Codigo Arbitro Anterior: "+partido.getFecha());
			partido.setFecha(new Date());
			log.info("Codigo Actual: "+partido.getFecha());
			
			partidoLogic.updatePartido(partido);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}		
	}
	
	@Test
	public void testD() {
		Ronda ronda = null;
		try {
			ronda = rondaLogic.getRonda(4L);
			
			log.info("Precio Ronda Anterior: "+ronda.getPrecio());
			ronda.setPrecio(950000D);
			log.info("Precio Ronda Anterior: "+ronda.getPrecio());
			
			rondaLogic.updateRonda(ronda);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

}
