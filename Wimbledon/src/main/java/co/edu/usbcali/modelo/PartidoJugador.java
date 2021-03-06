package co.edu.usbcali.modelo;
// Generated 14/11/2016 04:39:29 PM by Hibernate Tools 4.3.1.Final



/**
 * PartidoJugador generated by hbm2java
 */
public class PartidoJugador  implements java.io.Serializable {


     private Long codigopartidoJugador;
     private Jugador jugador;
     private Partido partido;

    public PartidoJugador() {
    }

	
    public PartidoJugador(Long codigopartidoJugador, Jugador jugador) {
        this.codigopartidoJugador = codigopartidoJugador;
        this.jugador = jugador;
    }
    public PartidoJugador(Long codigopartidoJugador, Jugador jugador, Partido partido) {
       this.codigopartidoJugador = codigopartidoJugador;
       this.jugador = jugador;
       this.partido = partido;
    }
   
    public Long getCodigopartidoJugador() {
        return this.codigopartidoJugador;
    }
    
    public void setCodigopartidoJugador(Long codigopartidoJugador) {
        this.codigopartidoJugador = codigopartidoJugador;
    }
    public Jugador getJugador() {
        return this.jugador;
    }
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public Partido getPartido() {
        return this.partido;
    }
    
    public void setPartido(Partido partido) {
        this.partido = partido;
    }




}


