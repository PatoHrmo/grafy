package gui;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;
import elementy.Vrchol;

/**
 * Created by Chudjak Kristián on 19.12.2016.
 */
public abstract class IGUIVrchol  extends PPath{

    abstract Vrchol getVrchol();
    abstract void setVrchol(Vrchol vrchol);

    abstract String getNazov();

    abstract void setPossition(float x, float y);

    abstract float getVelkost();


}
