package my.RestClient.Model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.mapping.Document;

@ComponentScan
@Document(collection = "clientCustomer")
public class ClientCustomer {
	private String name;
	private int rollno;
	private String email;
//-------------------------------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//-----------------------------------------	
//		public ClientCustomer(String name, int rollno, String email) {
//		super();
//		this.name = name;
//		this.rollno = rollno;
//		this.email = email;
//	}
 
}
