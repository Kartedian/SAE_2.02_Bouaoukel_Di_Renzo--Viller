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
    }
}