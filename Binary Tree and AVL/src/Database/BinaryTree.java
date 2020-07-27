package Database;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Uses a binary search tree to store a database of
 * PlayStation users. Nodes are ordered by user unique key (see the
 * User class for more detail).
 * @author Htet Pyae Aung
 * @version 1.0
 */
public class BinaryTree {

	public User root;
	
	
	
	public BinaryTree() {
		root = null;
	}
	
	public BinaryTree(User root) {
		this.root = root;
	}
	/**
	 * Making new friends is great. This method should add your new
	 * bestie to your database (tree). Remember that they should be
	 * added according to their key.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean beFriend(User friend) throws IllegalArgumentException {
		
			if(friend == null) {
				throw new IllegalArgumentException();
			}
			
			if (root == null){
				this.root = friend;
				return true;
			}
			
			User temp = root;
			//iterate to find the null spot in the tree
			while(temp!= null) {
				//if the passed user's key is greater than temp's key 
				if(friend.getKey() > temp.getKey()) {
					//go right of the root
					if(temp.getRight() == null) {
						//value is set if right value is null
						temp.setRight(friend);
						return true;
					}
					//if not, iterate again 
					temp = temp.getRight();
				}
				//go to left root, if passed user's key is smaller
				else if(friend.getKey() < temp.getKey()){
					if(temp.getLeft() == null) {
						temp.setLeft(friend);
						return true;
					}
					temp = temp.getLeft();
				}
				else {
					//return false if passed user is already existed
					return false;
				}
			 }
		//if tree has no null spot
		return false;
	}
	
	//Helper function to find the user in the tree
	private User findUser(User user) {
		User focusNode = root;
		//loop until focus node's key is same with passed user's key
		while(focusNode.getKey() != user.getKey()) {
			if(user.getKey()<focusNode.getKey()) {
				focusNode = focusNode.getLeft();
			}
			else {
				focusNode= focusNode.getRight();
			}
			
			if(focusNode == null) {
				//return null if user cannot be found
				return null;
			}
		}
		return focusNode;
	}

	
	public User deleteUser(User node, double key){
	    if(node == null)
	        return null;
	    if(key < node.getKey()){
	        node.setLeft(deleteUser(node.getLeft(), key));
	    }else if (key > node.getKey()){
	        node.setRight(deleteUser(node.getRight(), key)); 
	    }else{
	        // Leaf node deletion case
	        if(node.getLeft() == null && node.getRight() == null){
	            node = null;
	            
	        }
	        // If node has only one child
	        // Right child Deletion 
	        else if(node.getLeft() == null){
	            node = node.getRight();
	        }
	        // left child
	        else if(node.getRight() == null){
	            node = node.getLeft();
	        }
	        // If node has two children case
	        else{
	        	//find the successor of the to be deleted node
	            User successor = findSuccessor(node.getLeft());
	            if(node == root) {
	            	successor.setLeft(root.getLeft());
	            	successor.setRight(root.getRight());
	            	root = successor;
	            }
	            else {
		            successor.setLeft(node.getLeft());
		            successor.setRight(node.getRight());
		            // Copy the value
		            node = successor;
	            }
	            // delete successor node instead
	            node.setLeft(deleteUser(node.getRight(),successor.getKey()));
	        }
	    }
	    return node;
	}
	
	/**
	 * Helper method to find the successor of the passed user
	 * @param user
	 * @return user
	 */
	private User findSuccessor(User user){
	    if(user.getRight() == null)
	        return user;
	    else 
	        return findSuccessor(user.getRight());
	    
	}


	/**
	 * Sometimes friendships don't work out. In those cases it's best
	 * to remove that "friend" altogether. This method should remove
	 * all trace of that "friend" in the database (tree).
	 * @param friend the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException if "friend" is null
	 */
	public boolean deFriend(User friend) throws IllegalArgumentException {
		if(friend == null) {
			throw new IllegalArgumentException();
		}
		
		if(findUser(friend) == null) {
			return false;
		}
		else {
			//return true if the deletion is success
			User de = deleteUser(root,friend.getKey());
			return true;
		}

	}

