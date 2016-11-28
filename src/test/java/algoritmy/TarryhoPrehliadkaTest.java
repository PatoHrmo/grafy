package algoritmy;

import elementy.Graf;
import elementy.Hrana;
import elementy.Vrchol;

public class TarryhoPrehliadkaTest {

	public static void main(String[] args) {
		Vrchol a = new Vrchol(1,1,'a');
		Vrchol b = new Vrchol(1,1,'b');
		Vrchol c = new Vrchol(1,1,'c');
		Vrchol d = new Vrchol(1,1,'d');
		Vrchol e = new Vrchol(1,1,'e');
		
		Hrana ad = new Hrana(a,d,0,false);
		Hrana ac = new Hrana(a,c,0,false);
		Hrana be = new Hrana(b,e,0,false);
		Hrana bd = new Hrana(b,d,0,false);
		Hrana ce = new Hrana(c,e,0,false);
		Hrana de = new Hrana(d,e,0,false);
		
		Graf graf = new Graf();
		
		graf.vlozHranu(ad);
		graf.vlozHranu(ac);
		graf.vlozHranu(be);
		graf.vlozHranu(bd);
		graf.vlozHranu(ce);
		graf.vlozHranu(de);
		
		graf.vlozVrchol(a);
		graf.vlozVrchol(b);
		graf.vlozVrchol(c);
		graf.vlozVrchol(d);
		graf.vlozVrchol(e);
		
		TarryhoPrehliadka prehliadka = new TarryhoPrehliadka(graf);
		for(Hrana hrana : prehliadka.vytvorSledPodlaTarryho(a)) {
			System.out.println(hrana.toString());
		}

	}

}
