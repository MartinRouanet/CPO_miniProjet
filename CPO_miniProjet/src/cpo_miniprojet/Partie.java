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
import java.util.stream.Collectors;

/**
 *
 * @author marti
 */
public class Partie {
    private PlateauDeJeu plateau; // Instance du plateau de jeu
    private ArrayList<Character> couleursDisponibles; // Liste des couleurs possibles
    private Scanner scanner; // Scanner pour entrer les tentatives
    private Pion[][] tableauTentatives; // Matrice pour stocker les 12 tentatives de 4 pions
    private int tailleCombinaison;
    private int nbToursMax;
    private char[] tentativeCourante; // Représente la combinaison actuelle
    private int tourCourant; // Indique le tour actuel

    public Partie(int tailleCombinaison, int nbToursMax, List<Character> couleursDisponibles) {
        this.tailleCombinaison = tailleCombinaison;
        this.nbToursMax = nbToursMax;
        this.couleursDisponibles = new ArrayList<>(couleursDisponibles);
        this.scanner = new Scanner(System.in);
        this.tableauTentatives = new Pion[nbToursMax][tailleCombinaison]; // Matrice vide
        this.tourCourant = 0; // Initialisation du premier tour
        this.tentativeCourante = new char[tailleCombinaison]; // Réinitialisation des tentatives courantes

        // Générer une combinaison secrète aléatoire
        Pion[] combinaisonsSecretes = new Pion[tailleCombinaison];
        for (int i = 0; i < tailleCombinaison; i++) {
            char couleurAleatoire = couleursDisponibles.get((int) (Math.random() * couleursDisponibles.size()));
            combinaisonsSecretes[i] = new Pion(couleurAleatoire);
        }
        Combinaison combinaisonSecrete = new Combinaison(combinaisonsSecretes);

        // Créer une instance de PlateauDeJeu
        this.plateau = new PlateauDeJeu(combinaisonSecrete, nbToursMax);
    }

    public void afficherTableau() {
        System.out.println("\nEtat actuel du jeu :");
        for (int i = 0; i < tableauTentatives.length; i++) {
            System.out.print("Tour " + (i + 1) + ": ");
            for (int j = 0; j < tableauTentatives[i].length; j++) {
                if (tableauTentatives[i][j] != null) {
                    System.out.print(tableauTentatives[i][j].getCouleur() + " ");
                } else {
                    System.out.print("_ "); // Place vide
                }
            }
            System.out.println();
        }
    }
     public void lancerPartie() {
        while (!plateau.estVictoire() && !plateau.estDefaite() && tourCourant < nbToursMax) {
            afficherTableau(); // Afficher le tableau actuel
            
            System.out.println("\nTour " + (tourCourant + 1) + ": Entrez votre combinaison (" + tailleCombinaison + " lettres parmi " + couleursDisponibles + ") :");
            String input = scanner.nextLine().toUpperCase();
            // Validation de l'entrée utilisateur
            if (input.length() != tailleCombinaison || !input.matches("[" + couleursDisponibles.stream().map(String::valueOf).collect(Collectors.joining()) + "]{" + tailleCombinaison + "}")) {
                System.out.println("Entree invalide. Veuillez entrer exactement " + tailleCombinaison + " lettres parmi " + couleursDisponibles + ".");
                continue;
            }
            // Créer une tentative
            Pion[] pionsProposes = new Pion[tailleCombinaison];
            for (int i = 0; i < tailleCombinaison; i++) {
                pionsProposes[i] = new Pion(input.charAt(i));
                tableauTentatives[tourCourant][i] = pionsProposes[i]; // Mettre à jour la matrice
            }
            Combinaison tentative = new Combinaison(pionsProposes);
            // Ajouter la tentative au plateau
            plateau.proposerCombinaison(tentative);
            // Conditions de victoire ou défaite
            if (plateau.estVictoire()) {
                System.out.println("Felicitations ! Vous avez devine la combinaison secrete.");
                break;
            } else if (plateau.estDefaite()) {
                System.out.println("Desole, vous avez epuise toutes vos tentatives. La combinaison secrete etait : " + plateau.combinaisonSecrete);
                break;
            }
             incrementerTour(); // Passe au tour suivant
        }
    }
      public void afficherRegles() {
        System.out.println("Regles du jeu Mastermind :");
        System.out.println("1. Devinez la combinaison secrete composee de " + tailleCombinaison + " couleurs.");
        System.out.println("2. Les couleurs possibles sont : " + couleursDisponibles);
        System.out.println("3. Vous avez " + nbToursMax + " tentatives pour trouver la bonne combinaison.");
        System.out.println("4. Apres chaque tentative, vous obtiendrez un retour indiquant combien de pions");
        System.out.println("   sont corrects et bien places ou corrects mais mal places.");
        System.out.println("Bonne chance !");
    }

    public void terminerPartie() {
        System.out.println("Merci d'avoir joue a Mastermind !");
        System.out.println("Partie terminee.");
    }
    public void setTentativeCouleur(int i, int j, char couleur) {
        if (i == tourCourant) { // Vérifie si on est dans le bon tour
            tentativeCourante[j] = couleur; // Met à jour la tentative en cours
        }
    }
    // Gestion des tours
    public int getTourCourant() {
        return tourCourant;
    }
    public void incrementerTour() {
        if (tourCourant < nbToursMax - 1) {
            tourCourant++;
            tentativeCourante = new char[tailleCombinaison]; // Réinitialiser la tentative
        } else {
            System.out.println("Tous les tours sont terminés !");
        }
    }
}