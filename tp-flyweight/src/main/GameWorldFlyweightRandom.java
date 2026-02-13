package main;

import java.util.*;

public class GameWorldFlyweightRandom {

    private final List<Enemy> enemies = new ArrayList<>();
    private final Map<String, Integer> enemyTypeCount = new HashMap<>();
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private static final String[] TYPES = {"alien", "robot", "zombie"};

    public void generateEnemiesRandom(int count) {
        Random rand = new Random();
        
        for (String type : TYPES) {
            enemyTypeCount.put(type, 0);
        }

        for (int i = 0; i < count; i++) {
            String type = TYPES[rand.nextInt(TYPES.length)];
            
            enemyTypeCount.put(type, enemyTypeCount.get(type) + 1);
            
            EnemySprite sprite = EnemySpriteFactory.getSprite(type);

            int xBound = Math.max(PANEL_WIDTH - sprite.getWidth(), 1);
            int yBound = Math.max(PANEL_HEIGHT - sprite.getHeight(), 1);

            int x = rand.nextInt(xBound);
            int y = rand.nextInt(yBound);
            double speed = 1 + rand.nextDouble() * 3;

            enemies.add(new Enemy(x, y, speed, sprite));
        }
    }

    public List<Enemy> getEnemies() { 
        return enemies; 
    }
    
    public Map<String, Integer> getEnemyTypeCount() {
        return enemyTypeCount;
    }
}
