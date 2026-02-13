package main;

import javax.swing.*;

public class TD_FlyWeight {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); 
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("=== Patron Flyweight - Jeu d'Ennemis ===");
        System.out.println("Mémoire utilisée AVANT génération: " + formatMemory(memoryBefore));
        
        GameWorldFlyweight world = new GameWorldFlyweight();
        world.generateEnemies(50);
        
        runtime.gc();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Mémoire utilisée APRÈS génération: " + formatMemory(memoryAfter));
        System.out.println("Différence de mémoire: " + formatMemory(memoryAfter - memoryBefore));
        System.out.println("Nombre de sprites réellement créés: " + EnemySpriteFactory.getSpriteCount());
        System.out.println("Nombre total d'ennemis: " + world.getEnemies().size());
        System.out.println("Ratio: " + world.getEnemies().size() + " ennemis partagent " 
                           + EnemySpriteFactory.getSpriteCount() + " sprites");
        System.out.println("========================================");
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Jeu Flyweight");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null); // Centrer la fenêtre
            
            GamePanel panel = new GamePanel(world.getEnemies());
            frame.add(panel);
            frame.setVisible(true);
            
            Timer timer = new Timer(30, e -> {
                panel.updatePositions();
                panel.repaint();
            });
            timer.start();
            
            System.out.println("Fenêtre de jeu lancée avec succès !");
        });
    }

    private static String formatMemory(long bytes) {
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
}
