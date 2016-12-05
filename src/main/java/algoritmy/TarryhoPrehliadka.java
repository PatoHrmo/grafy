/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmy;

import elementy.Hrana;
import elementy.IGraf;
import elementy.Vrchol;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author patrik
 */
public class TarryhoPrehliadka extends Algoritmus {
	private Vrchol pociatocnyVrchol;
	List<Hrana> sled;
    public TarryhoPrehliadka(IGraf graf, Vrchol pociatocnyVrchol) {
    	super(graf);
    	this.pociatocnyVrchol = pociatocnyVrchol;
    }
    public List<Hrana> vytvorSledPodlaTarryho(Vrchol vrchol) {
        List<Hrana> vyslednySled = new LinkedList<>();
        Vrchol poslednyVrcholSledu;
        Hrana poslednaHranaSledu;
        // krok 1
        Hrana uvodnaHrana = vrchol.dajPrvuIncidentuHranu();
        if (uvodnaHrana == null) {
            return vyslednySled;
        }
        //System.out.println("start z vrchola: "+vrchol.getNazov());
        //System.out.println("uvodna hrana: "+uvodnaHrana.toString());
        uvodnaHrana.pouziZVrchola(vrchol);
        vrchol.setBolNavstiveny(true);
        uvodnaHrana.setHranaPrvehoPrichodu(true);
        uvodnaHrana.dajOpacnyVrcholKuVrcholu(vrchol).setBolNavstiveny(true);
        //System.out.println("opacnyVrchol k uvodnemu: "+uvodnaHrana.dajOpacnyVrcholKuVrcholu(vrchol).getNazov());
        vyslednySled.add(uvodnaHrana);

        // krok 2     
        poslednaHranaSledu = vyslednySled.get(vyslednySled.size() - 1);
        //System.out.println("posledna hrana sledu: "+poslednaHranaSledu.toString());
        poslednyVrcholSledu = poslednaHranaSledu.dajOpacnyVrcholKuVrcholu(vrchol);
        //System.out.println("posledna hrana sledu: "+poslednaHranaSledu.toString());
        List<Hrana> hranyPrvehoPrichodu = new ArrayList<>();
        boolean koniec = false;
        boolean naslaSaHrana;
        while (!koniec) {
        	naslaSaHrana = false;
            hranyPrvehoPrichodu.clear();
           // System.out.println("posledna vrchol sledu: "+poslednyVrcholSledu.toString());
            for (Hrana incidentnaHrana : poslednyVrcholSledu.getIncidentneHrany()) {
                if (!incidentnaHrana.jePouzitaZVrchola(poslednyVrcholSledu)) {
                    if (!incidentnaHrana.isHranaPrvehoPrichodu()) {
                        vyslednySled.add(incidentnaHrana);
                        incidentnaHrana.pouziZVrchola(poslednyVrcholSledu);
                       //s System.out.println("posledna hrana sledu: "+incidentnaHrana.toString());
                        poslednyVrcholSledu = incidentnaHrana.dajOpacnyVrcholKuVrcholu(poslednyVrcholSledu);
                        if (!poslednyVrcholSledu.isBolNavstiveny()) {
                            poslednyVrcholSledu.setBolNavstiveny(true);
                            incidentnaHrana.setHranaPrvehoPrichodu(true);
                        }
                        naslaSaHrana = true;
                        break;
                    } else {
                        hranyPrvehoPrichodu.add(incidentnaHrana);
                    }
                }
            }
            if(naslaSaHrana) {
            	continue;
            }
            if (!hranyPrvehoPrichodu.isEmpty()) {
                poslednaHranaSledu = hranyPrvehoPrichodu.get(0);
                vyslednySled.add(poslednaHranaSledu);
                poslednaHranaSledu.pouziZVrchola(poslednyVrcholSledu);
                //System.out.println("posledna hrana sledu: "+poslednaHranaSledu.toString());
                poslednyVrcholSledu = poslednaHranaSledu.dajOpacnyVrcholKuVrcholu(poslednyVrcholSledu);
            } else {
                koniec = true;
            }
        }

        // nastav naspat defaultne hodnoty
        nastavDefaultneHodnotyPomocnychPremennych();
        return vyslednySled;
    }

    private void nastavDefaultneHodnotyPomocnychPremennych() {
        for (Hrana hrana : graf.getHrany()) {
            hrana.nastavNavstiveniaNaDefaultneHodnoty();
        }
        for (Vrchol vrchol : graf.getVrcholy()) {
            vrchol.setBolNavstiveny(false);
        }
    }

	@Override
	public void spravAlgoritmus() {
		sled = vytvorSledPodlaTarryho(pociatocnyVrchol);
	}
	public List<Hrana> getSled() {
		return sled;
	}

}
