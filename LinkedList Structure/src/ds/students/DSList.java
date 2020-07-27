package ds.students;

import ds.interfaces.List;

/**
 * @author htetpyaeaung
 *
 */
public class DSList implements List {
	
	public Node head;
	private Node tail;
	
	public DSList() {
		
	}
	
	public DSList(Node head_) {
		
		if(head != null) {
			//assign the pass node to next node of head if head isn't null
			head.next = head_;
		}
		//if null, the passed node is assigned to both head and tail
		head = head_;
		tail = head_;
		
		
	}
	
	public DSList(DSList other) { // Copy constructor. 
		//assign the passed list's head to temporary node variable
		Node temp = other.head;
		//until temp isn't null
		while(temp !=null) {
			//add the temp into list by creating new token
			//to get a deep copy
			this.add(new Token(temp.getToken()));
			//move the temp to next node
			temp = temp.next;
			
		}
	}

	public Token remove(int index) {
		
		//assign the head to temp
		Node temp = head;
		int current  = 0;
		
		if(index > this.size()-1 || index <0) {
			//throw error if passed index is greater than total number
			//or less than 0
			throw new IndexOutOfBoundsException();
		}
		
		else {
			//find the node of the passed index until it isn't equal to passed index
			 while(current != index) {
				 //assign the next node to temp
				 temp= temp.next;
				 //increase the current
				 current++;
			 }
		}
		if(index==0) {
			//if it is 0, remove head and assign next node to head
			head = head.next;
		}
		//check index is not the last one of list
		else if(index!=this.size()-1) {
			//previous node of temp's next node is overwrite with temp's next node
			temp.prev.next = temp.next;
			//next node of temp's prev is overwrite with temp's prev node
			temp.next.prev = temp.prev;
		}
		else if (temp.next == null) {
			//if temp's next node is null
			//assign null to previous node of tail's next node
			tail.prev.next = null;
		}
		
		//return token of removed node
		return temp.getToken();
	}
	
	public int indexOf(Token obj) {
		//store head to temp variable
		Node temp = head;
		//create new node with given token
		Node newNode = new Node(null,null,obj);
		
		//initialise the position to zero
		int position = 0;
		//until temp is not null
		while(temp != null) {
			//check temp is equal to new node
			if(temp.equals(newNode)) {
				return position;
			}
			else {
				//move the temp to next one
				temp = temp.next;
				//increase the position
				position++;
			}
		}
		//-1 if those above conditions not works
		return -1;
	}

	public Node getNode(int index) {
		
		Node temp = head;
		int current  = 0;
		
		//return null if it is greater than totalNum or less than 0 
		if(index > this.size()-1 || index <0) {
			return null;
		}
		
		else {
			 while(current != index) {
				 temp= temp.next;
				 current++;
			 }
		}
		//return node
		return temp;
		
	}

	public Token get(int index) {
		
		Node temp = head;
		int current  = 0;
		
		if(index > this.size()-1 || index <0) {
			return null;
		}
		
		else {
			//loop through  until just before the given index
			 while(current != index) {
				 
				 temp= temp.next;
				 current++;
			 }
		}
		//return token
		 return temp.getToken();
		 
		 
	}

	public boolean isEmpty() {
		Node temp = head;
		//until the temp is not null
		if(temp!= null) {
			//overwrite temp with next node
			temp = temp.next;
			return false;
		}
		//true if temp is null
		return true;
	}

	public int size() {
		int totalNum = 0;
		
		Node temp = head;
		//until the temp is not null
		while(temp!=null) {
			//overwrite temp with next node
			temp = temp.next;
			//increase the total Num
			totalNum++;
		}
		//return final total num
		return totalNum;
	}
	
	@Override
	public String toString() {
		Node temp = head;
		//create new string and initialise it 
		String s = "";
		//until temp is null
		while(temp!= null) {
			//append to string of temp's token and 
			//add space
			s += temp.getToken().toString()+" ";
			//overwrite temp with next node
			temp = temp.next;
		}
		//use trim to remove trailing white space
		return s.trim();
	}

