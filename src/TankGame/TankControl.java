package TankGame;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * The TankControl Class handles player interaction(key presses/unpresses)
 * for the given tank. I(Alicia Ramirez) have edited the provided code
 * to suit my own needs (mainly the shoot button).
 *
 * @author anthony-pc (Edited by Alicia Ramirez)
 */
public class TankControl implements KeyListener {

    private Tank t1;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int angleUp;
    private final int angleDown;
    private final int angleRight;
    private final int angleLeft;
    private final int shoot;
    
    public TankControl(Tank t1, int up, int down, int left, int right, int angleUp, int angleDown, int angleLeft, int angleRight, int shoot) {
        this.t1 = t1;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.angleUp = angleUp;
        this.angleDown = angleDown;
        this.angleRight = angleRight;
        this.angleLeft = angleLeft;
        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.t1.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.t1.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.t1.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.t1.toggleRightPressed();
        }
        if (keyPressed == angleUp) {
            this.t1.toggleAngleUpPressed();
        }
        if (keyPressed == angleDown) {
            this.t1.toggleAngleDownPressed();
        }
        if (keyPressed == angleLeft) {
            this.t1.toggleAngleLeftPressed();
        }
        if (keyPressed == angleRight) {
            this.t1.toggleAngleRightPressed();
        }
        if (keyPressed == shoot) {
            this.t1.toggleSpacePressed();
        }
        

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == up) {
            this.t1.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.t1.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.t1.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.t1.unToggleRightPressed();
        }
        if (keyReleased == angleUp) {
            this.t1.unToggleAngleUpPressed();
        }
        if (keyReleased == angleDown) {
            this.t1.unToggleAngleDownPressed();
        }
        if (keyReleased == angleLeft) {
            this.t1.unToggleAngleLeftPressed();
        }
        if (keyReleased == angleRight) {
            this.t1.unToggleAngleRightPressed();
        }
        if (keyReleased == shoot) {
            this.t1.unToggleSpacePressed();
        }

    }
}
