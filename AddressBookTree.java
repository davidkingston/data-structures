// DAK: a binary tree implementation of the address book.
public class AddressBookTree implements IAddressBookInterface
{
	private TreeNode _rootNode = null;
	private int _count = 0;
	
	// DAK: adds a new entry to the tree
	public void Add(String firstName, String lastName, String emailAddress, String phoneNumber) {
		System.out.format("Adding %s %s.\n", firstName, lastName);
		
    	// DAK: create a new entry
		AddressBookEntry entry = new AddressBookEntry(firstName, lastName, emailAddress, phoneNumber);
		
		// DAK: create a case-insensitive key
		String key = firstName.toUpperCase() + lastName.toUpperCase();
		
		// DAK: if the tree is empty, make the new entry the root
		if (this._rootNode == null) {
			this._rootNode = new TreeNode(key, entry);
		// DAK: otherwise add the new entry to the root node
		} else {
			this.AddEntry(this._rootNode, key, entry);
		}
		
		// DAK: increment the count
		this._count ++;
		System.out.format("Successfully added %s %s. Current count = %d.\n\n", firstName, lastName, _count);
	}

	// DAK: removes the appropriate entry for the specified first and last name
	public void Remove(String firstName, String lastName) {
		System.out.format("Removing %s %s.\n", firstName, lastName);
		
		// DAK: create a case-insensitive key
		String key = firstName.toUpperCase() + lastName.toUpperCase();
		
		// DAK: create a new wrapper class that's used to pass the removed entry via a parameter
		RemovedEntry removedEntry = new RemovedEntry();
		
		// DAK: initiate the recursive removal
		this._rootNode = this.RemoveEntry(this._rootNode, key, removedEntry);
		
		// DAK: report the results
		if (removedEntry.get_value() == null) {
			System.out.format("Could not find an entry for %s %s. Current count = %d.\n\n", firstName, lastName, _count);
		} else {
			_count --;
			System.out.format("Successfully removed %s %s. Current count = %d.\n\n", firstName, lastName, _count);
		}
	}

	// DAK: retrieves the appropriate entry for the specified first and last name
	public AddressBookEntry GetEntry(String firstName, String lastName) {
		System.out.format("Searching for %s %s.\n", firstName, lastName);
		
		// DAK: create a case-insensitive key
		String key = firstName.toUpperCase() + lastName.toUpperCase();
		
		AddressBookEntry result = null;
		
		// DAK: if the tree is empty, no search is needed
		if (this._rootNode != null) {
			// DAK: initiate the recursive search
			result = this.GetEntry(this._rootNode, key); 
		}
		
		// DAK: report the results
		if (result == null) {
			System.out.format("Could not find an entry for %s %s.\n\n", firstName, lastName);
		} else {
			System.out.format("Successfully found %s %s with email address %s.\n\n", result.GetFirstName(), result.GetLastName(), result.GetEmailAddress());
		}
		
		return result;
	}
	
	// DAK: A recursive method to locate the node to be removed.
	private TreeNode RemoveEntry(TreeNode node, String key, RemovedEntry removedEntry) {
		if (node != null) {
			// DAK: if the key equals the current node, remove the current node
			if (key.equals(node.get_key())) {
				System.out.format("  %s = %s. Removing the current node.\n", key, node.get_key());
				// DAK: return the entry being removed
				removedEntry.set_value(node.get_value()); 
				// DAK: remove the located node
				node = this.RemoveNode(node); 
			// DAK: if the key is less than the current node, search the left path
			} else if (key.compareTo(node.get_key()) < 0) {
				System.out.format("  %s < %s. Searching the left path.\n", key, node.get_key());
				// DAK: search down the left path and update this node's left node with the updated return
				node.set_leftNode(this.RemoveEntry(node.get_leftNode(), key, removedEntry));
			// DAK: if the key is greater than the current node, search the right path
			} else {
				System.out.format("  %s > %s. Searching the right path.\n", key, node.get_key());
				// DAK: search down the right path and update this node's right node with the updated return
				node.set_rightNode(this.RemoveEntry(node.get_rightNode(), key, removedEntry));
			}
		}
		
		return node;
	}
	
	// DAK: Removes the specified node from the tree structure.
	private TreeNode RemoveNode(TreeNode node) {
		// DAK: this node has two children
		if ((node.get_leftNode() != null) && (node.get_rightNode() != null)) {
			// DAK: find the smallest value of the right (larger) child (effectively the next largest value)
			TreeNode smallestNode = this.NodeWithTheSmallestKey(node.get_rightNode());
			
			// DAK: set this node to the value of that next largest node
			node.set_key(smallestNode.get_key());
			node.set_value(smallestNode.get_value());
			
			// DAK: remove that next largest node from it's original place
			node = this.RemoveTheNodeWithTheSmallestKey(smallestNode);
		// DAK: this node has only a left child, so replace this node with that left node
		} else if (node.get_leftNode() != null) {
			node = node.get_leftNode();
		// DAK: this node has only a right child or no children, so replace this node with that right node
		} else {
			node = node.get_rightNode();
		}
		return node;
	}
	
