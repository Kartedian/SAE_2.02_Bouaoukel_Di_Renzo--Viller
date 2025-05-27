package org.example;

import graphe.*;

import java.util.ArrayList;

public class MainComp {

    // Graphe 1
    public static GrapheListe graphe1() {
        GrapheListe gra = new GrapheListe();
        gra.ajouterArc("D", "B", 23);
        gra.ajouterArc("B", "E", 12);
        gra.ajouterArc("B", "C", 10);
        gra.ajouterArc("C", "A", 19);
        gra.ajouterArc("A", "B", 12);
        gra.ajouterArc("E", "D", 43);
        gra.ajouterArc("E", "F", 87);
        return gra;
    }

    // Graphe 2
    public static GrapheListe graphe2() {
        GrapheListe gra = new GrapheListe();
        gra.ajouterArc("A", "B", 5);
        gra.ajouterArc("B", "C", 8);
        gra.ajouterArc("C", "D", 12);
        gra.ajouterArc("D", "E", 7);
        gra.ajouterArc("E", "F", 15);
        gra.ajouterArc("F", "A", 3);
        return gra;
    }

    // Graphe 3
    public static GrapheListe graphe3() {
        GrapheListe gra = new GrapheListe();
        gra.ajouterArc("A", "B", 4);
        gra.ajouterArc("A", "C", 2);
        gra.ajouterArc("A", "D", 7);
        gra.ajouterArc("B", "C", 1);
        gra.ajouterArc("B", "E", 5);
        gra.ajouterArc("C", "D", 3);
        gra.ajouterArc("C", "E", 8);
        gra.ajouterArc("D", "E", 2);
        gra.ajouterArc("D", "F", 6);
        gra.ajouterArc("E", "F", 4);
        gra.ajouterArc("E", "A", 9);
        gra.ajouterArc("F", "B", 1);
        return gra;
    }

    // Graphe 4
    public static GrapheListe graphe4() {
        GrapheListe gra = new GrapheListe();
        gra.ajouterArc("X", "Y", 100);
        gra.ajouterArc("X", "Z", 250);
        gra.ajouterArc("Y", "Z", 75);
        gra.ajouterArc("Y", "W", 180);
        gra.ajouterArc("Z", "W", 120);
        gra.ajouterArc("Z", "V", 90);
        gra.ajouterArc("W", "V", 200);
        gra.ajouterArc("V", "X", 150);
        return gra;
    }

    //Graphe 5
    public static GrapheListe graphe5() {
        GrapheListe gra = new GrapheListe();
        gra.ajouterArc("N1", "N2", 10);
        gra.ajouterArc("N2", "N3", 15);
        gra.ajouterArc("N3", "N4", 8);
        gra.ajouterArc("N4", "N5", 12);
        gra.ajouterArc("N5", "N1", 20);
        gra.ajouterArc("N1", "N3", 18);
        gra.ajouterArc("N2", "N4", 25);
        gra.ajouterArc("N3", "N5", 22);
        return gra;
    }

    public static void main(String[] args) {
        long tot=0;
        int i=0;
        ArrayList<GrapheListe> graphes = new ArrayList<>();
        graphes.add(graphe1());
        graphes.add(graphe2());
        graphes.add(graphe3());
        graphes.add(graphe4());
        graphes.add(graphe5());

        Strategie strategie=new Strategie(new Bellman());
        Valeurs Bellman;
        Valeurs Dijkstra;

        for(GrapheListe gra : graphes){
            i++;
            System.out.println("Graphe :"+i+"\n"+gra+"\nBellman:");

            long startTime=System.currentTimeMillis();
            strategie.setAlgorithme(new Bellman());
            Bellman = strategie.trouverChemins(gra, gra.listeNoeuds().get((int) (Math.abs(Math.random()*gra.listeNoeuds().size()))));
            long endTime=System.currentTimeMillis();
            System.out.println(Bellman +"\nBellman Time : "+(endTime-startTime)+"ms\n\nDijkstra:");
            tot+=(endTime-startTime);

            startTime=System.currentTimeMillis();
            strategie.setAlgorithme(new Dijkstra());
            Dijkstra = strategie.trouverChemins(gra, gra.listeNoeuds().get((int) (Math.abs(Math.random()*gra.listeNoeuds().size()))));
            endTime=System.currentTimeMillis();
            System.out.println(Dijkstra+"\nDijkstra Time : "+(endTime-startTime)+"ms\n\n");
            tot+=(endTime-startTime);
            System.out.println("---------------------------------------");
        }
        tot=tot/(i*2);
        System.out.println("Average Time : "+tot+"ms");
    }
}
