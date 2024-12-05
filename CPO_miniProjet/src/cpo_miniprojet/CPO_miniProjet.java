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
        // TODO code application logic here
                // Liste des couleurs disponibles pour la combinaison
        ArrayList<Character> couleursDisponibles = new ArrayList<>();
        couleursDisponibles.add('R'); // Rouge
        couleursDisponibles.add('B'); // Bleu
        couleursDisponibles.add('G'); // Vert
        couleursDisponibles.add('Y'); // Jaune

        // Initialisation d'une partie avec une combinaison de taille 4 et 5 tours maximum
        Partie partie = new Partie(4, 12, couleursDisponibles);
        
        // Afficher les règles du jeu
        partie.afficherRegles();
        
        // Lancer la partie
        partie.lancerPartie();
        
        // Terminer la partie
        partie.terminerPartie();
    }
}