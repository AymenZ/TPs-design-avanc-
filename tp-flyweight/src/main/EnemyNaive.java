package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class EnemyNaive {

    private int x, y;
    private double speed;
    private final BufferedImage image; 
    private final int width;
    private final int height;

    public EnemyNaive(int x, int y, double speed, String type) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        
        try (InputStream is = getClass().getResourceAsStream("/" + type + ".png")) {
            if (is == null) throw new RuntimeException("Fichier " + type + ".png introuvable !");
            this.image = ImageIO.read(is);
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger l'image : " + type, e);
        }
    }

    public void move(int panelWidth, int panelHeight) {
        x += speed;
        if (x > panelWidth - width) x = 0;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public BufferedImage getImage() { return image; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
