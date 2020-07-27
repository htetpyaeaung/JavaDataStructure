package Database;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a PlayStation user.
 * @author Htet Pyae Aung
 * @version 1.0
 */
public class User {
    private String username;
	private int level;
	private double key;
	private ArrayList<Trophy>trophies;
	private GameList games;
	private Calendar dob;
	private User left;
	private User right;
	private int height=1;
	

	/**
	 * Parameterised constructor for User Class
	 * @param username
	 * @param dob
	 * @param level
	 */
	public User(String username, Calendar dob, int level) {
		this.username = username;
		this.dob = dob;
		this.level = level;
    }

    /** 
     * DO NOT MODIFY THIS METHOD
     * This method uses the username and level to create a unique key.
     * As we don't want the username's hash to increase the level, it's first converted
     * to a floating point, then added to the level.
     * @return the unique key
     */
    public double calculateKey() {
        int hash = Math.abs(username.hashCode());
        // Calculate number of zeros we need
        int length = (int)(Math.log10(hash) + 1);
        // Make a divisor 10^x
        double divisor = Math.pow(10, length);
        // Return level.hash
        return level + hash / divisor;
    }

    public String toString() {
    	DecimalFormat dateFormat = new DecimalFormat("00");
    	String s= "";
    	s ="User: "+ username + "\n\n"+"Trophies: \n";
    	//iterate through the trophy array list
    	//use for each loop and add into string variable
    	for(Trophy t: trophies) {
    		s+=t.toString()+"\n";
    	}
    	
    	s += "\nGames: \n" + games.toString()+"\n\n"+ "Birth Date: "+dob.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)+" "
				+dateFormat.format(dob.get(Calendar.DATE))
				+","+" "+dob.get(Calendar.YEAR);
    	
		return s;
    }

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public Calendar getDob() {
		return dob;
	}
	
	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public double getKey() {
		key = this.calculateKey();
		return key;
	}
	
	public User getLeft() {
		return left;
	}
	
	public void setLeft(User left) {
		this.left = left;
	}
	
	public User getRight() {
		return right;
	}
	
	public void setRight(User right) {
		this.right = right;
	}

	public GameList getGames() {
		return games;
	}
	
	public void setGames(GameList games) {
		this.games = games;
	}

	public ArrayList<Trophy> getTrophies(){
		return trophies;
	}
	
	public void setTrophies(ArrayList<Trophy> trophies) {
		this.trophies = trophies;
		
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
}