	// DAK: A recursive method that finds the smallest value for the specified node (the farthest left).
	private TreeNode NodeWithTheSmallestKey(TreeNode node) {
		TreeNode leftNode = node.get_leftNode();
		
		if (leftNode != null) {
			// DAK: if this node has a left node, keep going.
			node = this.NodeWithTheSmallestKey(leftNode);
		}
		
		return node;
	}
	
	// DAK: A recursive method to remove the smallest value for the specified node (the farthest left).
	private TreeNode RemoveTheNodeWithTheSmallestKey(TreeNode node) {
		TreeNode leftNode = node.get_leftNode();
		
		if (leftNode != null) {
			// DAK: if this node has a left node, keep going.
			node = this.RemoveTheNodeWithTheSmallestKey(leftNode);
			// DAK: update this node's left node
			node.set_leftNode(leftNode);
		} else {
			// DAK: get this node's right node, which will sometimes be null.
			node = node.get_rightNode();
		}
		
		return node;
	}
	
	// DAK: A recursive method to find the specified key in the specified root node.
	private AddressBookEntry GetEntry(TreeNode node, String key) {
		AddressBookEntry result = null;
		
		// DAK: if the key equals the current node, return the current node's value
		if (key.equals(node.get_key())) {
			result = node.get_value();
			// DAK: if the key is less than the current node, search the left path
		} else if (key.compareTo(node.get_key()) < 0) {
			TreeNode leftNode = node.get_leftNode();
			if (leftNode != null) {
				System.out.format("  %s < %s. Searching the left path.\n", key, node.get_key());
				result = this.GetEntry(leftNode, key);
			}
		// DAK: if the key is greater than the current node, search the right path
		} else {
			TreeNode rightNode = node.get_rightNode();
			if (rightNode != null) {
				System.out.format("  %s > %s. Searching the right path.\n", key, node.get_key());
				result = this.GetEntry(rightNode, key);
			}
		}
		return result;
	}

	// DAK: A recursive method to add the specified entry to the specified root node.
	private void AddEntry (TreeNode node, String key, AddressBookEntry value) {
		// DAK: if the key equals the current node, update the current node
		if (key.equals(node.get_key())) {
			System.out.format("  %s = %s. Updating the current node.\n", key, node.get_key());
			node.set_value(value);
		// DAK: if the key is less than the current node, add it to the left node
		} else if (key.compareTo(node.get_key()) < 0) {
			TreeNode leftNode = node.get_leftNode();
			if (leftNode == null) {
				System.out.format("  %s < %s. Adding a new left node.\n", key, node.get_key());
				leftNode = new TreeNode(key, value);
				node.set_leftNode(leftNode);
			} else {
				System.out.format("  %s < %s. Adding to the left path.\n", key, node.get_key());
				this.AddEntry(leftNode, key, value);
			}
		// DAK: if the key is greater than the current node, add it to the right node
		} else {
			TreeNode rightNode = node.get_rightNode();
			if (rightNode == null) {
				System.out.format("  %s > %s. Adding a new right node.\n", key, node.get_key());
				rightNode = new TreeNode(key, value);
				node.set_rightNode(rightNode);
			} else {
				System.out.format("  %s > %s. Adding to the right path.\n", key, node.get_key());
				this.AddEntry(rightNode, key, value);
			}
		}
	}
}

// DAK: wrapper class to support returning the removed entry via a parameter
class RemovedEntry {
	AddressBookEntry _value;

	public AddressBookEntry get_value() {
		return _value;
	}

	public void set_value(AddressBookEntry _value) {
		this._value = _value;
	}
}

class TreeNode 
{
	private TreeNode _leftNode;
	private TreeNode _rightNode;
	private String _key;
	private AddressBookEntry _value;
	
	public TreeNode (String key, AddressBookEntry value) {
		this._key = key;
		this._value = value;
	}
	public TreeNode get_leftNode() {
		return this._leftNode;
	}
	public void set_leftNode(TreeNode leftNode) {
		this._leftNode = leftNode;
	}
	public TreeNode get_rightNode() {
		return this._rightNode;
	}
	public void set_rightNode(TreeNode rightNode) {
		this._rightNode = rightNode;
	}
	public String get_key() {
		return this._key;
	}
	public void set_key(String key) {
		this._key = key;
	}
	public AddressBookEntry get_value() {
		return this._value;
	}
	public void set_value(AddressBookEntry value) {
		this._value = value;
	}
	
}