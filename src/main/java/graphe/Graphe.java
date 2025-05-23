package graphe;

import java.util.List;

/**
 * L’interface Graphe proposée est fondée sur le TAD graphe de la Section 2.1
 */
public interface Graphe {

    /**
     * public List<String> listeNoeuds() qui retourne tous les nœuds du graphe (sous la
     * forme d’une liste de String)
     *
     * @return
     */
    public List<String> listeNoeuds();

    /**
     * public List<Arc> suivants(String n) qui retourne la liste des arcs partant du nœud n
     * passé en paramètre
     *
     * @param n
     * @return
     */
    public List<Arc> suivants(String n);
}
