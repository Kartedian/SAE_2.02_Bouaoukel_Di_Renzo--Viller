package graphe;

import java.util.ArrayList;

public class GrapheListe {
    private ArrayList<String> noeuds;
    private ArrayList<Arcs> adjacence;

    public GrapheListe(){
        this.noeuds=new ArrayList<String>();
        this.adjacence=new ArrayList<Arcs>();
    }

    public int getIndice(String n){
        if(this.noeuds.contains(n))
            return this.noeuds.indexOf(n);
        else
            return -1;
    }



    public void ajouterArc(String depart, String destination, double cout) {
        if (destination == null && destination.isEmpty() || depart == null && depart.isEmpty() || cout <= 0.0) {
            return;
        }
        if (!this.noeuds.contains(depart)) {
            this.noeuds.add(depart);
            this.adjacence.add(new Arcs());
        }
        this.adjacence.get(this.getIndice(depart)).ajouterArc(new Arc(destination, cout));
    }

    public String toString(){
        String res="";
        for(String noeud : this.noeuds){
            res+=noeud+" -> ";
            for(Arc arc : this.adjacence.get(getIndice(noeud)).getArcs()){
                res+= arc.toString() + " ";
            }
            res+="\n";
        }
        return res;
    }
}
