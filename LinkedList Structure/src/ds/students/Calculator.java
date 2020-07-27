package ds.students;

import java.util.EmptyStackException;

import ds.students.Token.Type;

/**
 * @author htetpyaeaung
 *
 */
public class Calculator {

	public DSQueue infixToPostfix(DSQueue infix) throws RuntimeException {
		//create an empty queue that will store the result
		DSQueue result = new DSQueue();
		//create an empty stack which will hold operator before 
		//putting into final queue
		DSStack holder = new DSStack();
		//create a new node and the passed queue's head will be assign to that
		Node current = infix.list.head;
		//until the node is null
		while(current !=null) {
			//check the node's type is operand
			if(current.getToken().type == Type.OPERAND) {
				//append to final queue if true
				result.offer(current.getToken());
			}
			//check the node type is operator
			else if(current.getToken().type == Type.OPERATOR){
				//until the stack is empty
				//check the top of stack's precedence is higher than the current token's
				while((holder.isEmpty()==false) && (holder.peek().getPrecedence()>=current.getToken().getPrecedence())){
					//if true, remove from stack and append to final queue
                   result.offer(holder.pop());
                }
				//if above two conditions aren't satisfy, it will put into stack
                holder.push(current.getToken());
			}
			else{
				//check if the token's operator is equal to right parenthesis
				if(current.getToken().getOperator().equals(")") ) {
					
					//top of stack is removed and assign to newly created token
					Token t = holder.pop();
					//until the token's operator is not equal to left parenthesis
					//and not null
					while((!t.getOperator().equals("(")) && (t != null)) {
						//assign to the final queue 
						result.offer(t);
						t = holder.pop();
					}
					//if it is null
					if(t == null) {
						//run time exception is thrown 
						throw new RuntimeException();
					}
					
				}
				else {
					//if it is left, assign to stack
					holder.push(current.getToken());
				}
			}
			//the node will move to the next one
			current = current.next;
		}
		
		//until i reach to the stack's size
		for(int i=0;i<=holder.size();i++) {
			//put it to queue
			result.offer(holder.pop());
		}
		//return the queue
		return result;
	}

	
	public double evaluatePostfix(DSQueue exp) throws RuntimeException{
		//create a new double variable and initialise 
		double result = 0.0;
		//create stack to hold the result before return
		DSStack holder = new DSStack();
		
		//create a new Node which will store the passed queue's head
		Node current = exp.list.head;
		//until the node is null
		while(current != null) {
			//check token's type is operand
			if(current.getToken().type == Type.OPERAND) {
				//add to stack if true
				holder.push(current.getToken());
			}
			else {
				//check stack is not empty
				if(holder.isEmpty() == false) {
					//get the top of stack and assign to variables
					double x = holder.pop().getOperand();
					double y = holder.pop().getOperand();
					
					//check current token's operator is equal to give one
					if(current.getToken().getOperator().equals("+")) {
						//do the operation and assign to final double variable
						result = y+x;
						//create a new token based on final variable
						Token tResult = new Token(result);
						//assign that token to stack 
						holder.push(tResult);
					}
					else if(current.getToken().getOperator().equals("-")) {
						result = y-x;
						Token tResult = new Token(result);
						holder.push(tResult);
					}
					else if(current.getToken().getOperator().equals("*")) {
						result = y*x;
						Token tResult = new Token(result);
						holder.push(tResult);
					}
					else if(current.getToken().getOperator().equals("/")) {
						result = y/x;
						Token tResult = new Token(result);
						holder.push(tResult);
					}
				}
				else {
					//if the stack is empty, throw error
					throw new EmptyStackException();
				}
				
			}
			//move the current node to next one
			current = current.next;
		}
		//check the stack size when there is no token to read
		if(holder.size()>1) {
			//if it is more than one, throw error
			throw new RuntimeException();
		}
		else {
			//if not, return the result
			return holder.pop().getOperand();
		}
	}
}
