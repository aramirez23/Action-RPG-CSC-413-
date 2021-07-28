package TankGame;

import TankGame.PowerUp.PowerUp;
import TankGame.Tile.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static javax.imageio.ImageIO.read;

/**
 * The Map Class creates and draws the map and minimap for both players.
 * It takes two InputStreams, one for the map file and the other
 * the powerUp placement file.
 *
 * @author Alicia Ramirez
 */
public class Map
{
    private Tile[][] tileTestMap;
    private PowerUp[][] tilePowerUpMap;
    private Image bg1;
    private Image bg2;
    private Image miniTank;
    private Image miniWall;
    private Image miniFloor;
    private Image miniMud;
    private Image miniEnemyTank;
    private Image miniFastRight;
    private Image miniFastLeft;
    private Image miniTransport;

    /**
     * Constructor for objects of class Map
     */
    public Map(InputStream mapFile, InputStream powerUpFile)
    {

        try {
            setMapDefault(mapFile, powerUpFile);
            BufferedImage tmp;
            bg1 = read(getClass().getClassLoader().getResourceAsStream("grass.png"));
            bg2 = read(getClass().getClassLoader().getResourceAsStream("Wall1.gif"));
            miniTank = read(getClass().getClassLoader().getResourceAsStream("miniTank.png"));
            miniEnemyTank = read(getClass().getClassLoader().getResourceAsStream("miniEnemyTank.png"));
            miniFloor = read(getClass().getClassLoader().getResourceAsStream("miniFloor.png"));
            miniWall = read(getClass().getClassLoader().getResourceAsStream("miniWall.png"));
            miniMud = read(getClass().getClassLoader().getResourceAsStream("miniMud.png"));
            miniFastRight = read(getClass().getClassLoader().getResourceAsStream("miniFastRight.png"));
            miniFastLeft = read(getClass().getClassLoader().getResourceAsStream("miniFastLeft.png"));
            miniTransport = read(getClass().getClassLoader().getResourceAsStream("miniTransport.png"));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Tile[][] getMap()
    {
        return tileTestMap;
    }
    public PowerUp[][] getPowerUpMap() {
        return tilePowerUpMap;
    }

    /**
     * Create the given map and powerUp placements. Uses a similar
     * technique to the one we learned in the Interpreter assignment.
     * This allows for custom maps to be built as long as they follow
     * the guidelines.
     * @param mapFile Map file to be created
     * @param powerUpFile PowerUp Placement file to be created
     */
    public void setMapDefault(InputStream mapFile, InputStream powerUpFile){
        Tile bc = null;
        Class c;
        System.out.println(System.getProperty("user.dir"));
        Scanner sc = new Scanner(new BufferedReader(new
                InputStreamReader(mapFile)));
        tileTestMap = new Tile[50][50];
        while(sc.hasNextLine()) {
            for (int i=0; i<tileTestMap.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    if (Integer.parseInt(line[j]) == 0) {
                        try {
                            c = Class.forName("TankGame.Tile.Floor");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("grass.png", i, j, 0);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 1) {
                        try {
                            c = Class.forName("TankGame.Tile.Wall");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("Wall1.gif", i, j, 1);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 2) {
                        try {
                            c = Class.forName("TankGame.Tile.BreakableWall");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("Wall2.gif", i, j, 2);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 3) {
                        try {
                            c = Class.forName("TankGame.Tile.Mud");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("mud.png", i, j, 3);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 4) {
                        try {
                            c = Class.forName("TankGame.Tile.FastRight");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("fastRight.png", i, j, 4);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 5) {
                        try {
                            c = Class.forName("TankGame.Tile.FastLeft");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("fastLeft.png", i, j, 5);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 6) {
                        try {
                            c = Class.forName("TankGame.Tile.Transport");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("transport.png", i, j, 6);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 7) {
                        try {
                            c = Class.forName("TankGame.Tile.Transport");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("transport.png", i, j, 7);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                }
            }
        }
        sc = new Scanner(new BufferedReader(new
                InputStreamReader(powerUpFile)));
        tilePowerUpMap = new PowerUp[50][50];
        while(sc.hasNextLine()) {
            for (int i=0; i<tilePowerUpMap.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    if (Integer.parseInt(line[j]) == 0) {
                        tilePowerUpMap[i][j] = null;
                    }
                    else if (Integer.parseInt(line[j]) == 1) {
                        try {
                            c = Class.forName("TankGame.PowerUp.HPPowerUp");
                            tilePowerUpMap[i][j] = (PowerUp) c.getDeclaredConstructor().newInstance();
                            tilePowerUpMap[i][j].init("Shield2.gif", i, j);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 2) {
                        try {
                            c = Class.forName("TankGame.PowerUp.Book");
                            tilePowerUpMap[i][j] = (PowerUp) c.getDeclaredConstructor().newInstance();
                            tilePowerUpMap[i][j].init("book.png", i, j);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }

                }
            }
        }

    }

    public void transportMap(InputStream mapFile, InputStream powerUpFile) {
        Tile bc = null;
        Class c;
        System.out.println(System.getProperty("user.dir"));
        Scanner sc = new Scanner(new BufferedReader(new
                InputStreamReader(mapFile)));
        tileTestMap = new Tile[50][50];
        while(sc.hasNextLine()) {
            for (int i=0; i<tileTestMap.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    if (Integer.parseInt(line[j]) == 0) {
                        try {
                            c = Class.forName("TankGame.Tile.Floor");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("grass.png", i, j, 0);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 1) {
                        try {
                            c = Class.forName("TankGame.Tile.Wall");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("Wall1.gif", i, j, 1);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 2) {
                        try {
                            c = Class.forName("TankGame.Tile.BreakableWall");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("Wall2.gif", i, j, 2);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 3) {
                        try {
                            c = Class.forName("TankGame.Tile.Mud");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("mud.png", i, j, 3);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 4) {
                        try {
                            c = Class.forName("TankGame.Tile.FastRight");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("fastRight.png", i, j, 4);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 5) {
                        try {
                            c = Class.forName("TankGame.Tile.FastLeft");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("fastLeft.png", i, j, 5);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 6) {
                        try {
                            c = Class.forName("TankGame.Tile.Transport");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("transport.png", i, j, 6);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 7) {
                        try {
                            c = Class.forName("TankGame.Tile.Transport");
                            tileTestMap[i][j] = (Tile) c.getDeclaredConstructor().newInstance();
                            tileTestMap[i][j].init("transport.png", i, j, 7);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                }
            }
        }
        sc = new Scanner(new BufferedReader(new
                InputStreamReader(powerUpFile)));
        tilePowerUpMap = new PowerUp[50][50];
        while(sc.hasNextLine()) {
            for (int i=0; i<tilePowerUpMap.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    if (Integer.parseInt(line[j]) == 0) {
                        tilePowerUpMap[i][j] = null;
                    }
                    else if (Integer.parseInt(line[j]) == 1) {
                        try {
                            c = Class.forName("TankGame.PowerUp.HPPowerUp");
                            tilePowerUpMap[i][j] = (PowerUp) c.getDeclaredConstructor().newInstance();
                            tilePowerUpMap[i][j].init("Shield2.gif", i, j);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }
                    else if (Integer.parseInt(line[j]) == 2) {
                        try {
                            c = Class.forName("TankGame.PowerUp.Book");
                            tilePowerUpMap[i][j] = (PowerUp) c.getDeclaredConstructor().newInstance();
                            tilePowerUpMap[i][j].init("book.png", i, j);
                        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("ERROR: Could not create new instance of tile.");
                        }
                    }

                }
            }
        }
    }

    public ArrayList<Slime> createEnemies(InputStream enemyMap) {
        Class c;
        System.out.println(System.getProperty("user.dir"));
        Scanner sc = new Scanner(new BufferedReader(new
                InputStreamReader(enemyMap)));
        ArrayList<Slime> slimes = new ArrayList<Slime>();
        while(sc.hasNextLine()) {
            for (int i=0; i<tileTestMap.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    if (Integer.parseInt(line[j]) == 1) {
                        slimes.add(new Slime(j*64, i*64));
                    }
                }
            }
        }
        return slimes;
    }

    public ArrayList<MiscNPC> createMiscNPC(InputStream npcMap) {
        Class c;
        System.out.println(System.getProperty("user.dir"));
        Scanner sc = new Scanner(new BufferedReader(new
                InputStreamReader(npcMap)));
        ArrayList<MiscNPC> npcList = new ArrayList<MiscNPC>();
        while(sc.hasNextLine()) {
            for (int i=0; i<tileTestMap.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    if (Integer.parseInt(line[j]) == 1) {
                        npcList.add(new MiscNPC(j*64, i*64, getClass().getClassLoader().getResourceAsStream("npc1.png"),
                                new ArrayList<String>(Arrays.asList("Hi, my name is Mary.", "I'm just standing here.",
                                        "I guess you're just standing too.")), "Mary"));
                    }
                    else if (Integer.parseInt(line[j]) == 2) {
                        npcList.add(new MiscNPC(j*64, i*64, getClass().getClassLoader().getResourceAsStream("npc2.png"),
                                new ArrayList<String>(Arrays.asList("You seem to have traveled from afar.",
                                        "One day I'll leave this place, but right now they need me.",
                                        "If you'll excuse me, I must go.")), "Bradley"));
                    }
                }
            }
        }
        return npcList;
    }

    /**
     * Nullifies breakable wall tile object that was hit
     * by bullet and replaces it with a floor tile object.
     * @param row
     * @param column
     */
    public void breakWall(int row, int column) {
        Class c;
        try {
            c = Class.forName("TankGame.Tile.Floor");
            tileTestMap[row][column] = (Tile) c.getDeclaredConstructor().newInstance();
            tileTestMap[row][column].init("grass.png", row, column, 0);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("ERROR: Could not create new instance of tile.");
        }
    }

    /**
     * Nullifies HP PowerUp object that intersected with tank.
     * @param row
     * @param column
     */
    public void destroyPowerUp(int row, int column) {
        tilePowerUpMap[row][column] = null;
    }

    public int getMapWidth() {
        return tileTestMap[0].length * 64;
    }

    public int getMapHeight() {
        return tileTestMap.length * 64;
    }

    /**
     * Draws map relative to player's location.
     * @param g
     * @param tankX
     * @param tankY
     * @param playerNum
     */
    void drawImage(Graphics g, int tankX, int tankY, String playerNum) {
        Graphics2D g2d = (Graphics2D) g;
        int iw = 64;
        int ih = 64;
        int x = 0, y = 0;
        if(playerNum.equals("Player 1")) {
            x = 0;
            y = 0;
        }

        for (int i = (tankY/64)-6; i < (tankY/64) + 6; i++) {
            for (int j = (tankX/64)-10; j < (tankX/64) + 11; j++) {
                g2d.drawImage(tileTestMap[i][j].getImg(), x - (tankX%64), y - (tankY%64), iw, ih, null);
                x += 64;
            }
            if(playerNum.equals("Player 1")) {
                x = 0;
            }
            y += 64;
        }

        if(playerNum.equals("Player 1")) {
            x = 0;
            y = 0;
        }

        for (int i = (tankY/64)-6; i < (tankY/64) + 6; i++) {
            for (int j = (tankX/64)-10; j < (tankX/64) + 11; j++) {
                if(tilePowerUpMap[i][j] != null) {
                    g2d.drawImage(tilePowerUpMap[i][j].getImg(), x - (tankX%64), y - (tankY%64), 22, 24, null);
                }
                x += 64;
            }
            if(playerNum.equals("Player 1")) {
                x = 0;
            }
            y += 64;
        }
    }

    /**
     * Draws minimap relative to player's location.
     * @param g
     * @param tank
     * @param playerNum
     */
    void drawMinimap(Graphics g, Rectangle tank, String playerNum) {
        Graphics2D g2d = (Graphics2D) g;
        int iw = 2;
        int ih = 2;
        int x = 0, y = 0;
        if(playerNum.equals("Player 1")) {
            x = 50;
            y = 720-120;
        }

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if(tileTestMap[i][j] instanceof Floor) {
                    g2d.drawImage(miniFloor, x, y, iw, ih, null);
                }
                else if(tileTestMap[i][j] instanceof BreakableWall || tileTestMap[i][j] instanceof Wall) {
                    g2d.drawImage(miniWall, x, y, iw, ih, null);
                }
                else if(tileTestMap[i][j] instanceof Mud) {
                    g2d.drawImage(miniMud, x, y, iw, ih, null);
                }
                else if(tileTestMap[i][j]instanceof FastRight) {
                    g2d.drawImage(miniFastRight, x, y, iw, ih, null);
                }
                else if(tileTestMap[i][j]instanceof FastLeft) {
                    g2d.drawImage(miniFastLeft, x, y, iw, ih, null);
                }
                else if(tileTestMap[i][j]instanceof Transport) {
                    g2d.drawImage(miniTransport, x, y, iw, ih, null);
                }
                if(tank.intersects(tileTestMap[i][j].getHitbox()) &&
                        !(tileTestMap[i][j] instanceof Wall || tileTestMap[i][j]instanceof BreakableWall)) {
                    g2d.drawImage(miniTank, x, y, iw, ih, null);
                }


                x += 2;
            }
            if(playerNum.equals("Player 1")) {
                x = 50;
            } else {
                x = (1280/2) + 50;
            }
            y += 2;
        }
    }
}
