import java.io.Serializable;
import java.util.Date;

class Account implements Serializable {
	private String id;	
	private String password;
	
	public Account(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public void edit(String id, String password) {
		setId(id);
		setPassword(password);		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		String description;	 	
		description = "name : " + id;	
		return description;
	}
}