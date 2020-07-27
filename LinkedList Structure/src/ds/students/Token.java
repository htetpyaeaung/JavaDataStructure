package ds.students;

/**
 * @author simont
 *
 */
public class Token {

	public enum Type { OPERATOR, OPERAND, PAREN };
	public Type type;
	
	private String operator;
	private double operand;

	public Token(double result) {
		this.operand = result;
		this.type = Type.OPERAND;
	}
	
	public Token(String op) {
		this.operator = op;
		this.type = Type.OPERATOR;
		
		if ( this.operator.equals("(") || this.operator.equals(")") ) {
			this.type = Type.PAREN;
		}
	}
	
	public Token(Token other) {
		this.operator = other.operator;
		this.operand = other.operand;
		this.type = other.type;
	}
	
	public String getOperator() {
		return operator;
	}
	public double getOperand() {
		return operand;
	}
	public int getPrecedence() {
		if ( type == Type.PAREN )
			return -1;
		if ( type != Type.OPERATOR )
			return 0;
		
		switch ( operator ) {
		case "+":
		case "-":
			return 0;
		case "*":
		case "/":
			return 2;
			
		}
		
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( obj == null )
			return false;
		if ( obj == this )
			return true;
		if ( !obj.getClass().equals(Token.class)) 
			return false;
		
		Token t = (Token)obj;
		if ( t.type == this.type ) {
			if ( this.type == Type.OPERATOR )
				return operator.equals(t.operator);
			else
				return operand == t.operand;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	public String toString() {
		return this.type == Type.OPERAND ? "" + this.operand : this.operator;
	}
}
