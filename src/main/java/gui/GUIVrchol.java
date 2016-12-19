package gui;
import edu.umd.cs.piccolo.event.*;
import edu.umd.cs.piccolo.nodes.*;
import edu.umd.cs.piccolo.util.*;
import elementy.Vrchol;


import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Chudjak Kristián on 20.10.2016.
 */

@SuppressWarnings("serial")
public class GUIVrchol extends IGUIVrchol {

    private boolean isPressed = false;
    private Color colorPressed = new Color(50,40,50);
    private float velkost = 50;
    private Vrchol vrchol;
    private PText nazovVrcholu;

    public GUIVrchol(Vrchol vrchol) {
        this.vrchol = vrchol;
        nazovVrcholu = new PText(vrchol.getNazov());
        nazovVrcholu.setFont(new Font("Helvetica", Font.PLAIN, 14));
        nazovVrcholu.setPaint(Color.CYAN);

        setPathToEllipse(0, 0, velkost, velkost);
        addInputEventListener(new PBasicInputEventHandler() {
            public void mousePressed(PInputEvent event) {
                super.mousePressed(event);
                isPressed = true;
                repaint();
            }
            public void mouseReleased(PInputEvent event) {
                super.mouseReleased(event);
                isPressed = false;
                repaint();
            }

        });
    }

    public void setPossition( float x,float y){
        setPathToEllipse(x, y, velkost, velkost);
        nazovVrcholu.setX(x);
        nazovVrcholu.setY(y);
    }

    protected void paint(PPaintContext paintContext) {
        if (isPressed) {
            super.paint(paintContext);
            nazovVrcholu.fullPaint(paintContext);
            setPaint(colorPressed);
        } else {
            super.paint(paintContext);
            nazovVrcholu.fullPaint(paintContext);
            setPaint(vrchol.getFarba());
        }
    }

    public String getNazov(){
        return vrchol.getNazov();
    }


    public Vrchol getVrchol() {
        return vrchol;
    }

    public float getVelkost() {
        return velkost;
    }

    public void setVrchol(Vrchol vrchol) {
        this.vrchol = vrchol;
    }
}