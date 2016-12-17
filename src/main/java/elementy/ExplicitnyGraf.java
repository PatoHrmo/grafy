package elementy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class ExplicitnyGraf implements IGraf{

    private List<Vrchol> vrcholy; // indexovan� zoznam vrcholov
    private List<Hrana> hrany; // indexovan�zoznam hr�n

    /**
     * Vytvor� nov� pr�zdny graf
     */
    public ExplicitnyGraf() {
    	vrcholy = new ArrayList<>();
    	hrany = new ArrayList<>();
        //kon�truktor   
    }


    private ExplicitnyGraf (ExplicitnyGraf graf) {
        //sluzi na vyhladanie duplicity vrcholov
        TreeMap<String,Vrchol> vlozene = new TreeMap<>();
        vrcholy = new ArrayList<>();
        hrany = new ArrayList<>();
        for(Hrana hrana : graf.hrany) {
            hrany.add(hrana.clone());
        }
        for(Hrana hrana : hrany) {
            Vrchol v1 = hrana.getVrchol1();
            Vrchol v2 = hrana.getVrchol2();
            if(!vlozene.containsKey(v1.getNazov())){
                vlozene.put(v1.getNazov(),v1);
                vrcholy.add(v1);
            }
            if(!vlozene.containsKey(v2.getNazov())){
                vlozene.put(v2.getNazov(),v2);
                vrcholy.add(v2);
            }
            v1.pridajIncidentnuHranu(hrana);
            v2.pridajIncidentnuHranu(hrana);
        }
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
        List<Hrana> hranyNaVymazanie = new ArrayList<>();
        for (Hrana hrana : hrany) {
            if (hrana.getVrchol1() == paVrchol || hrana.getVrchol2() == paVrchol) {
                hranyNaVymazanie.add(hrana);
                hrana.getVrchol1().vymazIncidentnuHranu(hrana);
                hrana.getVrchol2().vymazIncidentnuHranu(hrana);
            }
        }

        hrany.removeAll(hranyNaVymazanie);
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
        Hrana hrana = new Hrana(vrchol1,vrchol2,0,false);
        vlozHranu(hrana);
        return hrana;
    }

    @Override
    public Vrchol dajVrchol(String nazovVrcholu) {
        for (Vrchol vrchol :
                vrcholy) {
            if (vrchol.getNazov().equals(nazovVrcholu))
            return vrchol;
        }
        Vrchol vrchol = new Vrchol(nazovVrcholu);
        vrcholy.add(vrchol);
        return vrchol;
    }

    @Override
    public boolean vrcholExistuje(String nazovVrcholu) {
        for (Vrchol vrchol :
                vrcholy) {
            if (vrchol.getNazov().equals(nazovVrcholu))
                return true;
        }
        return false;
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
    
    public ExplicitnyGraf clone() {
    	return new ExplicitnyGraf(this);
    }

}
