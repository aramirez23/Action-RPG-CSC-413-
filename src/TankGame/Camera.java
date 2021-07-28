package TankGame;

import TankGame.Tile.*;
import TankGame.PowerUp.*;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

/**
 * The Camera Class handles collisions as well as drawing
 * the main gameplay loop (excluding the main menu).
 *
 * @author Alicia Ramirez
 */

public class Camera {
    private Map map;
    private Tank tank;
    private Bullet bullet;
    private String playerNum;
    private BufferedImage screenDivider;
    private UI ui;
    private int gameOver;
    private ArrayList<Slime> slimes;
    private ArrayList<MiscNPC> miscNPCS;
    private SpecialNPC specialNPC;

    Camera(Map map, Tank tank, SpecialNPC specialNPC, String playerNum, UI ui) {
        this.map = map;
        this.tank = tank;
        this.specialNPC = specialNPC;
        this.bullet = null;
        this.playerNum = playerNum;
        this.ui = ui;
        gameOver = 0;
        try {
            screenDivider = read(getClass().getClassLoader().getResourceAsStream("ScreenDivider.png"));
            slimes = map.createEnemies(getClass().getClassLoader().getResourceAsStream("mapEnemies.txt"));
            miscNPCS = map.createMiscNPC(getClass().getClassLoader().getResourceAsStream("mapNPC.txt"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Updates all elements of main gameplay loop.
    public int update() {
        if(gameOver == 1) {
            return 1;
        }
        if(!tank.inDialogue()) {
            for(int i = 0;i < miscNPCS.size();i++) {
                miscNPCS.get(i).setDialogueScreen(false);
            }
            specialNPC.setDialogueScreen(false);
        }
        this.tank.update();
        int temp = this.specialNPC.update(this.tank.getHasBook());
        if(temp > 2) {
            tank.questComplete(temp);
        }

        for(int i = 0;i < slimes.size();i++) {
            slimes.get(i).update(this.tank.getX(), this.tank.getY());
        }
        bullet = this.tank.getBullet();
        collisionDetection();
        return 0;
    }

    private void collisionDetection() {
        int x = 0;
        int y = 0;

        //Check Tank Collisions
        for(int i=0; i < map.getMapHeight()/64; i++) {
            for(int j=0; j < map.getMapWidth()/64; j++) {
                for(int k = 0;k < slimes.size();k++) {
                    if((map.getMap()[i][j] instanceof Wall || map.getMap()[i][j] instanceof BreakableWall) &&
                            map.getMap()[i][j].getHitbox().intersects(slimes.get(k).getHitbox())) {
                        slimes.get(k).wallHit();
                    }
                }
                if((map.getMap()[i][j] instanceof Wall || map.getMap()[i][j] instanceof BreakableWall) &&
                        map.getMap()[i][j].getHitbox().intersects(tank.getHitbox())) {
                        tank.wallHit();
                }
                if(map.getMap()[i][j] instanceof Mud &&
                        map.getMap()[i][j].getHitbox().intersects(tank.getHitbox())) {
                    tank.mudHit();
                }
                if(!(map.getMap()[i][j] instanceof Mud) &&
                        map.getMap()[i][j].getHitbox().intersects(tank.getHitbox())) {
                    tank.outMud();
                }
                if(map.getMap()[i][j] instanceof FastRight &&
                        map.getMap()[i][j].getHitbox().intersects(tank.getHitbox())) {
                    tank.fastRight();
                }
                if(map.getMap()[i][j] instanceof FastLeft &&
                        map.getMap()[i][j].getHitbox().intersects(tank.getHitbox())) {
                    tank.fastLeft();
                }
                if(map.getMap()[i][j] instanceof Transport &&
                        map.getMap()[i][j].getHitbox().intersects(tank.getHitbox())) {
                    Transport temp = (Transport)map.getMap()[i][j];
                    map.transportMap(temp.getTransportFile(), temp.getPowerUpFile());
                    tank.transportMap();
                    slimes = map.createEnemies(temp.getEnemiesFile());
                    miscNPCS = map.createMiscNPC(temp.getNpcFile());
                }
            }
        }
        for(int i = 0;i < miscNPCS.size();i++) {
            if(tank.getHitbox().intersects(miscNPCS.get(i).getHitbox())) {
                tank.wallHit();
                tank.setDialogueScreen();
                miscNPCS.get(i).advanceDialogue();
                miscNPCS.get(i).setDialogueScreen(true);
            }
        }
        if(tank.getHitbox().intersects(specialNPC.getHitbox())) {
            tank.wallHit();
            tank.setDialogueScreen();
            if(!tank.getQuestSet()) {
                tank.setQuest();
            }
            specialNPC.setDialogueScreen(true);
        }
        for(int i=0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if(map.getPowerUpMap()[i][j] != null && map.getPowerUpMap()[i][j] instanceof HPPowerUp &&
                        map.getPowerUpMap()[i][j].getHitbox().intersects(tank.getHitbox())) {
                    Sound.playHPPowerUp();
                    tank.addItem(map.getPowerUpMap()[i][j]);
                    tank.HPPowerUp();
                    map.destroyPowerUp(i, j);
                }
                if(map.getPowerUpMap()[i][j] != null && map.getPowerUpMap()[i][j] instanceof Book &&
                        map.getPowerUpMap()[i][j].getHitbox().intersects(tank.getHitbox())) {
                    Sound.playHPPowerUp();
                    tank.addItem(map.getPowerUpMap()[i][j]);
                    map.destroyPowerUp(i, j);
                }
            }
        }


        for(int i = 0;i < slimes.size();i++) {
            if(tank.getHitbox().intersects(slimes.get(i).getHitbox())) {
                //tank.wallHit();
                tank.hit();
            }
            if(slimes.get(i).getHitbox().intersects(tank.getHitbox())) {
                //slimes.get(i).wallHit();
            }
        }


        // Check bullet collisions.
        if(bullet != null) {
            for(int i=0; i < 50; i++) {

                for(int j=0; j < 50; j++) {

                    if(map.getMap()[i][j].getHitbox().intersects(bullet.getHitbox())) {
                        if(map.getMap()[i][j] instanceof Wall) {
                            bullet = null;
                            this.tank.nullBullet();
                            break;
                        }
                        else if(map.getMap()[i][j] instanceof BreakableWall) {
                            map.breakWall(i, j);
                            bullet = null;
                            this.tank.nullBullet();
                            break;
                        }
                    }
                }
                if(bullet == null) {
                    break;
                }
                for(int k = 0;k < slimes.size();k++) {
                    if(slimes.get(k).getHitbox().intersects(bullet.getHitbox())) {
                        slimes.get(k).bulletHit();
                        bullet = null;
                        this.tank.nullBullet();
                        tank.killEnemy();
                        break;
                    }
                }
                if(bullet == null) {
                    break;
                }
            }
        }


    }

    // Draws all elements of main gameplay loop.
    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        map.drawImage(g2d, tank.getX(), tank.getY(), playerNum);
        for(int i = 0;i < slimes.size();i++) {
            slimes.get(i).drawImage(g2d, tank.getX(), tank.getY());
        }
        if(bullet != null) {
            bullet.drawImage(g2d, tank.getX(), tank.getY(), playerNum);
        }
        for(int i = 0;i < miscNPCS.size();i++) {
            miscNPCS.get(i).drawImage(g2d, tank.getX(), tank.getY());
        }
        specialNPC.drawImage(g2d, tank.getX(), tank.getY());
        tank.drawImage(g2d, playerNum);
        ui.drawImage(g2d, tank.getHP(), tank.getMaxHP(), playerNum);
        map.drawMinimap(g2d, tank.getHitbox(), playerNum);
        if(tank.getWinner()) {
            Sound.playGameOver();
            g2d.drawString("Player 2 wins!", (1280/2)-144, (720/2)+24);
        }
        else if(tank.getDead()) {
            Sound.playGameOver();
            g2d.drawString("Player 1 wins!", (1280/2)-144, (720/2)+24);
        }
    }
}
