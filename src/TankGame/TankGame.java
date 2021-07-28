package TankGame;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import static javax.imageio.ImageIO.read;

/**
 * The TankGame Class is the main class for Tank Wars.
 * It sets up the frame, main menu, and main gameplay loop.
 * It also serves as a game state holder since when the game
 * ends it will restart to the main menu forever. I(Alicia Ramirez),
 * have edited the provided code to suit my purposes.
 *
 * @author anthony-pc (Edited by Alicia Ramirez)
 */
public class TankGame extends JPanel  {


    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private Menu menu;
    private Tank t1;
    private SpecialNPC specialNPC;
    private Map map1;
    private Camera camera;
    private UI ui1;
    private int gameStart;


    public static void main(String[] args) {
        Thread x;
        TankGame trex = new TankGame();
        trex.init();
        try {

            while (true) {
                // If either camera returns 1, then the game
                // has ended and must be restarted.
                if(trex.camera.update() == 1) {
                    Thread.sleep(5000);
                    Sound.stopMusicLoop();
                    trex.jf.dispose();
                    trex.init();
                }
                trex.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }

    }


    private void init() {
        InputStream mapFile = null;
        InputStream powerUpFile = null;
        gameStart = 0;
        this.jf = new JFrame("Action RPG");
        this.world = new BufferedImage(50*64, 50*64, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1img = null, t2img = null, mainMenu = null;
        try {
            t1img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("MainCharUp.gif"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        menu = new Menu();
        MenuControl mc = new MenuControl(menu, KeyEvent.VK_SPACE, KeyEvent.VK_ENTER);

        this.jf.setSize(TankGame.SCREEN_WIDTH, TankGame.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);
        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(mc);

        Sound.playMusicLoop();

        while(gameStart == 0) {
            int ret = 0;
            try {
                ret = menu.update();
                this.repaint();
                Thread.sleep(1000 / 144);
            } catch (InterruptedException ignored) {

            }

            /*
            Learned from https://www.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser
            Edited to change dialog title, to load my files properly, and to load two files.
            ret == 1 means the player chose to start the default map so it loads automatically.
            ret == 2 means the player chose custom map, so it opens one file chooser window to pick the map file
            and then another to pick the powerUp placement file. If they cancel out after the first window,
            it will remain on the first menu until the player goes through with an option.
             */
            if(ret == 1) {
                mapFile = getClass().getClassLoader().getResourceAsStream("map.txt");
                powerUpFile = getClass().getClassLoader().getResourceAsStream("mapPowerUp.txt");
                gameStart = 1;
            }
        }
        mc = null;
        menu = null;

        t1 = new Tank(768, 1600, 0, 0, 90, t1img);
        specialNPC = new SpecialNPC(1400, 900, getClass().getClassLoader().getResourceAsStream("MainCharDown00.png"), "You");


        TankControl tc1 = new TankControl(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_Q, KeyEvent.VK_C, KeyEvent.VK_I, KeyEvent.VK_SPACE, KeyEvent.VK_E);
        SpecialNPCControl specialNPCControl = new SpecialNPCControl(specialNPC, KeyEvent.VK_1, KeyEvent.VK_2);

        ui1 = new UI();

        map1 = new Map(mapFile, powerUpFile);

        camera = new Camera(map1, t1, specialNPC, "Player 1", ui1);


        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(specialNPCControl);



    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        if(gameStart != 0) {
            this.camera.drawImage(buffer);
        } else {
            this.menu.drawImage(buffer);
        }
        g2.drawImage(world,0,0,null);
    }


}
