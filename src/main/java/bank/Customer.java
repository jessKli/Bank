package bank;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public  class Customer {
	@Id
	private String id;
	private String idNumber;
	private String firstName;
	private String lastName;
	private String passWord;
	
	public Customer() {
		
	}
	public Customer(String birthDate,String first, String last, String pwd) {
		this.idNumber=birthDate;
		this.firstName=first;
		this.lastName=last;
		this.passWord=pwd;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	public String getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setPassWord(String pwd) {
		passWord=pwd;
	}
	public String getPassWord() {
		return passWord;
	}
	
	
	 @Override
	    public String toString() {
	        return String.format(
	                "Customer:[id=%s,idNumber=%s, firstName='%s', lastName='%s',passWord=%s]\n",
	                id, idNumber,firstName, lastName,passWord);
	    }
	 
	 
}
