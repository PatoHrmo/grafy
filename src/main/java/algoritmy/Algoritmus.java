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
 * @author Patrik
 */
public abstract class Algoritmus {

    /**
     * graf nad ktorom sa algoritmus vykonava
     */
    protected IGraf graf;
    protected List<Object> krokyAlgoritmu;

    /**
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
    protected abstract void spravAlgoritmus();
    
    public List<Object> getKroky() {
    	graf.odfarbiGraf();
    	spravAlgoritmus();
    	return getKrokyAlgoritmu();
    }

}
