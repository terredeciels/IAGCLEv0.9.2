package gcle;

import static gcle.FonctionBase.*;
import static gcle.ICodage.*;
import gcle.ICodage.TypeCoups;
import gcle.fonction.FSi;
import gcle.fonction.FSwitch;
import gcle.fonction.FTantQue;
import static gcle.fonction.FUtiles.*;
import gcle.fonction.Fonction;
import java.util.ArrayList;
import java.util.Arrays;

public class PFonction extends FonctionUtile implements ICodage {
    static class PriseOuAttaque extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            if (!c.recherchePionAttaqueRoque) {
                c.fonction = fog(fDiagonalePionPrise, fCaseSuivanteDiagonalePion);
            } else {
                c.typeCoups = TypeCoups.Attaque;
                c.fonction = fDiagonalePionAttaqueRoque;
            }
            return c;
        }
    }
    
    static class PieceAJouer extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.caseO = c.entier;
            c.booleen = c.etats[c.caseO] * c.couleur > 0;
            return c;
        }
    }
    static class CaseAJouer extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.collection = ECHIQUIER;
            c.fonction = fPiecesAJouer;
            fog(fTantQuePieceAJouer, fog(fCreerPositionIterateur, fSelect)).exec(c);
            c.fCoups = c.pseudoCoups;
            return c;
        }
    }
    static class EstUnPion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.typePiece == PION;
            return c;
        }
    }
    static class CaseSuivanteDirection extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
