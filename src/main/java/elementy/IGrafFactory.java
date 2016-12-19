package elementy;

public class IGrafFactory {
	public static IGraf getGraf(TYP typ) {
		switch(typ){
			case EXPLICITNY:
			return new ExplicitnyGraf();
			case DOPREDNY:
			return new DobrednyGraf();
			default : return null;
		}
	}

	public enum TYP{
		EXPLICITNY,DOPREDNY
	}
}
