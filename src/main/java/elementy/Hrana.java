/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementy;

import java.awt.Color;
import java.util.HashMap;

/**
 * Reprezentuje hranu grafu, sklad· sa z dvoch vrcholov, mÙûe byù orientovan·, m· svoju cenu poprÌpade Ôalöie atrib˙ty ktorÈ sizadal pouûÌvateæ.
 * @author Erik
 */
public class Hrana implements Cloneable{
    private Vrchol vrchol1;
    private Vrchol vrchol2;
    private boolean oriantovana;
    private double cena;
    private Color farba = Color.gray;
    @SuppressWarnings("rawtypes")
	//private HashMap atributy = new HashMap();
    private boolean pouzitaZVrcholu1DoVrcholu2;
    private boolean pouzitaZVrcholu2DoVrcholu1;
    private boolean hranaPrvehoPrichodu;

    
    /**
     * Vr·ti atrib˙ty hrany.
     * @return
     */
//    @SuppressWarnings("rawtypes")
//	public HashMap getAtrib˙ty() {
//        return atributy;
//    }

    /**
     * Vr·ti farbu hrany.
     * @return
     */
    public Color getFarba() {
        return farba;
    }

    /**
     * NastavÌ atrib˙ty hrany.
     * @param farba
     */
    public void setFarba(Color farba) {
        this.farba = farba;
    }
    
    /**
     * Vr·ti String vo form·te {nazov prvÈho vrcholu, n·zov druhÈho vrcholu} pre neorientovan˙ hranu
     * a (n·zov prvÈho vrcholu, n·zov druhÈho vrcholu) pre  orientovan˙ hranu.
     * @return
     */
    @Override
    public String toString() {
        return "{"+vrchol1.getNazov()+","+vrchol2.getNazov()+"}";
    }
    
    /**
     * Vr·ti cenu hrany.
     * @return
     */
    public double getCena() {
        return cena;
    }

    /**
     * NastavÌ cenu hrany.
     * @param cena
     */
    public void setCena(double cena) {
        this.cena = cena;
    }

     /**
     * VytvorÌ nov˙ hranu
     * @param paVrchol1 prv˝ vrchol hrany
     * @param paVrchol2 druh˝ vrchol hrany
     * @param cena cena hrany
     * @param orientovana orientovanosù hrany, true - orientovan· | false - neorientovan·
     */
    public Hrana(Vrchol paVrchol1, Vrchol paVrchol2, double cena,boolean orientovana) {
        this.vrchol1 = paVrchol1;
        this.vrchol2 = paVrchol2;
        this.cena = cena;
        this.oriantovana=orientovana;
        this.hranaPrvehoPrichodu = false;
        this.pouzitaZVrcholu1DoVrcholu2 = false;
        this.pouzitaZVrcholu2DoVrcholu1 = false;
    }
    
    private Hrana(Hrana hrana) {
		this.vrchol1 = hrana.vrchol1.clone();
		this.vrchol2 = hrana.vrchol2.clone();
		this.cena = hrana.cena;
		this.oriantovana = hrana.oriantovana;
		this.hranaPrvehoPrichodu = hrana.hranaPrvehoPrichodu;
		this.pouzitaZVrcholu1DoVrcholu2 = hrana.pouzitaZVrcholu1DoVrcholu2;
		this.pouzitaZVrcholu2DoVrcholu1 = hrana.pouzitaZVrcholu2DoVrcholu1;
		this.farba = new Color(hrana.farba.getRGB());
	}

	/**
     * Vr·ti prv˝ vrchol hrany.
     * @return
     */
    public Vrchol getVrchol1() {
        return vrchol1;
    }
    
    /**
     * Vr·ti prv˝ druh˙ hrany.
     * @return
     */
    public Vrchol getVrchol2() {
        return vrchol2;
    }
 
    /**
     * Vr·ti orientovanosù hrany.
     * @return
     */
    public boolean orientovana() {
        return oriantovana;
    }
    
    private boolean isPouzitaZVrcholu1DoVrcholu2() {
        return pouzitaZVrcholu1DoVrcholu2;
    }

    private void setPouzitaZVrcholu1DoVrcholu2(boolean pouzitaZVrcholu1DoVrcholu2) {
        this.pouzitaZVrcholu1DoVrcholu2 = pouzitaZVrcholu1DoVrcholu2;
    }

    private boolean isPouzitaZVrcholu2DoVrcholu1() {
        return pouzitaZVrcholu2DoVrcholu1;
    }

    private void setPouzitaZVrcholu2DoVrcholu1(boolean pouzitaZVrcholu2DoVrcholu1) {
        this.pouzitaZVrcholu2DoVrcholu1 = pouzitaZVrcholu2DoVrcholu1;
    }
    public void pouziZVrchola(Vrchol vrchol) {
        if(this.getVrchol1()==vrchol) {
            this.setPouzitaZVrcholu1DoVrcholu2(true);
        }
        if(this.getVrchol2()==vrchol) {
            this.setPouzitaZVrcholu2DoVrcholu1(true);
        }
    }
    public boolean jePouzitaZVrchola(Vrchol vrchol) {
        if(this.getVrchol1()==vrchol) {
            return this.isPouzitaZVrcholu1DoVrcholu2();
        }
        if(this.getVrchol2()==vrchol) {
            return this.isPouzitaZVrcholu2DoVrcholu1();
        }
        return false;
    }
    public void nastavNavstiveniaNaDefaultneHodnoty() {
        this.setPouzitaZVrcholu1DoVrcholu2(false);
        this.setPouzitaZVrcholu2DoVrcholu1(false);
        this.setHranaPrvehoPrichodu(false);
    }
    public Vrchol dajOpacnyVrcholKuVrcholu(Vrchol vrchol) {
        if(getVrchol1()==vrchol) {
            return getVrchol2();
        }
        if(getVrchol2()==vrchol) {
            return getVrchol1();
        }
        return null;
    }
    public boolean isHranaPrvehoPrichodu() {
        return hranaPrvehoPrichodu;
    }

    public void setHranaPrvehoPrichodu(boolean hranaPrvehoPrichodu) {
        this.hranaPrvehoPrichodu = hranaPrvehoPrichodu;
    }
    
    public Hrana clone() {
    	return new Hrana(this);
    }

}
