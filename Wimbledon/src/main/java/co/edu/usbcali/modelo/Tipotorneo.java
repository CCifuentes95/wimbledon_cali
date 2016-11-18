package co.edu.usbcali.modelo;
// Generated 14/11/2016 04:39:29 PM by Hibernate Tools 4.3.1.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Tipotorneo generated by hbm2java
 */
public class Tipotorneo  implements java.io.Serializable {


     private Long codigotipotorneo;
     private String descripcion;
     private Set<Torneo> torneos = new HashSet<Torneo>(0);

    public Tipotorneo() {
    }

	
    public Tipotorneo(Long codigotipotorneo, String descripcion) {
        this.codigotipotorneo = codigotipotorneo;
        this.descripcion = descripcion;
    }
    public Tipotorneo(Long codigotipotorneo, String descripcion, Set<Torneo> torneos) {
       this.codigotipotorneo = codigotipotorneo;
       this.descripcion = descripcion;
       this.torneos = torneos;
    }
   
    public Long getCodigotipotorneo() {
        return this.codigotipotorneo;
    }
    
    public void setCodigotipotorneo(Long codigotipotorneo) {
        this.codigotipotorneo = codigotipotorneo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set<Torneo> getTorneos() {
        return this.torneos;
    }
    
    public void setTorneos(Set<Torneo> torneos) {
        this.torneos = torneos;
    }




}


