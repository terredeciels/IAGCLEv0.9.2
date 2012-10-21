package gcle.fonction;

import gcle.Contexte;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FUtiles {
    public static Fonction fog(final Fonction f, final Fonction g) {
        return new Fonction() {
            @Override
            public Contexte exec(Contexte c) {
                return f.exec(g.exec(c));
            }
        };
    }
    public static Fonction fogoh(final Fonction f, final Fonction g, final Fonction h) {
        return new Fonction() {
            @Override
            public Contexte exec(Contexte c) {
                return f.exec(g.exec(h.exec(c)));
            }
        };
    }
    public static Fonction fogohoi(final Fonction f, final Fonction g, final Fonction h, final Fonction i) {
        return new Fonction() {
            @Override
            public Contexte exec(Contexte c) {
                return f.exec(g.exec(h.exec(i.exec(c))));
            }
        };
    }
    public static Contexte fFind(Contexte c) {
        Collection e = c.collection;
        Fonction f = c.fonction;
        if (e != null && f != null) {
            for (Iterator iter = e.iterator(); iter.hasNext();) {
                c.entier = (Integer) iter.next();
                if (f.exec(c).booleen) {
                    return c;
                }
            }
        }
        return c;
    }
    public final static Fonction fPour = new Fonction() {
        @Override
        public Contexte exec(Contexte c) {
            Collection e = c.collection;
            Fonction f = c.fonction;
            if (e != null && f != null) {
                for (Iterator it = e.iterator(); it.hasNext();) {
                    c.entier = (Integer) it.next();
                    f.exec(c);
                }
            }
            return c;
        }
    };
    public final static Fonction fSelect = new Fonction() {
        @Override
        public Contexte exec(Contexte c) {
            Collection e = c.collection;
            Fonction p = c.fonction;
            Collection result = new ArrayList(e.size());
            if (e != null && p != null) {
                for (Iterator<Integer> iter = e.iterator(); iter.hasNext();) {
                    c.entier = iter.next();
                    if (p.exec(c).booleen) {
                        result.add(c.entier);
                    }
                }
            }
            c.result = result;
            return c;
        }
    };
    public final static Fonction fExist = new Fonction() {
        @Override
        public Contexte exec(Contexte c) {
            for (Iterator it = c.collection.iterator(); it.hasNext();) {
                c.objet = it.next();
                if (c.fonction.exec(c).booleen) {
                    c.booleen = true;
                    return c;
                }
            }
            c.booleen = false;
            return c;
        }
    };
}
