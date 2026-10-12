
public class Node {
	
	private int value;
	private String text; 
	
	private Node left; 
	private Node right;
	private Node parent; 
	

	public Node ( int value,  String text ) {
		this.value = value;
		this.text = text;
			
	}

	public int getValue() {
		return this.value;
		
	}
	
	public String getText() {
		return this.text;
		
	}
	
	public Node getLeft() {
		return this.left;
		
	}
	
	public Node getRight() {
		return this.right;
		
	}
	
	public Node getParent() {
		return this.parent;
		
	}
	
	public void setLeft(Node newLeft) {
		this.left = newLeft;
		
	}
	
	public void setRight(Node newRight) {
		this.right = newRight;
		
	}
	
	public void setParent(Node newParent) {
		this.parent = newParent;
		
	}
	
	public void setValue( int newValue) {
		this.value = newValue;
		
	}
	
	public void setText( String newText) {
		this.text = newText;
		
	}
	

	
	
}
