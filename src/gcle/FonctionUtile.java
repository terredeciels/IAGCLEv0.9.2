package gcle;

import static gcle.FonctionBase.*;
import gcle.fonction.FSi;
import gcle.fonction.FTantQue;
import static gcle.fonction.FUtiles.*;
import gcle.fonction.Fonction;

public class FonctionUtile extends FonctionBase {
    static final Fonction fPseudoCoupsPromotion = fog(FTantQue
            .getInstance(fExisteCoupsPromotionSuivant,
            fog(fAjouterPionPromotion, fPiecePromotionSuivante)), fCreerCoupsPromotionIterateur);
    static final Fonction fSiPromotion = FSi
            .getInstance(fRangFinal, fPseudoCoupsPromotion, fAjouterCoupsDeplacement);
    static final Fonction fSiRangInitial = FSi
            .getInstance(fRangInitial, FSi
            .getInstance(fVide, fAjouterCoupsDeplacement));
    static final Fonction fSiVidePion = FSi
            .getInstance(fVide, fog(fog(fSiRangInitial, fCaseSuivanteAvancePion), fSiPromotion));
    static final Fonction fTraiterTypePiece = FSi
            .getInstance(fEstUnPion, fog(fPseudoCoupsPion,
            fog(fog(fSiVidePion, fCreerCoupsPionIterateur),
            fog(fCaseSuivanteAvancePion, fInitPseudoCoupsPion))),
            fog(fPour, fInitSelonGlissant));
    static final Fonction fTantQuePieceAJouer = FTantQue
            .getInstance(fExistePieceSuivante, fog(fog(fTraiterTypePiece, fTypePiece), fPieceSuivante));
    static final Fonction fTantQue = FTantQue
            .getInstance(fSuivantRoque, fog(FSi
            .getInstance(fCaseAttaqueRoque, fEstAttaqueRoque), fIterateurRoque));
    static final Fonction fTantQueConditionsRoque = FTantQue
            .getInstance(fConditionRoque, fog(FSi
            .getInstance(fRoquesEtat, FSi
            .getInstance(fRoquePossible, fAjouterCoupsRoque)), fInitConditionRoque));
    static final Fonction fTantQuePieceGlisse = FTantQue
            .getInstance(fVide, fog(fCaseSuivanteDirection, fAjouterCoupsDeplacement));
    static final Fonction fSiPieceOpposante = FSi
            .getInstance(fPieceOpposante, fAjouterCoupsPrise);
    static final Fonction fDiagonalePionPrise = FSi.getInstance(fPieceOpposante, FSi
            .getInstance(fRangFinal, fPseudoCoupsPromotion, fAjouterCoupsPrise));
    static final Fonction fConditionPionEnPassant = FSi
            .getInstance(fPionQuiALeTrait, fAjouterPionEnPassant);
    static final Fonction fPieceNonGlissante = fog(FSi
            .getInstance(fVide, fAjouterCoupsDeplacement, FSi
            .getInstance(fPieceOpposante, fAjouterCoupsPrise)), fPremiereCaseX);
    static final Fonction fPieceGlissante = fog(fSiPieceOpposante, fog(fTantQuePieceGlisse, fPremiereCaseX));
    static final Fonction fTraiterEnPassantSelonCouleur = fog(FTantQue
            .getInstance(fExisteCoupsPionSuivant,
            fog(fConditionPionEnPassant, fSensSuivantPionPromotion)), fCreerCoupsPionIterateur);
    static final Fonction fDiagonalePionAttaqueRoque = fog(
            FSi
            .getInstance(fExisteCase, fAjouterCoups), fCaseSuivanteDiagonalePion);
    static final Fonction fRoques = fogoh(fTantQueConditionsRoque, fIterateurAileRoques, fInitRoques);
    static final Fonction fAttaqueRoque = fog(fTantQue, fIterateurAtaqueRoque);
    public static final Fonction fCoupsLegaux = fog(fog(fRetirerCoupsMiseEnEchec, FSi
            .getInstance(fExisteCaseEP, fTraiterEnPassantSelonCouleur)), fog(fRoques, fCaseAJouer));
    static final Fonction[] tTypeCoups = {fTypeDeplOuPrise, fTypeEnPassant, fTypePromotion};
    static final Fonction[] tTypeAction = {fDeplacerPiece, fog(
        fDeplacerPieceEnPassant, fDeplacerPiece), fog(
        fDeplacerPiece, fDeplacerPiecePromotion)};
}
