package bank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {
	@Id
	public String id;
	public String firstName;
	public String lastName;
	public String password;
	public Customer() {
		System.out.println("H");
	}
	
	 @Override
	    public String toString() {
	        return String.format(
	                "Customer[id=%s, firstName='%s', lastName='%s',passWord=%s]",
	                id, firstName, lastName,password);
	    }
}
