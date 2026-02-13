package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWorldFlyweight {

    private final List<Enemy> enemies = new ArrayList<>();
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;

    public void generateEnemies(int count) {
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            String type = EnemySpriteFactory.getRandomType(i);
            EnemySprite sprite = EnemySpriteFactory.getSprite(type);

            int xBound = Math.max(PANEL_WIDTH - sprite.getWidth(), 1);
            int yBound = Math.max(PANEL_HEIGHT - sprite.getHeight(), 1);

            int x = rand.nextInt(xBound);
            int y = rand.nextInt(yBound);
            double speed = 1 + rand.nextDouble() * 3;

            enemies.add(new Enemy(x, y, speed, sprite));
        }
    }

    public List<Enemy> getEnemies() { return enemies; }
}
