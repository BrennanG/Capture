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
		@param wait the new waitTime for the square
	*/
	public void setWaitTime(int wait) {
		waitTime = wait;
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
		Decreases the Wait time by 1
	*/
	public void decrementWaitTime() {
		waitTime--;
	}

	/**
		Draws the square
	*/
	public void draw(Graphics2D g) {
		Color color;
		switch (waitTime) {
			case 0:
				color = new Color(0, 153, 0);	// Green
				break;
			case 1:
				color = new Color(51, 153, 255);// Light Blue
				break;
			case 2:
				color = new Color(0, 0, 204);	// Dark Blue
				break;
			case 3:
				color = new Color(76, 0, 153);	// Purple
				break;
			case 4:
				color = new Color(204, 0, 0);	// Red
				break;
			default:
				color = Color.GRAY;
		}
		g.setColor(Color.BLACK);
		g.fillRect(x, y, size, size);
		int innerSize = (int) (size * 0.9);
		int innerX = x + (int) ((size - innerSize) / 2);
		int innerY = y + (int) ((size - innerSize) / 2);
		g.setColor(color);
		g.fillRect(innerX, innerY, innerSize, innerSize);
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
