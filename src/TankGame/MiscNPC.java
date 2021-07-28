package TankGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * The MiscNPC will create an NPC with a customizable sprite
 * and dialogue(though without dialogue choices).
 *
 * @author Alicia Ramirez
 */
public class MiscNPC {

    private int x;
    private int y;

    private String name;
    private ArrayList<String> dialogue;
    private boolean dialogueScreen;
    private boolean advanceDialogue;
    private int dialoguePointer;

    private Rectangle hitbox;

    private BufferedImage NPC00;
    private BufferedImage dialogueBox;

    MiscNPC(int x, int y, InputStream img, ArrayList<String> dialogue, String name) {
        this.x = x;
        this.y = y;
        hitbox = new Rectangle(x, y, 56, 98);
        this.dialogue = dialogue;
        dialogueScreen = false;
        advanceDialogue = false;
        dialoguePointer = -1;
        this.name = name;

        try {
            NPC00 = ImageIO.read(img);
            dialogueBox = ImageIO.read(getClass().getClassLoader().getResourceAsStream("dialogueBox.png"));
        } catch(IOException e) {

        }
    }

    public void update(int tankX, int tankY) {

        hitbox = new Rectangle(x, y, 56, 98);
    }

    public void bulletHit() {
        x = 0;
        y = 0;
    }

    public void setDialogueScreen(boolean setter) {
        dialogueScreen = setter;
    }

    public void advanceDialogue() {
        if(dialoguePointer < dialogue.size()-1) {
            dialoguePointer++;
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    void drawImage(Graphics g, int tankX, int tankY) {
        AffineTransform rotation = AffineTransform.getTranslateInstance((1280/2)+(x - (tankX)), (720/2)+(y - (tankY)));
        rotation.rotate(Math.toRadians(0), NPC00.getWidth() / 2.0, NPC00.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(NPC00, rotation, null);
        //Prints dialogue box if activated.
        if(dialogueScreen) {
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
            g2d.drawImage(dialogueBox, 1280/2-214, 720-192, 480, 146, null);
            g2d.drawString(name, 1280/2-192, 720-156);
            g2d.drawString(dialogue.get(dialoguePointer), 1280/2-192, 720-136);
            g2d.drawString("Press Space To Continue", 1280/2-48, 720-64);
        }
    }
}
