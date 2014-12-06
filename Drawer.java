import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
	The Drawer Class
	Draws the game to the screen
*/

public class Drawer extends JPanel{

	/**
		Constructs a new Drawer
		@param board the board to be drawn
		@param player1 player 1
		@param player2 player 2
	*/
	public Drawer(Board board, Player player1, Player player2) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
		board.draw(g2);
		player1.draw(g2, board.getSquareSize(), board.getRowSize());
		player2.draw(g2, board.getSquareSize(), board.getRowSize());
    }

	/**
		Private Variables
	*/
	private Board board;	// The Board
	private Player player1;	// Player 1
	private Player player2;	// Player 2
}
