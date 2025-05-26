package org.example;

import graphe.*;

public class MainComp {
    public static void main(String[] args) {
        long tot
        //graphe n1
        GrapheListe gra = new GrapheListe();
        gra.ajouterArc("D", "B", 23);
        gra.ajouterArc("B", "E", 12);
        gra.ajouterArc("B", "C", 10);
        gra.ajouterArc("C", "A", 19);
        gra.ajouterArc("A", "B", 12);
        gra.ajouterArc("E", "D", 43);
        gra.ajouterArc("E", "D", 87);

        System.out.println("Graphe 1:\n"+gra);

        long startTime=System.currentTimeMillis();
        Strategie strategie = new Strategie(new Bellman());
        Valeurs test = strategie.trouverChemins(gra, "D");
        long endTime=System.currentTimeMillis();
        System.out.println("Bellman Time : "+(endTime-startTime)+"ms");

        startTime=System.currentTimeMillis();
        strategie.setAlgorithme(new Dijkstra());
        Valeurs test_2 = strategie.trouverChemins(gra, "D");
        endTime=System.currentTimeMillis();
        System.out.println("Dijkstra Time : "+(endTime-startTime)+"ms");

    }
}
