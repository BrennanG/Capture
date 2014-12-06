import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
    The Board Class
	Contains a 2D array of Squares
*/

public class Board {
    
    /**
        Constructs a new Board
        @param boardSize the total number of squares on the board
		@param squareSize the size of each square on the board
		@param topLeft the top-left point of the board
    */
    public Board(int boardSize, int squareSize, Point topLeft) {
        this.boardSize = boardSize;
        rowSize = (int) Math.sqrt(boardSize);
		this.squareSize = squareSize;
        
		int x = topLeft.x;
		int y = topLeft.y;
        board = new Square[rowSize][rowSize];
        for (int i = 0; i < rowSize; i++) {
            board[i] = new Square[rowSize];
            for (int j = 0; j < rowSize; j++) {
                board[i][j] = new Square(x, y, squareSize);
				x += squareSize;
            }
			y += squareSize;
			x = topLeft.x;
        }
    }
	
	
    
    /**
        Checks whether a given location is on the board
        @param location location to be checked
		@return whether or not the provided location is legal to move to
    */
    public boolean checkLegal(int location) {
        return location < boardSize;
    }
	
	/**
		Sets the wait time at the provided location to a random int between 0 and 4
		@param location the location to set
		@return new wait time at the location
	*/
	public int setRandomWaitTime(int location) {
		return board[(int)location/rowSize][location%rowSize].setRandomWaitTime();
	}
	
	/**
		Sets wait time at the provided location
		@param location location to set
		@param waitTime new waitTime for the square
	*/
	public void setWaitTime(int location, int waitTime) { 
		board[(int)location/rowSize][location%rowSize].setWaitTime(waitTime); 
	}
    
    public void decrementWaitTime(int location) {
        Square temp = board[(int)location/rowSize][location%rowSize];
        temp.setWaitTime(temp.getWaitTime() - 1);
    }
	
	/**
		Returns to wait time at the provided location
		@param location location to check
		@return the wait time at the provided location
	*/
	public int getWaitTime(int location) { 
		return board[(int)location/rowSize][location%rowSize].getWaitTime(); 
	}

	/**
		Draws the board
		@param g the Graphics2D that the board should be drawn to
	*/
	public void draw(Graphics2D g) {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < rowSize; j++) {
				board[i][j].draw(g);
			}
		}
	}
	
	/**
		Getters
	*/
	public int getBoardSize() { return boardSize; }
	public int getRowSize() { return rowSize; }
	public int getSquareSize() { return squareSize; }
    
    /**
        Private Variables
    */
    private int boardSize;  // The total number of squares on the board
    private int rowSize;    // The number of squares per row (equal to the square root of "boardSize")
	private int squareSize;	// The size of the squares
    private Square[][] board;  // The Board array
}
