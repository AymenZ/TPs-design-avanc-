package main;

public class Enemy {

    private int x, y;
    private double speed;
    private final EnemySprite sprite;

    public Enemy(int x, int y, double speed, EnemySprite sprite) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sprite = sprite;
    }

    public void move(int panelWidth, int panelHeight) {
        x += speed;
        if (x > panelWidth - sprite.getWidth()) x = 0;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public EnemySprite getSprite() { return sprite; }
}
