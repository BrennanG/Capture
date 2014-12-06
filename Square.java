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
	public Square(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
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

	*/
	public void draw(Graphics2D g) {
		Color color;
		switch (waitTime) {
			case 0:
				color = Color.GREEN;
				break;
			case 1:
				color = Color.BLUE;
				break;
			case 2:
				color = Color.YELLOW;
				break;
			case 3:
				color = Color.ORANGE;
				break;
			case 4:
				color = Color.RED;
				break;
			default:
				color = Color.BLUE;
		}
		g.setColor(color);
		g.fillRect(x, y, size, size);
	}
	
	/**
		Getters
	*/
	public int getSize() { return size; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWaitTime() { return waitTime; }
	
	/**
		Private Variables
	*/
	private int size;
	private int x;
	private int y;
	private int waitTime;
	private static final Random rand = new Random();
}
