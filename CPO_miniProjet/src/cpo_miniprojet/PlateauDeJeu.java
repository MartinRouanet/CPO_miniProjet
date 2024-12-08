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

    // Afficher les résultats immédiatement après la tentative
    System.out.println("Tentative : " + tentative);
    System.out.println("Resultat : " + reponse);
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