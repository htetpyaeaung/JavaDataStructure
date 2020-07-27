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
public class InfixToPostfixTest extends DSUnitTesting {

	private static String ID = "ds.tests.InfixToPostfix:";
	private static int runCount = 0;
	
	Calculator calc;
	DSQueue input;
	DSQueue expectedOutput;
	
	public InfixToPostfixTest(DSQueue input, DSQueue expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
	}
	@Before
	public final void setup() {
		calc = new Calculator();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> tokenStreams() {
		return Arrays.asList(new Object[][] { 
			// Simple operators.
			
			{ THelper.listCreator("1 + 2"), THelper.listCreator("1 2 +") },
			{ THelper.listCreator("2 - 4"), THelper.listCreator("2 4 -") },
			{ THelper.listCreator("4 * 9"), THelper.listCreator("4 9 *") },
			
			// Multiple operators, single expression
			{ THelper.listCreator("1 + 2 + 3"), THelper.listCreator("1 2 + 3 +") },
			{ THelper.listCreator("2 / 3 * 4"), THelper.listCreator("2 3 / 4 *") },
			
			// Different precedence values
			{ THelper.listCreator("1 + 2 * 3"), THelper.listCreator("1 2 3 * +") },
			{ THelper.listCreator("1 / 2 + 4 * 5 - 3"), THelper.listCreator("1 2 / 4 5 * + 3 -") },
			{ THelper.listCreator("2 * 3 - 4 / 2 + 1 / 2"), THelper.listCreator("2 3 * 4 2 / - 1 2 / +")},
			
			{ THelper.listCreator("7 / 1 + 3 * 2 * 1 / 4 / 3 + 2 * 1"), THelper.listCreator("7 1 / 3 2 * 1 * 4 / 3 / + 2 1 * +") },
			
			// Parens. 
			{ THelper.listCreator("2 / ( 3 + 1 ) * ( 4 * 2 )"), THelper.listCreator("2 3 1 + / 4 2 * *") }
				
		});
	}
	
	public static DSQueue listCreatorNotReversed(String args) {
		DSQueue queue = new DSQueue();
		String[] parts = args.split(" ");
		for ( int i = parts.length - 1 ; i >= 0; --i ) {
			Token t;
			if ( parts[i].matches("[+-/\\*()]"))
				t = new Token(parts[i]);
			else {
				t = new Token(Float.parseFloat(parts[i]));
			}

			queue.offer(t);
		}
	
		// REVERSE THE QUEUE. 
		// If we don't reverse it, it ends up reversed...
		DSStack temp = new DSStack();
		while ( !queue.isEmpty() ) 
			temp.push(queue.poll());
		while ( !temp.isEmpty() ) 
			queue.offer(temp.pop());
		return queue;
	}
	
	
	@Test
	public final void infixToPostfix() {
	
		Marks.getInstance().marks.put(ID + "infixToPostfix["+(runCount++)+"]", 1f);
		
		DSQueue givenInfix = new DSQueue((DSQueue)input);
		
		DSQueue result = (DSQueue)calc.infixToPostfix(input);

		assertEquals("Infix to Postfix conversion resulted in the wrong number of tokens: expected: "  
				+ expectedOutput + ", recieved: " + result, expectedOutput.size(), result.size());

		assertEquals("Converting: [" + givenInfix.toString() + "] resulted in an incorrect RPN expression. Expected: " 
				+ expectedOutput + ", got: " + result, expectedOutput.toString(), result.toString());
	}

}
