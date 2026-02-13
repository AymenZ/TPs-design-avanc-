package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWorldNaive {

    private final List<EnemyNaive> enemies = new ArrayList<>();
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private static final String[] TYPES = {"alien", "robot", "zombie"};

    public void generateEnemies(int count) {
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            String type = TYPES[i % TYPES.length];
            
            int xBound = Math.max(PANEL_WIDTH - 50, 1);
            int yBound = Math.max(PANEL_HEIGHT - 50, 1);

            int x = rand.nextInt(xBound);
            int y = rand.nextInt(yBound);
            double speed = 1 + rand.nextDouble() * 3;

            enemies.add(new EnemyNaive(x, y, speed, type));
        }
    }

    public List<EnemyNaive> getEnemies() { return enemies; }
}
