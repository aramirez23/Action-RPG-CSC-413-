package TankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * The SpecialNPCControl Class handles key presses for the Special NPC's
 * dialogue screen.
 *
 * @author Alicia Ramirez
 */
public class SpecialNPCControl implements KeyListener {

    private SpecialNPC npc;
    private final int choice1;
    private final int choice2;

    public SpecialNPCControl(SpecialNPC npc, int num1, int num2) {
        this.npc = npc;
        this.choice1 = num1;
        this.choice2 = num2;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == choice1) {
            this.npc.toggleNum1Pressed();
        }
        if (keyPressed == choice2) {
            this.npc.toggleNum2Pressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased == choice1) {
            this.npc.unToggleNum1Pressed();
        }
        if (keyReleased == choice2) {
            this.npc.unToggleNum2Pressed();
        }
    }
}