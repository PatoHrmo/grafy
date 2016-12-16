package elementy;

import java.util.List;

public interface IGraf {

	/**
	 * Vloží vrchol do grafu.
	 * @param paVrchol vrchol, ktorý sa vloží do grafu.
	 * @return
	 */
	boolean vlozVrchol(Vrchol paVrchol);

	/**
	 * Vloží hranu do grafu.
	 * @param paHrana hrana, ktorá sa vloží do grafu
	 * @return
	 */
	boolean vlozHranu(Hrana paHrana);

	

	/**
	 * Odstráni vrchol a všetky hrany s ním incidentné.
	 * @param paVrchol vrchol na odstránenie.
	 */
	void odstranVrchol(Vrchol paVrchol);

	/**
	 * Vráti indexovaný zoznam vrcholov.
	 * @return
	 */
	List<Vrchol> getVrcholy();

	/**
	 * Vráti indexovaný zoznam hrán.
	 * @return
	 */
	List<Hrana> getHrany();

	/**
	 * Odstráni hranu zgrafu.
	 * @param hrana Hrana na odstránenie.
	 */
	void odstranHranu(Hrana hrana);
	/**
	 * Vráti hranu (vrchol1,vrchol2) alebo {vrchol1,vrchol1}ak takáexistuje, inak vráti null;
	 * @param vrchol1
	 * @param vrchol2
	 * @return
	 */
	Hrana dajHranu(Vrchol vrchol1, Vrchol vrchol2);

	Vrchol dajVrchol(String nazovVrcholu);
	boolean vrcholExistuje(String nazovVrcholu);

	/**
	 * nastaví farbu všetkých komponentov grafu na sivú
	 */
	void odfarbiGraf();
	
	IGraf clone();

}