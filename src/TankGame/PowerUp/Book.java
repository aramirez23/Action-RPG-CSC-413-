package TankGame.PowerUp;

import TankGame.Tile.Tile;

import java.awt.*;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * The Book class will create a book object that will be added to the
 * player's inventory when walked over.
 *
 * @author Alicia Ramirez
 */
public class Book extends PowerUp {
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
        hitbox = new Rectangle(x, y, 24, 30);

    }

    public String toString() {
        return "Magic Spellbook";
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