package elementy;

import java.util.List;

public interface IGraf {

	/**
	 * Vlo�� vrchol do grafu.
	 * @param paVrchol vrchol, ktor� sa vlo�� do grafu.
	 * @return
	 */
	boolean vlozVrchol(Vrchol paVrchol);

	/**
	 * Vlo�� hranu do grafu.
	 * @param paHrana hrana, ktor� sa vlo�� do grafu
	 * @return
	 */
	boolean vlozHranu(Hrana paHrana);

	

	/**
	 * Odstr�ni vrchol a v�etky hrany s n�m incidentn�.
	 * @param paVrchol vrchol na odstr�nenie.
	 */
	void odstranVrchol(Vrchol paVrchol);

	/**
	 * Vr�ti indexovan� zoznam vrcholov.
	 * @return
	 */
	List<Vrchol> getVrcholy();

	/**
	 * Vr�ti indexovan� zoznam hr�n.
	 * @return
	 */
	List<Hrana> getHrany();

	/**
	 * Odstr�ni hranu zgrafu.
	 * @param hrana Hrana na odstr�nenie.
	 */
	void odstranHranu(Hrana hrana);
	/**
	 * Vr�ti hranu (vrchol1,vrchol2) alebo {vrchol1,vrchol1}ak tak�existuje, inak vr�ti null;
	 * @param vrchol1
	 * @param vrchol2
	 * @return
	 */
	Hrana dajHranu(Vrchol vrchol1, Vrchol vrchol2);

	Vrchol dajVrchol(String nazovVrcholu);
	boolean vrcholExistuje(String nazovVrcholu);

	/**
	 * nastav� farbu v�etk�ch komponentov grafu na siv�
	 */
	void odfarbiGraf();
	
	IGraf clone();

}