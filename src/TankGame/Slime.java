package TankGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The Slime class will create a Slime enemy that will chase
 * the player within a certain range. Is animating at all times.
 *
 * @author Alicia Ramirez
 */
public class Slime {

    private int x;
    private int y;
    private int oldx;
    private int oldy;

    private Rectangle hitbox;

    private BufferedImage Slime00;
    private BufferedImage Slime01;
    private BufferedImage Slime02;
    private BufferedImage Slime03;
    private BufferedImage[] SlimeSpriteSheet;
    private int animationCounter;
    private int animationTimer;

    Slime(int x, int y) {
        this.x = x;
        this.y = y;
        this.oldx = x;
        this.oldy = y;
        hitbox = new Rectangle(x+4, y+13, 28, 16);

        animationCounter = 0;
        animationTimer = 0;

        try {
            Slime00 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Slime00.png"));
            Slime01 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Slime01.png"));
            Slime02 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Slime02.png"));
            Slime03 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Slime03.png"));
            SlimeSpriteSheet = new BufferedImage[]{Slime00, Slime01, Slime02, Slime03};
        } catch(IOException e) {

        }
    }

    public void update(int tankX, int tankY) {
        this.oldx = x;
        this.oldy = y;
        this.move(tankX, tankY);

        hitbox = new Rectangle(x+4, y+13, 28, 16);
    }

    public void wallHit() {
        this.x = oldx;
        this.y = oldy;
    }

    public void bulletHit() {
        x = 0;
        y = 0;
    }

    // Will move towards the player within a certain range.
    public void move(int tankX, int tankY) {
        int tempy = 0;
        int tempx = 0;
        if(Math.abs(y-tankY) < 200 && y-50 < tankY) {
            tempy++;
        }
        else if(Math.abs(y-tankY) < 200 && y+50 > tankY) {
            tempy--;
        }
        if(Math.abs(x-tankX) < 200 && x < tankX) {
            tempx++;
        }
        else if(Math.abs(x-tankX) < 200 && x > tankX) {
            tempx--;
        }
        y += tempy;
        x += tempx;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    // Implements rudimentary animation system with frames and frame delay.
    void drawImage(Graphics g, int tankX, int tankY) {
        if(animationCounter == 4) {
            animationCounter = 0;
            animationTimer = 0;
        }
        AffineTransform rotation = AffineTransform.getTranslateInstance((1280/2)+(x - (tankX)), (720/2)+(y - (tankY)));
        rotation.rotate(Math.toRadians(0), SlimeSpriteSheet[animationCounter].getWidth() / 2.0, SlimeSpriteSheet[animationCounter].getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(SlimeSpriteSheet[animationCounter], rotation, null);

        if(animationTimer == 10) {
            animationCounter++;
            animationTimer = 0;
        }
        animationTimer++;
    }
}
