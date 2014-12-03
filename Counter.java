import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
	The Counter Class
	When the counter reaches its max size, it is automatically reset to 0 (so it will never be at its max size)
*/

public class Counter {
	
	/**
		Constructs a new Counter
	*/
	public Counter(int size) {
		this.size = size;
		counter = 0;
	}
	
	/**
		Increments the counter by 1, resetting to 0 if it reaches its max
		@return whether or not the counter was reset to 0
	*/
	public boolean increment() {
		counter++;
		if (counter == size) { 
			counter = 0;
			return true;
		}
		return false;
	}
	
	/**
		Getters
	*/
	public int getValue() { return counter; }
	
	/**
		Private Variables
	*/
	private int size;
	private int counter;
}