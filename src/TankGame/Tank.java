package TankGame;

import TankGame.PowerUp.Book;
import TankGame.PowerUp.PowerUp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

/**
 * The Tank Class creates and handles movement/interactions
 * for the tank. I(Alicia Ramirez) have edited the provided file
 * to suit my own needs.
 *
 * @author anthony-pc (Edited by Alicia Ramirez
 */
public class Tank{


    private int x;
    private int y;
    private int oldx;
    private int oldy;
    private int originalx;
    private int originaly;
    private int originalAngle;
    private int vx;
    private int vy;
    private int angle;

    private final int R = 2;
    private final int ROTATIONSPEED = 4;

    private int hp;
    private int maxhp;
    private int reputation;
    private int level;
    private int experience;
    private int invincibility;
    private boolean dead;
    private boolean winner;
    private boolean questScreen;
    private boolean statsScreen;
    private boolean inventoryScreen;
    private boolean dialogueScreen;
    private boolean questSet;
    private ArrayList<PowerUp> items;
    private ArrayList<String> quests;

    private Rectangle hitbox;
    private Bullet bullet;

    private BufferedImage img;
    private BufferedImage dialogueBox;
    private BufferedImage mainCharUp00;
    private BufferedImage mainCharUp01;
    private BufferedImage mainCharUp02;
    private BufferedImage mainCharUp03;
    private BufferedImage mainCharUp04;
    private BufferedImage mainCharUp05;
    private BufferedImage mainCharUp06;
    private BufferedImage mainCharUp07;
    private BufferedImage mainCharUp08;
    private BufferedImage[] mainCharUp;
    private BufferedImage mainCharLeft00;
    private BufferedImage mainCharLeft01;
    private BufferedImage mainCharLeft02;
    private BufferedImage mainCharLeft03;
    private BufferedImage mainCharLeft04;
    private BufferedImage mainCharLeft05;
    private BufferedImage mainCharLeft06;
    private BufferedImage mainCharLeft07;
    private BufferedImage mainCharLeft08;
    private BufferedImage[] mainCharLeft;
    private BufferedImage mainCharDown00;
    private BufferedImage mainCharDown01;
    private BufferedImage mainCharDown02;
    private BufferedImage mainCharDown03;
    private BufferedImage mainCharDown04;
    private BufferedImage mainCharDown05;
    private BufferedImage mainCharDown06;
    private BufferedImage mainCharDown07;
    private BufferedImage mainCharDown08;
    private BufferedImage[] mainCharDown;
    private BufferedImage mainCharRight00;
    private BufferedImage mainCharRight01;
    private BufferedImage mainCharRight02;
    private BufferedImage mainCharRight03;
    private BufferedImage mainCharRight04;
    private BufferedImage mainCharRight05;
    private BufferedImage mainCharRight06;
    private BufferedImage mainCharRight07;
    private BufferedImage mainCharRight08;
    private BufferedImage[] mainCharRight;
    private BufferedImage[] mainCharDirection;
    private int animationCounter;
    private int animationTimer;

    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean AngleUpPressed;
    private boolean AngleDownPressed;
    private boolean AngleRightPressed;
    private boolean AngleLeftPressed;
    private boolean SpacePressed;
    private boolean isMoving;

    private boolean mud;


    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.oldx = x;
        this.oldy = y;
        this.originalx = x;
        this.originaly = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.originalAngle = angle;

        dead = false;
        winner = false;
        mud = false;

        questScreen = false;
        statsScreen = false;
        inventoryScreen = false;
        dialogueScreen = false;
        questSet = false;
        items = new ArrayList<PowerUp>();
        quests = new ArrayList<String>();

        hitbox = new Rectangle(x+10, y+60, 40, 40);

        hp = 3;
        maxhp = 3;
        experience = 0;
        reputation = 0;
        level = 1;
        invincibility = 0;

        animationCounter = 0;
        animationTimer = 0;
        isMoving = false;

