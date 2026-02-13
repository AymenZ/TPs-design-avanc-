import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class interfaceGestion extends JFrame {
    
    private ApplicationGestionTaches app;
    private JTextArea affichageTaches;
    private JTextField champDescription;
    private JTextField champAncienneDesc;
    private JTextField champNouvelleDesc;
    
    public interfaceGestion() {
        app = new ApplicationGestionTaches();
        
        setTitle("Gestion des Tâches");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Panneau d'affichage des tâches
        affichageTaches = new JTextArea(10, 40);
        affichageTaches.setEditable(false);
        affichageTaches.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(affichageTaches);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Tâches"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Panneau des actions
        JPanel panneauActions = new JPanel();
        panneauActions.setLayout(new BoxLayout(panneauActions, BoxLayout.Y_AXIS));
        panneauActions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Section Créer une tâche
        JPanel panneauCreer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panneauCreer.setBorder(BorderFactory.createTitledBorder("Créer une Tâche"));
        panneauCreer.add(new JLabel("Description:"));
        champDescription = new JTextField(20);
        panneauCreer.add(champDescription);
        JButton btnCreer = new JButton("Créer");
        btnCreer.addActionListener(e -> creerTache());
        panneauCreer.add(btnCreer);
        panneauActions.add(panneauCreer);
        
        // Section Modifier une tâche
        JPanel panneauModifier = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panneauModifier.setBorder(BorderFactory.createTitledBorder("Modifier une Tâche"));
        panneauModifier.add(new JLabel("Ancienne:"));
        champAncienneDesc = new JTextField(15);
        panneauModifier.add(champAncienneDesc);
        panneauModifier.add(new JLabel("Nouvelle:"));
        champNouvelleDesc = new JTextField(15);
        panneauModifier.add(champNouvelleDesc);
        JButton btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(e -> modifierTache());
        panneauModifier.add(btnModifier);
        panneauActions.add(panneauModifier);
        
        // Section Supprimer une tâche
        JPanel panneauSupprimer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panneauSupprimer.setBorder(BorderFactory.createTitledBorder("Supprimer une Tâche"));
        panneauSupprimer.add(new JLabel("Description:"));
        JTextField champSupprimer = new JTextField(20);
        panneauSupprimer.add(champSupprimer);
        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(e -> supprimerTache(champSupprimer.getText()));
        panneauSupprimer.add(btnSupprimer);
        panneauActions.add(panneauSupprimer);
        
        // Bouton Annuler
        JPanel panneauAnnuler = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAnnuler = new JButton("Annuler la dernière commande");
        btnAnnuler.setFont(new Font("Arial", Font.BOLD, 12));
        btnAnnuler.addActionListener(e -> annulerCommande());
        panneauAnnuler.add(btnAnnuler);
        panneauActions.add(panneauAnnuler);
        
        add(panneauActions, BorderLayout.SOUTH);
        
        actualiserAffichage();
    }
    
    private void creerTache() {
        String description = champDescription.getText().trim();
        if (!description.isEmpty()) {
            app.creerTache(description);
            champDescription.setText("");
            actualiserAffichage();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une description", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modifierTache() {
        String ancienne = champAncienneDesc.getText().trim();
        String nouvelle = champNouvelleDesc.getText().trim();
        if (!ancienne.isEmpty() && !nouvelle.isEmpty()) {
            app.modifierTache(ancienne, nouvelle);
            champAncienneDesc.setText("");
            champNouvelleDesc.setText("");
            actualiserAffichage();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez remplir les deux champs", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void supprimerTache(String description) {
        String desc = description.trim();
        if (!desc.isEmpty()) {
            app.supprimerTache(desc);
            actualiserAffichage();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une description", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void annulerCommande() {
        app.annulerDerniereCommande();
        actualiserAffichage();
    }
    
    private void actualiserAffichage() {
        affichageTaches.setText("");
        StringBuilder sb = new StringBuilder();
        sb.append("=== TÂCHES ===\n\n");
        
        int index = 1;
        for (Tache tache : app.getGestionTaches().getTaches()) {
            sb.append(index++).append(". ");
            sb.append("[ID: ").append(tache.getId()).append("] ");
            sb.append(tache.getDescription());
            if (tache.estComplete()) {
                sb.append(" [COMPLÈTE]");
            }
            sb.append("\n");
        }
        
        if (app.getGestionTaches().getTaches().isEmpty()) {
            sb.append("Aucune tâche.\n");
        }
        
        affichageTaches.setText(sb.toString());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            interfaceGestion frame = new interfaceGestion();
            frame.setVisible(true);
        });
    }
}
