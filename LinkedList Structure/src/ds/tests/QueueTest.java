package ds.tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ds.interfaces.Queue;
import ds.students.DSQueue;
import ds.students.Token;

/**
 * @author simont
 *
 */
public class QueueTest extends DSUnitTesting {

	private static String ID = "ds.tests.Queue:";
	
	Queue myQueue;
	
	@Before
	public void setup() {
		myQueue = new DSQueue();
	}
	
	@Test
	public void testQueue() {
		
		Marks.getInstance().marks.put(ID + "Queue", 2f);
		
		
		/* We can't really test the initalization of the internal storage of Queue, (because it's protected). 
		 * So we'll check that the queue behaves properly when it's newly created: size is 0, says it's empty, etc. 
		 */
		Queue s = new DSQueue();
		assertTrue("Newly created Queue should be empty", s.isEmpty() );
		assertEquals("Newly created Queue should have size of 0", 0, s.size());
	}

	
	
	@Test
	public void testQueue_CopyConstructor() {
		
		Marks.getInstance().marks.put(ID + "Queue_CopyConstructor", 2f);
		
		
		/* The copy constructor implementation requires a deep copy. 
		 * That is, after copying the object changes to the original should not impact the copied object.
		 */
		Queue s = new DSQueue();
		s.offer(new Token(0));
		s.offer(new Token(1));
		
		Queue other = new DSQueue(s);
		
		assertEquals("Copy constructor should create a Queue of equal size to the copied Queue", 2, other.size());
		for ( int i = 0 ; i < 2 ; ++i ) {
			assertEquals("Copy constructor did not copy the Queue correctly", new Token(i), other.poll());
		}

		s.list.add(new Token(2));
		assertEquals("Changes to original queue should not impact the second queue", 0, other.size());

	}
	/**
	 * Test method for {@link Queue#offer(java.lang.Object)}.
	 */
	@Test
	public void testOffer() {
		
		Marks.getInstance().marks.put(ID + "Offer", 2f);
		
		assertEquals("Fresh queue shouldn't contain objects", 0, myQueue.size());
		
		assertTrue("Queue.offer() should return true if insertion succeeded", myQueue.offer(new Token(0)));
		assertEquals("Successful queue insertion should result in larger sized queue", 1, myQueue.size());
		
		myQueue.offer(new Token(1));
		assertFalse("Queue.offer() should insert at the end of the queue!", myQueue.peek().equals(new Token(1)));
	}

	/**
	 * Test method for {@link Queue#poll()}.
	 */
	@Test
	public void testPoll() {
		
		Marks.getInstance().marks.put(ID + "Poll", 2f);
		
		
		myQueue.offer(new Token(0));
		myQueue.offer(new Token(1));
		
		assertEquals("Queue.poll() should return the first object in queue", new Token(0), myQueue.poll());
		assertEquals("Queue.poll() should have decremented the queue size", 1, myQueue.size());
		assertEquals("Queue.poll() should return the first object in queue", new Token(1), myQueue.poll());
		assertEquals("Queue.poll() should have decremented the queue size", 0, myQueue.size());
		
	}

	/**
	 * Test method for {@link Queue#peek()}.
	 */
	@Test
	public void testPeek() {
		
		Marks.getInstance().marks.put(ID + "Peek", 2f);
		
		myQueue.offer(new Token(0));
		myQueue.offer(new Token(1));
		
		assertEquals("Queue.peek() should return the first object in queue", new Token(0), myQueue.peek());
		assertEquals("Queue.poll() should not have decremented the queue size", 2, myQueue.size());
		assertEquals("Queue.peek() should return the same object in subsequent attempts without removal", 
				new Token(0), myQueue.peek());
	}

}
