package algoritmy;

import elementy.IGraf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

import java.awt.Color;
import javax.swing.JTextPane;

/**
 * Floydov algoritmus
 * @author Erik
 */
public class Floydov extends Algoritmus {

    /**
     * vytvorenie inštancie algoritmu
     * @param graf graf nad ktorým sa Floydov algoritmus vykoná
     * 
     */
    public Floydov(IGraf graf) {
        super(graf);
    }

    /**
     * Samotný Floydov algoritmus. Pri každom kroku volá metódu vypis matice a pošle jejinformácie pre zobrazenie stavu po vykonaní tohto kroku. Nakonci sa zavolá metóda pre zobrazenie cesty.
     */
    @Override
    public void spravAlgoritmus() {
        int pocetVrcholov = graf.getVrcholy().size();
        
        //zostrojime maticu c
        double[][]c= new double[pocetVrcholov][pocetVrcholov];

        for(int i = 0; i<pocetVrcholov; i++){
            for(int j=0;j<pocetVrcholov;j++){
                if(i==j){
                    c[i][j]=0;
                }else if(graf.dajHranu(graf.getVrcholy().get(i), graf.getVrcholy().get(j))!=null){
                    c[i][j]=graf.dajHranu(graf.getVrcholy().get(i), graf.getVrcholy().get(j)).getCena();
                }else{
                    c[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        
        //zostrojime maticu x
        int[][] x = new int[pocetVrcholov][pocetVrcholov];
        for(int i = 0; i<pocetVrcholov; i++){
            for(int j=0;j<pocetVrcholov;j++){
                if(i==j){
                    x[i][j]=i;
                }else if(graf.dajHranu(graf.getVrcholy().get(i), graf.getVrcholy().get(j))!=null){
                    x[i][j]=i;
                }else{
                    x[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        
        //vypisMatice(c, x, -1, -1, -1);

        
        for(int k=0; k<pocetVrcholov;k++){
            //vypisMatice(c, x, k, -1, -1);

            for(int i = 0; i<pocetVrcholov; i++){
                for(int j=0;j<pocetVrcholov;j++){
                    if(i!=k && c[i][k]!=Integer.MAX_VALUE && j!=k && c[k][j]!=Integer.MAX_VALUE){
                        if(c[i][j] > c[i][k] + c[k][j]){
                            //vypisMatice(c, x, k, i, j);
                            c[i][j] = c[i][k] + c[k][j];
                            x[i][j] = x[k][j];
                        }
                    }
                    this.krokyAlgoritmu.add(c);
                }
            }
        }
        //vypisMatice(c, x, -1, -1, -1);
    }
}
