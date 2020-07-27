package ds.students;

import ds.interfaces.Queue;

/**
 * @author htetpyaeaung
 *
 */
public class DSQueue extends Queue {

	
	public DSQueue(Queue s) {
		//create a new list to copy
		list = new DSList();
		
		//store the passed node's head
		Node current = s.list.head;
		//until the node isn't null
		while(current !=null) {
			//add it to the created list
			//create a new token to make sure not the reference 
			list.add(new Token(current.getToken()));
			//move to node to next one
			current = current.next;
			
		}
	}

	public DSQueue() {
		list = new DSList();
	}

	@Override
	public boolean offer(Token t) {
		
		//catch the Null pointer exception if the give token is null

		try {
			//create a new node with given token
			Node newNode = new Node(null,null,t);
			//add to the list
			list.add(newNode.getToken());
			//return true if success
			return true;
			
		}catch(NullPointerException e){
			//return false if given token is null
			return false;
		}
		
		

	}

	@Override
	public Token poll() {
		//store the list's head to temp node
		Node temp = list.head;
		//until temp is not null
		if(temp != null) {
			//overwrite current head with next node of head 
			list.head = list.head.next;
			//return temp's token
			return temp.getToken();
		}
		//return null if there is nth in head
		return null;
	}

	@Override
	public Token peek() {
		return list.head.getToken();
	}

	@Override
	public String toString() {
		Node temp = list.head;
		String s = "";
		while(temp!= null) {
			//append give to string of given node's token to created string variable
			//and add space
			s += temp.getToken().toString()+" ";
			temp = temp.next;
		}
		//return the final string with trim to remove trailing whitespace
		return s.trim();
	}

	@Override
	public int size() {
		//return list's size 
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		Node temp = list.head;
		//check the temp is not null
		if(temp!= null) {
			//overwrite temp with next node
			temp = temp.next;
			//return false since it isn't empty
			return false;
		}
		//true if temp is null
		return true;
	}

}
