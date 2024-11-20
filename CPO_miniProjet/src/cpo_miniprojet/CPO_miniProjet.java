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
public class CPO_miniProjet {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // Instanciation de différents objets Pion
        Pion pionRouge = new Pion('R'); // Rouge
        Pion pionBleu = new Pion('B');  // Bleu
        Pion pionVert = new Pion('V');  // Vert

        // Test des méthodes getCouleur() et toString()
        System.out.println("Representation du pion rouge : " + pionRouge.getCouleur());
        System.out.println("Representation du pion bleu : " + pionBleu);
        System.out.println("Representation du pion vert : " + pionVert.getCouleur());
    }
}