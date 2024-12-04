/*
 * MiniProjet_MasterMind
 * TDC - Groupe 1
 * Martin Rouanet
 * 20 Novembre 2024
 */
package cpo_miniprojet;

/**
 *
 * @author marti
 */
public class Pion {
    // Attribut représentant la couleur du pion
    private Character couleur;

    // Constructeur pour initialiser le pion avec une couleur donnée
    public Pion(Character couleur) {
        this.couleur = couleur;
    }

    // Méthode pour obtenir la couleur du pion
    public Character getCouleur() {
        return couleur;
    }

    // Redéfinition de la méthode toString pour afficher la couleur du pion
    @Override
    public String toString() {
        return couleur.toString();
    }
}