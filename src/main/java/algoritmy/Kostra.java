package algoritmy;

import java.awt.Color;
import java.util.Collections;
import java.util.Random;

import elementy.Vrchol;
import elementy.Hrana;
import elementy.IGraf;
/**
 * Algoritmus pre hľadanie najdrahšej kostry
 *
 * @author Erik
 */
public class Kostra extends Algoritmus {

    /**
     * vytvorenie inštancie algoritmu
     *
     * @param graf graf nad ktorým sa Floydov algoritmus vykoná
     *
     */
    private boolean najlacnejsia;

    public Kostra(IGraf graf, boolean najlacnejsia) {
        super(graf);
        this.najlacnejsia = najlacnejsia;
    }

    /**
     * zoradí hrany v zozname hrán v grafe od najdrahšej po najlacnejšiu a
     * spustí algoritmus.
     */
    @Override
    public void spravAlgoritmus() {
        //zoradime hrany od najvacsej po najmensiu
        bubblesort(najlacnejsia);
        hladajKostru();
    }

    /**
     * algoritmus nájde a zafarbí najdrahšiu kostru grafu. V priebehu algortmu
     * sa každým krokom v bočnom paneli zobrazí aktuálne riešená hrana a či sa
     * hrana zaradí do kostry.
     */
    private void hladajKostru() {
        //inicializacia znaciek
        @SuppressWarnings("unused")
		int cenaKostry = 0;
        int znacka = 1;
        for (Vrchol vrchol : graf.getVrcholy()) {
            vrchol.setZnacka(znacka);
            vrchol.setFarba(generujFarbu(znacka));
            znacka++;
        }

        for (Hrana hrana : graf.getHrany()) {
            if (hrana.getVrchol1().getZnacka() == hrana.getVrchol2().getZnacka()) {
            } else {
                cenaKostry += hrana.getCena();
                double vacsiaZnacka = Math.max(hrana.getVrchol1().getZnacka(), hrana.getVrchol2().getZnacka());
                double mensiaZnacka = Math.min(hrana.getVrchol1().getZnacka(), hrana.getVrchol2().getZnacka());

                for (Vrchol vrchol : graf.getVrcholy()) {
                    if (vrchol.getZnacka() == vacsiaZnacka) {
                        vrchol.setZnacka(mensiaZnacka);
                        vrchol.setFarba(generujFarbu((int) mensiaZnacka));
                    }
                }
                hrana.setFarba(Color.red);
                hrana.getVrchol1().setFarba(generujFarbu((int) mensiaZnacka));
                hrana.getVrchol2().setFarba(generujFarbu((int) mensiaZnacka));
            }
        }
    }

    /**
     * zoradí hrany zostupne
     */
    private void bubblesort(boolean vzostupne) {
        while (true) {
            boolean zmena = false;
            for (int i = 0; i < graf.getHrany().size() - 1; i++) {
                if (graf.getHrany().get(i).getCena() > graf.getHrany().get(i + 1).getCena() && vzostupne) {
                    Collections.swap(graf.getHrany(), i, i + 1);
                    zmena = true;
                } else if (graf.getHrany().get(i).getCena() < graf.getHrany().get(i + 1).getCena() && !vzostupne) {
                    Collections.swap(graf.getHrany(), i, i + 1);
                    zmena = true;
                }
            }
            if (!zmena) {
                return;
            }
        }
    }

    /**
     * vygeneruje farbu vzhľadom na značku vrcholu
     *
     * @param paSeed Značka vrholu, pre vrcholy s rovnakou značkou vygeneruje
     * rovnakú farbu
     */
    private Color generujFarbu(int paSeed) {
        Random generator = new Random(paSeed);
        int r = generator.nextInt(255);
        int g = generator.nextInt(255);
        int b = generator.nextInt(255);
        return new Color(r, g, b);
    }
}
