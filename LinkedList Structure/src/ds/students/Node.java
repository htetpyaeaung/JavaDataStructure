package ds.students;

/**
 * @author simont
 *
 */
public class Node {

	public Node next;
	public Node prev;
	
	private Token t;

	public Node(Node next, Node prev, Token token) {
		this.next = next;
		this.prev = prev;
		this.t = token;
	}

	public Token getToken() {
		return t;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Node))
			return false;

		return t.equals(((Node)other).getToken());
	}

	@Override
	public int hashCode() {
		if ( t == null )
			return 0;
		return t.hashCode();
	}
}
