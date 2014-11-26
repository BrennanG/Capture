import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
	The Square Class
*/

public class Square {
	
	/**
		Constructs a new Square
	*/
	public Square(int size, int width, int height) {
		this.size = size;
		this.width = width;
		this.height = height;
		waitTime = rand.nextInt(5);
	}
	
	/**
		Sets the waitTime of the square
		@param waitTime the new waitTime for the square
	*/
	public void setWaitTime(int WaitTime) {
		this.waitTime = waitTime;
	}
	
	/**
		Sets the waitTime to a random int from 0 to 4
		@return the new waitTime for the square
	*/
	public int setRandomWaitTime() {
		waitTime = rand.nextInt(5);
		return waitTime;
	}
	
	/**
		Getters
	*/
	public int getSize() { return size; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getWaitTime() { return waitTime; }
	
	/**
		Private Variables
	*/
	private int size;
	private int width;
	private int height;
	private int waitTime;
	private static final Random rand = new Random();
}