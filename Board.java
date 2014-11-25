import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
    The Board Class
*/

public class Board {
    
    /**
        Constructs a new Board
        @param boardSize the total number of squares on the board
    */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        rowSize = Math.sqrt(boardSize);
        
        board = new Rectangle[rowSize][rowSize];
        for (int i = 0; i < rowSize; i++) {
            board[i] = new Rectangle[rowSize];
            for (int j = 0; j < rowSize; j++) {
                board[i][j] = new Rectangle(/*x, y, width, height*/);
            }
        }
    }
    
    /**
        Checks whether a given location is on the board
        @param location location to be checked
    */
    public boolean checkLocation(int location) {
        return location < boardSize;
    }
    
    /**
        Private Variables
    */
    private boardSize;  // The total number of squares on the board
    private rowSize;    // The number of squares per row (equal to the square root of "boardSize")
    private Rectangle[][] board;  // The Board array
}