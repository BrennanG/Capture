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
		@param game the GameManager
	*/
	public Drawer(Board board, Player player1, Player player2, GameManager game) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.game = game;
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
		board.draw(g2);
		player1.draw(g2, board.getSquareSize(), board.getRowSize());
		player2.draw(g2, board.getSquareSize(), board.getRowSize());

		g2.setFont(new Font("TimesRoman", Font.BOLD, 20));

		/**
			Wait-Time Key
		*/
		// Green
		g2.setColor(new Color(0, 153, 0));
		g2.fillRect(350, 550, 50, 50);
		// Light Blue
		g2.setColor(new Color(51, 153, 255));
		g2.fillRect(400, 550, 50, 50);
		// Dark Blue
		g2.setColor(new Color(0, 0, 204));
		g2.fillRect(450, 550, 50, 50);
		// Purple
		g2.setColor(new Color(76, 0, 153));
		g2.fillRect(500, 550, 50, 50);
		// Red
		g2.setColor(new Color(204, 0, 0));
		g2.fillRect(550, 550, 50, 50);
		g2.drawString("Wait-Time Key", 390, 535);

		/**
			Displays the winner if the game is over
		*/
		switch (game.getWinner()) {
			case 1:
				g2.drawString("Player 1 Wins!", 150, 500);
				break;
			case 2:
				g2.drawString("Player 2 Wins!", 150, 500);
				break;
			case 3:
				g2.drawString("It's A Tie", 150, 500);
				break;
			default:
				break;
		}
    }

	/**
		Private Variables
	*/
	private Board board;	// The Board
	private Player player1;	// Player 1
	private Player player2;	// Player 2
	private GameManager game;	// The GameManager
}
