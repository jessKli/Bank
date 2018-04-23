package bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//meaning it’s ready for use by Spring MVC to handle web requests
@RestController
public class BankController {
	@Autowired
	CustomerService service;

	@RequestMapping ("/")
	public String index() {		
		return "Welcome to our bank";
	}

	@RequestMapping ("/CreateNewCustomer")
	public String createNewCustomer() {
		return service.createNewCustomer();
	}
	
	@RequestMapping ("/GetCustomer")
	public Customer getCustomer() {
		return service.getCustomer();
	}
	
	@RequestMapping ("/DeleteCustomer")
	public void deleteCustomer() {
		 service.deleteCustomer();
	}
	@RequestMapping ("/ChangePwd")
	public String changePwdForCustomer() {		 
		 return service.changePwdForCustomer();
	}
}
