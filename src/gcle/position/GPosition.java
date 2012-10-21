package gcle.position;

import chesspresso.Chess;
import gcle.Contexte;
import gcle.FonctionUtile;
import gcle.ICodage;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.collections.iterators.ArrayIterator;


public class GPosition implements ICodage {

    private final static GPosition INSTANCE = new GPosition();
    private String fen;
    private Integer[] etats;
    private int trait;
    private ArrayList<GCoups> pseudocoups;
    private CPosition cp_position;
    private boolean droitPetitRoqueBlanc;
    private boolean droitGrandRoqueNoir;
    private boolean droitGrandRoqueBlanc;
    private boolean droitPetitRoqueNoir;
    private boolean[][] droitRoques;
    public boolean[][] getDroitRoques() {
        return droitRoques;
    }
    private int caseEP;
    private GPosition() {
        etats = new Integer[NB_CELLULES];
    }
    public static GPosition getInstance() {
        return INSTANCE;
    }
    public final void init(final String fen) throws IllegalArgumentException {
        this.fen = fen;
        cp_position = CPosition.getInstance();
        cp_position.init(fen);
        for (int caseO = 0; caseO < NB_CELLULES; caseO++) {
            etats[caseO] = OUT;
        }
        ArrayIterator itetats = new ArrayIterator(cp_position.getEtats());
        int indice = 0;
        while (itetats.hasNext()) {
            Integer e = (Integer) itetats.next();
            etats[CASES[indice]] = e;
            indice++;
        }
        if (cp_position.getTrait() == Chess.WHITE) {
            trait = BLANC;
        } else {
            trait = NOIR;
        }
        droitRoques = new boolean[2][2];
        droitRoques[0][0] = droitPetitRoqueBlanc = cp_position.getDroitPetitRoqueBlanc();
        droitRoques[1][0] = droitPetitRoqueNoir = cp_position.getDroitPetitRoqueNoir();
        droitRoques[0][1] = droitGrandRoqueBlanc = cp_position.getDroitGrandRoqueBlanc();
        droitRoques[1][1] = droitGrandRoqueNoir = cp_position.getDroitGrandRoqueNoir();

        if (cp_position.getCaseEP() == PAS_DE_CASE) {
            caseEP = -1;
        } else {
            caseEP = CASES[cp_position.getCaseEP()];
        }
        Contexte c =new Contexte(this);     
//        Fonction fCoupsLegaux = GFonction.fCoupsLegaux;
//        Contexte resultContexte = fCoupsLegaux.exec(new Contexte(this));
        pseudocoups = FonctionUtile.fCoupsLegaux.exec(c).fCoups;
    }
    public Integer[] getEtats() {
        return etats;
    }
    public int getTrait() {
        return trait;
    }
    public void setTrait(int trait) {
        this.trait = trait;
    }
    public int getCaseEP() {
        return caseEP;
    }
    public boolean getDroitPetitRoqueBlanc() {
        return droitPetitRoqueBlanc;
    }
    public boolean getDroitPetitRoqueNoir() {
        return droitPetitRoqueNoir;
    }
    public boolean getDroitGrandRoqueNoir() {
        return droitGrandRoqueNoir;
    }
    public boolean getDroitGrandRoqueBlanc() {
        return droitGrandRoqueBlanc;
    }
    public CPosition getCPosition() {
        return cp_position;
    }
    public String getFen() {
        return fen;
    }
    public ArrayList<String> toStringListGCoups() {
        ArrayList<String> result = new ArrayList();
        for (GCoups c : pseudocoups) {
            result.add(GCoups.getString(c));
        }
        Collections.sort(result);
        return result;
    }
    public GPosition copieEtats() {
        //copie des etats seulement !
        GPosition position = new GPosition();
        System.arraycopy(etats, 0, position.etats, 0, NB_CELLULES);
        return position;
    }
}
