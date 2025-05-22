package graphe;

import java.util.ArrayList;

public class GrapheListe {
    private ArrayList<String> noeuds;
    private ArrayList<Arcs> adjacence;

    public int getIndice(String n){
        if(noeuds.contains(n))
            return noeuds.indexOf(n);
        else
            return -1;
    }

    public void ajouterArc(String depart, String destination, double cout){
        if(destination == null && destination.isEmpty() || depart == null && depart.isEmpty() || cout<=0.0) {
            return;
        }
        this.noeuds.add(destination);
        this.adjacence.get(getIndice(depart)).ajouterArc(new Arc(destination, cout));
    }
}
