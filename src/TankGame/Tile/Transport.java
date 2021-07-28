package TankGame.Tile;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static javax.imageio.ImageIO.read;

/**
 * The Transport class will create a transport tile that when the player
 * steps on it, will transport them to another map.
 *
 * @author Alicia Ramirez
 */
public class Transport extends Tile{
    private Image img;
    private int x;
    private int y;
    private InputStream transportFile;
    private InputStream enemiesFile;
    private InputStream powerUpFile;
    private InputStream npcFile;
    private Rectangle hitbox;

    public void init(String imageFile, int row, int column, int tileType) {
        try {
            img = read(getClass().getClassLoader().getResourceAsStream(imageFile));
            // Load proper files according to object type.
            if(tileType == 6) {
                transportFile = getClass().getClassLoader().getResourceAsStream("Extra Maps/ArenaMap.txt");
                enemiesFile = getClass().getClassLoader().getResourceAsStream("Extra Maps/ArenaEnemies.txt");
                powerUpFile = getClass().getClassLoader().getResourceAsStream("Extra Maps/ArenaPowerUp.txt");
                npcFile = getClass().getClassLoader().getResourceAsStream("Extra Maps/ArenaNPC.txt");
            }
            else if(tileType == 7) {
                transportFile = getClass().getClassLoader().getResourceAsStream("map.txt");
                enemiesFile = getClass().getClassLoader().getResourceAsStream("mapEnemies.txt");
                powerUpFile = getClass().getClassLoader().getResourceAsStream("mapPowerUp.txt");
                npcFile = getClass().getClassLoader().getResourceAsStream("mapNPC.txt");
            }
        } catch (IOException e) {
        }
        x = (column) * 64;
        y = (row) * 64;
        hitbox = new Rectangle(x, y+40, 64, 64);

    }

    public String toString() {
        return "Transport";
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

    public InputStream getTransportFile() {
        return transportFile;
    }

    public InputStream getEnemiesFile() {
        return enemiesFile;
    }

    public InputStream getPowerUpFile() {
        return powerUpFile;
    }

    public InputStream getNpcFile() {
        return npcFile;
    }

}
