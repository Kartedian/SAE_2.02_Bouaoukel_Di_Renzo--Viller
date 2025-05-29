package graphe;

import jdk.dynalink.beans.StaticClass;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;

/**
 * Classe fournie, permet de stocker des valeurs associées au noeud et
 * des parents
 * - un noeud est represente par un String (son nom)
 * - on accede avec des get (getValeur et getParent)
 * - on modifie avec des set (setValeur et setParent)
 */
public class Valeurs {

    /**
     * attributs pour stocker les informations
     * (type Table = Dictionnaire)
     * dans le programme de 2 annee.
     */
    Map<String, Double> valeur;
    Map<String, String> parent;

    /**
     * constructeur vide (initialise la possibilité de stocker
     * des valeurs)
     */
    public Valeurs() {
        this.valeur = new TreeMap<>();
        this.parent = new TreeMap<>();
    }

    /**
     * permet d'associer une valeur a un nom de noeud (ici L(X))
     *
     * @param nom    le nom du noeud
     * @param valeur la valeur associée
     */
    public void setValeur(String nom, double valeur) {
        // modifie valeur
        this.valeur.put(nom, valeur);
    }

    /**
     * permet d'associer un parent a un nom de noeud (ici parent(X))
     *
     * @param nom    nom du noeud
     * @param parent nom du noeud parent associe
     */
    public void setParent(String nom, String parent) {
        this.parent.put(nom, parent);
    }

    /**
     * accede au parent stocke associe au noeud nom passe en
     * parametre
     *
     * @param nom nom du noeud
     * @return le nom du noeud parent
     */
    public String getParent(String nom) {
        return this.parent.get(nom);
    }


    /**
     * accede a la valeur associee au noeud nom passe en
     * parametre
     *
     * @param nom nom du noeud
     * @return la valeur stockee
     */
    public double getValeur(String nom) {
        if(this.valeur.containsKey(nom))
            return this.valeur.get(nom);
        return -1.0;
    }

    /**
     * retourne une chaine qui affiche le contenu
     * - par noeud stocke
     * - a chaque noeud, affiche la valeur puis le noeud
     *   parent
     *
     * @return descriptif du noeud
     */
    public String toString() {
        String res = "";
        // pour chaque noeud
        for (String s : this.valeur.keySet()) {
            // ajoute la valeur et le noeud parent
            Double valeurNoeud = valeur.get(s);
            String noeudParent = parent.get(s);
            res += s + " ->  V:" + valeurNoeud + " p:" + noeudParent + "\n";
        }
        return res;

    }

    /**
     * Cette méthode retourne une liste de nœuds correspondant au chemin menant au nœud
     * passé en paramètre depuis le point de départ donné lors de la construction de l’objet Valeurs.
     *
     * @param destination le nom du noeud de destination
     * @return une liste de String qui contient le chemin
     */
    public List<String> calculerChemin(String destination){
        List<String> chemin = new java.util.ArrayList<>();
        String parent = this.getParent(destination);
        chemin.add(destination);
        while(parent != null && !parent.isEmpty()) {
            chemin.add(parent);
            parent = this.getParent(parent);
        }
        return chemin;
    }

    /**
     * Affiche dans la console les étapes d'un chemin
     * Chaque élément de la liste est affiché avec son index sous la forme : "étape X : sommet".
     *
     * @param chemin La liste des noms des sommets représentant un chemin.
     * @return null (aucune valeur utile retournée ; cette méthode est prévue pour afficher).
     */
    public static Object ToStringChemin(List<String> chemin) {
        String liste = "";

        for (int i = 0; i < chemin.size(); i++) {
            liste += "étape " + i + " : " + chemin.get(i) + "\n";
        }

        System.out.println(liste);

        return null;
    }



}