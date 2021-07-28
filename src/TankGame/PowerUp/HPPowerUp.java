package TankGame.PowerUp;

import TankGame.Tile.Tile;

import java.awt.*;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * The HPPowerUp class will create a heart object that when picked up
 * will heal the player by 1 hp.
 *
 * @author Alicia Ramirez
 */
public class HPPowerUp extends PowerUp {
    private Image img;
    private int x;
    private int y;
    private Rectangle hitbox;

    public void init(String imageFile, int row, int column) {
        try {
            img = read(getClass().getClassLoader().getResourceAsStream(imageFile));
        } catch (IOException e) {
        }
        x = (column) * 64;
        y = (row) * 64;
        hitbox = new Rectangle(x, y, 22, 24);

    }

    public String toString() {
        return "HP PowerUp";
    }

    public Image getImg() {
        return img;
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

}