package se.klinc.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.klinc.bank.service.BankService;


//meaning it’s ready for use by Spring MVC to handle web requests
@RestController
public class BankController {
	
	@Autowired
	BankService bankservice;

	@RequestMapping ("/")
	public String welcome() {
		return "Welcome";
	}
	
	@RequestMapping ("/GetUserByUserIdAndPwd")
	public String index(@RequestParam(value="name", defaultValue="DummyName") String name, @RequestParam(value="pwd", defaultValue="DummyPwd") String pwd) {
		
		return bankservice.getGetCustomerByIdAndPwd(name, pwd);
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
//	@RequestMapping ("/ChangePwd")
//	public String changePwdForCustomer() {		 
//		 return custService.changePwdForCustomer();
//	}
	
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
	
//	private void getAllCustomers() {
//		System.out.println("All customers in the bank");
//		custService.getAllCustomers();
//	}
//	private void getAllAccountsForCustomer(String birth) {
//		System.out.println("All accounts in the bank");
//		accountService.getAllAccountsForCustomer(birth);
//	}
}
