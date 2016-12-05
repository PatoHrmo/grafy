/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementy;
import java.awt.Color;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Pamätá si súradnice vrcholu v diagrame, názov a farbu akou bude vyznaèený poprípade iné atribúty, ktoré si zadal užívate¾.
 * @author Erik
 */
@SuppressWarnings("serial")
public class Vrchol implements Serializable{
    
    private String nazov;
    
    //pomocne premenne k algoritmom
    private double znacka;
    private boolean pomBoolean;
    private boolean bolNavstiveny;
    private Vrchol pomVrchol;
    
    private Color farba = Color.gray;
    private List<Hrana> incidentneHrany;
    /**
     * Vytvorí nový vrchol.
     * @param paPoziciaX poloha vrcholu na osi x
     * @param paPoziciaY poloha vrcholu na osi y
     * @param paNazov názov vrcholu 
     */
    public Vrchol(String paNazov) {
        this.nazov = paNazov;
        this.bolNavstiveny = false;
        this.incidentneHrany = new LinkedList<>();
    }
    
   

    /**
     * nastaví názov vrcholu
     * @param nazov
     */
    public void setNazov(String nazov) {
        this.nazov = nazov;
    }
    
    /**
     * Vráti názov vrcholu
     * @return
     */
    public String getNazov() {
        return nazov;
    }
    
    /**
     * Vráti farbu vrcholu
     * @return
     */    
    public Color getFarba() {
        return farba;
    }

    /**
     * Nastaví farbu vrcholu
     * @param farba
     */
    public void setFarba(Color farba) {
        this.farba = farba;
    }
    
    /**
     * Vráti znaèku vrcholu
     * @return
     */  
    public double getZnacka() {
        return znacka;
    }

    /**
     * Nastaví farbu vrcholu
     * @param znacka
     */
    public void setZnacka(double znacka) {
        this.znacka = znacka;
    }

    /**
     * Vráti hodnotu pomocného boolean atribútu ktorý používam v algoritmoch
     * @return
     */ 
    public boolean isPomBoolean() {
        return pomBoolean;
    }

    /**
     * Nastaví hodnotu pomocného boolean atribútu ktorý používam v algoritmoch.
     * @param pomBoolean nová farba vrcholu
     */
    public void setPomBoolean(boolean pomBoolean) {
        this.pomBoolean = pomBoolean;
    }

    /**
     * Vráti pomocný vrchol, väèšinou používam na uchovanie predošlého vrcholu v ceste v niektorích algoritmoch.
     * @return
     */ 
    public Vrchol getPomVrchol() {
        return pomVrchol;
    }
    
    /**
     * Nastaví pomocný vrchol, väèšinou používam na uchovanie predošlého vrcholu v ceste v niektorích algoritmoch.
     * @param pomVrchol nová farba vrcholu
     */
    public void setPomVrchol(Vrchol pomVrchol) {
        this.pomVrchol = pomVrchol;
    }
    
    /**
     * Nastaví farbu vrcholu na èerveno
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
    
    public Hrana dajPrvuIncidentuHranu() {
        if(incidentneHrany.isEmpty())
            return null;
        return incidentneHrany.get(0);
    }
}
