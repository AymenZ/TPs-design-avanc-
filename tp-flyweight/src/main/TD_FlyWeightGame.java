package main;

import javax.swing.*;
import java.util.*;

public class TD_FlyWeightGame {

    private static final int TOTAL_ENEMIES = 100; // Nombre total d'ennemis à générer
    private static Map<String, Integer> enemyCount = new HashMap<>();
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║        JEU FLYWEIGHT - PARIEZ SUR LES ENNEMIS !          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Sur quel type d'ennemi pariez-vous ?");
        System.out.println("  1. ALIEN 👽");
        System.out.println("  2. ROBOT 🤖");
        System.out.println("  3. ZOMBIE 🧟");
        System.out.print("\nVotre choix (1-3) : ");
        
        int choix = 0;
        String pari = "";
        
        while (choix < 1 || choix > 3) {
            try {
                choix = scanner.nextInt();
                if (choix < 1 || choix > 3) {
                    System.out.print("Choix invalide ! Entrez 1, 2 ou 3 : ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Erreur ! Entrez un nombre (1, 2 ou 3) : ");
                scanner.next(); // Consommer l'entrée invalide
            }
        }
        
        switch (choix) {
            case 1: pari = "alien"; break;
            case 2: pari = "robot"; break;
            case 3: pari = "zombie"; break;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✨ Vous avez parié sur : " + pari.toUpperCase() + " !");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.println("Génération de " + TOTAL_ENEMIES + " ennemis aléatoires...");
        System.out.println();
        
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        
        GameWorldFlyweightRandom world = new GameWorldFlyweightRandom();
        world.generateEnemiesRandom(TOTAL_ENEMIES);
        enemyCount = world.getEnemyTypeCount();
        
        runtime.gc();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│              RÉSULTATS DE LA GÉNÉRATION                 │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("  👽 ALIEN  : " + enemyCount.getOrDefault("alien", 0) + " ennemis");
        System.out.println("  🤖 ROBOT  : " + enemyCount.getOrDefault("robot", 0) + " ennemis");
        System.out.println("  🧟 ZOMBIE : " + enemyCount.getOrDefault("zombie", 0) + " ennemis");
        System.out.println("  ─────────────────────────────────────────────────────");
        System.out.println("  📊 TOTAL  : " + TOTAL_ENEMIES + " ennemis");
        System.out.println();
        
        String typeGagnant = getTypeAvecLePlusDenemis();
        int maxCount = enemyCount.get(typeGagnant);
        
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│                  TYPE LE PLUS GÉNÉRÉ                    │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("  🏆 " + typeGagnant.toUpperCase() + " avec " + maxCount + " ennemis");
        System.out.println();
        
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        if (pari.equals(typeGagnant)) {
            System.out.println("║         🎉🎉🎉 FÉLICITATIONS ! VOUS AVEZ GAGNÉ ! 🎉🎉🎉      ║");
            System.out.println("║                                                           ║");
            System.out.println("║  Votre pari sur " + String.format("%-8s", pari.toUpperCase()) + " était le bon choix !       ║");
        } else {
            System.out.println("║            ❌ DOMMAGE ! VOUS AVEZ PERDU ! ❌              ║");
            System.out.println("║                                                           ║");
            System.out.println("║  Vous aviez parié sur " + String.format("%-8s", pari.toUpperCase()) + "                       ║");
            System.out.println("║  Mais c'est " + String.format("%-8s", typeGagnant.toUpperCase()) + " qui a gagné !                    ║");
        }
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│             INFORMATIONS FLYWEIGHT                      │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("  💾 Mémoire utilisée : " + formatMemory(memoryAfter - memoryBefore));
        System.out.println("  🎨 Sprites créés : " + EnemySpriteFactory.getSpriteCount());
        System.out.println("  ⚡ " + TOTAL_ENEMIES + " ennemis partagent " 
                           + EnemySpriteFactory.getSpriteCount() + " sprites !");
        System.out.println();
        
        System.out.println("Lancement de l'interface graphique...");
        System.out.println();
        
        String finalPari = pari;
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Jeu Flyweight - Pari : " + finalPari.toUpperCase());
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            
            GamePanelWithStats panel = new GamePanelWithStats(
                world.getEnemies(), 
                enemyCount, 
                finalPari, 
                typeGagnant
            );
            frame.add(panel);
            frame.setVisible(true);
            
            // Animation
            javax.swing.Timer timer = new javax.swing.Timer(30, e -> {
                panel.updatePositions();
                panel.repaint();
            });
            timer.start();
        });
        
        scanner.close();
    }
    

    private static String getTypeAvecLePlusDenemis() {
        String gagnant = "";
        int maxCount = 0;
        
        for (Map.Entry<String, Integer> entry : enemyCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                gagnant = entry.getKey();
            }
        }
        
        return gagnant;
    }
    

    private static String formatMemory(long bytes) {
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
}
