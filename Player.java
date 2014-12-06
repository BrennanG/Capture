import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
    The Player class
*/

public class Player {

    /**
        Constructs a new Player
        @param color the color of the player's pieces
        @param startingNumOfPieces the starting number of pieces for the player
        @param spawnLocations array of size numOfPieces that represents the spawn locations of each piece
    */
    public Player(Color color, Color selectedColor, int startingNumOfPieces, int[] spawnLocations) {
        this.color = color;
        this.selectedColor = selectedColor;
        this.numOfPieces = this.startingNumOfPieces = startingNumOfPieces;
		counter = new PieceCounter(numOfPieces);
		done = false;
        
        pieces = new Piece[numOfPieces];
        for (int i = 0; i < numOfPieces; i++) {
            pieces[i] = new Piece(color, selectedColor, spawnLocations[i]);
        }
    }
    
    /**
        Moves all of the player's pieces to the locations set by "setMove()"
    */
    public void move() {
        for (int i = 0; i < startingNumOfPieces; i++) {
            if (pieces[i] != null) {
                pieces[i].move();
            }
        }
    }
    
    /**
        Sets the the location that the piece will be moved to when "move()" is called
        @param piece the piece to set
        @param newLocation the location that the piece will be moved to when "move()" is called
    */
    public void setMove(int piece, int newLocation) {
        if (pieces[piece] != null) {
            pieces[piece].setMove(newLocation);
        }
    }
    
    /**
        Sets whether or not the piece was moved in the current turn
        @param piece the piece to set
        @param moved whether or not the piece was moved in the current turn
    */
    public void setJustMoved(int piece, boolean moved) {
        if (pieces[piece] != null) {
            pieces[piece].setJustMoved(moved);
        }
    }
	
	/**
		Sets all pieces to whether or not they were moved in the current turn (useful at end of turn to reset them all to false)
		@param moved whether or not the pieces were moved in the current turn
	*/
	public void setJustMoved(boolean moved) {
		for (int i = 0; i < startingNumOfPieces; i++) {
			if (pieces[i] != null) {
				pieces[i].setJustMoved(moved);
			}
		}
	}
    
    /**
        Deletes the pieces that are set to be deleted
    */
    public void deletePieces() {
        for (int i = 0; i < startingNumOfPieces; i++) {
			if (pieces[i] != null && pieces[i].getDelete()) {
				pieces[i] = null;
				numOfPieces--;
			}
		}
    }
	
	/**
		Sets the piece to be deleted at the end of the turn
		@param piece to be deleted
	*/
	public void setDeletePiece(int piece) {
		pieces[piece].setDelete();
	}
	
	/**
		Sets whether or not the player is finished with its turn
		@param whether or not the player is finished with its turn
	*/
	public void setDone(boolean done) {
		this.done = done;
	}
    
	/**
		Increments the current index to the next non-null piece and sets done to true if it looped back to the front
	*/
	public void nextPieceAndUpdateDone() {
        boolean temp = false;
        for (int i = 0; i < startingNumOfPieces; i++) {
            temp = (temp || counter.increment()); // temp stays true if increment() ever returns true
            if (pieces[counter.getValue()] != null) {
                break;
            }
        }
        done = temp;
	}

	public void resetPieceCounter() {
		counter.reset();
		for (int i = 0; i < startingNumOfPieces; i++) {
			if (pieces[i] != null) { return; }
			counter.increment();
		}
	}
    
    /**
        @return a string representation of the Player class
    */
    public String toString() {
        String string = "" + color + " player with " + numOfPieces + " out of " + startingNumOfPieces + " pieces remaining:\n";
        for (int i = 0; i < startingNumOfPieces; i++) {
            if (pieces[i] != null) {
                string += "\tPiece " + i + ": " + pieces[i].toString() + "\n";
            }
        }
        return string;
    }
    
    /**
        @param other object to be checked
        @return boolean representing whether or not "other" is equal to the object
    */
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (other == null) { return false; }
        if (getClass() != other.getClass()) { return false; }
        
        Player otherPlayer = (Player) other;
        if (color != otherPlayer.color) { return false; }
        for (int i = 0; i < startingNumOfPieces; i++) {
            if (pieces[i].equals(otherPlayer.pieces[i]) == false) { return false; }
        }
        return true;
    }

	/**
		Draws the player's pieces
		@param g the Graphics2D that the player's pieces should be drawn to
		@param squareSize the size of the square that the piece will be drawn onto
		@param rowSize the number of squares per row on the board
	*/
	public void draw(Graphics2D g, int squareSize, int rowSize) {
		g.setColor(color);
		for (int i = 0; i < startingNumOfPieces; i++) {
			if (pieces[i] != null) {
				boolean selected = (i == counter.getValue()) ? true : false;
				pieces[i].draw(g, squareSize, rowSize, selected);
			}
		}
	}
    
    /**
        Getters
    */
    public Color getColor() { return color; }
    public int getNumOfPieces() { return numOfPieces; }
    public int getStartingNumOfPieces() { return startingNumOfPieces; }
    public int getPieceLocation(int piece) { return pieces[piece].getLocation(); }
    public int getPrevLocation(int piece) { return pieces[piece].getPrevLocation(); }
	public int getCurrentPiece() { return counter.getValue(); }
	public boolean isDone() { return done; }
	public boolean getJustMoved(int piece) { return pieces[piece].getJustMoved(); }
	public boolean pieceExists(int piece) { return pieces[piece] != null; }
    
    /**
        Private Variables
    */
    private Color color;    // The color of the player's pieces
    private Color selectedColor; // The color of the player's piece's outline when selected
    private int numOfPieces;    // The number of pieces that the player currently has
    private int startingNumOfPieces;    // The number of pieces the player started with
    private Piece[] pieces; // Array of the player's pieces
	private PieceCounter counter;	// The player's counter
	private boolean done;	// Whether or not the player's turn is finished
}
