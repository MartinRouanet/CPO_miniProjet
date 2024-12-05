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

    // Méthode principale pour tester la classe Pion
    public static void main(String[] args) {
        // Instanciation de différents objets Pion
        Pion pionRouge = new Pion('R'); // Rouge
        Pion pionBleu = new Pion('B');  // Bleu
        Pion pionVert = new Pion('V');  // Vert

        // Test des méthodes getCouleur() et toString()
        System.out.println("Couleur du pion rouge : " + pionRouge.getCouleur());
        System.out.println("Representation du pion bleu : " + pionBleu);
        System.out.println("Couleur du pion vert : " + pionVert.getCouleur());
    }
}