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
	 * Vr�ti tak� vrchol, ktor�ho grafick� reprezent�cia na pl�tne (kruh) obsahuje bod so s�radnicami. Ak tak� vrchol nie je, vr�ti null.
	 * @param paX Xov� s�radnica bodu
	 * @param paY Ynov� s�radnica bodu
	 * @return
	 */
	Vrchol dajVrchol(int paX, int paY);

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
	 * Vr�ti prv� hranu v zozname pre ktor� plat�, �e bod sa nach�dza v tesnej bl�zkosti jej reprezent�cie. Ak tak� nie je , vr�ti null.
	 * @param paX Xov� s�radnica bodu
	 * @param paY Ynov� s�radnica bodu
	 * @return
	 */
	Hrana dajHranu(int paX, int paY);

	/**
	 * Vr�ti hranu (vrchol1,vrchol2) alebo {vrchol1,vrchol1}ak tak�existuje, inak vr�ti null;
	 * @param vrchol1
	 * @param vrchol2
	 * @return
	 */
	Hrana dajHranu(Vrchol vrchol1, Vrchol vrchol2);

	/**
	 * nastav� farbu v�etk�ch komponentov grafu na siv�
	 */
	void odfarbiGraf();

}