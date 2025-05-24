package graphe;

import java.util.ArrayList;
import java.util.List;

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

}