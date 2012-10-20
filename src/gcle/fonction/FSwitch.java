package gcle.fonction;

import gcle.Contexte;

public class FSwitch extends Fonction {
    private final Fonction[] iTp;
    private final Fonction[] iTf;
    private FSwitch(Fonction[] tp, Fonction[] tf) {
        iTp = tp;
        iTf = tf;
    }
    public static Fonction getInstance(Fonction[] tp, Fonction[] tf) {
        return new FSwitch(tp, tf);
    }
    @Override
    public Contexte exec(Contexte c) {
        for (int i = 0; i < iTp.length; i++) {
            if (iTp[i].exec(c).b == true) {
                iTf[i].exec(c);
                return c;
            }
        }
        return c;
    }
}
