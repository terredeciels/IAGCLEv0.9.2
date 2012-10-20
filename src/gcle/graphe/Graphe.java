package gcle.graphe;

import com.google.common.collect.Sets;
import java.util.HashSet;

public class Graphe<N extends Noeud> {

    private N noeudRacine;
    private final HashSet<Graphe<N>> enfants = Sets.newHashSet();
    public Graphe(N noeud) {
        this.noeudRacine = noeud;
    }
    public HashSet<Graphe<N>> getEnfants() {
        return enfants;
    }
    public void ajouterEnfant(Graphe<N> g) {
        enfants.add(g);
    }
    public void ajouterEnfant(N noeud) {
        Graphe<N> g = new Graphe<N>(noeud);
        enfants.add(g);
    }
    public N getNoeudRacine() {
        return noeudRacine;
    }
    public int compte() {
        return enfants.size();
    }
    @Override
    public String toString() {
        String temp = noeudRacine.getId() + "->[";
        for (Graphe<N> enfant : enfants) {
            temp += enfant.toString() + ",";
        }
        temp += "]";
        return temp;
    }
}