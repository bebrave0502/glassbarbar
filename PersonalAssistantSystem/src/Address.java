import java.io.Serializable;

public class Address implements Serializable {
	private String name;
	private String phoneNumber;
	
	public Address(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		String description;
		description = "name: " + name +" / ";
		description += "phone number: " + phoneNumber + "\n";
		return description;
	}		
}