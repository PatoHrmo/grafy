/**
 * Created by Chudjak Kristián on 20.10.2016.
 */
package gui;
import edu.umd.cs.piccolo.*;
import edu.umd.cs.piccolo.event.*;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolox.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("serial")
public class Platno extends PFrame {

    private PLayer vrcholVrstva;
    private PLayer hranyVrstva;
    private PLayer textVrstva;
    private boolean vytvaramHranu = false;
    private boolean vymazVrchol = false;

    private Vrchol[] vrcholyPreHranu;

    public Platno() {
        setSize(1200, 720);
        vrcholyPreHranu = new Vrchol[2];

        vrcholVrstva = getCanvas().getLayer();

        nastavDragHandler(vrcholVrstva);

        // pridaj button
        pridajVrcholButton();

        // vytvor hranu button
        vytvorHranuButton();

        // vymaz vrchol button
        vymazHranuButton();


    }



    public void initialize() {
        //Add Piccolo2D code here.
        vrcholVrstva = getCanvas().getLayer();
        hranyVrstva = new PLayer();
        textVrstva = new PLayer();
        // vlozim vrstvu s hranami naspod
        getCanvas().getCamera().addLayer(0, hranyVrstva);
        getCanvas().getCamera().addLayer(2, textVrstva);

        getCanvas().setBackground(new Color(176, 190, 192));
        getCanvas().setSize(1280, 720);
        getCanvas().setPanEventHandler(null);
//        getCanvas().addInputEventListener(new PDragEventHandler());


        PNode text = new PText("Klikni na vrchol pre vymazanie");
        text.setBounds(5, 640, 50, 50);
        textVrstva.addChild(text);

        nacitajData();
    }



    public static void main(String[] args) {
        new Platno();
    }

    private void nastavDragHandler(PLayer vrcholVrstva) {
        vrcholVrstva.addInputEventListener(new PDragEventHandler() {
            protected void drag(PInputEvent e) {
                super.drag(e);
                repaintHrany();
            }


        });

        vrcholVrstva.addInputEventListener(new PBasicInputEventHandler() {
            @Override
            public void mouseClicked(PInputEvent event) {
                super.mouseClicked(event);

                if (vymazVrchol) {
                    Vrchol vrchol = (Vrchol) event.getPickedNode();
                    vrcholVrstva.removeChild(vrchol);
                    vymazHranyPreVrchol(vrchol);
                } else if (vytvaramHranu) {

                    PNode vrchol = event.getPickedNode();
                    if (vrchol instanceof Vrchol) {
                        if (vrcholyPreHranu[0] == null) vrcholyPreHranu[0] = (Vrchol) vrchol;
                        else if (vrcholyPreHranu[1] == null) vrcholyPreHranu[1] = (Vrchol) vrchol;

                    }

                    if (vrcholyPreHranu[0] != null && vrcholyPreHranu[1] != null) {
                        vytvaramHranu = false;
                        pridajHranu(vrcholyPreHranu[0], vrcholyPreHranu[1]);
                        vrcholyPreHranu[0] = null;
                        vrcholyPreHranu[1] = null;
                    }

                }
            }
        });


    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void vymazHranyPreVrchol(Vrchol vrchol) {
        ArrayList<Hrana> hranyNaVymazanie = new ArrayList<>();
		ArrayList<PNode> list = (ArrayList) hranyVrstva.getChildrenReference();
        for (PNode node : list){
            if(node instanceof Hrana){
                Hrana hrana = (Hrana)node;
                if (hrana.obsahujeVrchol(vrchol)) {
                    hranyNaVymazanie.add(hrana);
                }
            }
        }

        for (Hrana hrana: hranyNaVymazanie){
            hranyVrstva.removeChild(hrana);
        }


    }

    @SuppressWarnings("unchecked")
	private void repaintHrany() {
        for (Iterator<Hrana> iter = hranyVrstva.getChildrenIterator(); iter.hasNext(); ) {
            iter.next().paint();
        }
    }

    private int pocVrcholovX = 0;
    private int pocVrcholovY = 0;


    public void pridajVrchol(Vrchol vrchol){
        vrchol.setPossition(120*pocVrcholovX,120*pocVrcholovY);
        vrcholVrstva.addChild(vrchol);
        pocVrcholovX++;
        if(pocVrcholovX > 6) {
            pocVrcholovY++;
            pocVrcholovX = 0;
        }
    }

    private void pridajHranu(Vrchol v1, Vrchol v2) {
        Hrana hrana = new Hrana(v1, v2);
        hranyVrstva.addChild(hrana);
        hrana.paint();

    }

    private void vymazHranuButton() {
        Button btnVymazVrchol = new Button("Vymaz Vrchol");
        btnVymazVrchol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vymazVrchol = !vymazVrchol;
                textVrstva.getChild(0).setVisible(vymazVrchol);

            }
        });
        btnVymazVrchol.setBounds(5, 600, 120, 30);
        add(btnVymazVrchol);
    }

    private void vytvorHranuButton() {
        Button btnVytvorHranu = new Button("Pridaj Hranu");
        btnVytvorHranu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vytvaramHranu = true;
            }
        });
        btnVytvorHranu.setBounds(5, 550, 120, 30);
        add(btnVytvorHranu);
    }

    private void pridajVrcholButton() {
        Button b = new Button("Pridaj Vrchol");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vrchol ts = new Vrchol(100);
                vrcholVrstva.addChild(ts);
            }
        });
        b.setBounds(5, 500, 120, 30);
        add(b);

    }


    private void nacitajData() {
        try(BufferedReader bfr = new BufferedReader(new FileReader("data.txt"))){
            HashMap<Integer,Vrchol> nacitaneVrcholy = new HashMap<>();

            String line;
            Vrchol v1;
            Vrchol v2;
            while((line = bfr.readLine() )!= null){
                String[] data = line.split("\\s+");
                v1 = new Vrchol(Integer.valueOf(data[0]));
                v2 = new Vrchol(Integer.valueOf(data[1]));
                if(nacitaneVrcholy.containsKey(v1.getId())){
                    v1 = nacitaneVrcholy.get(v1.getId());
                }else{
                    nacitaneVrcholy.put(v1.getId(),v1);
                    pridajVrchol(v1);
                }
                if(nacitaneVrcholy.containsKey(v2.getId())){
                    v2 = nacitaneVrcholy.get(v2.getId());
                }else{
                    nacitaneVrcholy.put(v2.getId(),v2);
                    pridajVrchol(v2);
                }


                pridajHranu(v1,v2);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
