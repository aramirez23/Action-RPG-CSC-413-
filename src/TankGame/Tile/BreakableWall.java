package TankGame.Tile;

import java.awt.*;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * The BreakableWall class creates a tile that when hit by a bullet,
 * will turn into a floor tile.
 *
 * @author Alicia Ramirez
 */
public class BreakableWall extends Tile{
    private Image img;
    private int x;
    private int y;
    private Rectangle hitbox;

    public void init(String imageFile, int row, int column, int tileType) {
        try {
            img = read(getClass().getClassLoader().getResourceAsStream(imageFile));
        } catch (IOException e) {
        }
        x = (column) * 64;
        y = (row) * 64;
        hitbox = new Rectangle(x, y+20, 64, 64);

    }

    public String toString() {
        return "Breakable Wall";
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