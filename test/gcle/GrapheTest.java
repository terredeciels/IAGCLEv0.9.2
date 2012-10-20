package gcle;

import gcle.fonction.Fonction;
import gcle.graphe.Graphe;
import org.junit.Test;

public class GrapheTest extends PFonction {
    public GrapheTest() {

        Graphe<Fonction> gr0 = new Graphe<Fonction>(PFonction.fCoupsLegaux);
//        gr0.ajouterEnfant(new Roques());
//        gr0.ajouterEnfant(new EnPassant());
        gr0.ajouterEnfant(fRetirerCoupsMiseEnEchec);
//      gr0.ajouterEnfant(fCaseAJouer);

        Graphe<Fonction> gr1 = new Graphe<Fonction>(fCaseAJouer);
//        gr1.ajouterEnfant(cCaseAJouer);
//      gr1.ajouterEnfant(new Noeud(202));
//      gr1.ajouterEnfant(new Noeud(203));

        gr0.ajouterEnfant(gr1);
        System.out.println(gr0);

//        Graphe<FonctionClasse> predicats = new Graphe<FonctionClasse>(pVide);
    }
    @Test
    public void init() {
//         GrapheTest grapheTest = new GrapheTest();
    }
    public static void main(String[] arg) {
        GrapheTest grapheTest = new GrapheTest();
    }
}
