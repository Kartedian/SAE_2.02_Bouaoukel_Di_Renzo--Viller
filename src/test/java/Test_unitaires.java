import graphe.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Test_unitaires {

    @Test
    public void Test_Arc() {
        Arc x = new Arc("A", 10);

        assertEquals("A(10.0)", x.toString());

        x = new Arc("A", 0);

        assertNotEquals("A(10.0)", x.toString());
        assertEquals("null(0.0)", x.toString());
    }

    @Test
    public void Test_Graphe() {
        GrapheListe x = new GrapheListe();
        x.ajouterArc("A", "B", 10);
        x.ajouterArc("B", "C", 10);
        x.ajouterArc("C", "A", 10);

        assertEquals("A -> B(10.0) \nB -> C(10.0) \nC -> A(10.0) \n", x.toString());
        assertEquals(1, x.getIndice("B"));
        assertEquals(2, x.getIndice("C"));
        assertEquals(0, x.getIndice("A"));

        assertEquals(1, x.suivants("A").size());
        assertNull(x.suivants("Y"));

        assertEquals(3, x.listeNoeuds().size());
    }


    /**
     * Test unitaire de l'algorithme de Bellman-Ford appliqué à un graphe orienté
     *
     * Le graphe est construit avec des arcs entre les sommets A, B, C, D, E et F.
     * Le test vérifie que l'algorithme Bellman-Ford, appliqué à partir du sommet "A",
     * renvoie les distances minimales correctes vers chaque autre sommet.
     *
     * Arcs du graphe :
     * - A → B (7), A → F (10)
     * - B → C (8), B → A (2)
     * - C → D (7), C → B (4)
     * - D → E (4), D → C (2)
     * - E → F (4), E → D (2)
     * - F → A (1), F → E (2)
     *
     * Chemins minimaux attendus depuis A :
     * - A → B : 7
     * - A → B → C : 15
     * - A → F : 10
     * - A → F → E : 12
     * - A → F → E → D : 14
     *
     * Les assertions vérifient que ces distances sont correctement trouvées par l'algorithme.
     */
    @Test
    public void Test_Bellman() {
        GrapheListe gra = new GrapheListe();

        gra.ajouterArc("A", "B", 7);
        gra.ajouterArc("A", "F", 10);
        gra.ajouterArc("B", "C", 8);
        gra.ajouterArc("B", "A", 2);
        gra.ajouterArc("C", "D", 7);
        gra.ajouterArc("C", "B", 4);
        gra.ajouterArc("D", "E", 4);
        gra.ajouterArc("D", "C", 2);
        gra.ajouterArc("E", "F", 4);
        gra.ajouterArc("E", "D", 2);
        gra.ajouterArc("F", "A", 1);
        gra.ajouterArc("F", "E", 2);

        Strategie strategie = new Strategie(new Bellman());
        Valeurs res = strategie.trouverChemins(gra, "A");

        assertEquals(res.getValeur("A"), 0.0);
        assertEquals(res.getValeur("B"), 7.0);  //A>B
        assertEquals(res.getValeur("C"), 15.0); //A>B>C
        assertEquals(res.getValeur("D"), 14.0); //A>F>E>D
        assertEquals(res.getValeur("E"), 12.0); //A>F>E
        assertEquals(res.getValeur("F"), 10.0); //A>F
    }

    @Test
    public void Test_Dijkstra() {
        GrapheListe gra = new GrapheListe();

        gra.ajouterArc("A", "B", 7);
        gra.ajouterArc("A", "F", 10);
        gra.ajouterArc("B", "C", 8);
        gra.ajouterArc("B", "A", 2);
        gra.ajouterArc("C", "D", 7);
        gra.ajouterArc("C", "B", 6);
        gra.ajouterArc("D", "E", 8);
        gra.ajouterArc("D", "C", 2);
        gra.ajouterArc("E", "F", 1);
        gra.ajouterArc("E", "D", 6);
        gra.ajouterArc("F", "A", 9);
        gra.ajouterArc("F", "E", 10);

        Strategie strategie = new Strategie(new Dijkstra());
        Valeurs res = strategie.trouverChemins(gra, "A");

        assertEquals(res.getValeur("A"), 0.0);
        assertEquals(res.getValeur("B"), 7.0);  //A>B
        assertEquals(res.getValeur("C"), 15.0); //A>B>C
        assertEquals(res.getValeur("D"), 22.0); //A>B>C>D
        assertEquals(res.getValeur("E"), 20.0); //A>F>E
        assertEquals(res.getValeur("F"), 10.0); //A>F
    }
}