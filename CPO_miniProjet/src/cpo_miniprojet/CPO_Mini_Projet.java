/*
 * MiniProjet_MasterMind
 * TDC - Groupe 1
 * Martin Rouanet
 * 20 Novembre 2024
 */
package cpo_miniprojet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author martin
 */
public class CPO_Mini_Projet extends javax.swing.JFrame {
    private Partie partie; // Référence à la classe Partie
    private JButton[][] boutons; // Matrice de boutons pour représenter le plateau graphique
    private int nbToursMax = 12;
    private int tailleCombinaison = 4;
    private int tourCourant = 0; // Le tour actuel

    public CPO_Mini_Projet() {
        ArrayList<Character> couleursDisponibles = new ArrayList<>();
        couleursDisponibles.add('R');
        couleursDisponibles.add('B');
        couleursDisponibles.add('G');
        couleursDisponibles.add('Y');

        this.partie = new Partie(tailleCombinaison, nbToursMax, couleursDisponibles);
        initComponents();
        initialiserPlateauGraphique();
    }

    private void initialiserPlateauGraphique() {
        boutons = new JButton[nbToursMax][tailleCombinaison + 2];
        PlateauDeJeu.setLayout(new GridLayout(nbToursMax, tailleCombinaison + 3)); // Ajouter une colonne pour le bouton "Valider"

        for (int i = 0; i < nbToursMax; i++) {
            for (int j = 0; j < tailleCombinaison; j++) {
                boutons[i][j] = new JButton();
                boutons[i][j].setEnabled(i == 0); // Activer uniquement la première ligne
                boutons[i][j].setText("");
                int ligne = i; // Variable locale pour capturer la valeur de `i`
                int colonne = j;

                boutons[i][j].addActionListener(e -> {
                    String texte = JOptionPane.showInputDialog(this, 
                            "Entrez le nom du pion (R, B, G, Y) :", 
                             "Nom du Pion", 
                            JOptionPane.PLAIN_MESSAGE);

                    if (texte != null && !texte.isEmpty()) {
                        texte = texte.toUpperCase();
                        if (texte.matches("[RBGY]")) {
                            boutons[ligne][colonne].setText(texte);
                            boutons[ligne][colonne].setForeground(Color.BLACK);
                        } else {
                            JOptionPane.showMessageDialog(this, 
                                    "Entrée invalide. Seules les lettres R, B, G, Y sont autorisées.", 
                                    "Erreur", 
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                PlateauDeJeu.add(boutons[i][j]);
            }

            // Ajouter un bouton "Valider" pour chaque ligne
            JButton boutonValider = new JButton("Valider");
            boutonValider.setEnabled(i == 0); // Activer uniquement pour la première ligne
            int ligne = i; // Variable locale pour capturer la valeur de `i`

            boutonValider.addActionListener(e -> {
                if (ligne == tourCourant && verifierLigneComplete(ligne)) {
                    incrementerTour(); // Passer au tour suivant
                } else {
                    JOptionPane.showMessageDialog(this, 
                            "Veuillez remplir tous les boutons avant de valider.", 
                            "Erreur", 
                            JOptionPane.WARNING_MESSAGE);
                }
            });

            PlateauDeJeu.add(boutonValider);

            // Ajouter les deux boutons supplémentaires à la fin de chaque ligne
            boutons[i][tailleCombinaison] = new JButton();
            boutons[i][tailleCombinaison].setEnabled(i == 0); // Activer uniquement la première ligne
            boutons[i][tailleCombinaison].setBackground(Color.WHITE);
            PlateauDeJeu.add(boutons[i][tailleCombinaison]);

            boutons[i][tailleCombinaison + 1] = new JButton();
            boutons[i][tailleCombinaison + 1].setEnabled(i == 0); // Activer uniquement la première ligne
            boutons[i][tailleCombinaison + 1].setBackground(Color.BLACK);
            boutons[i][tailleCombinaison + 1].setForeground(Color.WHITE);
            PlateauDeJeu.add(boutons[i][tailleCombinaison + 1]);
        }
    }

    private boolean verifierLigneComplete(int ligne) {
        for (int j = 0; j < tailleCombinaison; j++) {
            if (boutons[ligne][j].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void incrementerTour() {
        if (tourCourant < nbToursMax - 1) {
            // Désactiver la ligne actuelle
            for (int j = 0; j < tailleCombinaison; j++) {
                boutons[tourCourant][j].setEnabled(false);
            }

            // Activer la ligne suivante
            tourCourant++;
            for (int j = 0; j < tailleCombinaison; j++) {
                boutons[tourCourant][j].setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                    "Tous les tours sont terminés !", 
                    "Fin de Partie", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Color getColorFromChar(char colorChar) {
        switch (colorChar) {
            case 'R': return Color.RED;
            case 'B': return Color.BLUE;
            case 'G': return Color.GREEN;
            case 'Y': return Color.YELLOW;
            default: return Color.WHITE; // Couleur par défaut
        }
    }
    
    private void handleButtonClick(int i, int j) {
        // Changer la couleur du bouton en fonction du choix du joueur
        char[] couleurs = {'R', 'B', 'G', 'Y'};
        char couleurActuelle = ' '; // Défaut
    
        // Récupérer la couleur actuelle du bouton
        if (boutons[i][j].getBackground() == Color.RED) couleurActuelle = 'R';
        else if (boutons[i][j].getBackground() == Color.BLUE) couleurActuelle = 'B';
        else if (boutons[i][j].getBackground() == Color.GREEN) couleurActuelle = 'G';
        else if (boutons[i][j].getBackground() == Color.YELLOW) couleurActuelle = 'Y';
    
        // Déterminer la couleur suivante
        int index = (new String(couleurs).indexOf(couleurActuelle) + 1) % couleurs.length;
        char nouvelleCouleur = couleurs[index];
        boutons[i][j].setBackground(getColorFromChar(nouvelleCouleur)); // Appliquer la nouvelle couleur
    
        // Mettre à jour la tentative actuelle dans la classe Partie
        partie.setTentativeCouleur(i, j, nouvelleCouleur); // Méthode à implémenter dans Partie
    }
    
    /**
     * Initialise les composants générés automatiquement par l'IDE.
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PlateauDeJeu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PlateauDeJeu.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout PlateauDeJeuLayout = new javax.swing.GroupLayout(PlateauDeJeu);
        PlateauDeJeu.setLayout(PlateauDeJeuLayout);
        PlateauDeJeuLayout.setHorizontalGroup(
            PlateauDeJeuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        PlateauDeJeuLayout.setVerticalGroup(
            PlateauDeJeuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(PlateauDeJeu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 400, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CPO_Mini_Projet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CPO_Mini_Projet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CPO_Mini_Projet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CPO_Mini_Projet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CPO_Mini_Projet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PlateauDeJeu;
    // End of variables declaration//GEN-END:variables
}