package graphe;

/**
 * Interface définissant le contrat pour les algorithmes de recherche de plus court chemin.
 * Cette interface fait partie du patron de conception Stratégie, permettant
 * l'interchangeabilité des différents algorithmes de recherche (comme Dijkstra et Bellman-Ford).
 */
public interface Algorithme {

    /**
     * Résout le problème du plus court chemin dans un graphe à partir d'un nœud de départ.
     *
     * @param g le graphe sur lequel appliquer l'algorithme
     * @param depart le nœud de départ pour la recherche des chemins
     * @return un objet Valeurs contenant les distances minimales et les prédécesseurs pour chaque nœud
     * @throws IllegalArgumentException si le nœud de départ n'existe pas dans le graphe
     */
    Valeurs resoudre(Graphe g, String depart);
}