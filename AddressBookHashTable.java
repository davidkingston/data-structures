// DAK: a hash table implementation of the address book.
public class AddressBookHashTable implements IAddressBookInterface
{
    private final int HASHTABLELENGTH = 13;
    private int _count = 0;
    private LinkedList[] _hashTable = null;
    
    public AddressBookHashTable() {
    	// DAK: initialize the hashtable
    	_hashTable = new LinkedList[HASHTABLELENGTH];
    }
    
    // DAK: adds a new entry to the hashtable
    public void Add(String firstName, String lastName, String emailAddress, String phoneNumber) {
		System.out.format("Adding %s %s.\n", firstName, lastName);
		
    	// DAK: create a new entry
    	AddressBookEntry entry = new AddressBookEntry(firstName, lastName, emailAddress, phoneNumber);
    	
		// DAK: create a case-insensitive key
		String key = firstName.toUpperCase() + lastName.toUpperCase();
		
		// DAK: locate the appropriate hashbucket
		LinkedList LL = this.LocateHashBucket(key);
		
		// DAK: add the new entry to the hashbucket
		LL.Add(key, entry);
		
		// DAK: increment the count
		_count++;
		System.out.format("Successfully added %s %s. Current count = %d.\n\n", firstName, lastName, _count);
   }
    
	// DAK: removes the appropriate entry for the specified first and last name
	public void Remove(String firstName, String lastName)
	{
		System.out.format("Removing %s %s.\n", firstName, lastName);
		// DAK: create a case-insensitive key
		String key = firstName.toUpperCase() + lastName.toUpperCase();
		// DAK: locate the appropriate hashbucket
		LinkedList LL = this.LocateHashBucket(key);
		// DAK: remove this key from the hashbucket
		if (LL.Remove(key)) {
			// DAK: decrement the count
			_count--;
			System.out.format("Successfully removed %s %s. Current count = %d.\n\n", firstName, lastName, _count);
		} else {
			System.out.format("Could not find an entry for %s %s.\n\n", firstName, lastName);
		}
	}

	// DAK: retrieves the appropriate entry for the specified first and last name
	public AddressBookEntry GetEntry(String firstName, String lastName)
	{
		System.out.format("Searching for %s %s.\n", firstName, lastName);
		
		// DAK: create a case-insensitive key
		String key = firstName.toUpperCase() + lastName.toUpperCase();
		// DAK: locate the appropriate hashbucket
		LinkedList LL = this.LocateHashBucket(key);
		// DAK: get the appropriate entry from the hashbucket
		AddressBookEntry entry = LL.GetEntry(key);
		if (entry == null) {
			System.out.format("Could not find an entry for %s %s.\n\n", firstName, lastName);
		} else {
			System.out.format("Successfully found %s %s with email address %s.\n\n", entry.GetFirstName(), entry.GetLastName(), entry.GetEmailAddress());
		}
		// DAK: return the entry
		return entry;
	}

	public int GetCount() {
		return this._count;
	}
	
	// DAK: returns the appropriate hashbucket for the specified key
	private LinkedList LocateHashBucket(String key) {
		// DAK: hash the key
		int hashIndex = this.GetHashIndex(key);
		// DAK: locate the appropriate hashbucket
		LinkedList LL = this._hashTable[hashIndex];
		if (LL == null) {
			LL = new LinkedList();
			this._hashTable[hashIndex] = LL;
		}
		return LL;
	}
	
	// DAK: returns the hashed value of the specified key
    private int GetHashIndex(String key) {
    	int hashIndex = key.hashCode() % HASHTABLELENGTH;
    	if (hashIndex < 0) {
    		// DAK: make sure the hash is positive.
    		hashIndex += HASHTABLELENGTH;
    	}
    	return hashIndex;
    }
}
