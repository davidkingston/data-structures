
public class Main {
	public static void main(String[] args) {
		IAddressBookInterface addressBook = null;
		switch (args[0]) {
		case "HashTableAdd": 
			addressBook = new AddressBookHashTable();
			RunAddTestCases(addressBook);
			break;
		case "HashTableRemove": 
			addressBook = new AddressBookHashTable();
			RunRemoveTestCases(addressBook);
			break;
		case "HashTableFind": 
			addressBook = new AddressBookHashTable();
			RunFindTestCases(addressBook);
			break;
		case "TreeAdd":
			addressBook = new AddressBookTree();
			RunAddTestCases(addressBook);
			break;
		case "TreeRemove":
			addressBook = new AddressBookTree();
			RunRemoveTestCases(addressBook);
			break;
		case "TreeFind":
			addressBook = new AddressBookTree();
			RunFindTestCases(addressBook);
			break;
		}
	}
	
	private static void RunAddTestCases(IAddressBookInterface addressBook) {
		addressBook.Add("Bob", "Smith", "bsmith@somewhere.com", "555-235-1111");
		addressBook.Add("Jane", "Williams", "jw@something.com", "555-235-1112");
		addressBook.Add("Mohammed", "al-Salam", "mas@someplace.com", "555-235-1113");
		addressBook.Add("Pat", "Jones", "pjones@homesweethome.com", "555-235-1114");
		addressBook.Add("Billy", "Kidd", "billy_the_kid@nowhere.com", "555-235-1115");
		addressBook.Add("H.", "Houdini", "houdini@noplace.com", "555-235-1116");
		addressBook.Add("Jack", "Jones", "jjones@hill.com", "555-235-1117");
		addressBook.Add("Jill", "Jones", "jillj@hill.com", "555-235-1118");
		addressBook.Add("John", "Doe", "jdoe@somedomain.com", "555-235-1119");
		addressBook.Add("Jane", "Doe", "jdoe@somedomain.com", "555-235-1120");
		addressBook.Add("Test", "Case", "Test_Case@testcase.com", "555-235-1121");
		addressBook.Add("Nadezhda", "Kanachekhovskaya", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru", "555-235-1122");
		//addressBook.Add("Jo", "Wu", "wu@h.com", "555-235-1123");
		//addressBook.Add("Millard", "Fillmore", "millard@theactualwhitehouse.us", "555-235-1124");
		//addressBook.Add("Bob", "vanDyke", "vandyke@nodomain.com", "555-235-1125");
		//addressBook.Add("Upside", "Down", "upsidedown@rightsideup.com", "555-235-1126");
	}

	private static void RunRemoveTestCases(IAddressBookInterface addressBook) {
		addressBook.Add("Bob", "Smith", "bsmith@somewhere.com", "555-235-1111");
		addressBook.Add("Jane", "Williams", "jw@something.com", "555-235-1112");
		addressBook.Add("Mohammed", "al-Salam", "mas@someplace.com", "555-235-1113");
		addressBook.Add("Pat", "Jones", "pjones@homesweethome.com", "555-235-1114");
		addressBook.Add("Billy", "Kidd", "billy_the_kid@nowhere.com", "555-235-1115");
		addressBook.Add("H.", "Houdini", "houdini@noplace.com", "555-235-1116");
		addressBook.Add("Jack", "Jones", "jjones@hill.com", "555-235-1117");
		addressBook.Add("Jill", "Jones", "jillj@hill.com", "555-235-1118");
		//addressBook.Add("John", "Doe", "jdoe@somedomain.com", "555-235-1119");
		//addressBook.Add("Jane", "Doe", "jdoe@somedomain.com", "555-235-1120");
		//addressBook.Add("Test", "Case", "Test_Case@testcase.com", "555-235-1121");
		//addressBook.Add("Nadezhda", "Kanachekhovskaya", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru", "555-235-1122");
		//addressBook.Add("Jo", "Wu", "wu@h.com", "555-235-1123");
		//addressBook.Add("Millard", "Fillmore", "millard@theactualwhitehouse.us", "555-235-1124");
		//addressBook.Add("Bob", "vanDyke", "vandyke@nodomain.com", "555-235-1125");
		//addressBook.Add("Upside", "Down", "upsidedown@rightsideup.com", "555-235-1126");
		addressBook.GetEntry("Jack", "Jones");
		addressBook.GetEntry("H.", "Houdini");
		addressBook.GetEntry("Ulysses", "Grant");
	}

	private static void RunFindTestCases(IAddressBookInterface addressBook) {
		addressBook.Add("Bob", "Smith", "bsmith@somewhere.com", "555-235-1111");
		addressBook.Add("Jane", "Williams", "jw@something.com", "555-235-1112");
		addressBook.Add("Mohammed", "al-Salam", "mas@someplace.com", "555-235-1113");
		addressBook.Add("Pat", "Jones", "pjones@homesweethome.com", "555-235-1114");
		addressBook.Add("Billy", "Kidd", "billy_the_kid@nowhere.com", "555-235-1115");
		addressBook.Add("H.", "Houdini", "houdini@noplace.com", "555-235-1116");
		addressBook.Add("Jack", "Jones", "jjones@hill.com", "555-235-1117");
		addressBook.Add("Jill", "Jones", "jillj@hill.com", "555-235-1118");
		//addressBook.Add("John", "Doe", "jdoe@somedomain.com", "555-235-1119");
		//addressBook.Add("Jane", "Doe", "jdoe@somedomain.com", "555-235-1120");
		addressBook.Remove("Pat", "Jones");
		addressBook.Remove("H.", "Houdini");
		addressBook.Remove("Pat", "Jones");
		addressBook.Remove("Ulysses", "Grant");
	}
}
