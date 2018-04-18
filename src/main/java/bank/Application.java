package bank;

import java.io.Console;
import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.Query;
@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	private Repository repository;

	public static void main(String []args) {

		SpringApplication.run(Application.class, args);

	}

	public void run(String... args) throws Exception {
		//loop through all elements in customer table
		System.out.println(repository.findAll());
		//create new user if idNumber doesnt exist
		String birth="1996-03-24-9991";
		String first="Rasmus";
		String last="Forsten-Klinc";
		String pwd="dummyPwd";
		boolean createNewUser=true;
			if(repository.findByIdNumber(birth)!=null) {
				System.out.println("User already exist");
				createNewUser=false;
		
		}
		if(createNewUser) {
			repository.insert(new Customer(birth,first, last,pwd));
		}
		
		//change password on the new customer
		Customer cust=repository.findByIdNumber(birth);
		cust.setPassWord("pwdIsChanged");
		repository.save(cust);		
		
		//delete this new customer
		repository.deleteById(repository.findByIdNumber(birth).getId());
		
		
	}
	
	
}
