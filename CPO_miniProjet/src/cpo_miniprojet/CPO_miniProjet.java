/*
 * MiniProjet_MasterMind
 * TDC - Groupe 1
 * Martin Rouanet
 * 20 Novembre 2024
 */
package cpo_miniprojet;

import java.util.ArrayList;

/**
 *
 * @author marti
 */
public class CPO_miniProjet {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // Création d'une liste de couleurs disponibles pour Combinaison
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