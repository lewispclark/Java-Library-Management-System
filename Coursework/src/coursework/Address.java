package coursework;

public class Address {
	// Attributes
	private int houseNum;
	private String postcode;
	private String city;
	
	// Constructor
	public Address(int houseNum, String postcode, String city){
		this.houseNum = houseNum;
		this.postcode = postcode;
		this.city = city;
	}
	
	// Getters
	public int getHouseNum() {
		return this.houseNum;
	}
	
	public String getPostcode() {
		return this.postcode;
	}
	
	public String getCity() {
		return this.city;
	}
	
}
