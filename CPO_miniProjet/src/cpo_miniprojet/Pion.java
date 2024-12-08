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
    private char couleur; // Couleur du pion

    // Constructeur
    public Pion(char couleur) {
        this.couleur = couleur;
    }

    // Getter pour la couleur
    public char getCouleur() {
        return couleur;
    }

    // Méthode toString pour afficher la couleur
    @Override
    public String toString() {
        return String.valueOf(couleur); // Retourne la couleur sous forme de chaîne
    }
}