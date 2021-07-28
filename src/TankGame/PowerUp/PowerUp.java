package TankGame.PowerUp;

import java.awt.*;

/**
 * The PowerUp abstract class is the blueprint for what a powerUp object will have.
 *
 * @author Alicia Ramirez
 */
public abstract class PowerUp{
    // To initialize the PowerUp object.
    public abstract void init(String imageFile, int row, int column);

    // To get the type of PowerUp object.
    public abstract String toString();

    // To get the associated image of the PowerUp object.
    public abstract Image getImg();

    public abstract int getX();

    public abstract int getY();

    // To get the hitbox of the PowerUp object.
    public abstract Rectangle getHitbox();

}
