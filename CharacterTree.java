import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.FileWriter;
import java.io.PrintWriter;
public class CharacterTree {

	private Node root;
	
	private int counter;
	
	
	public CharacterTree() {
		root = null; 
	}
	
	
	
	
	//inserts new node into BST//
	public void insert(int newValue, String newText) {
		
		if (root == null){
			root = new Node(newValue , newText);
				
		}
		
		else {
			Node currentNode = root;
			boolean placed = false;    	// just telling us if Node has been placed yet, starting it a false because haven't inserted anything yet.
			
			while (!placed) {			// while Node is not placed//
				
				
				//case 01: new = current ---> //
				if(newValue == currentNode.getValue()) {
					placed = true;
					System.out.println("ERROE Duplicate vlaue :( ");
				}
				
					//case 02: new value< current--->go left// 
				
				else if(newValue < currentNode.getValue()) {  //if node inserted is less than current node
					
					if(currentNode.getLeft() == null) {         	// and if current node is empty //
																	// go left//
						currentNode.setLeft(new Node(newValue, newText)); 	//new node and attach to left child 
						currentNode.getLeft().setParent(currentNode);		//where parent is
						placed = true;     								//while loop out
					}
					else {
						currentNode = currentNode.getLeft(); 			//move  current node down to child //
						}
					}
				else {
					
						//case 03: new value > current ---> Go Right // 
					if ( currentNode.getRight() == null ) {						//If current node is empty// 
						
						currentNode.setRight(new Node(newValue , newText )); 		 //new node & attach to right child
						currentNode.getRight().setParent(currentNode);  			//where parent is 
						placed = true;
					}
					else {
						currentNode = currentNode.getRight();						//move current node down to child
					}
				}
			
		
				
		
			}
			
		}
		
}
	
	
	
	//prints tree using breadth first traveral//
	 public void breadthFirst() {
		 LinkedList<Node>queue = new LinkedList<Node>();
		 
		 queue.add(root);
		 
		 while(!queue.isEmpty()) {
			 
			 Node currentNode = queue.remove();
			 System.out.println(currentNode.getValue() + " " + currentNode.getText());
			 
			 if(currentNode.getLeft() != null ) {
				 queue.add(currentNode.getLeft());
			 }
			 if(currentNode.getRight() != null) {
				 queue.add(currentNode.getRight());
				 
			 }
			 
		 }
	 }	 
			 
	 
	 
	 //traverses tree in order and assigning node #s)
	 public void inOrder(Node n) {
			if(n != null) {
				
				inOrder(n.getLeft());
				
				n.setValue(counter);
				counter++;
				
				
				inOrder(n.getRight());
			}
		}
	 
	 
	 // assigns new node #s and resets counter//
	 public void NewNodeNumbers() {
		 counter = 0;
		 inOrder(root);
		 
	 }
	 
	 

	 
		 //loading the char tree from txt file //
		 public void loadTree() {
			 try {
					BufferedReader reader = new BufferedReader(new FileReader("tree.txt"));
					 String line; 
					 while ((line = reader.readLine()) != null) {		
						 String [] parts = line.split(",");				
						 insert(Integer.parseInt(parts[0]), parts[1]);     			
				}
					reader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				 
		 }
		 
		 
		 
		 
		 
		 // Travering tree to id a character and learns new character if needed//
		 public void identifyCharacter() {
			 
			 Scanner input = new Scanner(System.in);
			 Node currentNode = root;			
			 
			 while(currentNode.getLeft() != null || currentNode.getRight() != null) {
				 
				 System.out.println(currentNode.getText());   	// print question//
				 String answer = input.nextLine();				// read users input//
				 
				 
				 //yes//
				if(answer.equalsIgnoreCase("yes")) 	{				//	yes--> left //
					currentNode = currentNode.getLeft();
					
				}
				else if ( answer.equalsIgnoreCase("No")) { 			//no--> right//
					currentNode = currentNode.getRight();
				}
			
			 }
			 
			 
			 System.out.println("Is your character " + currentNode.getText() +  "?" );    // GUESSSS print statment// 
			 String  finalAnswer = input.nextLine();
			 
			 
			 
			 // if we guess right...//
			 
			 if(finalAnswer.equalsIgnoreCase("yes")) {
				 System.out.println(" I Guessed Your Character!!");
				 
			 }
			 else {
				 //wrong---> learn new character//
				 
				 System.out.println(" Who was your character?? ");   		//ask //
				 String newchar = input.nextLine();							// read in//
				 
				 System.out.println("Give me a characteristic that discribes " + newchar + " But not " + currentNode.getText() + ":");  		//ask//
				 String newCharacteristic = input.nextLine();					//read//
				 
				 
				 String oldchar = currentNode.getText();
				  currentNode.setText(newCharacteristic);
				  
				  currentNode.setLeft(new Node(0, newchar));
				  currentNode.setRight(new Node(0, oldchar));
				  
				  currentNode.getLeft().setParent(currentNode);
				  currentNode.getRight().setParent(currentNode);
			 }
			 
			 
			 
		 }
		 
			 //getting out of breadthfirst//
		 
		 // re labeling and saving the tree to tree txt file//
		 public void saveTree() {
			 NewNodeNumbers();
		 
			 
		try {
			PrintWriter writer = new PrintWriter(new FileWriter("tree.txt"));
			
			LinkedList<Node>queue = new LinkedList<Node>();
			queue.add(root);
			
			while(!queue.isEmpty()) {
				Node currentNode = queue.remove();
				
				writer.println(currentNode.getValue() + "," + currentNode.getText());
				
				
				if(currentNode.getLeft() != null) {
					queue.add(currentNode.getLeft());
				
				}
				if(currentNode.getRight() != null) {
					queue.add(currentNode.getRight());
						
				} 
				
			}
			
			writer.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();	
		}
	 
	 
		
	}
}