	/**
	 * In your quest to be the very best you need to know how many
	 * of your friends are ranked higher than you. This method should
	 * return the number of higher ranked users that the provided reference
	 * user, or zero if there are none (woot!).
	 * @param reference The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countBetterPlayers(User reference) throws IllegalArgumentException {
		//this will store the number of better player
		int totalPlayer = 0;
		
		if(reference == null) {
			throw new IllegalArgumentException();
		}
		
		//if given user is not existed in the tree, return -1
		//use find user helper method to find the user in tree
		if(findUser(reference) == null) {
			return -1;
		}
		
		else {
			//create a queue and add the root first 
			Queue<User> queue = new LinkedList<User>();
			queue.add(root);
			//until queue is empty 
			while(!queue.isEmpty()) {
				//temp will store user that return and remove the head from queue
				User temp = queue.poll();
				//if level of temp is greater than passed user's
				if(temp.getLevel() > reference.getLevel()) {
					//increase the count
					totalPlayer++;
				}
				//if not, go to left of tree and add to queue
				if(temp.getLeft() != null) {
					queue.add(temp.getLeft());
				}
				//go to right
				if(temp.getRight() != null) {
					queue.add(temp.getRight());
				}
			}
		}
		
		return totalPlayer;
	}

	/**
	 * Bragging rights are well earned, but it's good to be sure that you're actually
	 * better than those over whom you're lording your achievements. This method
	 * should find all those friends who have a lower level than you, or zero if
	 * there are none (you suck).
	 * @param reference The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countWorsePlayers(User reference) throws IllegalArgumentException {
		int totalPlayer = 0;
		
		if(reference == null) {
			throw new IllegalArgumentException();
		}
		
		if(findUser(reference) == null) {
			return -1;
		}
		
		else {
			Queue<User> queue = new LinkedList<User>();
			queue.add(root);
			
			while(!queue.isEmpty()) {
				User temp = queue.poll();
				if(temp.getLevel() < reference.getLevel()) {
					totalPlayer++;
				}
				
				if(temp.getLeft() != null) {
					queue.add(temp.getLeft());
				}
				if(temp.getRight() != null) {
					queue.add(temp.getRight());
				}
			}
		}
		
		return totalPlayer;
	}

	/**
	 * The best player may not necessarily be measured by who has the highest level.
	 * Platinum trophies are the holy grail, so it would be good to know who has the
	 * most. This method should return the user with the highest number of platinum trophies.
	 * @return the user with the most platinum trophies, or null if there are none
	 */
	public User mostPlatinums() {
		//three different queue with different purposes
		Queue<User> queue = new LinkedList<User>();
		Queue<User> nominee = new LinkedList<User>();
		Queue<User> winners = new LinkedList<User>();
		queue.add(root);
		
		User winnerTot = null;
		int topPlat = 0;
		//the same as counting better player
		//loop until queue is empty
		while(!queue.isEmpty()) {
			User temp = queue.poll();
			int platPlayer = 0;
			boolean Match = false;
			
			//iterate the trophy list
			for(Trophy t: temp.getTrophies()) {
				//check current trophy is platinum or not
				if(t.getRank().equals(Trophy.Rank.PLATINUM)) {
					//if yes, increase the count
		    		platPlayer++;
		    		//change match to yes
		    		Match = true;	
		    	}
	    	}
			//if current player has more platinum than top
			if(platPlayer > topPlat) {
				//add his current platinum count to top
				topPlat = platPlayer;
				//remove all previous top players from nominee
				nominee.removeAll(nominee);
				//add the current player
				nominee.add(temp);
			}
			else if((platPlayer == topPlat) && Match == true) {
				//if the count is same, add to that
				nominee.add(temp);
			}
			//go around the tree
			if(temp.getLeft() != null) {
				queue.add(temp.getLeft());
			}
			if(temp.getRight() != null) {
				queue.add(temp.getRight());
			}
		}
		
		//if we have more than one nominee
		if(nominee.size()>1) {
			int topGold = 0;
			
			//loop until nominee is empty 
			while(!nominee.isEmpty()) {
				int gold = 0 ;
				boolean Match = false;
				User temp = nominee.poll();
				for(Trophy t: temp.getTrophies()) {
					if(t.getRank().equals(Trophy.Rank.GOLD)) {
			    		gold++;
			    		Match = true;	
			    	}
		    	}
				
				if(gold > topGold) {
					topPlat = gold;
					winnerTot = temp;
				}
				else if((gold == topGold) && Match == true) {
					//add to final winner
					winners.add(temp);
				}
			}
			//works only there is more than one winner
			while(!winners.isEmpty()) {
				winners.add(winnerTot);
				return winners.poll();
			}
			
			return winnerTot;
			
		}
		else {
			return nominee.peek();
		}
	}
	
