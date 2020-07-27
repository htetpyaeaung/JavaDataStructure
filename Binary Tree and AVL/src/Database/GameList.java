package Database;

import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a single linked-list of Database.Game objects.
 * @author Htet Pyae Aung
 * @version 1.0
 */
public class GameList {

    public Game head;

	public GameList(Game head) {
		this.head = head;
    }
	

    public String toString() {
    	//head is assigned to temp
    	Game temp = head;
    	String s = "";
    	if(head == null) {
    		return "Empty game list";
    	}
    	else {
    		//use while loop to iterate through linked list 
    		//until temp is not null
	    	while(temp != null) {
	    		//if there is more items in the list
	    		if(temp.getNext() != null) {
		    		s += temp.toString()+"\n";
	    		}
	    		else {
	    			//if nth left, new line is removed from adding to string
	    			s += temp.toString();
		    		
	    		}
	    		temp = temp.getNext();
	    	}
	    	return s;
    	}
    }
     
    /**
     * This method add the game object to the linked list
     * variables --> temp, match
     * temp is loaded with head
     * match is set to false which will be use later
     * @param game
     */
    public void addGame(Game game) {
    	Game temp = head;
    	boolean match = false;
    	if(game == null) {
    		throw new IllegalArgumentException();
    	}
    	
	    if(head == null) {
	    	head = game;
	    }
	    else {
	    	//while loop will search whether provided game is already existed
	    	//in the linked list
	    	while(temp.getNext() != null) {
	    		//until temp is not equal to given parameter
	    		if(!temp.equals(game)) {
	    			temp = temp.getNext();
	    		}
	    		else {
	    			//if found, set match to true and break the loop
	    			match = true;
	    			break;
	    		}
	    	}
	    	//adding to game list will only work if the match value is false
	    	if(match == false) {
	    		//check head is not equal to provided game object
	    		//add if it is not equal 
	    		if(!head.equals(game)) {
	    			temp.setNext(game);
	    		}
	    	}
	    			
	    }
    }

    /**
     * This method will get the game from the game list 
     * temp is loaded with head
     * @param name
     * @return temp
     */
	public Game getGame(String name) {
		Game temp = head;
		if(name == null) {
			throw new IllegalArgumentException();
		}
		//if the provided name is equal to head name
		//return head
		else if(head.getName() == name){
			return head;
		}
		else {
			//iterate through the game list
			//loop until temp is not null
			while(temp !=null ) {
				//if temp's name is not equal to provided game name
				//temp is set with its next object
				if(temp.getName() != name) {
					temp = temp.getNext();
				}
				else {
					//if found, break the loop
					break;
				}
			}
		}
		return temp;
		
	}
	
	/**
	 * This method remove the game from the linked list which has the same name as passed parameters name
	 * Variables --> temp, prev
	 * temp is loaded with head
	 * prev is set to null which will be used to track before item of temp
	 * @param name
	 */
	public void removeGame(String name) {
		Game temp = head;
		Game prev = null;
		if(name == null) {
			throw new IllegalArgumentException();
		}
		else if(head.getName() == name) {
			//if head's name is same as passed game name. remove head and 
			//set head with next item of head
			head = head.getNext();
		}
		else {
			//iterate through the linked list if it is not the same as head
			while(temp != null) {
				//if temp's name is same with passed name
				if(temp.getName() == name) {
					//set next item of prev to temp's next item
					prev.setNext(temp.getNext());

				}
				else {
					//if it is not same, set prev to temp
					prev = temp;
					
				}
				//temp will be loaded with next item of temp
				temp = temp.getNext();
			}
			
			
		}
	}
	
	/**
	 * This method also delete the game object from the list but the parameter is game object to be deleted
	 * temp is loaded with head
	 * prev is set to null which will be used later to track the previous item of temp 
	 * @param game
	 */
	public void removeGame(Game game) {
		Game temp = head;
		Game prev = null;
		if(game == null) {
			throw new IllegalArgumentException();
		}
		else if(head.equals(game)) {
			head = head.getNext();
		}
		else {
			while(temp != null) {
				if(temp.equals(game)) {
					prev.setNext(temp.getNext());
				}
				else {
					prev = temp;
					
				}
				temp = temp.getNext();
			}
		}
	}
	
	
}
