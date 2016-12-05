/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmy;


import java.awt.Color;

import elementy.Vrchol;
import elementy.Hrana;
import elementy.IGraf;

/**
 * Algoritmus pre  hĺbkové prehľadávanie rekurziou
 * @author Erik
 */
public class PrehladavanieDoHlbky extends Algoritmus {

    private Vrchol vrchol;

    /**
     * vytvorenie inštancie algoritmu
     * @param graf graf nad ktorým sa Floydov algoritmus vykoná
     */
    public PrehladavanieDoHlbky(IGraf graf,Vrchol vrchol) {
        super(graf);
        this.vrchol = vrchol;
    }
    
    /**
     * vyzve používateľa aby vybral prvý vrchol stromu a potom spustí algoritmus
     */
    @Override
    public void spravAlgoritmus() {
        prehladavanieDoHlbky(vrchol);       
    }
    
    /**
     * Samotný rekurzívny algoritmus.
     * Pri každom kroku zafarbí novoobjavený vrchol a hranu ktorou sme ho objavili.
     * @param paVrchol ďalší nájdený vrchol, na začiatku je to prvý vrchol ktorý označil používateľ
     */
    private void prehladavanieDoHlbky(Vrchol paVrchol) {
        paVrchol.zafarbi();
        for (Hrana hrana : graf.getHrany()) {
            if (hrana.getVrchol1() == paVrchol && hrana.getVrchol2().getFarba() != Color.red) {
                hrana.setFarba(Color.red);
                prehladavanieDoHlbky(hrana.getVrchol2());
            } else if (hrana.getVrchol2() == paVrchol && hrana.getVrchol1().getFarba() != Color.red) {
                hrana.setFarba(Color.red);
                prehladavanieDoHlbky(hrana.getVrchol1());
            }
        }
    }
    
}
