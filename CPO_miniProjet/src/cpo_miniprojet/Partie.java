/*
 * MiniProjet_MasterMind
 * TDC - Groupe 1
 * Martin Rouanet
 * 20 Novembre 2024
 */
package cpo_miniprojet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author marti
 */
public class Partie {
    private PlateauDeJeu plateau; // Instance du plateau de jeu
    private ArrayList<Character> couleursDisponibles; // Liste des couleurs possibles
    private Scanner scanner; // Scanner pour entrer les tentatives

    // Constructeur pour initialiser la partie avec des paramètres personnalisés
    public Partie(int tailleCombinaison, int nbToursMax, List<Character> couleursDisponibles) {
        this.couleursDisponibles = new ArrayList<>(couleursDisponibles);
        this.scanner = new Scanner(System.in);
        
        // Générer une combinaison secrète aléatoire
        Pion[] combinaisonsSecretes = new Pion[tailleCombinaison];
        for (int i = 0; i < tailleCombinaison; i++) {
            char couleurAleatoire = couleursDisponibles.get((int) (Math.random() * couleursDisponibles.size()));
            combinaisonsSecretes[i] = new Pion(couleurAleatoire);
        }
        Combinaison combinaisonSecrete = new Combinaison(combinaisonsSecretes);
        
        // Créer une instance de PlateauDeJeu avec la combinaison secrète et le nombre de tours
        this.plateau = new PlateauDeJeu(combinaisonSecrete, nbToursMax);
    }

    // Affiche les règles du jeu et les instructions
    public void afficherRegles() {
        System.out.println("Bienvenue dans MasterMind !");
        System.out.println("Voici les regles :");
        System.out.println("1. Vous devez deviner la combinaison secrete de 4 pions.");
        System.out.println("2. A chaque tentative, vous recevrez des indices.");
        System.out.println("3. Un indice contient deux informations :");
        System.out.println("   - Le nombre de pions bien places (noirs).");
        System.out.println("   - Le nombre de pions corrects mais mal places (blancs).");
        System.out.println("4. Vous avez un nombre limite de tentatives pour trouver la combinaison.");
        System.out.println("Bonne chance !");
    }

    // Méthode principale du jeu où le joueur entre ses tentatives
    public void lancerPartie() {
        int tour = 1;
        while (!plateau.estVictoire() && !plateau.estDefaite()) {
            // Afficher l'état actuel du plateau
            plateau.afficherPlateau();
            
            // Demander à l'utilisateur de proposer une combinaison
            System.out.println("\nTour " + tour + ": Entrez votre combinaison (4 lettres parmi R, B, G, Y) :");
            String input = scanner.nextLine().toUpperCase();
            
            // Vérifier que l'entrée est valide
            if (input.length() != 4 || !input.matches("[RGBY]{4}")) {
                System.out.println("Entree invalide. Veuillez entrer exactement 4 lettres parmi R, B, G, Y.");
                continue;
            }

            // Créer une nouvelle combinaison à partir de l'entrée de l'utilisateur
            Pion[] pionsProposes = new Pion[4];
            for (int i = 0; i < 4; i++) {
                pionsProposes[i] = new Pion(input.charAt(i));
            }
            Combinaison tentative = new Combinaison(pionsProposes);
            
            // Ajouter la tentative et obtenir les indices
            plateau.proposerCombinaison(tentative);
            
            // Vérifier les conditions de victoire ou de défaite
            if (plateau.estVictoire()) {
                System.out.println("Felicitations ! Vous avez devine la combinaison secrete.");
                break;
            } else if (plateau.estDefaite()) {
                System.out.println("Desole, vous avez épuise toutes vos tentatives. La combinaison secrete etait : "
                        + plateau.combinaisonSecrete);
                break;
            }
            
            tour++;
        }
    }

    // Méthode pour terminer la partie
    public void terminerPartie() {
        if (plateau.estVictoire()) {
            System.out.println("Vous avez gagne la partie !");
        } else if (plateau.estDefaite()) {
            System.out.println("La partie est perdue !");
        }
    }
}