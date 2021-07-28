package TankGame;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * The Bullet Class creates and handles movement/interactions
 * for the bullet.
 *
 * @author Alicia Ramirez
 */
public class Bullet{


    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;

    private final int R = 2;

    private Rectangle hitbox;

    private BufferedImage img;


    Bullet(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        hitbox = new Rectangle(x+7, y + 7, 10, 10);

    }



    public void update() {
        this.move();
        this.move();
        hitbox = new Rectangle(x+7, y + 7, 10, 10);
    }


    private void move() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    void drawImage(Graphics g, int tankX, int tankY, String playerNum) {
        AffineTransform rotation = null;
        if(playerNum.equals("Player 1")) {
            rotation = AffineTransform.getTranslateInstance((1280/2)+(x - (tankX)), (720/2)+(y - (tankY)));
        }
        if(rotation != null) {
            rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.img, rotation, null);
        }
    }



}
