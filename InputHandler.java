import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InputHandler implements KeyListener {
	public InputHandler(GameManager game) {
		this.game = game;
	}

	public void keyTyped(KeyEvent e) {
        game.handleKey(e);
    }
     
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        return;
    }
     
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        return;
    }

	private GameManager game;
}
