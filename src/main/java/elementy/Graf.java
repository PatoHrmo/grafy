package elementy;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javafx.scene.shape.Circle;

/**
 *
 * @author Erik
 */
@SuppressWarnings("restriction")
public class Graf{

    private ArrayList<Vrchol> vrcholy = new ArrayList<>(); // indexovaný zoznam vrcholov
    private ArrayList<Hrana> hrany = new ArrayList<>(); // indexovanýzoznam hrán

    /**
     * Vytvorí nový prázdny graf
     */
    public Graf() {
        //konštruktor   
    }

    /**
     * Vloží vrchol do grafu.
     * @param paVrchol vrchol, ktorý sa vloží do grafu.
     * @return
     */
    public boolean vlozVrchol(Vrchol paVrchol) {
        vrcholy.add(paVrchol);
        return true;
    }

    /**
     * Vloží hranu do grafu.
     * @param paHrana hrana, ktorá sa vloží do grafu
     * @return
     */
    public boolean vlozHranu(Hrana paHrana) {
        hrany.add(paHrana);
        paHrana.getVrchol1().pridajIncidentnuHranu(paHrana);
        paHrana.getVrchol2().pridajIncidentnuHranu(paHrana);
        return true;
    }

    /**
     * Vráti taký vrchol, ktorého grafická reprezentácia na plátne (kruh) obsahuje bod so súradnicami. Ak taký vrchol nie je, vráti null.
     * @param paX Xová súradnica bodu
     * @param paY Ynová súradnica bodu
     * @return
     */
    public Vrchol dajVrchol(int paX, int paY) {
        for (Vrchol vrchol : vrcholy) {
            Circle circle = new Circle(vrchol.getPoziciaX() * 20 + 20, vrchol.getPoziciaY() * 20 + 20, 15);
            if (circle.intersects(paX, paY, 0, 0)) {
                return vrchol;

            }
        }
        return null;
    }

    
    /**
     * Odstráni vrchol a všetky hrany s ním incidentné.
     * @param paVrchol vrchol na odstránenie.
     */
    public void odstranVrchol(Vrchol paVrchol) {
        for (Hrana hrana : hrany) {
            if (hrana.getVrchol1() == paVrchol || hrana.getVrchol2() == paVrchol) {
                hrany.remove(hrana);
                hrana.getVrchol1().vymazIncidentnuHranu(hrana);
                hrana.getVrchol2().vymazIncidentnuHranu(hrana);
            }
        }
        vrcholy.remove(paVrchol);
    }

    /**
     * Vráti indexovaný zoznam vrcholov.
     * @return
     */
    public ArrayList<Vrchol> getVrcholy() {
        return vrcholy;
    }

    /**
     * Vráti indexovaný zoznam hrán.
     * @return
     */
    public ArrayList<Hrana> getHrany() {
        return hrany;
    }

    /**
     * Odstráni hranu zgrafu.
     * @param hrana Hrana na odstránenie.
     */
    public void odstranHranu(Hrana hrana) {
        hrany.remove(hrana);
        hrana.getVrchol1().vymazIncidentnuHranu(hrana);
        hrana.getVrchol2().vymazIncidentnuHranu(hrana);
    }

    /**
     * Vráti prvú hranu v zozname pre ktorú platí, že bod sa nachádza v tesnej blízkosti jej reprezentácie. Ak taká nie je , vráti null.
     * @param paX Xová súradnica bodu
     * @param paY Ynová súradnica bodu
     * @return
     */
    public Hrana dajHranu(int paX, int paY) {
        for (Hrana hrana : hrany) {
            Line2D line = new Line2D.Double(hrana.getVrchol1().getPoziciaX() * 20 + 20, hrana.getVrchol1().getPoziciaY() * 20 + 20, hrana.getVrchol2().getPoziciaX() * 20 + 20, hrana.getVrchol2().getPoziciaY() * 20 + 20);
            if (line.intersects(paX - 5, paY - 5, 10, 10)) {
                return hrana;
            }
        }
        return null;
    }

    /**
     * Vráti hranu (vrchol1,vrchol2) alebo {vrchol1,vrchol1}ak takáexistuje, inak vráti null;
     * @param vrchol1
     * @param vrchol2
     * @return
     */
    public Hrana dajHranu(Vrchol vrchol1, Vrchol vrchol2) {
        for (Hrana hrana : hrany) {
            if ((vrchol1 == hrana.getVrchol1() && vrchol2 == hrana.getVrchol2()) || (vrchol2 == hrana.getVrchol1() && vrchol1 == hrana.getVrchol2()&&!hrana.orientovana())) {
                return hrana;
            }
        }
        return null;
    }

    /**
     * nastaví farbu všetkých komponentov grafu na sivú
     */
    public void odfarbiGraf() {
        for (Vrchol vrchol : vrcholy) {
            vrchol.setFarba(Color.gray);
        }
        for (Hrana hrana : hrany) {
            hrana.setFarba(Color.gray);
        }
    }

}
