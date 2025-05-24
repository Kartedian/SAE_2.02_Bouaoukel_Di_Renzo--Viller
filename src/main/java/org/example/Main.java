package org.example;

import graphe.*;

public class Main {
    public static void main(String[] args) {
        GrapheListe gra = new GrapheListe();
        gra.ajouterArc("D", "B", 23);
        gra.ajouterArc("B", "E", 12);
        gra.ajouterArc("B", "C", 10);
        gra.ajouterArc("C", "A", 19);
        gra.ajouterArc("A", "B", 12);
        gra.ajouterArc("E", "D", 43);
        gra.ajouterArc("E", "D", 87);

        System.out.println(gra.toString());

        Strategie strategie = new Strategie(new Bellman());
        Valeurs test = strategie.trouverChemins(gra, "D");
        System.out.println(test.toString());
        System.out.println(test.calculerChemin("E")+"\n");

        strategie.setAlgorithme(new Dijkstra());
        Valeurs test_2 = strategie.trouverChemins(gra, "D");
        System.out.println(test_2.toString());
        System.out.println(test_2.calculerChemin("E")+"\n");

        GrapheListe gra_file = new GrapheListe("./src/main/resources/test.txt");
        System.out.println(gra_file.toString());
    }
}