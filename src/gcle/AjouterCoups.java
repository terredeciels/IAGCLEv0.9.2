package gcle;

import gcle.fonction.Fonction;
import gcle.position.GCoups;
import gcle.position.GPosition;

public class AjouterCoups extends Contexte {
    public AjouterCoups(GPosition gposition) {
        super(gposition);
    }
    public AjouterCoups(GPosition gposition, int couleur) {
        super(gposition, couleur);
    }
    static class AjouterCoupsNormal extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.pseudoCoups.add(new GCoups(c.etats[c.caseO], c.caseO, c.caseX, c.etats[c.caseX], c.typeCoups));
            return c;
        }
    }
    static class AjouterPionPromotion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.pseudoCoups.add(new GCoups(PION, c.caseO, c.caseX, c.etats[c.caseX], TypeCoups.Promotion, c.piecePromotion));
            return c;
        }
    }
    static class AjouterCoupsPrise extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.pseudoCoups.add(new GCoups(c.etats[c.caseO], c.caseO, c.caseX, c.etats[c.caseX], TypeCoups.Prise));
            return c;
        }
    }
    static class AjouterCoupsDeplacement extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.pseudoCoups.add(new GCoups(c.etats[c.caseO], c.caseO, c.caseX, c.etats[c.caseX], TypeCoups.Deplacement));
            return c;
        }
    }
    static class AjouterPionEnPassant extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.fCoups.add(new GCoups(PION, c.caseO, c.gposition.getCaseEP(), 0, TypeCoups.EnPassant));
            return c;
        }
    }
    static class AjouterCoupsRoque extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.fCoups.add(new GCoups(ROI, CASESROQUE[c.aile][0], CASESROQUE[c.aile][2], 0, TypeCoups.Roque));
            return c;
        }
    }
}
