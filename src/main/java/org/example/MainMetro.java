package org.example;

import graphe.*;

import java.util.List;
import java.util.Scanner ;

/**
 * Classe principale de l'application Metro.
 *
 * Cette classe charge un graphe représentant le réseau de métro à partir d'un fichier texte,
 * puis applique deux algorithmes (Dijkstra et Bellman-Ford) sur plusieurs
 * trajets définis. Elle affiche pour chaque trajet :
 *
 *  -le chemin trouvé par chaque algorithme
 *  -la durée d'exécution de chaque algorithme (en millisecondes)
 *
 *
 * Le graphe est lu à partir du fichier <code>src/main/resources/plan-reseau.txt</code>.
 *
 */
public class MainMetro {

    /**
     * Point d'entrée principal du programme.
     */
    public static void main(String[] args) {
        // Lecture du graphe à partir du fichier réseau
        Graphe gra = LireReseau.lire("src/main/resources/plan-reseau.txt");

        // Déclaration des deux stratégies : Dijkstra et Bellman
        Strategie dijkstra = new Strategie(new Dijkstra());
        Strategie bellman = new Strategie(new Bellman());

        // Tableau des paires de stations à tester
        String[][] tests = {
                {"Blanche", "Assemblée Nationale"},
                {"Charles de Gaulle, Étoile", "Richelieu Drouot"},
                {"Bastille", "Saint-Lazare"},
                {"Gare de Lyon", "Filles du Calvaire"},
                {"Nation", "Louvre, Rivoli"}
        };

        //L'utilisateur choisie si il veut utiliser les méthodes avec pénalité ou non
        Scanner scanner = new Scanner(System.in);
        String reponse;

        while (true) {
            System.out.print("Voulez vous utilisé la version avec pénalité ? répondre par 'oui' ou 'non' : ");
            reponse = scanner.nextLine().trim().toLowerCase();

            if (reponse.equals("oui") || reponse.equals("non")) {
                break;
            }

            System.out.println("Réponse invalide. Essayez encore.");
        }

        if (reponse.equals("non")) {

        System.out.println("Vous avez choisi la version sans pénalité");
        // Boucle sur les trajets à tester
        for (int i = 0; i < tests.length; i++) {
            String depart = tests[i][0];
            String arrivee = tests[i][1];

            System.out.println("=== Test " + (i + 1) + " : " + depart + " -> " + arrivee + " ===");

            System.out.println("Calcul avec Dijkstra :");
            long startDij = System.nanoTime();
            Valeurs resDij = dijkstra.trouverChemins(gra, depart);
            List<String> cheminDij = resDij.calculerChemin(arrivee);
            long endDij = System.nanoTime();

            long durationNanoDij = endDij - startDij;
            double dureeMillisDij = durationNanoDij / 1_000_000.0;

            System.out.println("Chemin emprunté : " + cheminDij);
            System.out.println("Durée de Dijkstra en millisecondes : " + dureeMillisDij + "\n");

            System.out.println("Calcul avec Bellman :");
            long startBell = System.nanoTime();
            Valeurs resBell = bellman.trouverChemins(gra, depart);
            List<String> cheminBell = resBell.calculerChemin(arrivee);
            long endBell = System.nanoTime();

            long durationNanoBell = endBell - startBell;
            double dureeMillisBell = durationNanoBell / 1_000_000.0;

            System.out.println("Chemin emprunté : " + cheminBell);
            System.out.println("Durée de Bellman en millisecondes : " + dureeMillisBell + "\n");
        }} else  {
            System.out.println("Vous avez choisi la version avec pénalité");
            // Boucle sur les trajets à tester
            for (int i = 0; i < tests.length; i++) {
                String depart = tests[i][0];
                String arrivee = tests[i][1];

                System.out.println("=== Test " + (i + 1) + " : " + depart + " -> " + arrivee + " ===");

                System.out.println("Calcul avec Dijkstra :");
                long startDij = System.nanoTime();
                Valeurs resDij = dijkstra.trouverChemins2(gra, depart);
                List<String> cheminDij = resDij.calculerChemin(arrivee);
                long endDij = System.nanoTime();

                long durationNanoDij = endDij - startDij;
                double dureeMillisDij = durationNanoDij / 1_000_000.0;

                System.out.println("Chemin emprunté : " + cheminDij);
                System.out.println("Durée de Dijkstra en millisecondes : " + dureeMillisDij + "\n");

                System.out.println("Calcul avec Bellman :");
                long startBell = System.nanoTime();
                Valeurs resBell = bellman.trouverChemins2(gra, depart);
                List<String> cheminBell = resBell.calculerChemin(arrivee);
                long endBell = System.nanoTime();

                long durationNanoBell = endBell - startBell;
                double dureeMillisBell = durationNanoBell / 1_000_000.0;

                System.out.println("Chemin emprunté : " + cheminBell);
                System.out.println("Durée de Bellman en millisecondes : " + dureeMillisBell + "\n");




            }
}}}
