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
        int blancs = 0; // Pions mal placés

        boolean[] marquesSecretes = new boolean[taille]; // Pour marquer les pions déjà utilisés dans la combinaison secrète
        boolean[] marquesProposees = new boolean[taille]; // Pour marquer les pions déjà utilisés dans la combinaison proposée

        // Étape 1 : Calcul des pions noirs (corrects et bien placés)
        for (int i = 0; i < taille; i++) {
            if (this.elements[i].getCouleur().equals(autre.elements[i].getCouleur())) {
                noirs++;
                marquesSecretes[i] = true;
                marquesProposees[i] = true;
            }
        }

        // Étape 2 : Calcul des pions blancs (corrects mais mal placés)
        for (int i = 0; i < taille; i++) {
            if (!marquesProposees[i]) { // Si le pion n'est pas déjà bien placé
                for (int j = 0; j < taille; j++) {
                    if (!marquesSecretes[j] // Si le pion dans la combinaison secrète n'a pas encore été utilisé
                            && this.elements[j].getCouleur().equals(autre.elements[i].getCouleur())) {
                        blancs++;
                        marquesSecretes[j] = true;
                        break;
                    }
                }
            }
        }

        return new int[]{noirs, blancs};
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

    // Méthode principale pour tester la classe Combinaison
    public static void main(String[] args) {
        // Création d'une liste de couleurs disponibles
        ArrayList<Character> couleurs = new ArrayList<>();
        couleurs.add('R'); // Rouge
        couleurs.add('B'); // Bleu
        couleurs.add('G'); // Vert
        couleurs.add('Y'); // Jaune

        // Génération d'une combinaison aléatoire
        Combinaison combinaisonSecrete = Combinaison.genererAleatoire(4, couleurs);
        System.out.println("Combinaison secrete : " + combinaisonSecrete);

        // Création d'une combinaison proposée par le joueur
        Pion[] proposition = {new Pion('R'), new Pion('B'), new Pion('G'), new Pion('Y')};
        Combinaison combinaisonProposee = new Combinaison(proposition);
        System.out.println("Combinaison proposee : " + combinaisonProposee);

        // Comparaison des combinaisons
        int[] resultats = combinaisonSecrete.comparer(combinaisonProposee);
        System.out.println("Pions noirs (bien places) : " + resultats[0]);
        System.out.println("Pions blancs (mal places) : " + resultats[1]);
    }
}