package ds.students;

import java.util.EmptyStackException;

import ds.interfaces.Stack;

/**
 * @author htetpyaeaung
 *
 */
public class DSStack extends Stack {

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}

	public DSStack() {
		list = new DSList();
	}
	
	public DSStack(DSStack other) {
		//create new list to be copied
		list = new DSList();
		//passed stack's head is assigned to a node
		Node current = other.list.head;
		//until current is not null
		while(current !=null) {
			//current is add into list by creating as new token 
			//which perform deep copy
			list.add(new Token(current.getToken()));
			//move the current to next node
			current = current.next;
			
		}
	}
	
	public Token push(Token obj) {
		//create a new node with given token
		Node newNode = new Node(null,null,obj);
		//add the given token at index 0
		list.add(0,newNode.getToken());
		//return the token of new node
		return newNode.getToken();
	}

	public Token peek() {
		//check whether stack is empty
		//throw exception if empty
		if(this.isEmpty() == true) {
			throw new EmptyStackException();
		}
		//if not, return the token of head of the list
		return list.head.getToken();
	}

	public Token pop() {
		//check whether the stack is empty
		//throw exception if empty
		try {
			//create a new node to temporarily store head node
			Node temp = list.head;
			//head is replaced with next node of head
			list.head = list.head.next;
			//return the removed head node
			return temp.getToken();

		}catch(EmptyStackException e) {
			e.printStackTrace();
			System.out.println("Stack is empty");
			//return null if the stack is empty
			return null;
		}
	}

	public boolean isEmpty() {
		//create a new node to temporarily store head node
		Node temp = list.head;
		//until temp is not null
		if(temp!= null) {
			//move temp to next node
			temp = temp.next;
			return false;
		}
		//return true if temp is null
		return true;
	}

	public int size() {
		return list.size();
	}
	
	@Override
	public String toString() {
		Node temp = list.head;
		String s = "";
		while(temp!= null) {
			s += temp.getToken().toString()+" ";
			temp = temp.next;
		}
		return s.trim();
	}

}
