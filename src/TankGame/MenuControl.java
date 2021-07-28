package TankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * The MenuControl Class handles key presses for the main menu.
 *
 * @author Alicia Ramirez
 */
public class MenuControl implements KeyListener {

    private Menu menu;
    private final int space;
    private final int enter;

    public MenuControl(Menu menu, int space, int enter) {
        this.menu = menu;
        this.space = space;
        this.enter = enter;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == space) {
            this.menu.toggleSpacePressed();
        }
        if (keyPressed == enter) {
            this.menu.toggleEnterPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == space) {
            this.menu.unToggleSpacePressed();
        }
        if (keyReleased == enter) {
            this.menu.unToggleEnterPressed();
        }
    }
}