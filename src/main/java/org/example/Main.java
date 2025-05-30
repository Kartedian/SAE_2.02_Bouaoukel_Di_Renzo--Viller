package org.example;

import graphe.*;

import java.util.List;
/**
 * La classe {@code Main} contient le point d'entrée principal de l'application.
 * Elle permet de tester les algorithmes de plus court chemin (Bellman-Ford et Dijkstra)
 * sur un graphe construit en mémoire.
 */
public class Main {

/**
 * Point d'entrée de l'application.
 * Crée un graphe, y ajoute des arcs, et applique les algorithmes de Bellman-Ford
 * et de Dijkstra pour calculer les plus courts chemins à partir du sommet "C".
 */
    public static void main(String[] args) {
        // Création d'un graphe et ajout d'arcs
        GrapheListe gra = new GrapheListe();
        gra.ajouterArc("A", "B", 12);
        gra.ajouterArc("A", "D", 87);

        gra.ajouterArc("B", "E", 11);
        gra.ajouterArc("C", "A", 19);
        gra.ajouterArc("D", "C", 10);
        gra.ajouterArc("D", "B", 23);
        gra.ajouterArc("E", "D", 43);


        System.out.println(gra.toString());

        // Application de Bellman-Ford
        Strategie strategie = new Strategie(new Bellman());
        Valeurs test = strategie.trouverChemins(gra, "C");
        System.out.println(test.toString());
        System.out.println(test.calculerChemin("D")+"\n");

        // Affichage du chemin calculé jusqu'à D
        Valeurs.ToStringChemin(test.calculerChemin("D"));


        // Application de Dijkstra
        strategie.setAlgorithme(new Dijkstra());
        Valeurs test_2 = strategie.trouverChemins(gra, "C");
        System.out.println(test_2.toString());
        System.out.println(test_2.calculerChemin("D")+"\n");


    }
}