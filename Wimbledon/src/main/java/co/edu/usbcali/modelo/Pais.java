package co.edu.usbcali.modelo;
// Generated 14/11/2016 04:39:29 PM by Hibernate Tools 4.3.1.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Pais generated by hbm2java
 */
public class Pais  implements java.io.Serializable {


     private Long codigopais;
     private String nombre;
     private Set<Jugador> jugadors = new HashSet<Jugador>(0);

    public Pais() {
    }

	
    public Pais(Long codigopais, String nombre) {
        this.codigopais = codigopais;
        this.nombre = nombre;
    }
    public Pais(Long codigopais, String nombre, Set<Jugador> jugadors) {
       this.codigopais = codigopais;
       this.nombre = nombre;
       this.jugadors = jugadors;
    }
   
    public Long getCodigopais() {
        return this.codigopais;
    }
    
    public void setCodigopais(Long codigopais) {
        this.codigopais = codigopais;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<Jugador> getJugadors() {
        return this.jugadors;
    }
    
    public void setJugadors(Set<Jugador> jugadors) {
        this.jugadors = jugadors;
    }




}

