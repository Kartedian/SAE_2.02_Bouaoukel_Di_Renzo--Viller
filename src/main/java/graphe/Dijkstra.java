package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe pour résoudre le plus court chemin dans un graphe
 * avec l'algorithme de Bellman-Ford.
 */
public class Dijkstra implements Algorithme {

    /**
     * Résout le plus court chemin depuis un nœud de départ dans le graphe donné
     * en utilisant l'algorithme de Dijsktra.
     *
     * @param g      le graphe sur lequel calculer les chemins
     * @param depart le nœud de départ
     * @return un objet Valeurs contenant les distances minimales et les prédécesseurs
     * @throws IllegalArgumentException si il n'y a pas de nœud de départ dans le graphe
     */
    public Valeurs resoudre(Graphe g, String depart){

        Valeurs valeurs = new Valeurs();
        List<String> Q = new ArrayList<>();
        if (!g.listeNoeuds().contains(depart)) {
            throw new IllegalArgumentException("Le nœud de départ n'existe pas dans le graphe.");
        }

        // Initialisation : distance 0 pour depart, Max_Value pour les autres
        for (String v : g.listeNoeuds()) {
            valeurs.setValeur(v, Double.POSITIVE_INFINITY);
            valeurs.setParent(v, "");
            Q.add(v);
        }

        valeurs.setValeur(depart, 0.0);

        while (!Q.isEmpty()) {
            String u = trouveMin(Q, valeurs);
            Q.remove(u);

            List<Arc> arc = g.suivants(u);
            if (arc != null) {
                for (Arc arco : arc) {
                    String v = arco.getDest();
                    if (Q.contains(v)) {
                        double d = valeurs.getValeur(u) + arco.getCout();
                        if (d < valeurs.getValeur(v)) {

                            valeurs.setValeur(v, d);
                            valeurs.setParent(v, u);
                        }
                    }
                }
            }
        }

        return valeurs;
    }

    /**
     * Trouve le nœud avec la valeur minimale parmi les nœuds non visités.
     * Cette méthode est utilisée dans l'algorithme de Dijkstra pour sélectionner
     * le prochain nœud à traiter ayant la plus petite distance depuis le nœud de départ.
     *
     * @param Q la liste des nœuds non encore visités
     * @param valeurs l'objet contenant les valeurs (distances) associées à chaque nœud
     * @return le nom du nœud ayant la plus petite valeur, ou null si la liste est vide
     */

    private static String trouveMin(List<String> Q, Valeurs valeurs) {
        String minNodo = null;
        double minValore = Double.POSITIVE_INFINITY;

        for (String nodo : Q) {
            double valore = valeurs.getValeur(nodo);
            if (valore < minValore) {
                minValore = valore;
                minNodo = nodo;
            }
        }

        return minNodo;
    }

    /**
     * Version 2 de Dijkstra qui ajoute une pénalité lors d'un changement de ligne.
     */
    public Valeurs resoudre2(Graphe g, String depart) {
        Valeurs valeurs = new Valeurs();
        List<String> Q = new ArrayList<>();
        Map<String, String> ligneParente = new HashMap<>();

        if (!g.listeNoeuds().contains(depart)) {
            throw new IllegalArgumentException("Le nœud de départ n'existe pas dans le graphe.");
        }

        for (String v : g.listeNoeuds()) {
            valeurs.setValeur(v, Double.POSITIVE_INFINITY);
            valeurs.setParent(v, "");
            Q.add(v);
        }

        valeurs.setValeur(depart, 0.0);
        ligneParente.put(depart, null);

        while (!Q.isEmpty()) {
            String u = trouveMin(Q, valeurs);
            Q.remove(u);

            for (Arc arc : g.suivants(u)) {
                String v = arc.getDest();
                if (!Q.contains(v)) continue;


                double distU = valeurs.getValeur(u);
                String ligneU = ligneParente.get(u);
                String ligneArc = arc.getLigne();
                double cout = arc.getCout();

                //Pénalité de 1000 pour être sur que les changements de ligne sont évité au plus possible
                double penalite = (ligneU != null && !ligneU.equals(ligneArc)) ? 1000.0 : 0.0;
                double nouveauCout = distU + cout + penalite;

                if (nouveauCout < valeurs.getValeur(v)) {
                    valeurs.setValeur(v, nouveauCout);
                    valeurs.setParent(v, u);
                    ligneParente.put(v, ligneArc);
                }
            }
        }

        return valeurs;
    }




}