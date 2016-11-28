/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmy;

import java.awt.Color;
import java.util.ArrayList;


import elementy.Graf;
import elementy.Hrana;
import elementy.Vrchol;

/**
 * Algoritmus pre prehÄľadĂˇvanie doĹˇĂ­rky rekurziou
 * @author Erik
 */
public class PrehladavanieDoSirky extends Algoritmus{

    
    private Vrchol vrchol;
    
    /**
     * vytvorenie inĹˇtancie algoritmu
     * @param graf graf nad ktorĂ˝m sa Floydov algoritmus vykonĂˇ

     */
    public PrehladavanieDoSirky(Graf graf,Vrchol vrchol) {
    	super(graf);
        this.vrchol = vrchol;
    }

    /**
     * vyzve pouĹľĂ­vateÄľa aby vybral prvĂ˝ vrchol stromu a potom spustĂ­ algoritmus
     */
    @Override
    public void spravAlgoritmus() {
        ArrayList<Vrchol> a = new ArrayList<>();        
        vrchol.zafarbi();
        a.add(vrchol);
        prehladavanieDoSirky(a);
    }
    
    /**
     * SamotnĂ˝ rekurzĂ­vny algoritmus.
     * Pri kaĹľdom kroku zafarbĂ­ novoobjavenĂ˝ vrchol a hranu ktorou sme ho objavili.
     * @param vrcholy zoznam objavenĂ˝ch vrcholov z ktorĂ˝ch sme eĹˇte nezaÄŤali prehÄľadĂˇvaĹĄ ÄŹalej, na zaÄŤiatku je v Ĺ�omlenjedenvrchol, ten ktorĂ˝ si vybral pouĹľĂ­vateÄľ 
     */
    private void prehladavanieDoSirky(ArrayList<Vrchol> vrcholy) {
        if (vrcholy.isEmpty()) {
            return;
        }
        vrcholy.get(0).zafarbi();

        for (Hrana hrana : graf.getHrany()) {
            if (hrana.getVrchol1() == vrcholy.get(0) && !hrana.getVrchol2().getFarba().equals(Color.red)) {
                vrcholy.add(hrana.getVrchol2());
                hrana.getVrchol2().zafarbi();
                hrana.setFarba(Color.red);

            } else if (hrana.getVrchol2() == vrcholy.get(0) && !hrana.getVrchol1().getFarba().equals(Color.red)) {
                vrcholy.add(hrana.getVrchol1());
                hrana.getVrchol1().zafarbi();
                hrana.setFarba(Color.red);

            }
        }
        vrcholy.remove(0);
        prehladavanieDoSirky(vrcholy);
    }

}
