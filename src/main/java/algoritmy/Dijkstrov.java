package algoritmy;

import java.awt.Color;

import elementy.Vrchol;
import elementy.Hrana;
import elementy.IGraf;

/**
 * Dijkstrov algoritmus
 *
 * @author Erik
 */
public class Dijkstrov extends Algoritmus {

    /**
     * vytvorí inštanciu tohto konkrétneho algoritmu
     *
     * @param graf graf nad ktorým sa dijkstrov algoritmus vykoná
     * @param textPane panel pre výstupy krokov algoritmu
     * @param pauser nástroj na krokovanie
     * @param vstupy nástroj na získavanie vstupov (začiatočný vrchol, koncový
     * vrchol)
     */
    private Vrchol aVrchol;

    public Dijkstrov(IGraf graf, Vrchol vrchol) {
        super(graf);
        this.aVrchol = vrchol;
    }

    /**
     * samotný dijkstrov algoritmus
     */
    @Override
    public void spravAlgoritmus() {
        //inicializacia, nastavime znacky vsetkych vrcholov na infinity a zaciatocnyVrchol na 0
        for (Vrchol vrchol : graf.getVrcholy()) {
            vrchol.setZnacka(Integer.MAX_VALUE);
        }
        
        Vrchol riadiaciVrchol = aVrchol;
        aVrchol.setZnacka(0);
        aVrchol.zafarbi();
        
        while (!vyfarbeneVsetkyVrcholy()) {
            // vypis riadku postupu algoritmu
            // pre kazdu hranu ktora obsahuje riadiaci vrchol a jej druhy vrchol nieje oznaceny trvalou znackou
            // skontrolujeme ci je aktualna znacka tohto vrchola vacsia ako znacka riadiaceho vrcholu + cena hrany
            for (Hrana hrana : graf.getHrany()) {
                if (hrana.getVrchol1() == riadiaciVrchol && !hrana.getVrchol2().getFarba().equals(Color.red)) {
                    if (hrana.getVrchol2().getZnacka() > riadiaciVrchol.getZnacka() + hrana.getCena()) {
                        // ak ano, nastavime znacku tohto vrcholu na znacka riadiaceho+cena Hrany
                        hrana.getVrchol2().setZnacka(riadiaciVrchol.getZnacka() + hrana.getCena());
                        hrana.getVrchol2().setPomVrchol(riadiaciVrchol);
                    }
                } else if (hrana.getVrchol2() == riadiaciVrchol && !hrana.getVrchol1().getFarba().equals(Color.red) && !hrana.orientovana()) {
                    if (hrana.getVrchol1().getZnacka() > riadiaciVrchol.getZnacka() + hrana.getCena()) {
                        // ak ano, nastavime znacku tohtoo vrcholu na znacka riadiaceho+cena Hrany
                        hrana.getVrchol1().setZnacka(riadiaciVrchol.getZnacka() + hrana.getCena());
                        hrana.getVrchol1().setPomVrchol(riadiaciVrchol);
                    }
                }
            }

            //vyberiem vrchol s najmensou NEtrvalou znackou, nastavime ho za riadiaci a znacku nastavime na trvalu
            for (Vrchol vrchol : graf.getVrcholy()) {
                if (!vrchol.getFarba().equals(Color.red)) {
                    riadiaciVrchol = vrchol;
                }
            }
            for (Vrchol vrchol : graf.getVrcholy()) {
                if (!vrchol.getFarba().equals(Color.red) && vrchol.getZnacka() < riadiaciVrchol.getZnacka()) {
                    riadiaciVrchol = vrchol;
                }
            }
            riadiaciVrchol.zafarbi();
            this.krokyAlgoritmu.add(graf.clone());
        }  
    }

    private boolean vyfarbeneVsetkyVrcholy() {
        for (Vrchol vrchol : graf.getVrcholy()) {
            if (!vrchol.getFarba().equals(Color.red)) {
                return false;
            }
        }
        return true;
    }
}
