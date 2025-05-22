package graphe;

import java.util.List;

public class Arcs {
    private List<Arc> arcs;

    public Arcs() {
        this.arcs = new java.util.ArrayList<Arc>();
    }

    public void ajouterArc(Arc a) {
        if (a != null)
            this.arcs.add(a);
    }

    public List<Arc> getArcs() {
        return arcs;
    }
}
