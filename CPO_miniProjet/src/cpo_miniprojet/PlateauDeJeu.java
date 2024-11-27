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
public class PlateauDeJeu {
    Combinaison combinaisonSecrete; // La combinaison à deviner
    private ArrayList<Combinaison> tentatives; // Historique des combinaisons proposées
    private ArrayList<String> reponses; // Liste des réponses pour chaque tentative
    private int nbToursMax; // Nombre maximum de tours autorisés
    private int taille;

    // Constructeur
    public PlateauDeJeu(Combinaison combinaisonSecrete, int nbToursMax) {
        this.combinaisonSecrete = combinaisonSecrete;
        this.tentatives = new ArrayList<>();
        this.reponses = new ArrayList<>();
        this.nbToursMax = nbToursMax;
    }

    // Méthode pour ajouter une tentative et calculer les indices
    public void proposerCombinaison(Combinaison tentative) {
        tentatives.add(tentative);

        // Comparaison avec la combinaison secrète
        int[] resultats = combinaisonSecrete.comparer(tentative);
        String reponse = resultats[0] + " noirs, " + resultats[1] + " blancs";
        reponses.add(reponse);
    }

    // Méthode pour afficher l'état actuel du plateau
    public void afficherPlateau() {
        System.out.println("Plateau de jeu :");
        for (int i = 0; i < tentatives.size(); i++) {
            System.out.println("Tentative " + (i + 1) + " : " + tentatives.get(i) + " -> " + reponses.get(i));
        }
    }
    // Vérifie si la dernière tentative correspond à la combinaison secrète


    // Vérifie si le joueur a épuisé tous ses tours
    public boolean estDefaite() {
        return tentatives.size() >= nbToursMax && !estVictoire();
    }

    // Méthode principale pour tester la classe PlateauDeJeu
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
            System.out.println("Defaite ! Vous avez Epuise toutes vos tentatives.");
        } else {
            System.out.println("La partie continue...");
        }
    }

    public boolean estVictoire() {
    // Vérifie si la dernière tentative est identique à la combinaison secrète
    if (tentatives.isEmpty()) {
        return false;
    }
    Combinaison derniereTentative = tentatives.get(tentatives.size() - 1);
    int[] resultats = combinaisonSecrete.comparer(derniereTentative);
    
    // Si tous les pions sont bien placés (noirs == taille de la combinaison), la victoire est atteinte
    return resultats[0] == taille;
}
}