package TankGame;

import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;

import static javax.imageio.ImageIO.read;

/**
 * The Menu Class handles drawing and key presses on the main menu.
 *
 * @author Alicia Ramirez
 */

public class Menu {
    private BufferedImage mainMenuImg;

    private boolean SpacePressed;
    private boolean EnterPressed;

    private int gameStart;

    Menu() {
        try {
            gameStart = 0;
            mainMenuImg = read(getClass().getClassLoader().getResourceAsStream("Title.bmp"));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int update() {
        if (this.SpacePressed) {
            gameStart = 2;
            return 2;
        }
        if (this.EnterPressed) {
            gameStart = 1;
            return 1;
        }
        return 0;
    }

    public void notLoading() {
        gameStart = 0;
    }

    void toggleSpacePressed() {
        this.SpacePressed = true;
    }

    void toggleEnterPressed() {
        this.EnterPressed = true;
    }

    void unToggleSpacePressed() {
        this.SpacePressed = false;
    }

    void unToggleEnterPressed() {
        this.EnterPressed = false;
    }


    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //Since the font is in resources, we need to register it in order to
        //actually use it in the game.
        try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("steelfish rounded bd.ttf")));
            g.setFont(new Font("Steelfish Rounded", Font.BOLD, 32));
            // To antialias the text so it looks nicer.
            g2d.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        } catch (FontFormatException | IOException e) {

        }

        // Draws main menu.
        g2d.drawImage(mainMenuImg, 0, 0, 1280, 720, null);
        g2d.drawString("Action RPG", (1280/2)-96, 256);
        g2d.drawString("Enter - Start Game", (1280/2)-128, 720-60);
        if(gameStart == 1) {
            g2d.drawString("Loading...", (1280/2)-64, 32);
        }
    }
}