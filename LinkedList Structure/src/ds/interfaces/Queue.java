package ds.interfaces;

import ds.students.DSList;
import ds.students.Token;

/**
 * The Queue interface for Data Structures Assignment #1:
 * 	Reverse Polish Notation calculator. 
 * 
 * @author simont
 * Jan, 2014. 
 * @param  The type of object stored in the Queue. 
 */
public abstract class Queue {

	public DSList list;

	/**
	 * Inserts the given object into the Queue if possible. 
	 * @param t
	 * @return True if the object was inserted. 
	 * 
	 * @throws NullPointerException if the given object is null.
	 */
	public abstract boolean offer(Token t);
	
	/**
	 * Removes and returns the head of the Queue. 
	 * @return The head of the Queue. 
	 */
	public abstract Token poll();
	
	/**
	 * Retrieves, but does not remove, the head of this Queue. 
	 * If the Queue is empty, returns null. 
	 * @return The head of the Queue. 
	 */
	public abstract Token peek();
	
	@Override
	public abstract String toString();
	
	public abstract int size();
	
	public abstract boolean isEmpty();
}
