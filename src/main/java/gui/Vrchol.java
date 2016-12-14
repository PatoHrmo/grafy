package gui;
import edu.umd.cs.piccolo.event.*;
import edu.umd.cs.piccolo.nodes.*;
import edu.umd.cs.piccolo.util.*;


import java.awt.*;

/**
 * Created by Chudjak Kristián on 20.10.2016.
 */

public class Vrchol extends PPath {

    private boolean isPressed = false;
    private Color color = new Color(60,39,53);
    private Color colorPressed = new Color(50,40,50);
    private int id;


    public Vrchol(int id) {
        setPathToEllipse(0, 0, 50, 50);
        this.id = id;

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
        setPathToEllipse(x, y, 50, 50);
    }

    protected void paint(PPaintContext paintContext) {
        if (isPressed) {
            super.paint(paintContext);
            setPaint(colorPressed);

        } else {
            super.paint(paintContext);
            setPaint(color);
        }
    }

    public int getId() {
        return id;
    }
}