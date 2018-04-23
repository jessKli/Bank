package bank;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//meaning it’s ready for use by Spring MVC to handle web requests
@RestController
public class BankController {
	@Autowired
	CustomerRepository repository;

	@RequestMapping ("/")
	public String index() {
		
		return "Welcome to our bank";
	}
	
	@RequestMapping ("/CreateNewCustomer")
	public String createNewCustomer() {
		Scanner scan=new Scanner(System.in);
		System.out.print("Ange personnummer: ");
		String birth=scan.nextLine();
		if(repository.findByIdNumber(birth)!=null) {
			System.out.println("User already exist");	
		}
		else {
			System.out.print("Ange förnamn: ");
			String first=scan.nextLine();
			System.out.print("Ange efternamn: ");
			String last=scan.nextLine();
			System.out.print("Välj lösenord: ");
			String pwd=scan.nextLine();
			repository.insert(new Customer(birth,first, last,pwd));
			System.out.println("User is created");	
		}
		return "Create new customer";
	}
}
