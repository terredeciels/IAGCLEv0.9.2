package gcle.fonction;

import gcle.Contexte;

public class FTantQue extends  Fonction {

    private final Fonction iPredicat;
    private final Fonction iFermeture;
    public FTantQue(Fonction predicat, Fonction fermeture) {
        super();
        iPredicat = predicat;
        iFermeture = fermeture;
    }
    public static Fonction getInstance(Fonction predicat, Fonction fermeture) {
        if (predicat == null) {
            throw new IllegalArgumentException("Predicate must not be null");
        }
        if (fermeture == null) {
            throw new IllegalArgumentException("Closure must not be null");
        }
        return new FTantQue(predicat, fermeture);
    }
    @Override
    public Contexte exec(Contexte c) {

        while (iPredicat.exec(c).booleen) {
            iFermeture.exec(c);
        }
        return c;
    }
}
