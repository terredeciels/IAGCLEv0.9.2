package gcle.fonction;

import gcle.Contexte;
import gcle.graphe.Noeud;

public abstract class Fonction extends Noeud {
    public abstract Contexte exec(Contexte c);
}
