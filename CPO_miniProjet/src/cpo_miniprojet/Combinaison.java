/*
 * MiniProjet_MasterMind
 * TDC - Groupe 1
 * Martin Rouanet
 * 20 Novembre 2024
 */
package cpo_miniprojet;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author marti
 */
public class Combinaison {
    private Pion[] elements; // Tableau de pions représentant la combinaison
    int taille;      // Taille de la combinaison (par défaut 4)

    // Constructeur pour initialiser la combinaison à partir d'un tableau de pions
    public Combinaison(Pion[] elements) {
        this.elements = elements;
        this.taille = elements.length;
    }

    // Méthode pour générer une combinaison aléatoire
    public static Combinaison genererAleatoire(int taille, ArrayList<Character> couleursDisponibles) {
        Random random = new Random();
        Pion[] pions = new Pion[taille];
        for (int i = 0; i < taille; i++) {
            // Sélection aléatoire d'une couleur dans la liste disponible
            char couleurAleatoire = couleursDisponibles.get(random.nextInt(couleursDisponibles.size()));
            pions[i] = new Pion(couleurAleatoire);
        }
        return new Combinaison(pions);
    }

    // Méthode pour comparer deux combinaisons et calculer les indices (noirs et blancs)
    public int[] comparer(Combinaison autre) {
        int noirs = 0; // Pions bien placés
        int blancs = 0; // Pions corrects mais mal placés

        boolean[] marquesSecretes = new boolean[taille];
        boolean[] marquesProposees = new boolean[taille];

        // Étape 1 : Calcul des noirs (bien placés)
        for (int i = 0; i < taille; i++) {
            if (this.elements[i].getCouleur() == autre.elements[i].getCouleur()) { // Comparaison avec '=='
                noirs++;
                marquesSecretes[i] = true;
                marquesProposees[i] = true;
            }
        }

        // Étape 2 : Calcul des blancs (mal placés)
        for (int i = 0; i < taille; i++) {
            if (!marquesProposees[i]) { // Si ce pion proposé n'est pas déjà bien placé
                for (int j = 0; j < taille; j++) {
                    if (!marquesSecretes[j] && // Si ce pion secret n'est pas déjà utilisé
                        this.elements[j].getCouleur() == autre.elements[i].getCouleur()) { // Comparaison avec '=='
                        blancs++;
                        marquesSecretes[j] = true; // Marque ce pion secret comme utilisé
                        break; // Sort de la boucle pour éviter de réutiliser le même pion
                    }
                }
            }
        }

        return new int[]{noirs, blancs};
    }

    // Méthode pour obtenir les couleurs des pions sous forme de tableau de char
    public char[] getCouleurs() {
        char[] couleurs = new char[taille];
        for (int i = 0; i < taille; i++) {
            couleurs[i] = this.elements[i].getCouleur();  // Récupère la couleur de chaque pion
        }
        return couleurs;
    }

    // Méthode toString pour afficher les couleurs des pions
    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder();
        for (Pion pion : elements) {
            resultat.append(pion.toString());
        }
        return resultat.toString();
    }
}