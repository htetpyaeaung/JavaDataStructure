package ds.tests;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ds.students.Calculator;
import ds.students.DSQueue;
import ds.students.DSStack;
import ds.students.Token;

/**
 * @author simont
 *
 */
@RunWith(Parameterized.class)
public class EvaluationTests extends DSUnitTesting {

	private static String ID = "ds.tests.Evaluations:";
	private static int runCount = 0;
	
	
	DSQueue input;
	double value;
	Calculator calc;
	
	public EvaluationTests(DSQueue input, double value) {
		this.input = input;
		this.value = value;
	}
	
	@Before
	public void setup() {
		calc = new Calculator();
	}
	
	public static DSQueue listCreator(String args) {
		DSQueue queue = new DSQueue();
		String[] parts = args.split(" ");
		for ( int i = 0; i < parts.length; i++ ) {
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
	
	@Parameterized.Parameters
	public static Collection<Object[]> tokenStreams() {
		return Arrays.asList(new Object[][] { 
			
				// Simple operators. 
				{ listCreator("2 1 +"), 3 },	// 1
				{ listCreator("4 2 -"), 2 },	// 2
				{ listCreator("9 4 *"), 36 },	// 3
				
				// Multiple operators, single expression
				{listCreator("3 2 1 + +"), 6 },	// 4
				{ listCreator("2 3 * 4 /"), 1.5}, 	// 5
				
				// Different precedence values
				{ listCreator("3 2 1 + *"), 9 },	// 6
				{ listCreator("1 2 / 4 5 * + 3 -"), 17.5 }, 	//7
				{ listCreator("2 3 * 4 2 / - 1 2 / +"), 4.5},	//8 
				
				// Parens. 
				{ listCreator("1 3 - 2 1 + *"), -6}, // 9
				{ listCreator("3 6 / 1 4 / 5 * - 3 *"), -2.25 } // 10
				
		});
	}
	
	@Test
	public final void postfixEvaluation() {
		
		Marks.getInstance().marks.put(ID + "postfixEvaluation["+(runCount++)+"]", 1f);
		
		double result = calc.evaluatePostfix(new DSQueue((DSQueue)input));
		
		assertEquals("Evaluating [" + input + "] failed (got [" + result + "], expected: [" + value +"])", result, value, 0.0001f);
	}

}
