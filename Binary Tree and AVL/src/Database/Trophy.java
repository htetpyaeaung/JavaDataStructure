package Database;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in
 * four ranks: bronze, silver, gold and platinum. The date the trophy was
 * earned and its respective game is also stored.
 * @author Htet Pyae Aung
 * @version 1.0
 */
public class Trophy {
	
	private String name;
	private Rank rank;
	private Rarity rarity;
	private Calendar obtained;
	private Game game;
	
	
    public enum Rank {
		BRONZE, GOLD, SILVER, PLATINUM

	}

	public enum Rarity {
		RARE, ULTRA_RARE, VERY_RARE, UNCOMMON, COMMON

	}
	
	
	
	public Trophy() {}

	/**
	 * Parameterised constructor for trophy class
	 * @param name
	 * @param rank
	 * @param rarity
	 * @param obtained
	 * @param game
	 */
    public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game) {
    	this.name = name;
    	this.rank = rank;
    	this.rarity = rarity;
    	this.obtained = obtained;
    	this.game = game;
    }

	public String toString() {
    	DecimalFormat dateFormat = new DecimalFormat("00");
		return "\""+name+"\""+","+" rank: "+ rank.name()+","+" rarity: "+ rarity.name()+","+" obtained on: "
				+obtained.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)+" "
				+dateFormat.format(obtained.get(Calendar.DATE))
				+","+" "+obtained.get(Calendar.YEAR);
    }

	public Object getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getRank() {
		// TODO Auto-generated method stub
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Object getRarity() {
		// TODO Auto-generated method stub
		return rarity;
	}
	
	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public Object getObtained() {
		// TODO Auto-generated method stub
		return obtained;
	}
	
	public void setObtained(Calendar obtained) {
		this.obtained = obtained;
	}

	public Object getGame() {
		// TODO Auto-generated method stub
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
}
