import graphe.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Test_unitaires {

    @Test
    public void Test_Arc_1() {
        Arc x = new Arc("A", 10);

        assertEquals("A(10.0)", x.toString());

        x = new Arc("A", 0);

        assertNotEquals("A(10.0)", x.toString());
        assertEquals("A(0.0)", x.toString());
    }

    @Test
    public void Test_Graphe_1() {
        GrapheListe x = new GrapheListe();
        x.ajouterArc("A", "B", 10);
        x.ajouterArc("B", "C", 10);
        x.ajouterArc("C", "A", 10);

        assertEquals("A -> B(10.0) C(10.0) \nB -> C(10.0) A(10.0) \nC -> A(10.0) B(10.0) \n", x.toString());
        //assertFalse(x.listeNoeuds().contains("D"));
    }

}
