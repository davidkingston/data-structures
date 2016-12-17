// DAK: a sequentially allocated list to be used as a hash bucket.
// The list allows us to store multiple items in the same
// bucket when we get hash collisions.
public class LinkedList {
	private Node _firstNode;
	
	// DAK: add a new node as the first node in the chain
	public void Add(String key, AddressBookEntry entry) {
		Node newNode = new Node(key, entry, this._firstNode);
		this._firstNode = newNode;
	}
	
	// DAK: return the entry corresponding to the specified key
	public AddressBookEntry GetEntry(String key) {
		AddressBookEntry entry = null;
		boolean found = false;
		
		// DAK: walk the chain until we find the one we're looking for or we reach the end of the chain.
		Node currentNode = this._firstNode;
		while (!found && (currentNode != null)) {
			if (currentNode.get_key().equals(key)) {
				entry = currentNode.get_value();
				found = true;
			}
			currentNode = currentNode.get_nextNode();
		}
		return entry;
	}

	// DAK: remove the node corresponding to the specified key
	public boolean Remove(String key) {
		Node previousNode = null;
		boolean found = false;
		
		// DAK: walk the chain until we find the one we're looking for or we reach the end of the chain.
		Node currentNode = this._firstNode;
		while (!found && (currentNode != null)) {
			if (currentNode.get_key().equals(key)) {
				if (previousNode == null) {
					this._firstNode = currentNode.get_nextNode();
				} else {
					// DAK: update the previous node to point to the next node, effectively removing the current node from the chain.
					previousNode.set_nextNode(currentNode.get_nextNode());
				}
				found = true;
			}
			previousNode = currentNode;
			currentNode = currentNode.get_nextNode();
		}
		
		return found;
	}
}

class Node {
    private String _key;
    private AddressBookEntry _value;
    private Node _nextNode;

    public Node (String key, AddressBookEntry value, Node nextNode) {
    	this._key = key;
    	this._value = value;
    	this._nextNode = nextNode;
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
	
	public Node get_nextNode()	{
		return this._nextNode;
	}
	
	public void set_nextNode(Node node) {
		this._nextNode = node;
	}
}
