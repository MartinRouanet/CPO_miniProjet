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
    // Création d'une combinaison secrète fixe pour tester
        Pion[] secretPions = {new Pion('R'), new Pion('B'), new Pion('G'), new Pion('Y')};
        Combinaison combinaisonSecrete = new Combinaison(secretPions);

        // Initialisation du plateau avec un maximum de 5 tours
        PlateauDeJeu plateau = new PlateauDeJeu(combinaisonSecrete, 5);

        // Ajout de plusieurs tentatives
        Pion[] tentative1 = {new Pion('R'), new Pion('B'), new Pion('G'), new Pion('Y')}; // Bonne combinaison
        Pion[] tentative2 = {new Pion('B'), new Pion('R'), new Pion('Y'), new Pion('G')}; // Combinaison incorrecte
        Pion[] tentative3 = {new Pion('R'), new Pion('G'), new Pion('B'), new Pion('Y')}; // Combinaison partiellement correcte

        plateau.proposerCombinaison(new Combinaison(tentative1));
        plateau.proposerCombinaison(new Combinaison(tentative2));
        plateau.proposerCombinaison(new Combinaison(tentative3));

        // Affichage de l'état du plateau
        plateau.afficherPlateau();

        // Vérification des conditions de victoire et défaite
        if (plateau.estVictoire()) {
            System.out.println("Victoire ! Vous avez devine la combinaison secrete.");
        } else if (plateau.estDefaite()) {
            System.out.println("Defaite ! Vous avez epuise toutes vos tentatives.");
        } else {
            System.out.println("La partie continue...");
        }
    }
}