/*
 * MiniProjet_MasterMind
 * TDC - Groupe 1
 * Martin Rouanet
 * 20 Novembre 2024
 */
package cpo_miniprojet;
import java.awt.Color;
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


    /**
     * Creates new form CPO_Mini_Projet
     */
public CPO_Mini_Projet() {
        // Initialiser les couleurs disponibles
        ArrayList<Character> couleursDisponibles = new ArrayList<>();
        couleursDisponibles.add('R');
        couleursDisponibles.add('B');
        couleursDisponibles.add('G');
        couleursDisponibles.add('Y');

        // Créer une instance de Partie
        this.partie = new Partie(tailleCombinaison, nbToursMax, couleursDisponibles);

        // Initialiser les composants
        initComponents();
        
        // Initialisation du plateau graphique
        initialiserPlateauGraphique();

}

private void initialiserPlateauGraphique() {
    // Créer une matrice pour contenir les boutons
    boutons = new JButton[nbToursMax][tailleCombinaison + 2];
    
    // Configurer le layout du plateau
    PlateauDeJeu.setLayout(new GridLayout(nbToursMax, tailleCombinaison + 2));

    for (int i = 0; i < nbToursMax; i++) {
        for (int j = 0; j < tailleCombinaison; j++) {
            // Initialiser un bouton activé pour permettre l'entrée de texte
            boutons[i][j] = new JButton();
            boutons[i][j].setEnabled(true); // Activer le bouton
            boutons[i][j].setText(""); // Initialiser avec du texte vide

            // Capturer les indices pour l'utilisation dans la lambda
            int ligne = i;
            int colonne = j;

            // Ajouter un ActionListener pour chaque bouton
            boutons[i][j].addActionListener(e -> {
                String texte = JOptionPane.showInputDialog(this, 
                        "Entrez le nom du pion (R, B, G, Y) :", 
                        "Nom du Pion", 
                        JOptionPane.PLAIN_MESSAGE);

                if (texte != null && !texte.isEmpty()) {
                    // Valider l'entrée et mettre à jour le texte du bouton
                    texte = texte.toUpperCase(); // Convertir en majuscule
                    if (texte.matches("[RBGY]")) {
                        boutons[ligne][colonne].setText(texte);
                        boutons[ligne][colonne].setForeground(Color.BLACK); // Couleur du texte
                    } else {
                        JOptionPane.showMessageDialog(this, 
                                "Entrée invalide. Seules les lettres R, B, G, Y sont autorisées.", 
                                "Erreur", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            
            // Ajouter le bouton au plateau
            PlateauDeJeu.add(boutons[i][j]);
        }

        // Ajouter les deux boutons supplémentaires à la fin de chaque ligne
        // Premier bouton : fond blanc
        boutons[i][tailleCombinaison] = new JButton();
        boutons[i][tailleCombinaison].setEnabled(true);
        boutons[i][tailleCombinaison].setBackground(Color.WHITE);
        PlateauDeJeu.add(boutons[i][tailleCombinaison]);

        // Deuxième bouton : fond noir, texte blanc
        boutons[i][tailleCombinaison + 1] = new JButton();
        boutons[i][tailleCombinaison + 1].setEnabled(true);
        boutons[i][tailleCombinaison + 1].setBackground(Color.BLACK);
        boutons[i][tailleCombinaison + 1].setForeground(Color.WHITE);
        PlateauDeJeu.add(boutons[i][tailleCombinaison + 1]);
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
