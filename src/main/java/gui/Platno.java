/**
 * Created by Chudjak Kristián on 20.10.2016.
 */
package gui;
import algoritmy.Kostra;
import algoritmy.TarryhoPrehliadka;
import edu.umd.cs.piccolo.*;
import edu.umd.cs.piccolo.event.*;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.*;
import elementy.ExplicitnyGraf;
import elementy.Hrana;
import elementy.IGraf;
import elementy.Vrchol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

@SuppressWarnings("serial")
public class Platno extends PFrame {

    private PLayer vrcholVrstva;
    private PLayer hranyVrstva;
    private PLayer textVrstva;
    private boolean vytvaramHranu = false;
    private boolean vymazVrchol = false;
    private Label label;
    private IGraf graf;

    private GUIVrchol[] vrcholyPreHranu;

    public Platno() {
        setSize(1200, 720);
        vrcholyPreHranu = new GUIVrchol[2];
        getCanvas().setAnimatingRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);
        getCanvas().setInteractingRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);
        getCanvas().setDefaultRenderQuality(PPaintContext.HIGH_QUALITY_RENDERING);

        this.graf = new ExplicitnyGraf();


        vrcholVrstva = getCanvas().getLayer();

        nastavDragHandler(vrcholVrstva);

        // pridaj button
        pridajVrcholButton();

        // vytvor hranu button
        vytvorHranuButton();

        // vymaz vrchol button
        vymazVrcholButton();

        //
        kostraPrehliadkaButton();


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

        label = new Label("Klikni na vrchol pre vymazanie");
        label.setBounds(5, 640, 200, 50);
        add(label);

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
                repaint();
            }


        });

        vrcholVrstva.addInputEventListener(new PBasicInputEventHandler() {
            @Override
            public void mouseClicked(PInputEvent event) {
                super.mouseClicked(event);

                if (vymazVrchol) {
                    GUIVrchol vrchol = (GUIVrchol) event.getPickedNode();
                    vrcholVrstva.removeChild(vrchol);
                    vymazHranyPreVrchol(vrchol);
                    graf.odstranVrchol(vrchol.getVrchol());
                } else if (vytvaramHranu) {

                    PNode vrchol = event.getPickedNode();
                    if (vrchol instanceof GUIVrchol) {
                        if (vrcholyPreHranu[0] == null) vrcholyPreHranu[0] = (GUIVrchol) vrchol;
                        else if (vrcholyPreHranu[1] == null) vrcholyPreHranu[1] = (GUIVrchol) vrchol;

                    }

                    if (vrcholyPreHranu[0] != null && vrcholyPreHranu[1] != null) {
                        vytvaramHranu = false;
                        pridajHranu(vrcholyPreHranu[0], vrcholyPreHranu[1],0);
                        vrcholyPreHranu[0] = null;
                        vrcholyPreHranu[1] = null;
                    }

                }
            }
        });


    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void vymazHranyPreVrchol(GUIVrchol vrchol) {
        ArrayList<GUIHrana> hranyNaVymazanie = new ArrayList<>();
		ArrayList<PNode> list = (ArrayList) hranyVrstva.getChildrenReference();
        for (PNode node : list){
            if(node instanceof GUIHrana){
                GUIHrana hrana = (GUIHrana)node;
                if (hrana.obsahujeVrchol(vrchol)) {
                    hranyNaVymazanie.add(hrana);
                }
            }
        }

        for (GUIHrana hrana: hranyNaVymazanie){
            hranyVrstva.removeChild(hrana);
        }


    }


    private List<Object> kroky = null;
    private int krok = 0;
    private void kostraPrehliadkaButton() {
        Button b = new Button("Kostra");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Kostra kostra = new Kostra(graf,true);
                kroky=kostra.getKroky();
                krok = kroky.size()-1;

                repaint();
                repaintHrany();
            }
        });
        b.setBounds(150, 500, 120, 30);
        add(b);
        b = new Button("<");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(krok > 0){
                    krok--;
                }
                IGraf grafVKroku = (IGraf) kroky.get(krok);
                nastavGraf(grafVKroku);
                System.out.println("max: "+(kroky.size()-1)+"krok: "+krok);
            }
        });
        b.setBounds(300, 500, 50, 30);
        add(b);
        b = new Button(">");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(krok < kroky.size()-1){
                    krok++;
                }
                IGraf grafVKroku = (IGraf) kroky.get(krok);
                nastavGraf(grafVKroku);
                System.out.println("max: "+(kroky.size()-1)+"krok: "+krok);
            }
        });
        b.setBounds(360, 500, 50, 30);
        add(b);


    }

    private void nastavGraf(IGraf grafVKroku) {
        graf = grafVKroku;
        TreeMap<String, Vrchol> vrcholy = new TreeMap<>();
        HashMap<String,Hrana> hrany = new HashMap<>();
        for (Vrchol vrchol :
                graf.getVrcholy()) {
            vrcholy.put(vrchol.getNazov(),vrchol);
        }
        for (Hrana hrana :
                graf.getHrany()) {
            // vlozim hranu, ta ma nazov vrchol1+vrchol2
            hrany.put(hrana.getVrchol1().getNazov()+hrana.getVrchol2().getNazov(),hrana);
        }


        for(Iterator<GUIHrana> iter = hranyVrstva.getChildrenIterator(); iter.hasNext();) {
            GUIHrana guiHrana = iter.next();
            String nazovHrany = guiHrana.getVrchol01().getVrchol().getNazov() +
                                    guiHrana.getVrchol02().getVrchol().getNazov();
            Hrana hrana = hrany.get(nazovHrany);
            if(hrana != null) guiHrana.setHrana(hrana);
        }

        for(Iterator<GUIVrchol> iter = vrcholVrstva.getChildrenIterator(); iter.hasNext();) {
            GUIVrchol guiVrchol = iter.next();
            Vrchol vrchol = vrcholy.get(guiVrchol.getVrchol().getNazov());
            if(vrchol!= null) guiVrchol.setVrchol(vrchol);
        }


        repaint();
        repaintHrany();
    }


    @SuppressWarnings("unchecked")
	private void repaintHrany() {
        for(Iterator<GUIHrana> iter = hranyVrstva.getChildrenIterator(); iter.hasNext();) {
            iter.next().update();
        }
    }




    private int pocVrcholovX = 0;
    private int pocVrcholovY = 0;


    public void pridajVrchol(GUIVrchol vrchol){
        vrchol.setPossition(120*pocVrcholovX,120*pocVrcholovY);
        vrcholVrstva.addChild(vrchol);
        pocVrcholovX++;
        if(pocVrcholovX > 6) {
            pocVrcholovY++;
            pocVrcholovX = 0;
        }
    }

    private void pridajHranu(GUIVrchol v1, GUIVrchol v2,double cena) {
        if(v1.getNazov().equals(v2.getNazov())) return ;
        Hrana hrana = graf.dajHranu(v1.getVrchol(),v2.getVrchol());
        hrana.setCena(cena);
        GUIHrana guiHrana = new GUIHrana(v1, v2,hrana);
        hranyVrstva.addChild(guiHrana);

        repaintHrany();
        /*hrana.update();*/

    }

    private void vymazVrcholButton() {
        Button btnVymazVrchol = new Button("Vymaz Vrchol");
        btnVymazVrchol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vymazVrchol = !vymazVrchol;
                //textVrstva.getChild(0).setVisible(vymazVrchol);
                label.setVisible(vymazVrchol);

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
                String name = JOptionPane.showInputDialog("Zadaj nazov vrchola.");
                if(name == null|| graf.vrcholExistuje(name)) return ;
                Vrchol vrchol = graf.dajVrchol(name);
                GUIVrchol ts = new GUIVrchol(vrchol);
                vrcholVrstva.addChild(ts);
                repaint();
            }
        });
        b.setBounds(5, 500, 120, 30);
        add(b);

    }


    private void nacitajData() {
        try(BufferedReader bfr = new BufferedReader(new FileReader("data.txt"))){
            HashMap<String, GUIVrchol> nacitaneVrcholy = new HashMap<>();

            String line;
            GUIVrchol guiV1;
            GUIVrchol guiV2;
            while((line = bfr.readLine() )!= null){
                String[] data = line.split("\\s+");
                Vrchol v1 = graf.dajVrchol(data[0]);
                Vrchol v2 = graf.dajVrchol(data[1]);
                guiV1 = new GUIVrchol(v1);
                guiV2 = new GUIVrchol(v2);

                if(nacitaneVrcholy.containsKey(guiV1.getNazov())){
                    guiV1 = nacitaneVrcholy.get(guiV1.getNazov());
                }else{
                    nacitaneVrcholy.put(guiV1.getNazov(),guiV1);
                    pridajVrchol(guiV1);
                }
                if(nacitaneVrcholy.containsKey(guiV2.getNazov())){
                    guiV2 = nacitaneVrcholy.get(guiV2.getNazov());
                }else{
                    nacitaneVrcholy.put(guiV2.getNazov(),guiV2);
                    pridajVrchol(guiV2);
                }
                double cena = Double.valueOf(data[2]);
                pridajHranu(guiV1,guiV2,cena);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
