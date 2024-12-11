/*
 * MiniProjet_MasterMind
 * TDC - Groupe 1
 * Martin Rouanet
 * 20 Novembre 2024
 */
package cpo_miniprojet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author marti
 */
public class Combinaison {
    private Pion[] elements; // Tableau de Pions représentant la combinaison

    // Constructeur
    public Combinaison(Pion[] elements) {
        this.elements = elements;
    }

    // Méthode pour comparer une tentative avec la combinaison secrète
    public int[] comparer(Combinaison autre) {
        int noirs = 0; // Nombre de pions bien placés
        int blancs = 0; // Nombre de pions bien placés mais mal positionnés

        Pion[] autreElements = autre.getElements();
        Map<Character, Integer> combinaisonMap = new HashMap<>();

        // Calculer le nombre de pions bien placés
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].getCouleur() == autreElements[i].getCouleur()) {
                noirs++;
            } else {
                combinaisonMap.put(elements[i].getCouleur(), combinaisonMap.getOrDefault(elements[i].getCouleur(), 0) + 1);
            }
        }

        // Calculer le nombre de pions bien placés mais mal positionnés
        for (int i = 0; i < autreElements.length; i++) {
            if (combinaisonMap.containsKey(autreElements[i].getCouleur()) && combinaisonMap.get(autreElements[i].getCouleur()) > 0) {
                blancs++;
                combinaisonMap.put(autreElements[i].getCouleur(), combinaisonMap.get(autreElements[i].getCouleur()) - 1);
            }
        }

        return new int[] { noirs, blancs };
    }

    // Getter pour obtenir les éléments de la combinaison
    public Pion[] getElements() {
        return elements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Pion p : elements) {
            sb.append(p.getCouleur()).append(" ");
        }
        return sb.toString().trim();
    }
}