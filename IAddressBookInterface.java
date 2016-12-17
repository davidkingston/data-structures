
public interface IAddressBookInterface
{

	public void Add(String firstName, String lastName, String emailAddress, String phoneNumber);
	
	public void Remove(String firstName, String lastName);
	
	public AddressBookEntry GetEntry(String firstName, String lastName);
}
