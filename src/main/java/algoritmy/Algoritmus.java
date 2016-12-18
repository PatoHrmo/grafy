/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import elementy.Hrana;
import elementy.IGraf;

/**
 * Abstraktná trieda, dedia z nej triedy reprezentujúce konkrétny algoritmus. 
 * @author Erik
 */
public abstract class Algoritmus {

    /**
     * graf nad ktorým sa algoritmus vykonáva
     */
    protected IGraf graf;
    protected List<Object> krokyAlgoritmu;

    /**
     *
     * @param graf
     */
    public Algoritmus(IGraf graf) {
        this.graf = graf;
        krokyAlgoritmu = new ArrayList<>();
    }
    public List<Object> getKrokyAlgoritmu() {
    	return krokyAlgoritmu;
    }
    
    /**
     * metóda,ktorá sa volá pri spúšťní algoritmu
     */
    public void spravAlgoritmus(){
        for (Hrana hrana :
                graf.getHrany()) {
            hrana.setFarba(Color.GRAY);
        }
    }
        
}
