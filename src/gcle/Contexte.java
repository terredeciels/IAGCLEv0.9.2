package gcle;

import gcle.fonction.Fonction;
import gcle.position.GCoups;
import gcle.position.GPosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Contexte extends Primitif implements ICodage //, Iterable<Integer>
{
    public ArrayList<GCoups> fCoups;
    ArrayList<GCoups> pseudoCoups, coupsARetirerCarEchec,
            pseudoCoupsPositionSimule, pseudoCoupsAttaqueRoques;
    GPosition gposition, gpositionSimul;
    Integer couleur, couleurSimul;
    Integer aile, aileDame, aileRoi;
    Integer constante;//à nommer
    Integer caseO, caseX, caseRoi, caseEP, E1ouE8, F1ouF8, G1ouG8;
    Integer typePiece;
    Integer piecePrise = 0, piecePromotion;
    Integer caseAttaquante;
    Integer pieceADeplacer;
    Integer direction, estOuOuest, NordSudSelonCouleur;
    Integer[] etats, etatsSimul;
    Integer[] directionsPiece;
    Integer[] dEtats;
    TypeCoups typeCoups;
    GCoups coups, coupsAControlerPourEchec;
    boolean estAttaqueDuRoi, estPieceGlissante, recherchePionAttaqueRoque;
    Iterator<Integer> iterateurPosition, iterateurAile, iterateurPromotion, iterateurSens;
    Iterator<GCoups> iterateurAttaqueRoque, iterateurfiltrechecs, iterateurcoupspositionsimule;
    public Collection collection, result;
    public Fonction fonction;
    public Object objet;
    public Contexte(GPosition gposition) {
        this.gposition = gposition;
        couleur = gposition.getTrait();
        etats = gposition.getEtats();
        pseudoCoups = new ArrayList<>();
    }
    Contexte(GPosition gposition, int couleur) {
        this.gposition = gposition;
        this.couleur = couleur;
        etats = gposition.getEtats();
        pseudoCoups = new ArrayList<>();
    }
  
   
}