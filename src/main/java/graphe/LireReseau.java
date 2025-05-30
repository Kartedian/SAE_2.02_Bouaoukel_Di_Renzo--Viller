package graphe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe utilitaire permettant de lire un fichier texte décrivant un réseau de métro
 * et de le transformer en un Graphe. */

public class LireReseau {

    /**
     * Lit un fichier texte contenant la description d’un réseau de métro et retourne
     * un graphe représentant ce réseau.
     *
     * @param fichier le chemin vers le fichier à lire
     * @return un objet GrapheListe représentant le réseau
     */
    public static Graphe lire(String fichier) {
        GrapheListe graphe = new GrapheListe();
        Map<String, String> idVersNom = new HashMap<>(); // Associe ID station à son nom
        boolean lireStations = false;
        boolean lireConnexions = false;

        try{
            FileReader reader = new FileReader(fichier);
            BufferedReader buff = new BufferedReader(reader);

            String line;
            while((line = buff.readLine()) != null){
                // Détection des sections
                if(line.startsWith("%% Stations:")){
                    lireStations = true;
                    lireConnexions = false;
                    line=buff.readLine();
                    continue;
                } else if (line.startsWith("%% Connexions:")) {
                    lireStations = false;
                    lireConnexions = true;
                    line=buff.readLine();
                    continue;
                }
                // Lecture des stations
                if(lireStations){
                    String[] tab = line.split(":");
                    if(tab.length == 5){
                        String id = tab[0];
                        String nom = tab[1];
                        idVersNom.put(id, nom);
                        if (!graphe.listeNoeuds().contains(nom)) {
                            graphe.ajouterArc(nom, nom, 0.0, "none"); // Arc factice
                        }
                    }
                    // Lecture des connexions
                } else if(lireConnexions){
                    String[] parties = line.split(":");
                    if(parties.length == 4){
                        String idDepart = parties[0];
                        String idArrivee = parties[1];
                        double temps = Double.parseDouble(parties[2]);
                        String ligneMetro = parties[3];

                        String nomDepart = idVersNom.get(idDepart);
                        String nomArrivee = idVersNom.get(idArrivee);

                        if (nomDepart != null && nomArrivee != null) {
                            // Ajout bidirectionnel avec la même ligne
                            graphe.ajouterArc(nomDepart, nomArrivee, temps, ligneMetro);
                            graphe.ajouterArc(nomArrivee, nomDepart, temps, ligneMetro);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return graphe;
    }
}