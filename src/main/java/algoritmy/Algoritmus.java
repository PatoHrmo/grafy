/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmy;

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


    /**
     *
     * @param graf
     * @param textPane
     * @param pauser
     * @param vstupy
     */
    public Algoritmus(IGraf graf) {
        this.graf = graf;
    }
    
    /**
     * metóda,ktorá sa volá pri spúšťní algoritmu
     */
    public abstract void spravAlgoritmus();
        
}
