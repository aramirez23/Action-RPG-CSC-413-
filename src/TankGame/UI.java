package TankGame;

import java.awt.*;
import java.io.IOException;

/**
 * The UI class handles drawing the hp and lives of the given tank.
 *
 * @author Alicia Ramirez
 */
public class UI {

    void drawImage(Graphics g, int hp, int maxhp, String playerNum) {
        Graphics2D g2d = (Graphics2D) g;
        //Since the font is in resources, we need to register it in order to
        //actually use it in the game.
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
        // Draw health bar
        g2d.setColor(Color.GREEN);
        g2d.fillRect(5, 5, 50*hp, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawString("HP: " + hp + "/" + maxhp, 10, 48);
    }
}
