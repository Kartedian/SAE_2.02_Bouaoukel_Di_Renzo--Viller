package graphe;

import java.util.List;

/**
 * On souhaite definir un ensemble d'arcs patant d'un noeud sous la forme d'un onbjet de type Arcs.
 * La classe arcs est un classe tre simple qui ne fait que gerer une liste. Elle possede:
 *
 * Un attribut List<Arc> arcs qui represente la liste d'arcs a gerer.
 */
public class Arcs {
    private List<Arc> arcs;

    /**
     * un constructeur Arcs() qui construit une liste d’arcs vide
     */
    public Arcs() {
        this.arcs = new java.util.ArrayList<Arc>();
    }

    /**
     * une méthode void ajouterArc(Arc a) qui ajoute l’arc a à la liste d’arcs
     *
     * @param a
     */
    public void ajouterArc(Arc a) {
        if (a != null)
            this.arcs.add(a);
    }

    /**
     * une méthode List<Arc> getArcs() qui retourne l’ensemble des arcs de la listee (c’est-àdire l’attribut arcs)
     *
     * @return
     */
    public List<Arc> getArcs() {
        return arcs;
    }
}
