package TankGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * The SpecialNPC creates an NPC that follows the player on any
 * map and has dialogue with choices.
 *
 * @author Alicia Ramirez
 */
public class SpecialNPC {

    private int x;
    private int y;

    private String name;
    private ArrayList<String> dialogue;
    private boolean dialogueScreen;
    private boolean advanceDialogue;
    private int dialoguePointer;

    private Rectangle hitbox;

    private boolean num1Pressed;
    private boolean num2Pressed;

    private BufferedImage NPC00;
    private BufferedImage dialogueBox;

    SpecialNPC(int x, int y, InputStream img, String name) {
        this.x = x;
        this.y = y;
        hitbox = new Rectangle(x, y, 56, 98);
        this.dialogue = new ArrayList<String>();
        dialogue.add("I'm you, will you get yourself the magic spellbook to save the town?");
        dialogue.add("Then never speak to yourself again. I will haunt you always.");
        dialogue.add("Thank you, but please watch out for the slimes. I will be near you always.");
        dialogue.add("You have gotten the book! The town is saved!");
        dialogue.add("I see you've gotten the book. Are you trying to rub it in your own face?");
        dialogueScreen = false;
        advanceDialogue = false;
        dialoguePointer = 0;
        this.name = name;

        try {
            NPC00 = ImageIO.read(img);
            dialogueBox = ImageIO.read(getClass().getClassLoader().getResourceAsStream("dialogueBox.png"));
        } catch(IOException e) {

        }
    }

    public int update(boolean hasBook) {
        if(dialogueScreen && hasBook == false && dialoguePointer < 1) {
            if (this.num1Pressed) {
                dialoguePointer = 1;
            }
            else if (this.num2Pressed) {
                dialoguePointer = 2;
            }
        }
        if(dialogueScreen && hasBook && dialoguePointer == 2) {
            dialoguePointer = 3;
        }
        if(dialogueScreen && hasBook && dialoguePointer == 1) {
            dialoguePointer = 4;
        }

        hitbox = new Rectangle(x, y, 56, 98);
        return dialoguePointer;
    }

    void toggleNum1Pressed() {
        this.num1Pressed = true;
    }

    void toggleNum2Pressed() {
        this.num2Pressed = true;
    }

    void unToggleNum1Pressed() {
        this.num1Pressed = false;
    }

    void unToggleNum2Pressed() {
        this.num2Pressed = false;
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
            if(dialoguePointer == 0) {
                g2d.drawString("1. No.", 1280/2-48, 720-84);
                g2d.drawString("2. Okay.", 1280/2-48, 720-64);
            }
            else {
                g2d.drawString("Press Space To Continue", 1280/2-48, 720-64);
            }

        }
    }
}
