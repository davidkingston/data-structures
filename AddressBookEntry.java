// DAK: a simple model class.
public class AddressBookEntry
{
	private String _firstName;
	private String _lastName;
	private String _emailAddress;
	private String _phoneNumber;
	
	// DAK: overloaded constructor to populate the object when it's instantiated.
	public AddressBookEntry(String firstName, String lastName, String emailAddress, String phoneNumber)
	{
		this.SetFirstName(firstName);
		this.SetLastName(lastName);
		this.SetEmailAddress(emailAddress);
		this.SetPhoneNumber(phoneNumber);
	}
	
	// DAK: the default constructor is private so it can't be used externally.
	@SuppressWarnings("unused")
	private AddressBookEntry(){}
	
	public String GetFirstName()
	{
		return this._firstName;
	}
	
	public void SetFirstName(String value)
	{
		this._firstName = value;
	}
	
	public String GetLastName()
	{
		return this._lastName;
	}
	
	public void SetLastName(String value)
	{
		this._lastName = value;
	}
	
	public String GetEmailAddress()
	{
		return this._emailAddress;
	}
	
	public void SetEmailAddress(String value)
	{
		this._emailAddress = value;
	}
	
	public String GetPhoneNumber()
	{
		return this._phoneNumber;
	}
	
	public void SetPhoneNumber(String value)
	{
		this._phoneNumber = value;
	}
	
}
