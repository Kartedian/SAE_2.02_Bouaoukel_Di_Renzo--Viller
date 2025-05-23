package graphe;

/**
 * Un objet de la classe Arc represente un arc partant d'un noeud. Il est decrit par deux attributs prives:
 *
 * String dest qui represent le nom du noeud destination de l'arc;
 * double cout correspondant au cout de l'arc.
 *
 */
public class Arc {
    private String dest;
    private double cout;

    /**
     * Construit un nouvel arc
     *
     * @param dest
     * @param cout
     */
    public Arc(String dest, double cout) {
        if (cout > 0.0)
            this.cout = cout;
        else
            return;

        if (dest == null && dest.isEmpty())
            this.dest = dest;
        else {
            this.cout=0.0;
            return;
        }
    }

    /**
     * Renvoie une representation textuelle de l'arc
     *
     * @return la chaine au format "destination(cout)"
     */

    public String toString(){
        return this.dest+"("+this.cout+")";
    }

    public double getCout() {
        return cout;
    }

    public String getDest(){

        return dest;
    }
}
