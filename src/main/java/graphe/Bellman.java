package graphe;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Classe pour résoudre le plus court chemin dans un graphe
 * avec l'algorithme de Bellman-Ford.
 */
public class Bellman implements Algorithme{

    /**
     * Résout le plus court chemin depuis un nœud de départ dans le graphe donné
     * en utilisant l'algorithme de Bellman-Ford.
     *
     * @param g      le graphe sur lequel calculer les chemins
     * @param depart le nœud de départ
     * @return un objet Valeurs contenant les distances minimales et les prédécesseurs
     * @throws IllegalArgumentException si il n'y a pas de nœud de départ dans le graphe
     */
    public Valeurs resoudre(Graphe g, String depart) {
        List<String> noeuds = g.listeNoeuds();
        if (!noeuds.contains(depart)) {
            throw new IllegalArgumentException("Le nœud de départ n'existe pas dans le graphe.");
        }




        Valeurs valeurs = new Valeurs();

        // Initialisation : distance 0 pour depart, Max_Value pour les autres
        for (String n : noeuds) {
            if (n.equals(depart)) {
                valeurs.setValeur(n, 0.0);
            } else {
                valeurs.setValeur(n, Double.MAX_VALUE);
            }
            valeurs.setParent(n, "");
        }

        int taille = noeuds.size();


        for (int i = 1; i <= taille -1; i++) {
            boolean modif = false; // Pour détecter si une amélioration a été faite

            for (String u : noeuds) {
                double distU = valeurs.getValeur(u);
                if (distU == Double.POSITIVE_INFINITY) continue;
                List<Arc> arcs = g.suivants(u);

                for (Arc arc : arcs) {
                    String v = arc.getDest();
                    double cout = arc.getCout();
                    double distV = valeurs.getValeur(v);
                    if (distV > distU + cout) {
                        valeurs.setValeur(v, distU + cout);
                        valeurs.setParent(v, u);
                        modif = true;
                    }
                }
            }
            // Si aucune modification on arrête l'algorithme
            if (!modif) {
                break;
            }
        }



        return valeurs;
    }

    /**
     * Version 2 de Bellman qui ajoute une pénalité lors d'un changement de ligne.
     */
    public Valeurs resoudre2(Graphe g, String depart) {
        List<String> noeuds = g.listeNoeuds();
        if (!noeuds.contains(depart)) {
            throw new IllegalArgumentException("Le nœud de départ n'existe pas dans le graphe.");
        }

        Valeurs valeurs = new Valeurs();
        Map<String, String> ligneParNoeud = new HashMap<>(); // Pour garder la ligne empruntée pour chaque station

        for (String n : noeuds) {
            if (n.equals(depart)) {
                valeurs.setValeur(n, 0.0);
            } else {
                valeurs.setValeur(n, Double.MAX_VALUE);
            }
            valeurs.setParent(n, "");
            ligneParNoeud.put(n, null);
        }

        int taille = noeuds.size();

        for (int i = 1; i <= taille - 1; i++) {
            boolean modif = false;

            for (String u : noeuds) {
                double distU = valeurs.getValeur(u);
                if (distU == Double.POSITIVE_INFINITY) continue;

                String ligneU = ligneParNoeud.get(u);

                for (Arc arc : g.suivants(u)) {
                    String v = arc.getDest();
                    double cout = arc.getCout();
                    String ligneArc = arc.getLigne();

                    //Pénalité de 1000 pour être sur que les changements de ligne sont évité au plus possible
                    double penalite = (ligneU != null && !ligneU.equals(ligneArc)) ? 1000.0 : 0.0;
                    double nouveauCout = distU + cout + penalite;

                    if (valeurs.getValeur(v) > nouveauCout) {
                        valeurs.setValeur(v, nouveauCout);
                        valeurs.setParent(v, u);
                        ligneParNoeud.put(v, ligneArc);
                        modif = true;
                    }
                }
            }

            if (!modif) break;
        }

        return valeurs;
    }
}

