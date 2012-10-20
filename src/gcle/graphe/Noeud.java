package gcle.graphe;

import gcle.Contexte;

public abstract class Noeud {
    public Contexte c;
 public String getId() {
        return this.getClass().getSimpleName();
       
    }
}
