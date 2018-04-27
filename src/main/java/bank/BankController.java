package bank;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//meaning it’s ready for use by Spring MVC to handle web requests
@RestController
public class BankController {
	@Autowired
	CustomerService custService;
	@Autowired
	AccountService accountService;

	@RequestMapping ("/")
	public String index() {		
		return "Welcome to our bank";
	}

//	@RequestMapping ("/CreateNewCustomer")
//	public String createNewCustomer() {
//		return custService.createNewCustomer();
//	}
	
//	@RequestMapping ("/GetCustomer")
//	public Customer getCustomer() {
//		return custService.getCustomer();
//	}
	
//	@RequestMapping ("/DeleteCustomer")
//	public void deleteCustomer() {
//		custService.deleteCustomer();
//	}
	@RequestMapping ("/ChangePwd")
	public String changePwdForCustomer() {		 
		 return custService.changePwdForCustomer();
	}
	
//	@RequestMapping ("/CreateAccount")
//	public String createAccount() {	
//		String returnMessage=null;
//		//first check if user is a customer
//		Customer cust=custService.getCustomer();
//		if(cust==null) {
//			returnMessage="You are not a customer, please create a customer first";
//		}
//		else {
//			accountService.createAccount();
//		}
//		getAllCustomers();
//		getAllAccountsForCustomer(cust.getIdNumber());
//		return returnMessage;
//	}
	
	private void getAllCustomers() {
		System.out.println("All customers in the bank");
		custService.getAllCustomers();
	}
	private void getAllAccountsForCustomer(String birth) {
		System.out.println("All accounts in the bank");
		accountService.getAllAccountsForCustomer(birth);
	}
}