	/**
	 * You or one of your friends bought a new game! This method should add it to their
	 * GameList.
	 * @param username The user who has bought the game
	 * @param game The game to be added
	 */
	public void addGame(String username, Game game) throws IllegalArgumentException {
		
		if((username == null) || (game == null)) {
			throw new IllegalArgumentException();
		}
		else {
			Queue<User> queue = new LinkedList<User>();
			queue.add(root);
			
			User change = null;
			while(!queue.isEmpty()) {
				User temp = queue.poll();
				//check current temp's username is equal to passed username
				if(temp.getUsername() == username) {
					//assigned that user object to change variable
					change = temp;
					//break the loop
					break;
				}
				
				if(temp.getLeft() != null) {
					queue.add(temp.getLeft());
				}
				if(temp.getRight() != null) {
					queue.add(temp.getRight());
				}
			}
			//use add games method from game list class to add the passed game
			change.getGames().addGame(game);
		}
	}

	/**
	 * You or one of your friends achieved a new trophy! This method should add it to
	 * their trophies.
	 * @param username The user who has earned a new trophy
	 * @param trophy The trophy to be added   
	 */
	public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
		if((username == null) || (trophy == null)) {
			throw new IllegalArgumentException();
		}
		else {
			Queue<User> queue = new LinkedList<User>();
			queue.add(root);
			
			User change = null;
			while(!queue.isEmpty()) {
				User temp = queue.poll();
				if(temp.getUsername() == username) {
					change = temp;
					break;
				}
				
				if(temp.getLeft() != null) {
					queue.add(temp.getLeft());
				}
				if(temp.getRight() != null) {
					queue.add(temp.getRight());
				}
			}
			//use array list add method to add the trophy to the trophies 
			change.getTrophies().add(trophy);
		}
	}

	/**
	 * You or one of your friends has gained one level! This is great news, except that
	 * it may have ruined your tree structure! A node move may be in order.
	 * @param username The user whose level has increased
	 */
	public void levelUp(String username) throws IllegalArgumentException {
		if(username == null){
			throw new IllegalArgumentException();
		}
		else {
			Queue<User> queue = new LinkedList<User>();
			queue.add(root);
			
			User change = null;
			while(!queue.isEmpty()) {
				User temp = queue.poll();
				if(temp.getUsername() == username) {
					change = temp;
					break;
				}
				
				if(temp.getLeft() != null) {
					queue.add(temp.getLeft());
				}
				if(temp.getRight() != null) {
					queue.add(temp.getRight());
				}
			}
			//remove the user with old level from the tree using de friend method
			this.deFriend(change);
			//one level up from current to the user
			change.setLevel(change.getLevel()+1);
			//add the user back to the tree
			this.beFriend(change);
		}
	}

	//Following methods are helper methods for adding data for AVL tree
	/**
	 * It return the balance of the passed object by subtracting the height of right user from left
	 * return 0 if user is null
	 * @param user
	 * @return the balance
	 */
	public int getBalance(User user) {
		if(user != null) {
			return (getHeight(user.getLeft())-getHeight(user.getRight()));
		}
		return 0;
	}
	
	/**
	 * It return the height of the passed user object
	 * @param n
	 * @return height
	 */
	public int getHeight(User user) {
		if (user != null) {
			return user.getHeight();
		}
		return 0;
	}
	
	/**
	 * This method rotate the node into right turn 
	 * @param user
	 * @return user
	 */
	public User rightRotate(User user) {
		//store the user on the left side of passed user object
		User fstUser = user.getLeft();
		//store user on the right side of above user object
		User secOne = fstUser.getRight();
		
		//rotate the tree
		fstUser.setRight(user);
		user.setLeft(secOne);
		
		//adjust the height
		fstUser.setHeight(Math.max(getHeight(fstUser.getLeft()), getHeight(fstUser.getRight()))+1);
		user.setHeight(Math.max(getHeight(user.getLeft()), getHeight(user.getRight()))+1);
		return fstUser;
	}
	
	public User leftRotate(User user) {
		User fstUser = user.getRight();
		User secOne = fstUser.getLeft();
		
		//rotate the tree
		fstUser.setLeft(user);
		user.setRight(secOne);
		
		
		//adjust the height again
		user.setHeight(Math.max(getHeight(user.getLeft()), getHeight(user.getRight()))+1);
		fstUser.setHeight(Math.max(getHeight(fstUser.getLeft()), getHeight(fstUser.getRight()))+1);
		
		return fstUser;
	}
	
	/**
	 * This method recursively add the user into the AVL tree
	 * @param root
	 * @param change
	 * @return
	 */
	public User insert(User root, User change) {
		if(root == null) {
			return change;
		}
	    if(change.getKey() < root.getKey()){
	    	root.setLeft(insert(root.getLeft(), change));
	    }
	    else if(change.getKey() > root.getKey()){
	    	root.setRight(insert(root.getRight(), change)); 
	    }
	    
		// update the node height
	    root.setHeight(Math.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1);

	    //get the balance different
		int balDiff = getBalance(root);

		// Do left tree rotation
		if (balDiff > 1 &&  change.getKey()< root.getLeft().getKey()) {
			return rightRotate(root);
		}

		// Right tree rotation
		if (balDiff < -1 && change.getKey() > root.getRight().getKey()) {
			return leftRotate(root);
		}

		// Left Right Rotation 
		if (balDiff > 1 && change.getKey() > root.getLeft().getKey()) {
			root.setLeft(leftRotate(root.getLeft()));
			return rightRotate(root);
		}

		// Right Left Tree rotation
		if (balDiff < -1 && change.getKey() < root.getRight().getKey()) {
			root.setRight(rightRotate(root.getRight())); 
			return leftRotate(root);
		}
		return root;
	}
	
	/**
	 * As your friends list grows, adding with regular binary tree rules will
	 * result in an unbalanced and inefficient tree. One approach to fix this
	 * is to implement an add method that uses AVL balancing. This method should
	 * work in the same way as beFriend, but maintain a balanced tree according to
	 * AVL rules.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		
		if(friend == null) {
			throw new IllegalArgumentException();
		}
		else {
			//use recursive method written above to add the user into AVL tree
			root = insert(root,friend);
			//return true
			return true;
		}
	}

	/**
	 * A nice, neat print-out of your friends would look great in the secret scrap-book
	 * that you keep hidden underneath your pillow. This method should print out the
	 * details of each user, traversing the tree in order.
	 * @return A string version of the tree
	 */
	public String toString() {
		String left = "";
		String right = "";

		Stack<User> so = new Stack<User>(); 
        User curr = root; 
        // iterate through the tree
        while (curr != null || so.size() > 0) 
        { 
  
            /* reach to left side of the tree */
            while (curr !=  null) 
            { 
                //push the user object into the stack before traverse
                so.push(curr); 
                //go the left again
                curr = curr.getLeft(); 
            } 
  
            //current is null and loaded with top of stack
            curr = so.pop(); 

            if(root.getKey() < curr.getKey()) {
            	if(curr.getLeft()!= null || curr.getRight() != null) {
	            	if(so.size()>0 || curr.getRight() != null) {
	            		//loaded into right string
		            	right += curr.toString() + "\n";
	            	}
		            else {
		            	right += curr.toString();
		            }
		            	
	            }
            	else {
            		if(so.size()>0) {
            			right += curr.toString() + "\n";
            		}
            		else {
            			right += curr.toString();
            		}
            	}
            }
            else {
            	//if tree is on left side, add to s
            	left += curr.toString() + "\n";
            }
            
  
            
            //go to right after visiting left
            curr = curr.getRight(); 
        } 
        //Concatenate both string variable
		return left + right;
	}
}
