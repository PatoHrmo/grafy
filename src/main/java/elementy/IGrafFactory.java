package elementy;

public class IGrafFactory {
	public static IGraf getGraf(String typ) {
		switch(typ.toLowerCase()){
		case "explicitny": 
			return new ExplicitnyGraf();
		case "dopredny":
			return new DobrednyGraf();
			default : return null;
		}
	}
}
