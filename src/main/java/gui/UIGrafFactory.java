package gui;

import elementy.ExplicitnyGraf;
import elementy.Hrana;
import elementy.IGraf;
import elementy.Vrchol;

/**
 * Created by Chudjak Kristián on 15.12.2016.
 */
public class UIGrafFactory {
    public static IGUIVrchol getVrchol(UIVrcholTYP typ,Vrchol vrchol) {
        switch(typ){
            case KLASICKY:
                return new GUIVrchol(vrchol);
            default : return null;
        }
    }
    public static IGUIHrana getHrana(UIHranaTYP typ, IGUIVrchol v1, IGUIVrchol v2, Hrana h2) {
        switch(typ){
            case KLASICKY:
                return new GUIHrana(v1,v2,h2);
            default : return null;
        }
    }

    public enum UIVrcholTYP {
        KLASICKY
    }
    public enum UIHranaTYP {
        KLASICKY
    }

}
