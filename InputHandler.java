import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
    The InputHandler Class
    Calls GameManager's handleKey() function when a key is pressed
*/

public class InputHandler implements KeyListener {

    /**
        Constructs a new InputHandler
        @param game the GameManager
    */
	public InputHandler(GameManager game) {
		this.game = game;
	}

    /**
        Informs the GameManager that a key has been pressed
        @param e the KeyEvent
    */
	public void keyTyped(KeyEvent e) {
        game.handleKey(e);
    }
     
    // Nothing happens
    public void keyPressed(KeyEvent e) {
        return;
    }
     
    // Nothing happens
    public void keyReleased(KeyEvent e) {
        return;
    }

    /**
        Private Variable
    */
	private GameManager game;   // The GameManager
}
