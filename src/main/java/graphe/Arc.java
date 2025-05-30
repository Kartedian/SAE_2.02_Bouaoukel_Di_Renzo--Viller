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
    private String ligne;

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

        if (dest != null && !dest.isEmpty())
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

    /**
     * Retourne le coût associé à cet arc.
     *
     * @return le coût de l’arc
     */

    public double getCout() {
        return cout;
    }


    /**
     * Retourne le nom du nœud de destination de l’arc.
     *
     * @return le nom de la destination
     */
    public String getDest(){

        return dest;
    }

    /**
     * Construit un arc avec une ligne spécifiée.
     *
     * @param dest  le nom du nœud de destination
     * @param cout  le coût de l’arc
     * @param ligne la ligne à laquelle appartient l’arc
     */

    public Arc(String dest, double cout, String ligne) {
        if (cout > 0.0)
            this.cout = cout;
        else
            return;

        if (dest != null && !dest.isEmpty())
            this.dest = dest;
        else {
            this.cout=0.0;
            return;
        }

        if(ligne != null && !ligne.isEmpty())
            this.ligne = ligne;
        else {
            this.dest = null;
            this.cout = 0.0;
            return;
        }
    }

    /**
     * Retourne la ligne à laquelle appartient cet arc.
     *
     * @return le nom de la ligne
     */
    public String getLigne() {return this.ligne;}
}
