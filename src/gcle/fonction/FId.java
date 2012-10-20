package gcle.fonction;

import gcle.Contexte;

public class FId extends Fonction {
    private static final FId INSTANCE = new FId();
    private FId() {
    }
    public static FId getInstance() {
        return INSTANCE;
    }
    @Override
    public Contexte exec(Contexte c) {
        return c;
    }
}
