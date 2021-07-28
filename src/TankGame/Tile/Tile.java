package TankGame.Tile;



import java.awt.*;

/**
 * The Tile abstract class is the blueprint for what a tile object will have.
 *
 * @author Alicia Ramirez
 */
public abstract class Tile{
    // To initialize the Tile object.
    public abstract void init(String imageFile, int row, int column, int tileType);

    // To get the type of Tile object.
    public abstract String toString();

    // To get the associated image of the Tile object.
    public abstract Image getImg();

    public abstract int getX();

    public abstract int getY();

    // To get the hitbox of the Tile object.
    public abstract Rectangle getHitbox();

}
