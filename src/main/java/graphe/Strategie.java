package graphe;

/**
 * Classe qui implémente le patron de conception Stratégie pour la recherche de plus courts chemins.
 * Elle permet de changer dynamiquement l'algorithme utilisé (Dijkstra, Bellman-Ford, etc.)
 * tout en maintenant une interface cohérente pour le client.
 */
public class Strategie{
    /** L'algorithme actuellement utilisé pour la recherche de chemins */
    private Algorithme algorithme;

    /**
     * Constructeur qui initialise la stratégie avec un algorithme spécifique.
     *
     * @param algorithme l'algorithme à utiliser initialement
     */
    public Strategie(Algorithme algorithme) {
        this.algorithme = algorithme;
    }

    /**
     * Change l'algorithme utilisé pour la recherche de chemins.
     *
     * @param algorithme le nouvel algorithme à utiliser
     */
    public void setAlgorithme(Algorithme algorithme) {
        this.algorithme = algorithme;
    }

    /**
     * Trouve les plus courts chemins dans le graphe à partir d'un nœud de départ
     * en utilisant l'algorithme actuellement sélectionné.
     *
     * @param g le graphe sur lequel effectuer la recherche
     * @param depart le nœud de départ
     * @return un objet Valeurs contenant les résultats de la recherche
     */
    public Valeurs trouverChemins(Graphe g, String depart) {
        return algorithme.resoudre(g, depart);
    }

    /**
     * Trouve les plus courts chemins dans le graphe à partir d'un nœud de départ
     * en utilisant l'algorithme sélectionné avec le système de pénalité.
     *
     * @param g le graphe sur lequel effectuer la recherche
     * @param depart le nœud de départ
     * @return un objet Valeurs contenant les résultats de la recherche
     */
    public Valeurs trouverChemins2(Graphe g, String depart) {
        return algorithme.resoudre2(g, depart);
    }
}