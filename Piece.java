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
        @param color sets the color of the piece
        @param location sets the location of the piece
    */
    public Piece(Color color, int location) {
        this.color = color;
        this.location = location;
        newLocation = null;
        justMoved = false;
    }
    
    /**
        Moves the piece to the location set by "setMove()"
        @return whether or not the piece moved (bool)
    */
    public boolean move() {
        if (newLocation != null) {
            location = newLocation;
            newLocation = null;
            setJustMoved(true);
            return true;
        }
        return false;
    }
    
    /**
        Sets the teh location that the piece will be moved to when "move()" is called
        @param newLocation the location that the piece will be moved to when "move()" is called
    */
    public void setMove(int newLocation) {
        this.newLocation = newLocation;
    }
    
    /**
        Sets whether or not the piece was moved in the current turn
        @param moved whether or not the piece was moved in th current turn
    */
    public void setJustMoved(boolean moved) {
        justMoved = moved;
    }
    
    /**
        Getters
    */
    public boolean getJustMoved() { return justMoved; }
    public int getLocation() { return location; }
    
    private Color color;    // The color of the peice
    private int location;   // The piece's current location on the board
    private int newLocation;    // The location that the piece will move to when "move()" is called
    private boolean justMoved;  // Boolean that represents if the piece moved in the current turn (for capturing)
}