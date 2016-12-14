package gui;


import java.awt.*;
import java.awt.geom.Point2D;
import edu.umd.cs.piccolo.nodes.PPath;
/**
 * Created by Chudjak Kristián on 20.10.2016.
 */
@SuppressWarnings("serial")
public class Hrana extends PPath{
    private Vrchol vrchol01;
    private Vrchol vrchol02;

    public Hrana(Vrchol v1, Vrchol v2){
        this.vrchol01 = v1;
        this.vrchol02 = v2;

        this.setStroke(new BasicStroke(5));

    }

    public void paint(){

        Point2D start = vrchol01.getFullBoundsReference().getCenter2D();
        Point2D end = vrchol02.getFullBoundsReference().getCenter2D();

        this.reset();
        this.moveTo((float)start.getX(), (float)start.getY());
        this.lineTo((float)end.getX(), (float)end.getY());

    }

    public boolean obsahujeVrchol(Vrchol v){
        return vrchol01 == v || vrchol02 == v;
    }

}
