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

    public Partie(int tailleCombinaison, int nbToursMax, List<Character> couleursDisponibles) {
        this.tailleCombinaison = tailleCombinaison;
        this.nbToursMax = nbToursMax;
        this.couleursDisponibles = new ArrayList<>(couleursDisponibles);
        this.scanner = new Scanner(System.in);
        this.tableauTentatives = new Pion[nbToursMax][tailleCombinaison]; // Matrice vide

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
    int tour = 0;
    while (!plateau.estVictoire() && !plateau.estDefaite() && tour < nbToursMax) {
        afficherTableau(); // Afficher le tableau actuel
        
        System.out.println("\nTour " + (tour + 1) + ": Entrez votre combinaison (" + tailleCombinaison + " lettres parmi " + couleursDisponibles + ") :");
        String input = scanner.nextLine().toUpperCase();
        
        // Validation de l'entrée utilisateur
        if (input.length() != tailleCombinaison || !input.matches("[" + couleursDisponibles.stream().map(String::valueOf).collect(Collectors.joining()) + "]{" + tailleCombinaison + "}")) {
        System.out.println("Entrée invalide. Veuillez entrer exactement " + tailleCombinaison + " lettres parmi " + couleursDisponibles + ".");
        continue;
    }

        // Créer une tentative
        Pion[] pionsProposes = new Pion[tailleCombinaison];
        for (int i = 0; i < tailleCombinaison; i++) {
            pionsProposes[i] = new Pion(input.charAt(i));
            tableauTentatives[tour][i] = pionsProposes[i]; // Mettre à jour la matrice
        }
        Combinaison tentative = new Combinaison(pionsProposes);

        // Ajouter la tentative au plateau
        plateau.proposerCombinaison(tentative);

        // Conditions de victoire ou défaite
        if (plateau.estVictoire()) {
            System.out.println("Félicitations ! Vous avez deviné la combinaison secrète.");
            break;
        } else if (plateau.estDefaite()) {
            System.out.println("Désolé, vous avez épuisé toutes vos tentatives. La combinaison secrète était : "
                    + plateau.combinaisonSecrete);
            break;
        }

        tour++;
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
}