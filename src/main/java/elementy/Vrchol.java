/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementy;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Pam�t� si s�radnice vrcholu v diagrame, n�zov a farbu akou bude vyzna�en� popr�pade in� atrib�ty, ktor� si zadal u��vate�.
 * @author Erik
 */
public class Vrchol implements Cloneable {
    
    private String nazov;
    
    //pomocne premenne k algoritmom
    private double znacka;
    private boolean pomBoolean;
    private boolean bolNavstiveny;
    private Vrchol pomVrchol;
    
    private Color farba = Color.gray;
    private List<Hrana> incidentneHrany;
    /**
     * Vytvor� nov� vrchol.
     * @param paNazov n�zov vrcholu 
     */
    public Vrchol(String paNazov) {
        this.nazov = paNazov;
        this.bolNavstiveny = false;
        this.incidentneHrany = new LinkedList<>();
    }
    private Vrchol(Vrchol Vrchol) {
    	this.nazov = Vrchol.nazov;
    	this.znacka = Vrchol.znacka;
    	this.pomBoolean = Vrchol.pomBoolean;
    	this.bolNavstiveny = Vrchol.bolNavstiveny;
    	if(Vrchol.pomVrchol!=null) {
    		this.pomVrchol = Vrchol.pomVrchol.clone();
    	}
    	this.farba = new Color(Vrchol.farba.getRGB());
    	this.incidentneHrany = new LinkedList<>();

    }
    
   

    /**
     * nastav� n�zov vrcholu
     * @param nazov
     */
    public void setNazov(String nazov) {
        this.nazov = nazov;
    }
    
    /**
     * Vr�ti n�zov vrcholu
     * @return
     */
    public String getNazov() {
        return nazov;
    }
    
    /**
     * Vr�ti farbu vrcholu
     * @return
     */    
    public Color getFarba() {
        return farba;
    }

    /**
     * Nastav� farbu vrcholu
     * @param farba
     */
    public void setFarba(Color farba) {
        this.farba = farba;
    }
    
    /**
     * Vr�ti zna�ku vrcholu
     * @return
     */  
    public double getZnacka() {
        return znacka;
    }

    /**
     * Nastav� farbu vrcholu
     * @param znacka
     */
    public void setZnacka(double znacka) {
        this.znacka = znacka;
    }

    /**
     * Vr�ti hodnotu pomocn�ho boolean atrib�tu ktor� pou��vam v algoritmoch
     * @return
     */ 
    public boolean isPomBoolean() {
        return pomBoolean;
    }

    /**
     * Nastav� hodnotu pomocn�ho boolean atrib�tu ktor� pou��vam v algoritmoch.
     * @param pomBoolean nov� farba vrcholu
     */
//    public void setPomBoolean(boolean pomBoolean) {
//        this.pomBoolean = pomBoolean;
//    }

    /**
     * Vr�ti pomocn� vrchol, v��inou pou��vam na uchovanie predo�l�ho vrcholu v ceste v niektor�ch algoritmoch.
     * @return
     */ 
    public Vrchol getPomVrchol() {
        return pomVrchol;
    }
    
    /**
     * Nastav� pomocn� vrchol, v��inou pou��vam na uchovanie predo�l�ho vrcholu v ceste v niektor�ch algoritmoch.
     * @param pomVrchol nov� farba vrcholu
     */
    public void setPomVrchol(Vrchol pomVrchol) {
        this.pomVrchol = pomVrchol;
    }
    
    /**
     * Nastav� farbu vrcholu na �erveno
     */    
    public void zafarbi(){
        farba=Color.red;
    }
    
    public boolean isBolNavstiveny() {
        return bolNavstiveny;
    }

    public void setBolNavstiveny(boolean bolNavstiveny) {
        this.bolNavstiveny = bolNavstiveny;
    }
    
    public List<Hrana> getIncidentneHrany() {
        return incidentneHrany;
    }

    public void pridajIncidentnuHranu(Hrana hrana) {
        incidentneHrany.add(hrana);
    }
    
    public void vymazIncidentnuHranu(Hrana hrana) {
        incidentneHrany.remove(hrana);
    }
    public Vrchol clone() {
    	return new Vrchol(this);
    }
}
