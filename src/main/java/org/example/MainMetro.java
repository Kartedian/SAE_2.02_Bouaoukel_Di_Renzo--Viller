package org.example;

import graphe.*;

public class MainMetro {
    public static void main(String[] args) {
        Graphe gra = LireReseau.lire("./src/main/resources/plan-reseau.txt");
        System.out.println(gra);

        Strategie dijkstra = new Strategie(new Dijkstra());
        Strategie bellman = new Strategie(new Bellman());
        Valeurs res;

        for(int i=0;i<5;i++) {
            String a=gra.listeNoeuds().get((int)(Math.random()*gra.listeNoeuds().size()));
            res = dijkstra.trouverChemins(gra, "A");
            System.out.println(res.toString());
            System.out.println(res.calculerChemin("E")+"\n");

            res = bellman.trouverChemins(gra, "A");
            System.out.println(res.toString());
            System.out.println(res.calculerChemin("E")+"\n");
        }
    }
}
