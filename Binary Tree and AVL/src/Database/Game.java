package Database;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a PlayStation game.
 * @author Htet Pyae Aung
 * @version 1.0
 */
public class Game {

	private String name;
	private Calendar released;
	private Game next;
	private int totalTrophies;
	
	
	
    public Game() {}

    //Parameterised constructor for Game class
    public Game(String name, Calendar released, int totalTrophies) {
    	this.name = name;
    	this.released = released;
    	this.totalTrophies = totalTrophies;
    }
    
    /**
     * toString Class
     * use decimal format class to have 0 in front of single digit
     * use get display name of Calendar class to show the string representation of given calendar field
     * Calendar.Date will return month name in short because of short parameter
     * Calendar.Date use date format object created above to format the digit
     */
    
    public String toString() {
    	DecimalFormat dateFormat = new DecimalFormat("00");
		return "\""+name+"\""+","+" released on: "+released.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)+" "
				+dateFormat.format(released.get(Calendar.DATE))
				+","+" "+released.get(Calendar.YEAR);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (released == null) {
			if (other.released != null)
				return false;
		} else if (!released.equals(other.released))
			return false;
		if (totalTrophies != other.totalTrophies)
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getReleased() {
		return released;
	}

	public void setReleased(Calendar released) {
		this.released = released;
	}

	public Game getNext() {
		return next;
	}

	public void setNext(Game next) {
		this.next = next;
	}

	public int getTotalTrophies() {
		return totalTrophies;
	}

	public void setTotalTrophies(int totalTrophies) {
		this.totalTrophies = totalTrophies;
	}
    
 
	
	
	
	
}
