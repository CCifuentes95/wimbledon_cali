package co.edu.usbcali.modelo;
// Generated 14/11/2016 04:39:29 PM by Hibernate Tools 4.3.1.Final



/**
 * Equipo generated by hbm2java
 */
public class Equipo  implements java.io.Serializable {


     private Long codigoequipo;
     private Jugador jugador;
     private String nombre;

    public Equipo() {
    }

    public Equipo(Long codigoequipo, Jugador jugador, String nombre) {
       this.codigoequipo = codigoequipo;
       this.jugador = jugador;
       this.nombre = nombre;
    }
   
    public Long getCodigoequipo() {
        return this.codigoequipo;
    }
    
    public void setCodigoequipo(Long codigoequipo) {
        this.codigoequipo = codigoequipo;
    }
    public Jugador getJugador() {
        return this.jugador;
    }
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




}

