package ds.tests;

import ds.interfaces.Queue;
import ds.students.DSQueue;
import ds.students.Token;

public class THelper {

	public static Queue listCreator(String args) {
		Queue queue = new DSQueue();
		String[] parts = args.split(" ");
		for ( int i = 0 ; i < parts.length; ++i ) {
			Token t;
			if ( parts[i].matches("[+-/\\*()]"))
				t = new Token(parts[i]);
			else {
				t = new Token(Float.parseFloat(parts[i]));
			}

			queue.offer(t);
		}
		return queue;
	}
	
}
