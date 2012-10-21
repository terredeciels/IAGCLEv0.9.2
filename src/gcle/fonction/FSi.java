package gcle.fonction;

import gcle.Contexte;

public class FSi extends  Fonction {

    private final Fonction iPFonction;
    private final Fonction iFVrai;
    private final Fonction iFfaux;
    private FSi(Fonction pFonction, Fonction fVrai, Fonction fFaux) {
        super();
        iPFonction = pFonction;
        iFVrai = fVrai;
        iFfaux = fFaux;
    }
    public static Fonction getInstance(Fonction pred, Fonction fvrai) {
        return getInstance(pred, fvrai, FId.getInstance());
    }
    public static  Fonction getInstance(Fonction pred, Fonction fvrai, Fonction ffaux) {
        if (pred == null) {
            throw new IllegalArgumentException("Predicate must not be null");
        }
        if (fvrai == null || ffaux == null) {
            throw new IllegalArgumentException("Closures must not be null");
        }
        return new FSi(pred, fvrai, ffaux);
    }
    @Override
    public Contexte exec(Contexte c) {

        if (iPFonction.exec(c).booleen == true) {
            iFVrai.exec(c);
        } else {
            iFfaux.exec(c);
        }
        return c;
    }
}
