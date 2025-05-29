package org.example;

import graphe.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GrapheListe gra = new GrapheListe();
        gra.ajouterArc("A", "B", 12);
        gra.ajouterArc("A", "D", 87);

        gra.ajouterArc("B", "E", 11);
        gra.ajouterArc("C", "A", 19);
        gra.ajouterArc("D", "C", 10);
        gra.ajouterArc("D", "B", 23);
        gra.ajouterArc("E", "D", 43);


        System.out.println(gra.toString());

        Strategie strategie = new Strategie(new Bellman());
        Valeurs test = strategie.trouverChemins(gra, "E");
        System.out.println(test.toString());


        Valeurs.ToStringChemin(test.calculerChemin("C"));




        strategie.setAlgorithme(new Dijkstra());
        Valeurs test_2 = strategie.trouverChemins(gra, "E");
        System.out.println(test_2.toString());
        System.out.println(test_2.calculerChemin("C")+"\n");

        GrapheListe gra_file = new GrapheListe("./src/main/resources/test.txt");
        System.out.println(gra_file.toString());
    }
}