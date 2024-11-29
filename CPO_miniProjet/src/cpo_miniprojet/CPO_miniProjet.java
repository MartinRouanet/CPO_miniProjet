/*
 * MiniProjet_MasterMind
 * TDC - Groupe 1
 * Martin Rouanet
 * 20 Novembre 2024
 */
package cpo_miniprojet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti
 */
public class CPO_miniProjet {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
       // Liste des couleurs possibles pour les pions
        List<Character> couleursDisponibles = new ArrayList<>();
        couleursDisponibles.add('R'); // Rouge
        couleursDisponibles.add('B'); // Bleu
        couleursDisponibles.add('G'); // Vert
        couleursDisponibles.add('Y'); // Jaune

        // Paramètres du jeu : taille de la combinaison, nombre de tours max
        int tailleCombinaison = 4;
        int nbToursMax = 10;

        // Créer une instance de la classe Partie et démarrer le jeu
        Partie partie = new Partie(tailleCombinaison, nbToursMax, couleursDisponibles);

        // Afficher les règles du jeu
        partie.afficherRegles();

        // Lancer la partie
        partie.lancerPartie();
        
        // Terminer la partie et afficher le résultat
        partie.terminerPartie();
    }
}