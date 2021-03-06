package co.edu.usbcali.modelo;
// Generated 14/11/2016 04:39:29 PM by Hibernate Tools 4.3.1.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Resultado generated by hbm2java
 */
public class Resultado  implements java.io.Serializable {


     private Long codigoresultado;
     private Integer resuadoa;
     private Integer resutadob;
     private Set<PartidoResultado> partidoResultados = new HashSet<PartidoResultado>(0);

    public Resultado() {
    }

	
    public Resultado(Long codigoresultado, Integer resuadoa, Integer resutadob) {
        this.codigoresultado = codigoresultado;
        this.resuadoa = resuadoa;
        this.resutadob = resutadob;
    }
    public Resultado(Long codigoresultado, Integer resuadoa, Integer resutadob, Set<PartidoResultado> partidoResultados) {
       this.codigoresultado = codigoresultado;
       this.resuadoa = resuadoa;
       this.resutadob = resutadob;
       this.partidoResultados = partidoResultados;
    }
   
    public Long getCodigoresultado() {
        return this.codigoresultado;
    }
    
    public void setCodigoresultado(Long codigoresultado) {
        this.codigoresultado = codigoresultado;
    }
    public Integer getResuadoa() {
        return this.resuadoa;
    }
    
    public void setResuadoa(Integer resuadoa) {
        this.resuadoa = resuadoa;
    }
    public Integer getResutadob() {
        return this.resutadob;
    }
    
    public void setResutadob(Integer resutadob) {
        this.resutadob = resutadob;
    }
    public Set<PartidoResultado> getPartidoResultados() {
        return this.partidoResultados;
    }
    
    public void setPartidoResultados(Set<PartidoResultado> partidoResultados) {
        this.partidoResultados = partidoResultados;
    }




}