        //Loading all character movement frames.
        try {
            mainCharUp00 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp00.png"));
            mainCharUp01 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp01.png"));
            mainCharUp02 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp02.png"));
            mainCharUp03 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp03.png"));
            mainCharUp04 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp04.png"));
            mainCharUp05 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp05.png"));
            mainCharUp06 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp06.png"));
            mainCharUp07 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp07.png"));
            mainCharUp08 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp08.png"));
            mainCharUp = new BufferedImage[]{mainCharUp00, mainCharUp01, mainCharUp02, mainCharUp03, mainCharUp04,
                    mainCharUp05, mainCharUp06, mainCharUp07, mainCharUp08};

            mainCharLeft00 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharLeft00.png"));
            mainCharLeft01 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharLeft01.png"));
            mainCharLeft02 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharLeft02.png"));
            mainCharLeft03 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharLeft03.png"));
            mainCharLeft04 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharLeft04.png"));
            mainCharLeft05 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharLeft05.png"));
            mainCharLeft06 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharLeft06.png"));
            mainCharLeft07 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharLeft07.png"));
            mainCharLeft08 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharLeft08.png"));
            mainCharLeft = new BufferedImage[]{mainCharLeft00, mainCharLeft01, mainCharLeft02, mainCharLeft03, mainCharLeft04,
                    mainCharLeft05, mainCharLeft06, mainCharLeft07, mainCharLeft08};

            mainCharDown00 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharDown00.png"));
            mainCharDown01 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharDown01.png"));
            mainCharDown02 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharDown02.png"));
            mainCharDown03 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharDown03.png"));
            mainCharDown04 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharDown04.png"));
            mainCharDown05 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharDown05.png"));
            mainCharDown06 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharDown06.png"));
            mainCharDown07 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharDown07.png"));
            mainCharDown08 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharDown08.png"));
            mainCharDown = new BufferedImage[]{mainCharDown00, mainCharDown01, mainCharDown02, mainCharDown03, mainCharDown04,
                    mainCharDown05, mainCharDown06, mainCharDown07, mainCharDown08};

            mainCharRight00 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharRight00.png"));
            mainCharRight01 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharRight01.png"));
            mainCharRight02 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharRight02.png"));
            mainCharRight03 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharRight03.png"));
            mainCharRight04 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharRight04.png"));
            mainCharRight05 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharRight05.png"));
            mainCharRight06 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharRight06.png"));
            mainCharRight07 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharRight07.png"));
            mainCharRight08 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharRight08.png"));
            mainCharRight = new BufferedImage[]{mainCharRight00, mainCharRight01, mainCharRight02, mainCharRight03, mainCharRight04,
                    mainCharRight05, mainCharRight06, mainCharRight07, mainCharRight08};

            dialogueBox = ImageIO.read(getClass().getClassLoader().getResourceAsStream("dialogueBox.png"));
        } catch(IOException e) {

        }
        mainCharDirection = mainCharDown;
    }


    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
        isMoving = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
        isMoving = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleAngleUpPressed() {
        this.AngleUpPressed = true;
    }

    void toggleAngleDownPressed() {
        this.AngleDownPressed = true;
    }

    void toggleAngleRightPressed() {
        this.AngleRightPressed = true;
    }

    void toggleAngleLeftPressed() {
        this.AngleLeftPressed = true;
    }

    void toggleSpacePressed() {
        this.SpacePressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
        isMoving = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
        isMoving = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
        isMoving = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
        isMoving = false;
    }

    void unToggleAngleUpPressed() {
        this.AngleUpPressed = false;
    }

    void unToggleAngleDownPressed() {
        this.AngleDownPressed = false;
    }

    void unToggleAngleRightPressed() {
        this.AngleRightPressed = false;
    }

    void unToggleAngleLeftPressed() {
        this.AngleLeftPressed = false;
    }

    void unToggleSpacePressed() {
        this.SpacePressed = false;
    }



    public void update() {
        oldx = x;
        oldy = y;
        // Check to see if player has leved up.
        checkLevel();

        if (bullet != null) {
            bullet.update();
        }
        // Decrease time until the player can be hit again.
        if(invincibility > 0) {
            invincibility--;
        }

        //If player is in dialogue, they can't move/shoot.
        if(!dialogueScreen) {
            if (this.UpPressed) {
                isMoving = true;
                mainCharDirection = mainCharUp;
                this.angle = 270;
                this.moveForwards();
            }
            else if (this.DownPressed) {
                isMoving = true;
                mainCharDirection = mainCharDown;
                this.angle = 90;
                this.moveBackwards();
            }

            else if (this.LeftPressed) {
                isMoving = true;
                mainCharDirection = mainCharLeft;
                this.angle = 180;
                this.moveLeft();
            }
            else if (this.RightPressed) {
                isMoving = true;
                mainCharDirection = mainCharRight;
                this.angle = 0;
                this.moveRight();
            }

            if (this.AngleUpPressed) {
                this.questScreen = true;
            }
            if(this.AngleUpPressed == false) {
                this.questScreen = false;
            }
            if (this.AngleDownPressed) {
                this.statsScreen = true;
            }
            if (this.AngleDownPressed == false) {
                this.statsScreen = false;
            }
            if (this.AngleLeftPressed) {
                inventoryScreen = true;
            }
            if (this.AngleLeftPressed == false) {
                inventoryScreen = false;
            }
            if (this.SpacePressed && bullet == null) {
                this.shootBullet();
            }
        }
        // When in dialogue, player can only press button to quit dialogue.
        if(dialogueScreen) {
            if (this.AngleRightPressed) {
                setDialogueScreen();
            }
        }

        hitbox = new Rectangle(x+10, y+60, 40, 40);
    }

    private void moveBackwards() {
        vy = (int) Math.abs(Math.round(R * Math.cos(Math.toRadians(0))));
        if(mud) {
            y += vy/2;
        } else {
            y += vy;
        }
    }

    private void moveForwards() {
        vy = (int) Math.abs(Math.round(R * Math.cos(Math.toRadians(0))));
        if(mud) {
            y -= vy/2;
        } else {
            y -= vy;
        }
    }

    private void moveLeft() {
        vx = (int) Math.abs(Math.round(R * Math.cos(Math.toRadians(0))));
        if(mud) {
            x -= vx/2;
        } else {
            x -= vx;
        }
    }

    private void moveRight() {
        vx = (int) Math.abs(Math.round(R * Math.cos(Math.toRadians(0))));
        if(mud) {
            x += vx/2;
        } else {
            x += vx;
        }
    }

    // Force tank right on Fast Right tile
    public void fastRight() {
        x+=2;
    }

    // Force tank left on Fast Left tile
    public void fastLeft() {
        x-=2;
    }

    // Stop tank when it hits wall.
    public void wallHit() {
        this.x = oldx;
        this.y = oldy;
    }

    // Turn on mud movement conditions when on mud.
    public void mudHit() {
        mud = true;
    }

    // Turn off mud movement conditions when out of mud.
    public void outMud() {
        mud = false;
    }

    // Creates bullet and launches it from the center of the tank.
    private void shootBullet() {
        BufferedImage bulletImg;
        try {
            bulletImg = read(getClass().getClassLoader().getResourceAsStream("Shell.gif"));
            bullet = new Bullet(x + 30,y + 50,0,0,this.angle, bulletImg);
            Sound.playSmallExplosion();
        } catch(IOException e) {

        }
    }

    // Check if player has leveled up, and if so, increase their level and health.
    private void checkLevel() {
        if(experience >= 100) {
            level++;
            maxhp++;
            hp = maxhp;
            experience -= 100;
        }
    }

    // Add an item to the player's inventory only if they haven't picked
    // one of the same type already.
    public void addItem(PowerUp item) {
        boolean temp = false;
        for(int i = 0; i<items.size();i++) {
            if(items.get(i).getClass() == item.getClass()) {
                temp = true;
            }
        }
        if(!temp) {
            items.add(item);
        }
    }

    // Change if player is in dialogue session.
    public void setDialogueScreen() {
        if(dialogueScreen) {
            dialogueScreen = false;
        } else {
            dialogueScreen = true;
        }
    }

    public boolean inDialogue() {
        return dialogueScreen;
    }

    /**
     * Handles when the tank gets hit by a bullet. If they are over 1 hp,
     * they just lose one hp. Otherwise they lose a life and get sent back to
     * their spawning point. If they had 0 lives left, then they died and it's
     * game over. They turn into an explosion.
     *
     * @return If the tank reached a game over. (1 if it did, 0 if not).
     * @throws IOException
     */
    public int hit() {
        if(invincibility == 0) {
            hp--;
            if(hp > 0) {
                Sound.playGrunt();
            }
            else {
                Sound.playLargeExplosion();
            }
            invincibility = 120;
        }
        if(hp <= 0) {
            hp = maxhp;
            x = originalx;
            y = originaly;
            angle = originalAngle;
        }
        return 0;
    }

    // Adds the quest to the quest log.
    public void setQuest() {
        quests.add("Get the Magic Spellbook");
        questSet = true;
    }

    // Deals with the multiple endings of the quest.
    public void questComplete(int result) {
        if(!quests.isEmpty()) {
            quests.clear();
            experience += 100;
            if(result == 3) {
                reputation++;
            }
            else if(result == 4) {
                reputation--;
            }
        }
    }

    // Give player experience if enemy has been killed
    public void killEnemy() {
        experience += 10;
    }

    // Transport player to new map start position.
    public void transportMap() {
        x = originalx;
        y = originaly;
    }

    public boolean getWinner() {
        return winner;
    }

    public boolean getDead() {
        return dead;
    }

    public void HPPowerUp() {
        if(hp < maxhp) {
            hp++;
        }
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void nullBullet() {
        bullet = null;
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

    public int getHP() {
        return hp;
    }

    public int getMaxHP() {
        return maxhp;
    }

    public BufferedImage getImg() {
        return img;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    // Returns if player has book object in inventory.
    public boolean getHasBook() {
        boolean temp = false;
        for(int i = 0; i<items.size();i++) {
            if(items.get(i) instanceof Book) {
                temp = true;
            }
        }
        if(!temp) {
            return false;
        }
        return true;
    }

    // Checks if quest has been added to quest log.
    public boolean getQuestSet() {
        return questSet;
    }


    // Implements rudimentary animation system with frames and frame delay.
    void drawImage(Graphics g, String playerNum) {
        if(animationCounter == 9 || isMoving == false) {
            animationCounter = 0;
            animationTimer = 0;
        }
        AffineTransform rotation = null;
        if (playerNum.equals("Player 1")) {
            rotation = AffineTransform.getTranslateInstance(1280 / 2, 720 / 2);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(mainCharDirection[animationCounter], rotation, null);
        if(animationTimer == 5) {
            animationCounter++;
            animationTimer = 0;
        }
        animationTimer++;

        // Draws quest log if activated.
        if(questScreen) {
            try {
                GraphicsEnvironment ge =
                        GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Corki Rounded.otf")));
                g.setFont(new Font("Corki Rounded", Font.BOLD, 48));

                // To antialias the text so it looks nicer.
                g2d.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

            } catch (FontFormatException | IOException e) {

            }
            g.setColor(Color.BLACK);
            g2d.drawString("Quests:", 1280-280, 48);
            for(int i=0; i<quests.size();i++) {
                g2d.drawString(quests.get(i), 1280-400, ((i+1)*48)+48);
            }
        }
        // Draws stats screen if activated.
        if(statsScreen) {
            try {
                GraphicsEnvironment ge =
                        GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Corki Rounded.otf")));
                g.setFont(new Font("Corki Rounded", Font.BOLD, 20));
                g.setColor(Color.BLACK);

                // To antialias the text so it looks nicer.
                g2d.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

            } catch (FontFormatException | IOException e) {

            }
            g2d.drawImage(dialogueBox, 1280/2-214, 720/2-16, 480, 146, null);
            g2d.drawString("Stats", 1280/2-32, 720/2+20);
            g2d.drawString("Level: " + level, 1280/2-32, 720/2+40);
            g2d.drawString("Max HP: " + maxhp, 1280/2-32, 720/2+60);
            g2d.drawString("Reputation: " + reputation, 1280/2-32, 720/2+80);
            g2d.drawString("Experience: " + experience + "/100", 1280/2-32, 720/2+100);
        }
        // Draws inventory screen if activated.
        if(inventoryScreen) {
            try {
                GraphicsEnvironment ge =
                        GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Corki Rounded.otf")));
                g.setFont(new Font("Corki Rounded", Font.BOLD, 20));
                g.setColor(Color.BLACK);

                // To antialias the text so it looks nicer.
                g2d.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

            } catch (FontFormatException | IOException e) {

            }
            g2d.drawImage(dialogueBox, 1280/2-214, 720/2-16, 480, 146, null);
            g2d.drawString("Items", 1280/2-32, 720/2+20);
            for(int i=0; i < items.size();i++) {
                g2d.drawString(items.get(i).toString(), 1280/2-32, 720/2+((i+2)*20));
            }
        }
    }



}
