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
        assertEquals("A(0.0)", x.toString());
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



        Valeurs res = Bellman.resoudre(gra, "A");
        assertEquals(res.getValeur("A"), 0.0);
        assertEquals(res.getValeur("B"), 7.0);  //A>B
        assertEquals(res.getValeur("C"), 15.0); //A>B>C
        assertEquals(res.getValeur("D"), 14.0); //A>F>E>D
        assertEquals(res.getValeur("E"), 12.0); //A>F>E
        assertEquals(res.getValeur("F"), 10.0); //A>F
    }
}
