/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmy;

import java.util.ArrayList;
import java.util.List;

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
     * @param textPane
     * @param pauser
     * @param vstupy
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
    public abstract void spravAlgoritmus();
        
}
