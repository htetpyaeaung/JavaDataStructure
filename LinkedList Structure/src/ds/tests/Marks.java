package ds.tests;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author simont
 *
 */
public class Marks {
	
	public HashMap<String, Float> marks;
	public ArrayList<String> failed;
	public ArrayList<String> passed;
	
	private static Marks instance = new Marks();
	
	private Marks() {
		this.marks = new HashMap<String, Float>();
		this.failed = new ArrayList<String>();
		this.passed = new ArrayList<String>();
	}
	
	public static Marks getInstance() {
		return instance;
	}

}
