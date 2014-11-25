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
    public Player(Color color, int startingNumOfPieces, int[] spawnLocations) {
        this.color = color;
        this.numOfPieces = this.startingNumOfPieces = startingNumOfPieces;
        
        pieces = new Piece[numOfPieces];
        for (int i = 0; i < numOfPieces; i++) {
            pieces[i] = new Piece(color, spawnLocations[i]);
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
            pieces[piece].setJustMove(moved);
        }
    }
    
    /**
        Deletes the pieces
        @param piece the piece to be deleted
    */
    public void deletePiece(int piece) {
        pieces[piece] = null;
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
        Getters
    */
    public Color getColor() { return color; }
    public int getNumOfPieces() { return numOfPieces; }
    public int getStartingNumOfPieces() { return startingNumOfPieces; }
    public int getPieceLocation(int piece) { return pieces[piece].getLocation(); }
    
    /**
        Private Variables
    */
    private Color color;    // The color of the player's pieces
    private int numOfPieces;    // The number of pieces that the player currently has
    private int startingNumOfPieces;    // The number of pieces the player started with
    private Piece[] pieces; // Array of the player's pieces
}