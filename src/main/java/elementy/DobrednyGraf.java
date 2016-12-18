package elementy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DobrednyGraf implements IGraf {
	Map<String, VrcholStruct> vrcholy;
	
	public DobrednyGraf() {
		vrcholy = new TreeMap<>();
	}
	@Override
	public boolean vlozVrchol(Vrchol paVrchol) {
		if(vrcholy.containsKey(paVrchol.getNazov())) 
				return false;
		vrcholy.put(paVrchol.getNazov(), new VrcholStruct(paVrchol));
		return true;
	}

	@Override
	public boolean vlozHranu(Hrana paHrana) {
		VrcholStruct vrcholStruct1 = vrcholy.get(paHrana.getVrchol1().getNazov());
		VrcholStruct vrcholStruct2 = vrcholy.get(paHrana.getVrchol2().getNazov());
		if(vrcholStruct1==null || vrcholStruct2==null)
			return false;
		vrcholStruct1.vlozHranu(vrcholStruct2.getNazov(), paHrana);
		vrcholStruct2.vlozHranu(vrcholStruct1.getNazov(), paHrana);
		return true;
	}

	@Override
	public void odstranVrchol(Vrchol paVrchol) {
		String nazovOdstranenehoVrcholu = paVrchol.getNazov();
		VrcholStruct odstraneny = vrcholy.remove(nazovOdstranenehoVrcholu);
		if(odstraneny==null) 
			return;
		for(String nazovVrcholu : odstraneny.getNazvyIncidentnychVrcholov()) {
			vrcholy.get(nazovVrcholu).odstranHranu(nazovOdstranenehoVrcholu);
		}
		
	}

	@Override
	public List<Vrchol> getVrcholy() {
		List<Vrchol> vrcholy = new LinkedList<>();
		for(VrcholStruct vstruct : this.vrcholy.values()) {
			vrcholy.add(vstruct.getVrchol());
		}
		return vrcholy;
	}

	@Override
	public List<Hrana> getHrany() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void odstranHranu(Hrana hrana) {
		String nazov1Vrcholu = hrana.getVrchol1().getNazov();
		String nazov2Vrcholu = hrana.getVrchol2().getNazov();
		vrcholy.get(nazov1Vrcholu).odstranHranu(nazov2Vrcholu);
		vrcholy.get(nazov2Vrcholu).odstranHranu(nazov1Vrcholu);

	}

	@Override
	public Hrana dajHranu(Vrchol vrchol1, Vrchol vrchol2) {
		VrcholStruct vrcholStruct = vrcholy.get(vrchol1.getNazov());
		if(vrcholStruct==null)
			return null;
		return vrcholStruct.getHranu(vrchol2.getNazov());
	}

	@Override
	public Vrchol dajVrchol(String nazovVrcholu) {
		VrcholStruct vrcholStruct = vrcholy.get(nazovVrcholu);
		if(vrcholStruct==null)
			return null;
		return vrcholStruct.getVrchol();
	}

	@Override
	public boolean vrcholExistuje(String nazovVrcholu) {
		VrcholStruct vrcholStruct = vrcholy.get(nazovVrcholu);
		if(vrcholStruct==null)
			return false;
		return true;
	}

	@Override
	public void odfarbiGraf() {
		// TODO Auto-generated method stub

	}

	@Override
	public IGraf clone() {
		// TODO Auto-generated method stub
		return null;
	}
	// pomocna vnorena trieda
	class VrcholStruct {
		String nazov;
		Vrchol vrchol;
		Map<String, Hrana> hrany;
		
		public VrcholStruct(Vrchol vrchol) {
			this.nazov=vrchol.getNazov();
			this.vrchol = vrchol;
			hrany = new TreeMap<>();
		}
		public boolean vlozHranu(String nazovVrcholu, Hrana hrana) {
			if(hrany.containsKey(nazovVrcholu))
				return false;
			hrany.put(nazovVrcholu, hrana);
			return true;
		}
		public void odstranHranu(String nazovOpacnehoVrcholu) {
			hrany.remove(nazovOpacnehoVrcholu);
		}
		public Hrana getHranu(String opacnyVrchol) {
			return hrany.get(opacnyVrchol);
		}
		public String getNazov() {
			return nazov;
		}

		public Vrchol getVrchol() {
			return vrchol;
		}
		public Set<String> getNazvyIncidentnychVrcholov() {
			return hrany.keySet();
		}
	}
	
}

