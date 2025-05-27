package graphe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation d'un graphe orienté et pondéré utilisant des listes d'adjacence.
 * Le graphe est représenté par une liste de noeuds et une liste d'adjacence
 * contenant les arcs sortants de chaque noeud.
 */
public class GrapheListe implements Graphe{
    /** Liste des noms des noeuds du graphe */
    private ArrayList<String> noeuds;

    /** Liste des arcs sortants pour chaque noeud (liste d'adjacence) */
    private ArrayList<Arcs> adjacence;

    /**
     * Construit un nouveau graphe vide
     */
    public GrapheListe(){
        this.noeuds = new ArrayList<String>();
        this.adjacence = new ArrayList<Arcs>();
    }

    /**
     * un constructeur prenant en paramètre le nom du fichier contenant le descriptif (sous forme de liste d’arcs)
     * du graphe et construisant l’objet graphe correspondant
     *
     * @param file
     */
    public GrapheListe(String file){
        this.noeuds = new ArrayList<String>();
        this.adjacence = new ArrayList<Arcs>();

        try{
            FileReader reader = new FileReader(file);
            BufferedReader buff = new BufferedReader(reader);

            String line;
            while((line = buff.readLine()) != null){
                String[] parts = line.split(" ");
                if(parts.length == 3){
                    ajouterArc(parts[0], parts[1], Double.parseDouble(parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public List<String> listeNoeuds(){
        return new ArrayList<>(this.noeuds);
    }

    public List<Arc> suivants(String n) {
        // Renvoie la liste des arcs sortants du noeud n
        List<Arc> res = new ArrayList<>();
        if (this.noeuds.contains(n)) {
            int index = getIndice(n);
            return new ArrayList<>(this.adjacence.get(index).getArcs());
        }
        return null;
    }


    /**
     * Recherche l'indice d'un noeud dans la liste des noeuds
     *
     * @param n le nom du noeud à rechercher
     * @return l'indice du noeud dans la liste, ou -1 si le noeud n'existe pas
     */
    public int getIndice(String n){
        // Recherche l'indice du noeud dans la liste des noeuds
        if(this.noeuds.contains(n))
            return this.noeuds.indexOf(n);
        else
            return -1;
    }

    /**
     * Ajoute un arc au graphe avec vérification et création automatique des noeuds si nécessaire.
     * Si les noeuds de départ ou d'arrivée n'existent pas, ils sont automatiquement ajoutés au graphe.
     *
     * @param depart le nom du noeud de départ
     * @param destination le nom du noeud d'arrivée
     * @param cout le coût (poids) de l'arc
     * @return void si les paramètres sont invalides (null, vides ou coût négatif)
     */
    public void ajouterArc(String depart, String destination, double cout) {
        // Vérifie les paramètres
        if (destination == null && destination.isEmpty() || depart == null && depart.isEmpty() || cout <= 0.0) {
            return;
        }
        // Ajoute les noeuds si nécessaire
        if (!this.noeuds.contains(depart)) {
            this.noeuds.add(depart);
            this.adjacence.add(new Arcs());
        }
        this.adjacence.get(this.getIndice(depart)).ajouterArc(new Arc(destination, cout));
    }

    /**
     * Renvoie une représentation textuelle du graphe.
     * Le format est: noeud -> destination1(cout1) destination2(cout2) ...
     *
     * @return une chaîne représentant le graphe
     */
    public String toString(){
        String res = "";
        for(String noeud : this.noeuds){
            res += noeud + " -> ";
            for(Arc arc : this.adjacence.get(getIndice(noeud)).getArcs()){
                res += arc.toString() + " ";
            }
            res += "\n";
        }
        return res;
    }

    public void ajouterArc(String depart, String destination, double cout, String ligneMetro) {
        // Vérifie les paramètres
        if (destination == null && destination.isEmpty() || depart == null && depart.isEmpty() || cout <= 0.0 || ligneMetro == null && ligneMetro.isEmpty()) {
            return;
        }
        // Ajoute les noeuds si nécessaire
        if (!this.noeuds.contains(depart)) {
            this.noeuds.add(depart);
            this.adjacence.add(new Arcs());
        }
        this.adjacence.get(this.getIndice(depart)).ajouterArc(new Arc(destination, cout, ligneMetro));
    }
}