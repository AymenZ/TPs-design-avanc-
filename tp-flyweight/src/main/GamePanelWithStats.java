package main;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class GamePanelWithStats extends JPanel {

    private final List<Enemy> enemies;
    private final Map<String, Integer> enemyCount;
    private final String pari;
    private final String gagnant;
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;

    public GamePanelWithStats(List<Enemy> enemies, Map<String, Integer> enemyCount, 
                              String pari, String gagnant) {
        this.enemies = enemies;
        this.enemyCount = enemyCount;
        this.pari = pari;
        this.gagnant = gagnant;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(new Color(20, 20, 40)); // Fond bleu foncé
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        for (Enemy enemy : enemies) {
            g2d.drawImage(
                enemy.getSprite().getImage(),
                enemy.getX(),
                enemy.getY(),
                this
            );
        }
        
        // Panneau de statistiques en haut
        drawStatsPanel(g2d);
        
        // Résultat du pari en bas
        drawBetResult(g2d);
    }
    

    private void drawStatsPanel(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(5, 5, 350, 120);
        
        g2d.setColor(new Color(100, 200, 255));
        g2d.drawRect(5, 5, 350, 120);
        
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.setColor(Color.YELLOW);
        g2d.drawString("📊 STATISTIQUES", 15, 25);
        
        g2d.setFont(new Font("Courier New", Font.PLAIN, 14));
        int y = 50;
        
        int alienCount = enemyCount.getOrDefault("alien", 0);
        g2d.setColor(alienCount == enemyCount.get(gagnant) ? Color.GREEN : Color.WHITE);
        g2d.drawString("👽 ALIEN  : " + String.format("%3d", alienCount), 20, y);
        y += 20;
        
        int robotCount = enemyCount.getOrDefault("robot", 0);
        g2d.setColor(robotCount == enemyCount.get(gagnant) ? Color.GREEN : Color.WHITE);
        g2d.drawString("🤖 ROBOT  : " + String.format("%3d", robotCount), 20, y);
        y += 20;
        
        int zombieCount = enemyCount.getOrDefault("zombie", 0);
        g2d.setColor(zombieCount == enemyCount.get(gagnant) ? Color.GREEN : Color.WHITE);
        g2d.drawString("🧟 ZOMBIE : " + String.format("%3d", zombieCount), 20, y);
        y += 20;
        
        g2d.setColor(Color.CYAN);
        g2d.drawString("🎨 Sprites: " + EnemySpriteFactory.getSpriteCount(), 200, 50);
    }
    

    private void drawBetResult(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(200, PANEL_HEIGHT - 80, 400, 70);
        
        boolean victoire = pari.equals(gagnant);
        
        g2d.setColor(victoire ? new Color(0, 255, 0) : new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(200, PANEL_HEIGHT - 80, 400, 70);
        
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        String message = victoire ? "🎉 VOUS AVEZ GAGNÉ ! 🎉" : "❌ VOUS AVEZ PERDU ! ❌";
        g2d.drawString(message, 240, PANEL_HEIGHT - 50);
        
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.setColor(Color.WHITE);
        String details = "Votre pari: " + pari.toUpperCase() + " | Gagnant: " + gagnant.toUpperCase();
        g2d.drawString(details, 250, PANEL_HEIGHT - 25);
    }

    public void updatePositions() {
        for (Enemy enemy : enemies) {
            enemy.move(PANEL_WIDTH, PANEL_HEIGHT);
        }
    }
}
