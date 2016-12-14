package elementy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;




public class Graf implements IGraf{

    private List<Vrchol> vrcholy; // indexovaný zoznam vrcholov
    private List<Hrana> hrany; // indexovanýzoznam hrán

    /**
     * Vytvorí nový prázdny graf
     */
    public Graf() {
    	vrcholy = new ArrayList<>();
    	hrany = new ArrayList<>();
        //konštruktor   
    }

    /* (non-Javadoc)
	 * @see elementy.IGraf#vlozVrchol(elementy.Vrchol)
	 */
    @Override
	public boolean vlozVrchol(Vrchol paVrchol) {
        vrcholy.add(paVrchol);
        return true;
    }

    /* (non-Javadoc)
	 * @see elementy.IGraf#vlozHranu(elementy.Hrana)
	 */
    @Override
	public boolean vlozHranu(Hrana paHrana) {
        hrany.add(paHrana);
        paHrana.getVrchol1().pridajIncidentnuHranu(paHrana);
        paHrana.getVrchol2().pridajIncidentnuHranu(paHrana);
        return true;
    }


    
    /* (non-Javadoc)
	 * @see elementy.IGraf#odstranVrchol(elementy.Vrchol)
	 */
    @Override
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

    /* (non-Javadoc)
	 * @see elementy.IGraf#getVrcholy()
	 */
    @Override
	public List<Vrchol> getVrcholy() {
        return vrcholy;
    }

    /* (non-Javadoc)
	 * @see elementy.IGraf#getHrany()
	 */
    @Override
	public List<Hrana> getHrany() {
        return hrany;
    }

    /* (non-Javadoc)
	 * @see elementy.IGraf#odstranHranu(elementy.Hrana)
	 */
    @Override
	public void odstranHranu(Hrana hrana) {
        hrany.remove(hrana);
        hrana.getVrchol1().vymazIncidentnuHranu(hrana);
        hrana.getVrchol2().vymazIncidentnuHranu(hrana);
    }

   

    /* (non-Javadoc)
	 * @see elementy.IGraf#dajHranu(elementy.Vrchol, elementy.Vrchol)
	 */
    @Override
	public Hrana dajHranu(Vrchol vrchol1, Vrchol vrchol2) {
        for (Hrana hrana : hrany) {
            if ((vrchol1 == hrana.getVrchol1() && vrchol2 == hrana.getVrchol2()) || (vrchol2 == hrana.getVrchol1() && vrchol1 == hrana.getVrchol2()&&!hrana.orientovana())) {
                return hrana;
            }
        }
        return null;
    }

    /* (non-Javadoc)
	 * @see elementy.IGraf#odfarbiGraf()
	 */
    @Override
	public void odfarbiGraf() {
        for (Vrchol vrchol : vrcholy) {
            vrchol.setFarba(Color.gray);
        }
        for (Hrana hrana : hrany) {
            hrana.setFarba(Color.gray);
        }
    }

}