	public boolean add(Token obj) {
		//create node with given token 
		Node nNode = new Node(null,null,obj);
		if(obj == null) {
			throw new NullPointerException();
		}
		
		if(head == null) {
			//assign head and tail with created new node
			head = nNode;
			tail = nNode;
			return true;
		}
		else if (head != null){
			//new node is put into next node of tail
			tail.next =nNode;
			//new node's previous node is replaced with tail
			nNode.prev = tail;
			//tail is replaced with new node
			tail = nNode;
			//next node of tail is set null
			tail.next = null;
			return true;
		}
		else {
			//return false if add operation is not successful
			return false;
		}
		
	}

	public boolean add(int index, Token obj) {
		Node nNode = new Node(null,null,obj);
		//if index is over 0 or over the size of list
		//throw exception
		if(index <0 || index>this.size()) {
			throw new IndexOutOfBoundsException();
		}
		//if given token is null
		//throw exception
		else if(obj == null) {
			throw new NullPointerException();
		}
		
		else {
			if(head == null) {
				head = nNode;
				tail = nNode;
			}
			//if index is at head
			else if(index  == 0) {
				//new node's next is replaced with head
				nNode.next = head;
				//new node is put into head
				head = nNode;
			}
			
			else if ( index >0) {
				Node current = head;
				Node prev = null;
				int i  = 0;
				//until i is not greater than index
				while(i<index) {
					//previous is set to current
					prev = current;
					//current is replaced with next node
					current = current.next;
					//if current is null
					//break the loop
					if(current == null) {
						break;
					}
					//increase i
					i++;
				}
				//if index is not the last one of the list
				if(index<this.size()) {
					//replace new node's next node with current
					nNode.next = current;
					//replace prev's next node with new node
					prev.next = nNode;
				}
				else {
					//if index at the last of list
					//next node of tail is replaced with new node
					tail.next =nNode;
					//new node's previous node is replaced with tail 
					nNode.prev = tail;
				}
				return true;
			}
			
		}
		//return false if the add operation is not successful
		return false;
	}

	public boolean contains(Token obj) {
		
		Node temp = head;
		Node newNode = new Node(null,null,obj);
		//if the given object is null
		//throw exception
		if(obj == null) {
			throw new NullPointerException();
		}
		//until the temp is not null
		while(temp!= null) {
			//check temp is equal to newly created node
			if(temp.equals(newNode)) {
				//return true if it is equal
				return true;
			}
			else {
				//if not, move the temp to next node
				temp = temp.next;
			}
		}
		//return false if the token is not in list
		return false;
	}

	public boolean remove(Token obj) {
		
		Node temp = head;
		Node prevNode = null;
		Node newNode = new Node(null,null,obj);
		//if object is null
		//throw exception
		if(obj == null) {
			throw new NullPointerException();
		}
		//until temp is not null
		while(temp != null) {
			//compare temp with created node 
			//check if it is equal
			if(temp.equals(newNode)) {
				//if temp is equal head
				if(temp == head ) {
					//head is replaced with next node of head
					head = head.next;
					//return true
					return true;
				}
				//if temp is not tail
				else if(temp.next!=null){
					//previous node of temp's next node is replaced with next node of temp
					temp.prev.next = temp.next;
					//next node of temp's previous node is replaced with previous node of temp
					temp.next.prev = temp.prev;
					return true;
				}
				else {
					//previous node of tail's next node is replaced with null
					tail.prev.next = null;
					return true;
				}
			}
			else {
				//previous node is replaced with temp
				prevNode = temp;
			}
			//move temp to next node
			temp = temp.next;
		}
		//return false if the remove operation is not successful 
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + ((tail == null) ? 0 : tail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DSList other = (DSList) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} 
		else if (!head.equals(other.head))
			return false;
		if (tail == null) {
			if (other.tail != null)
				return false;
		} else if (!tail.equals(other.tail))
			return false;
		return true;
	}
	
}
