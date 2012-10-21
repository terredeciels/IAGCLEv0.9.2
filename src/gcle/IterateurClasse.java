package gcle;

import static fj.data.Array.*;
import gcle.fonction.Fonction;

public class IterateurClasse extends PFonction {
    //debut iterateur position
    static class SuivantePiece extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.caseO = c.iterateurPosition.next();
            return c;
        }
    }
    static class ExistePieceSuivante extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.iterateurPosition.hasNext();
            return c;
        }
    }
    static class CreerIterateurPosition extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.iterateurPosition = c.result.iterator();
            return c;
        }
    }
    //fin iterateur position
    //
    //debut iterateur promotion pion
    static class ExisteCoupsPromotionSuivant extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.iterateurPromotion.hasNext();
            return c;
        }
    }
    static class SuivantePiecePromotion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.piecePromotion = c.couleur * c.iterateurPromotion.next();
            return c;
        }
    }
    static class CreerIterateurPromotion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.iterateurPromotion = array(FOU, CAVALIER, DAME, TOUR).iterator();
            return c;
        }
    }
    //fin iterateur promotion pion
    //
    //debut iterateur sens est ouest
    static class SuivantSens extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.estOuOuest = c.iterateurSens.next();
            return c;
        }
    }
     static class SensSuivantPionPromotion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.caseO = c.gposition.getCaseEP() + mDirectionSelonCouleur.get(c.couleur) + c.iterateurSens.next();
            return c;
        }
    }
    static class ExisteCoupsPionSuivant extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.iterateurSens.hasNext();
            return c;
        }
    }
    static class CreerIterateurSens extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.iterateurSens = array(est, ouest).iterator();
            return c;
        }
    }
    //fin iterateur sens
    //
    //debut iterateur ailes dame ou roi
    static class ExisteAileSuivante extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.iterateurAile.hasNext();
            return c;
        }
    }
    static class SuivanteAileRoque extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.aile = c.iterateurAile.next();
            return c;
        }
    }
    static class CreerIterateurAileRoque extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.iterateurAile = array(c.aileRoi, c.aileDame).iterator();
            return c;
        }
    }
    //fin iterateur ailes dame ou roi
    //
    // debut iterateur attaque roque
    static class ExisteAttaqueRoqueSuivante extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.iterateurAttaqueRoque.hasNext();
            return c;
        }
    }
    static class SuivanteCaseRoque extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.caseAttaquante = c.iterateurAttaqueRoque.next().getCaseX();
            return c;
        }
    }
    static class CreerIterateurAttaqueRoque extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.iterateurAttaqueRoque = c.pseudoCoupsAttaqueRoques.iterator();
            return c;
        }
    }
    // fin iterateur attaque roque
    //
    //debut iterateur filtre echec
    static class IterateurFiltreEchecs extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.coupsAControlerPourEchec = c.iterateurfiltrechecs.next();
            return c;
        }
    }
    static class ExisteCoupsARetirerSuivant extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.iterateurfiltrechecs.hasNext();
            return c;
        }
    }
    static class CreerIterateurFiltreEchec extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.iterateurfiltrechecs = c.fCoups.iterator();
            return c;
        }
    }
    //fin iterateur filtre echec
    //
    // ?
    static class CreerIterateurPositionSimule extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.iterateurcoupspositionsimule = c.pseudoCoupsPositionSimule.iterator();
            return c;
        }
    }
    static class EstCaseDuRoi extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.iterateurcoupspositionsimule.next().getCaseX() == c.caseRoi;
            return c;
        }
    }
   //
}
