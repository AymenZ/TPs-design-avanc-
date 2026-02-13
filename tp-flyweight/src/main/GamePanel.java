package main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {

    private final List<Enemy> enemies;
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;

    public GamePanel(List<Enemy> enemies) {
        this.enemies = enemies;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Dessiner tous les ennemis
        for (Enemy enemy : enemies) {
            g.drawImage(
                enemy.getSprite().getImage(),
                enemy.getX(),
                enemy.getY(),
                this
            );
        }
        
        // Afficher le nombre d'ennemis et de sprites
        g.setColor(Color.WHITE);
        g.drawString("Ennemis: " + enemies.size(), 10, 20);
        g.drawString("Sprites créés: " + EnemySpriteFactory.getSpriteCount(), 10, 40);
    }

    public void updatePositions() {
        for (Enemy enemy : enemies) {
            enemy.move(PANEL_WIDTH, PANEL_HEIGHT);
        }
    }
}
