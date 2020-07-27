package ds.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ds.students.DSStack;
import ds.students.Token;

/**
 * @author simont
 *
 */
public class StackTest extends DSUnitTesting {

	DSStack myStack;
	
	private static String ID = "ds.tests.Stack:";
	
	@Before
	public void setup() {
		myStack = new DSStack();
	}

	@Test
	public void testEmpty() {
		
		Marks.getInstance().marks.put(ID + "Empty", 2f);
		
		DSStack s = new DSStack();
		assertTrue("Newly created Stack should be empty", s.isEmpty());
		s.push(new Token(0));
		assertFalse("Stack containing an element shouldn't be empty", s.isEmpty());
	}
	
	@Test
	public void testStack_CopyConstructor() {
		Marks.getInstance().marks.put(ID + "Stack_CopyConstructor", 2f);
		/* The copy constructor implementation requires a deep copy. 
		 * That is, after copying the object changes to the original should not impact the copied object.
		 */
		DSStack s = new DSStack();
		s.push(new Token(0));
		s.push(new Token(1));
		
		DSStack other = new DSStack(s);
		
		assertEquals("Copy constructor should create a Stack of equal size to the copied Stack", 2, other.size());
		for ( int i = 1 ; i >= 0 ; --i ) {
			assertEquals("Copy constructor did not copy the Stack correctly", new Token(i), other.pop());
		}

		
		s.push(new Token(2));
		assertEquals("Changes to original stack should not impact the second stack", 0, other.size());
		
	}


	/**
	 * Test method for {@link Stack#peek()}.
	 */
	@Test
	public void testPeek() {
		Marks.getInstance().marks.put(ID + "Peek", 2f);
		myStack.push(new Token(0));
		assertNotNull("Stack.peek() should not return null when an object should exist at the top of the stack", myStack.peek());
		assertEquals("Stack.peek() should return the object at the top of the stack.", new Token(0), myStack.peek());
		assertFalse("Stack.peek() should not remove the object from the stack.", myStack.isEmpty());
	}

	/**
	 * Test method for {@link Stack#pop()}.
	 */
	@Test
	public void testPop() {
		Marks.getInstance().marks.put(ID + "Pop", 2f);
		myStack.push(new Token(0));
		assertEquals("Stack.pop() should return the object at the top of the stack.", new Token(0), myStack.pop());
		assertTrue("Stack.pop() should remove the object from the stack.", myStack.isEmpty());
	}

	/**
	 * Test method for {@link Stack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		Marks.getInstance().marks.put(ID + "Push", 2f);
		myStack.push(new Token(0));
		myStack.push(new Token(1));
		assertEquals("Stack.push() should add to the top of the stack", new Token(1), myStack.peek());
		assertEquals("Stack.push() didn't result in the correct size stack", 2, myStack.size());
	}

}
