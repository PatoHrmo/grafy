package gui;


import java.awt.*;
import java.awt.geom.Point2D;

//import atdixon.java2d.example.*;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolo.util.PPaintContext;
import elementy.Hrana;

/**
 * Created by Chudjak Kristián on 20.10.2016.
 */
@SuppressWarnings("serial")
public class GUIHrana extends IGUIHrana {
    private IGUIVrchol vrchol01;
    private IGUIVrchol vrchol02;
    private Hrana hrana;
    private PText cenaHrany;

    private boolean orientovana;
    private ArrowNode arrow;

    public GUIHrana(IGUIVrchol v1, IGUIVrchol v2, Hrana hrana) {
        this.vrchol01 = v1;
        this.vrchol02 = v2;
        this.hrana = hrana;
        this.cenaHrany = new PText(String.valueOf(hrana.getCena()));
        cenaHrany.setFont(new Font("Helvetica", Font.PLAIN, 14));
        cenaHrany.setPaint(Color.RED);

        BasicStroke bs = new BasicStroke(5);

        this.setStroke(bs);
        orientovana = false;
        arrow = new ArrowNode();
        addChild(arrow);
        addChild(1,cenaHrany);


    }


    public void update() {

        setStrokePaint(hrana.getFarba());
        Point2D start = vrchol01.getFullBoundsReference().getCenter2D();
        Point2D end = vrchol02.getFullBoundsReference().getCenter2D();


        this.reset();
        this.moveTo((float) start.getX(), (float) start.getY());
        this.lineTo((float) end.getX(), (float) end.getY());
        setTextPath(start,end);


        if(orientovana) {
            arrow.redrawArrow(start, end, vrchol02.getVelkost() / 2);
        }
        getChild(0).setVisible(orientovana);


    }

    private void setTextPath(Point2D start, Point2D end) {
        double x = (start.getX() + end.getX() )/2;
        double y = (start.getY() + end.getY())/2;
        this.cenaHrany.setX(x);
        this.cenaHrany.setY(y);

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

    public boolean obsahujeVrchol(IGUIVrchol v) {
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

            arrow.setPaint(hrana.getFarba());
            arrow.setStrokePaint(hrana.getFarba());

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

    public IGUIVrchol getVrchol01() {
        return vrchol01;
    }

    public void setVrchol01(GUIVrchol vrchol01) {
        this.vrchol01 = vrchol01;
    }

    public IGUIVrchol getVrchol02() {
        return vrchol02;
    }

    public void setVrchol02(GUIVrchol vrchol02) {
        this.vrchol02 = vrchol02;
    }

    public void setHrana(Hrana hrana) {
        this.hrana = hrana;
    }
}
