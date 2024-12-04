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
        System.out.println("\nÉtat actuel du jeu :");
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
            
            System.out.println("\nTour " + (tour + 1) + ": Entrez votre combinaison (4 lettres parmi R, B, G, Y) :");
            String input = scanner.nextLine().toUpperCase();
            
            // Vérification d'entrée
            if (input.length() != tailleCombinaison || !input.matches("[RGBY]{" + tailleCombinaison + "}")) {
                System.out.println("Entree invalide. Veuillez entrer exactement " + tailleCombinaison + " lettres parmi R, B, G, Y.");
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

    void afficherRegles() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void terminerPartie() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Pion[][] getTableauTentatives() {
    return tableauTentatives;
    }
}