//     ??      c.caseX= c.iterateurCaseX.next();
            c.caseX = c.caseX + c.direction;
            return c;
        }
    }
    static class CaseSuivanteAvancePion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.caseX = c.caseX + c.NordSudSelonCouleur;
            return c;
        }
    }
    static class CaseSuivanteDiagonalePion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.caseX = c.caseO + c.NordSudSelonCouleur + c.estOuOuest;
            return c;
        }
    }
    static class InitPseudoCoupsPion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.NordSudSelonCouleur = c.couleur == BLANC ? nord : sud;
            c.caseX = c.caseO;
            return c;
        }
    }
    static class PseudoCoupsPion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            FTantQue
                    .getInstance(fExisteCoupsPionSuivant,
                    fog(fPriseOuAttaque
                    //eliminer c ? :
                    .exec(c).fonction, fSensSuivant))
                    .exec(c);
            return c;
        }
    }
    static class PremiereCaseX extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.direction = c.entier;
            c.caseX = c.caseO + c.direction;
            return c;
        }
    }
    static class EstPieceGlissante extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = TGLISSANT[c.typePiece];
            return c;
        }
    }
    static class InitSelonGlissant extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.collection = Arrays.asList(LDIRECTIONS.get(c.typePiece));
            c.fonction = fEstPieceGlissante.exec(c).booleen ? fPieceGlissante : fPieceNonGlissante;
            return c;
        }
    }
    static class PoseRecherchePionAttaqueRoque extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.recherchePionAttaqueRoque = true;
            return c;
        }
    }
    static class RoqueContexte extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            // attention: -couleur
            Contexte d = new Contexte(c.gposition, -c.couleur);
            c.pseudoCoupsAttaqueRoques = fog(fCaseAJouer, fPoseRecherchePionAttaqueRoque).exec(d).pseudoCoups;
            c.dEtats = d.gposition.getEtats();
            return d;
        }
    }
    static class ExisteCaseEP extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.gposition.getCaseEP() != PAS_DE_CASE;
            return c;
        }
    }
    static class InitRoques extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.aileRoi = c.couleur == BLANC ? 0 : 2;
            c.aileDame = c.couleur == BLANC ? 1 : 3;
            c.constante = c.couleur == BLANC ? 0 : -2;
            return c;
        }
    }
    static class CasesAttaqueRoque extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.caseAttaquante == c.E1ouE8
                    || c.caseAttaquante == c.F1ouF8
                    || c.caseAttaquante == c.G1ouG8;
            return c;

        }
    }
    static class EstAttaqueRoque extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.estAttaqueDuRoi = true;
            return c;
        }
    }
    static class RoquesEtat extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = fRoqueContexte
                    .exec(c).gposition
                    .getDroitRoques()[BCOULEUR.get(c.couleur)][c.aile + c.constante];
            return c;
        }
    }
    static class RoquePossible extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            boolean possible = c.dEtats[CASESROQUE[c.aile][1]] == VIDE && c.dEtats[CASESROQUE[c.aile][2]] == VIDE;
            if (c.aile == c.aileDame) {
                possible &= c.dEtats[CASESROQUE[c.aile][3]] == VIDE;
            }
            c.E1ouE8 = CASESROQUE[c.aile][0];
            c.F1ouF8 = CASESROQUE[c.aile][1];
            c.G1ouG8 = CASESROQUE[c.aile][2];
            c.estAttaqueDuRoi = false;
            c.booleen = possible &= !(fAttaqueRoque.exec(c).estAttaqueDuRoi);
            return c;
        }
    }
    static class PieceOpposante extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = fExisteCase.exec(c).booleen && c.etats[c.caseX] * c.couleur < 0;
            return c;
        }
    }
    static class Vide extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.etats[c.caseX] == VIDE;
            return c;
        }
    }
    static class TypePiece extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.typePiece = Math.abs(c.etats[c.caseO]);
            return c;
        }
    }
    static class ExisteCase extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.etats[c.caseX] != OUT;
            return c;

        }
    }
    static class RangFinal extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            if (c.caseX >= a1 && c.caseX <= h1 && c.couleur == NOIR) {
                c.booleen = true;
            } else if (c.caseX >= a8 && c.caseX <= h8 && c.couleur == BLANC) {
                c.booleen = true;
            } else {
                c.booleen = false;
            }
            return c;
        }
    }
    static class RangInitial extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            if ((c.caseO >= a7) && (c.caseO <= h7) && (c.couleur == NOIR)) {
                c.booleen = true;
            } else if ((c.caseO >= a2) && (c.caseO <= h2) && (c.couleur == BLANC)) {
                c.booleen = true;
            } else {
                c.booleen = false;
            }
            return c;
        }
    }
    static class PionQuiALeTrait extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            fTypePiece.exec(c);
            c.booleen = c.typePiece == PION && (c.etats[c.caseO] < 0 ? BLANC : NOIR) == c.couleur;
            return c;
        }
    }
  
    static class CaseDuRoi extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.caseO = c.entier;
            c.booleen = Math.abs(c.etatsSimul[c.caseO]) == ROI && c.etatsSimul[c.caseO] * c.couleur > 0;
            return c;
        }
    }
    static class DeplacerPiece extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.etatsSimul[c.caseX] = c.pieceADeplacer;
            c.etatsSimul[c.caseO] = VIDE;
            return c;
        }
    }
    static class DeplacerPieceEnPassant extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.etatsSimul[c.caseX + mDirectionSelonCouleur.get(c.couleur)] = VIDE;
            return c;
        }
    }
    static class DeplacerPiecePromotion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.pieceADeplacer = c.coupsAControlerPourEchec.getPiecePromotion();
            return c;
        }
    }
    static class FiltreEchec extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            fIterateurFiltreEchecs.exec(c);
            Contexte c1 = fSimulePosition.exec(c);
            c.pseudoCoupsPositionSimule = fCaseAJouer.exec(new Contexte(c1.gpositionSimul, c1.gpositionSimul.getTrait())).pseudoCoups;
            c.collection = ECHIQUIER;
            c.fonction = fCaseDuRoi;
            c.caseRoi = fFind(c).entier;
            FSi.getInstance(fog(fExist, fInitEstEnEchec), fAjouterCoupsARetirer).exec(c);
            return c;
        }
    }
    static class InitEstEnEchec extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            fCreerIterateurPositionSimule.exec(c);
            c.collection = c.pseudoCoupsPositionSimule;
            c.fonction = fEstCaseDuRoi;
            return c;
        }
    }
    static class InitFiltreEchec extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.coupsARetirerCarEchec = new ArrayList();
            fCreerIterateurFiltreEchec.exec(c);
            FTantQue.getInstance(fExisteCoupsARetirerSuivant, fFiltreEchec).exec(c);
            return c;
        }
    }
    static class RetirerCoupsMiseEnEchec extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.fCoups.removeAll(fInitFiltreEchec.exec(c).coupsARetirerCarEchec);
            return c;
        }
    }
    static class SimulePosition extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.gpositionSimul = c.gposition.copieEtats();
            c.gpositionSimul.setTrait(-c.couleur);
            c.etatsSimul = c.gpositionSimul.getEtats();
            c.caseO = c.coupsAControlerPourEchec.getCaseO();
            c.caseX = c.coupsAControlerPourEchec.getCaseX();
            c.typeCoups = c.coupsAControlerPourEchec.getTypeDeCoups();
            c.pieceADeplacer = c.etatsSimul[c.caseO];
            FSwitch.getInstance(tTypeCoups, tTypeAction).exec(c);
            return c;
        }
    }
    static class TypeDeplOuPrise extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.typeCoups == TypeCoups.Deplacement || c.typeCoups == TypeCoups.Prise;
            return c;
        }
    }
    static class TypeEnPassant extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.typeCoups == TypeCoups.EnPassant;
            return c;
        }
    }
    static class TypePromotion extends Fonction {
        @Override
        public Contexte exec(Contexte c) {
            c.booleen = c.typeCoups == TypeCoups.Promotion;
            return c;
        }
    }
}