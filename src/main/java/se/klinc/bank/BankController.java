package se.klinc.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.klinc.bank.dao.Account;
import se.klinc.bank.dao.Customer;
import se.klinc.bank.dao.Transaction;
import se.klinc.bank.service.AccountService;
import se.klinc.bank.service.BankService;
import se.klinc.bank.service.CustomerService;
import se.klinc.bank.service.TransactionService;

import java.util.ArrayList;
import java.util.List;


//meaning it’s ready for use by Spring MVC to handle web requests
@RestController
public class BankController {

    @Autowired
    BankService bankservice;
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;

    @RequestMapping("/")
    public String welcome() {
        return "Welcome";
    }


//    http://localhost:8080/getAllUsers
    @RequestMapping("/getAllUsers")
    public List<String> GetUsers() {
        List<Customer> custList = customerService.getAllCustomers();
        List<String> idList = new ArrayList<>();
        custList.stream().sorted((c1, c2) -> c1.getIdNumber().compareTo(c2.getIdNumber())).forEach(c1 -> idList.add(c1.getIdNumber()));
        return idList;
    }

    //http://localhost:8080/getAllAccountsAndTransForUser?idNumber=8
    @RequestMapping("/getAllAccountsAndTransForUser")
    public String GetAccountsAndTransForUser(@RequestParam(value = "idNumber") String id) {
        //get all accounts for user
        List<Account> accountList = accountService.getAllAccountsForCustomer(id);
        StringBuilder sb = new StringBuilder();
        //first loop get the account from accountlist,add name to sb, and then get the all transactionList
//        second loop get all the transactions for the given account and append
        accountList.forEach(a ->
        {
            sb.append("Account: " + a.getAccountName());
            transactionService.getAllTransactionsForAccount(a.getAccountId()).forEach(t -> sb.append("Tran: " + t.toString()));
        });


        return sb.toString();
    }

    @RequestMapping("/GetUserByUserIdAndPwd")
    public String index(@RequestParam(value = "name", defaultValue = "DummyName") String name, @RequestParam(value = "pwd", defaultValue = "DummyPwd") String pwd) {

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
