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

    private ArrayList<Vrchol> vrcholy = new ArrayList<>(); // indexovan� zoznam vrcholov
    private ArrayList<Hrana> hrany = new ArrayList<>(); // indexovan�zoznam hr�n

    /**
     * Vytvor� nov� pr�zdny graf
     */
    public Graf() {
        //kon�truktor   
    }

    /**
     * Vlo�� vrchol do grafu.
     * @param paVrchol vrchol, ktor� sa vlo�� do grafu.
     * @return
     */
    public boolean vlozVrchol(Vrchol paVrchol) {
        vrcholy.add(paVrchol);
        return true;
    }

    /**
     * Vlo�� hranu do grafu.
     * @param paHrana hrana, ktor� sa vlo�� do grafu
     * @return
     */
    public boolean vlozHranu(Hrana paHrana) {
        hrany.add(paHrana);
        paHrana.getVrchol1().pridajIncidentnuHranu(paHrana);
        paHrana.getVrchol2().pridajIncidentnuHranu(paHrana);
        return true;
    }

    /**
     * Vr�ti tak� vrchol, ktor�ho grafick� reprezent�cia na pl�tne (kruh) obsahuje bod so s�radnicami. Ak tak� vrchol nie je, vr�ti null.
     * @param paX Xov� s�radnica bodu
     * @param paY Ynov� s�radnica bodu
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
     * Odstr�ni vrchol a v�etky hrany s n�m incidentn�.
     * @param paVrchol vrchol na odstr�nenie.
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
     * Vr�ti indexovan� zoznam vrcholov.
     * @return
     */
    public ArrayList<Vrchol> getVrcholy() {
        return vrcholy;
    }

    /**
     * Vr�ti indexovan� zoznam hr�n.
     * @return
     */
    public ArrayList<Hrana> getHrany() {
        return hrany;
    }

    /**
     * Odstr�ni hranu zgrafu.
     * @param hrana Hrana na odstr�nenie.
     */
    public void odstranHranu(Hrana hrana) {
        hrany.remove(hrana);
        hrana.getVrchol1().vymazIncidentnuHranu(hrana);
        hrana.getVrchol2().vymazIncidentnuHranu(hrana);
    }

    /**
     * Vr�ti prv� hranu v zozname pre ktor� plat�, �e bod sa nach�dza v tesnej bl�zkosti jej reprezent�cie. Ak tak� nie je , vr�ti null.
     * @param paX Xov� s�radnica bodu
     * @param paY Ynov� s�radnica bodu
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
     * Vr�ti hranu (vrchol1,vrchol2) alebo {vrchol1,vrchol1}ak tak�existuje, inak vr�ti null;
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
     * nastav� farbu v�etk�ch komponentov grafu na siv�
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
