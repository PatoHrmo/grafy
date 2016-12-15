package gui;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PPaintContext;
import elementy.Hrana;
import javafx.scene.transform.Affine;

/**
 * Created by Chudjak Kristián on 20.10.2016.
 */
@SuppressWarnings("serial")
public class GUIHrana extends PPath {
    private GUIVrchol vrchol01;
    private GUIVrchol vrchol02;

    private boolean orientovana;
    private ArrowNode arrow;


    public GUIHrana(GUIVrchol v1, GUIVrchol v2) {
        this.vrchol01 = v1;
        this.vrchol02 = v2;

        BasicStroke bs = new BasicStroke(5);

        this.setStroke(bs);
        orientovana = true;
        arrow = new ArrowNode();
        addChild(arrow);

    }


    public void update() {

        setStrokePaint(Color.RED);
        Point2D start = vrchol01.getFullBoundsReference().getCenter2D();
        Point2D end = vrchol02.getFullBoundsReference().getCenter2D();

        this.reset();
        this.moveTo((float) start.getX(), (float) start.getY());
        this.lineTo((float) end.getX(), (float) end.getY());

        arrow.redrawArrow(start,end,vrchol02.getVelkost()/2);

    }


    @Override
    protected void paint(PPaintContext paintContext) {
        super.paint(paintContext);

    }


    private double getAngle(){
        Point2D start = vrchol01.getFullBoundsReference().getCenter2D();
        Point2D end = vrchol02.getFullBoundsReference().getCenter2D();

        return Math.atan2(end.getY() - start.getY(), end.getX() - start.getX());
    }

    public boolean obsahujeVrchol(GUIVrchol v) {
        return vrchol01 == v || vrchol02 == v;
    }


    public class ArrowNode extends PPath {
        private Point2D[] points = new Point2D[3];
        private static final double ARROW_LENGTH = 30;
        private static final double ARROW_WIDTH = 10;

        public ArrowNode() {
            points[0] = new Point2D.Float(0, 25);
            points[1] = new Point2D.Float(-25, -25);
            points[2] = new Point2D.Float(25, -25);
            this.setPathToPolyline(points);

        }


        private PPath redrawArrow(Point2D start, Point2D end,float velkostVrchola) {

            arrow.setPaint(Color.RED);
            arrow.setStrokePaint(Color.RED);

            double x1 = start.getX();
            double y1 = start.getY();
            double x4 = end.getX();
            double y4 = end.getY();

            Point2D point = makeDistanceFromOrigin(x1 - x4, y1 - y4, velkostVrchola );
            x4 = x4 + point.getX();
            y4 = y4 + point.getY();



            Point2D e = makeDistanceFromOrigin(x1 - x4, y1 - y4, ARROW_LENGTH);
            x1 = x4 + e.getX();
            y1 = y4 + e.getY();

            // arrow tails
            double m = (y4 - y1) / (x4 - x1);
            double at_x1 = x1 + m * ARROW_WIDTH / Math.sqrt(m*m + 1);
            double at_y1 = y1 - ARROW_WIDTH / Math.sqrt(m*m + 1);
            double at_x2 = x1 - m * ARROW_WIDTH / Math.sqrt(m*m + 1);
            double at_y2 = y1 + ARROW_WIDTH / Math.sqrt(m*m + 1);
            arrow.reset();
            arrow.moveTo((float) at_x1, (float) at_y1);
            arrow.lineTo((float) at_x2, (float) at_y2);
            arrow.lineTo((float) x4, (float) y4);
            arrow.lineTo((float) at_x1, (float) at_y1);
            arrow.closePath();

            return arrow;
        }


        protected Point2D makeDistanceFromOrigin(double x, double y, double dist) {
            double denom = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            return new Point2D.Double(
                    dist * x / denom,
                    dist * y / denom
            );
        }



    }
}
