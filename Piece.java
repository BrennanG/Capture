import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
    The Piece class
    To move a piece, "setMove()" must be called to set where the new location will be. Then "move()" is called to actually move the piece
*/

public class Piece {

    /**
        Constructs a new Piece
        @param color the color of the piece
        @param location the location of the piece
    */
    public Piece(Color color, Color selectedColor, int location) {
        this.color = color;
        this.selectedColor = selectedColor;
        this.location = location;
        newLocation = prevLocation = -1;
        justMoved = false;
		delete = false;
    }
    
    /**
        Moves the piece to the location set by "setMove()"
    */
    public void move() {
        if (newLocation >= 0) {
            prevLocation = location;
            location = newLocation;
            newLocation = -1;
            setJustMoved(true);
        }
    }
    
    /**
        Sets the the location that the piece will be moved to when "move()" is called
        @param newLocation the location that the piece will be moved to when "move()" is called
    */
    public void setMove(int newLocation) {
        this.newLocation = newLocation;
    }
    
    /**
        Sets whether or not the piece was moved in the current turn
        @param moved whether or not the piece was moved in the current turn
    */
    public void setJustMoved(boolean moved) {
        justMoved = moved;
    }
	
	/**
		Sets the piece to be deleted at the end of the turn
	*/
	public void setDelete() {
		delete = true;
	}
    
    /**
        @return a string representation of the Piece class
    */
    public String toString() {
        return "" + color + " piece at square " + location;
    }
    
    /**
        @param other object to be checked
        @return boolean representing whether or not "other" is equal to the object
    */
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (other == null) { return false; }
        if (getClass() != other.getClass()) { return false; }
        
        Piece otherPiece = (Piece) other;
        return (color == otherPiece.color) 
            && (location == otherPiece.location)
            && (newLocation == otherPiece.newLocation)
            && (prevLocation == otherPiece.prevLocation)
            && (justMoved == otherPiece.justMoved);
    }

	/**
		Draws the piece
		@param g the Graphics2D that the piece should be drawn to
		@param squareSize the size of the square that the piece will be drawn onto
		@param rowSize the number of squares per row on the board
		@param selected whether or not the piece is currently selected
	*/
	public void draw(Graphics2D g, int squareSize, int rowSize, boolean selected) {
		int x = (location % rowSize) * squareSize;
		int y = (int) (location / rowSize) * squareSize;
		g.fillOval(x, y, squareSize, squareSize);
		if (selected) {
		    g.setColor(selectedColor);
		    g.drawOval(x, y, squareSize, squareSize);
		    g.setColor(color);
		}
	}
    
    /**
        Getters
    */
    public boolean getJustMoved() { return justMoved; }
    public int getLocation() { return location; }
    public int getPrevLocation() { return prevLocation; }
    public boolean getDelete() { return delete; }
    
    /**
        Private Variables
    */
    private Color color;    // The color of the piece
    private Color selectedColor; // The outline color of the piece when selected
    private int location;   // The piece's current location on the board
    private int newLocation;    // The location that the piece will move to when "move()" is called
    private int prevLocation;   // The location of the piece prior to the most recent move()
    private boolean justMoved;  // Boolean that represents if the piece moved in the current turn (for capturing)
	private boolean delete;	// Boolean representing whether or not the piece will be deleted at the end of the turn
}
