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
        @param boardsize the total number of squares on the board
    */
    public GameManager(int piecesPerPlayer, Color color1, Color color2, int[] spawnLocations1, int[] spawnLocations2, int boardSize) {
        player1 = new Player(color1, piecesPerPlayer, spawnLocations1);
        player2 = new Player(color2, piecesPerPlayer, spawnLocations2);
        board = new Board(boardSize);
    }
    
    /**
        Private Variables
    */
    private Player player1; // Player 1
    private Player player2; // Player 2
    private Board board;    // The Board
}