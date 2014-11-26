import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
    The GameManager Class
    Handles the main logic of the game
*/

public class GameManager extends Component {
    
    /**
        Constructs a new GameManager
        @param piecesPerPlayer starting number of pieces per player
        @param color1 the color of player1's pieces
        @param color2 the color of player2's pieces
        @param spawnLocations1 array of spawn locations for player1's pieces
        @param spawnLocations2 array of spawn locations for player2's pieces
        @param boardSize the total number of squares on the board
    */
    public GameManager(int piecesPerPlayer, Color color1, Color color2, int[] spawnLocations1, int[] spawnLocations2, int boardSize) {
        player1 = new Player(color1, piecesPerPlayer, spawnLocations1);
        player2 = new Player(color2, piecesPerPlayer, spawnLocations2);
        board = new Board(boardSize);
		input = new InputHandler();
    }
	
	/**
		Handles keyPresses from InputHandler
		@param key the keyEvent representing which key was pressed
	*/
	public void handleKey(KeyEvent key) {
		Player player = getPlayer(key);
		if (player == null) { return; }
		int location = player.getLocation(player.getCurrentIndex());
		int newLocation = getNewLocation(location, key);
		if (board.checkLegal(newLocation)) {
			player.setMove(player.getCurrentIndex(), newLocation);
			if (player.incrementCurrentIndex()) {
				player.setDone(true);
			}
		}
		
		if (player1.isDone() && player2.isDone()) {
			player1.move()
			player2.move()
			player1.setDone(false);
			player2.setDone(false);
			checkCollisions();
			player1.setJustMoved(false);
			player2.setJustMoved(false);
		}
	}
	
	/**
		Private Functions
	*/
	private Player getPlayer(KeyEvent key) {
		switch (key) {
			// Player 1
			case VK_W: case VK_S: case VK_A: case VK_D:
				return player1;
			// Player 2
			case VK_I: case VK_K: case VK_J: case VK_L:
				return player2;
			default:
				return null;
		}
	}
	
	private int getNewLocation(int location, KeyEvent key) {
		switch (key) {
			case VK_W: case VK_I:
				return location - board.getRowSize();
			case VK_S: case VK_K:
				return location + board.getRowSize();
			case VK_A: case VK_J:
				return location - 1;
			case VK_D: case VK_L:
				return location + 1;
			default:
				return location;
		}
	}
	
	private void checkCollisions() {
		
	}
    
    /**
        Private Variables
    */
    private Player player1; // Player 1
    private Player player2; // Player 2
    private Board board;    // The Board
	private InputHandler input;	// InputHandler
}