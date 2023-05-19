package sandy.springBootProject.model;

import org.springframework.data.mongodb.core.mapping.Document;


public class ClientCustomerDetails {
	private String name;
	private String email;

	private int rollno;
//---constructor using field-----------------------
	
//	public CustomerDetails(String name, String email) {
//	super();
//	this.name = name;
//	this.email = email;

//}
//---------------------------	
    public String getName() {  
    return name;
    }
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	
	
	
	
//--constructor is used when all fields of model class are used always----	

	

}
/* primary key automatic generated or 
 * we can use sequence generator
 */ 
