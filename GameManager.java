import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
    The GameManager Class
    Handles the main logic of the game
*/

public class GameManager extends JFrame {
    
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
        player1 = new Player(color1, color2, piecesPerPlayer, spawnLocations1);
        player2 = new Player(color2, color1, piecesPerPlayer, spawnLocations2);
        board = new Board(boardSize, 50, new Point(0,0));
		input = new InputHandler(this);
		winner = 0;

		setTitle("Capture");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        add(new Drawer(board, player1, player2));
		addKeyListener(input);
        setSize(500, 500);
        setLocationRelativeTo(null); 
    }
	
	/**
		Handles keyPresses from InputHandler
		@param key the keyEvent representing which key was pressed
	*/
	public void handleKey(KeyEvent key) {
		Player player = getPlayer(key);
		if (player == null || winner > 0) { return; } // An unused key was pushed or player's turn in finished
		int location = player.getPieceLocation(player.getCurrentPiece());
        int newLocation = getNewLocation(location, key);
        if (board.checkLegal(newLocation) && player.isDone() == false) {
			System.out.println(key.getKeyChar());
			repaint();
            player.setMove(player.getCurrentPiece(), newLocation);
            player.nextPieceAndUpdateDone();
            int x;
            for (int i = 0; i < player.getStartingNumOfPieces(); i++) {
                x = player.isDone() ? 1 : 0;
                if (board.getWaitTime(player.getPieceLocation(player.getCurrentPiece())) > x) {
                    player.nextPieceAndUpdateDone();
                }
                else {
                    break;
                }
            }
        }
		checkEndOfTurn();    
	}
	
	/**
		The main function
	*/
	public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int[] spawns1 = {9, 36, 63};
				int[] spawns2 = {17, 44, 71};
                GameManager g = new GameManager(3, Color.WHITE, Color.BLACK, spawns1, spawns2, 81);
                g.setVisible(true);
            }
        });
    }
	
	
	/**
		Private Functions
	*/
	private Player getPlayer(KeyEvent key) {
		switch (key.getKeyChar()) {
			// Player 1
			case 'w': case 's': case 'a': case 'd':
				return player1;
			// Player 2
			case 'i': case 'k': case 'j': case 'l':
				return player2;
			default:
				return null;
		}
	}
	
	private int getNewLocation(int location, KeyEvent key) {
		switch (key.getKeyChar()) {
			case 'w': case 'i':
				return location - board.getRowSize();
			case 's': case 'k':
				return location + board.getRowSize();
			case 'a': case 'j':
				return location - 1;
			case 'd': case 'l':
				return location + 1;
			default:
				return location;
		}
	}
    
    private void checkEndOfTurn() {
        if (player1.isDone() && player2.isDone()) {
			player1.move();
			player2.move();
			player1.setDone(false);
			player2.setDone(false);
			setWaitTimes();
			handleCollisions();
			player1.setJustMoved(false);
			player2.setJustMoved(false);
            
            boolean player1dead = (player1.getNumOfPieces() <= 0);
            boolean player2dead = (player2.getNumOfPieces() <= 0);
            if (player1dead && player2dead) {
                winner = 3;
            }
            else if (player2dead) {
                winner = 1;
            }
            else if (player1dead) {
                winner = 2;
            }

			player1.resetPieceCounter();
			player2.resetPieceCounter();
			
			findStartingPiece(player1);
			findStartingPiece(player2);
			
			repaint();
		}
    }
	
	private void setWaitTimes() {
		for (int i = 0; i < player1.getStartingNumOfPieces(); i++) {
			if (player1.pieceExists(i)) {
				if (player1.getJustMoved(i)) {
					board.setRandomWaitTime(player1.getPrevLocation(i));
				}
				else {
					board.decrementWaitTime(player1.getPieceLocation(i));
				}
			}
			if (player2.pieceExists(i)) {
				if (player2.getJustMoved(i)) {
					board.setRandomWaitTime(player2.getPrevLocation(i));
				}
				else {
					board.decrementWaitTime(player2.getPieceLocation(i));
				}
			}
		}
	}
	
	private void handleCollisions() {
		for (int i = 0; i < player1.getStartingNumOfPieces(); i++) {
			for (int j = 0; j < player2.getStartingNumOfPieces(); j++) {
				// player 1 collision with player 1
				if (player1.pieceExists(i) && player1.pieceExists(j) && player1.getPieceLocation(i) == player1.getPieceLocation(j) && i != j) {
					deleteCollision(player1, player1, i, j);
				}
				
				// player 1 collision with player 2
				if (player1.pieceExists(i) && player2.pieceExists(j) && player1.getPieceLocation(i) == player2.getPieceLocation(j)) {
					deleteCollision(player1, player2, i, j);
				}
					
				// player 2 collision with player 2
				if (player2.pieceExists(i) && player1.pieceExists(j) && player2.getPieceLocation(i) == player2.getPieceLocation(j) && i != j) {
					deleteCollision(player2, player2, i, j);
				}
			}
		}
		player1.deletePieces();
		player2.deletePieces();
	}
	
	private void deleteCollision(Player playerA, Player playerB, int i, int j) {
		board.setWaitTime(playerA.getPieceLocation(i), 0);
		boolean movedA = playerA.getJustMoved(i);
		boolean movedB = playerB.getJustMoved(j);
		if (movedA && movedB) {
			playerA.setDeletePiece(i);
			playerB.setDeletePiece(j);
		}
		else if (movedA && !movedB) {
			playerB.setDeletePiece(j);
		}
		else if (!movedA && movedB) {
			playerA.setDeletePiece(i);
		}
	}

	private void findStartingPiece(Player player) {
		for (int i = 0; i < player.getStartingNumOfPieces(); i++) {
			if (player.isDone() == false && board.getWaitTime(player.getPieceLocation(player.getCurrentPiece())) <= 0) {
				break;
			}
			player.nextPieceAndUpdateDone();
			if (i == player.getStartingNumOfPieces()) { // None of the pieces can move on the turn
				player.setDone(true);
			}
		}
	}
    
    /**
        Private Variables
    */
    private Player player1; // Player 1
    private Player player2; // Player 2
    private Board board;    // The Board
	private InputHandler input;	// InputHandler
	private int winner;
}
