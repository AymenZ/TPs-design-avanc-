package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EnemySpriteFactory {

    private static final Map<String, EnemySprite> sprites = new HashMap<>();
    private static final String[] TYPES = {"alien", "robot", "zombie"};

    public static EnemySprite getSprite(String type) {
        if (sprites.containsKey(type)) return sprites.get(type);

        try (InputStream is = EnemySpriteFactory.class.getResourceAsStream("/" + type + ".png")) {
            if (is == null) throw new RuntimeException("Fichier " + type + ".png introuvable !");
            BufferedImage img = ImageIO.read(is);
            EnemySprite sprite = new EnemySprite(type, img);
            sprites.put(type, sprite);
            return sprite;
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger l'image : " + type, e);
        }
    }

    public static String getRandomType(int i) {
        return TYPES[i % TYPES.length];
    }

    public static int getSpriteCount() {
        return sprites.size();
    }
}
