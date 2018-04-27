package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repository;
	@Autowired
	AccountService accountService;

	public String createNewCustomer() {
		String returnString;
		String[] args = getUserInput(false);
		if (okToCreateUser(args[0])) {
			repository.insert(new Customer(args[0], args[1], args[2], args[3]));
			returnString = "Customer is created";
		} else {
			returnString = "Customer already exist";
		}
		return returnString;
	}
	
	public Customer getCustomer() {
		System.out.print(repository.findAll() + "\n");
		String[] args = getUserInput(true);
		return repository.findByIdNumber(args[0]);
	}

	public String deleteCustomer() {
		String returnString = null;
		List<Account> customerAccounts = new ArrayList<>();
		String[] args = getUserInput(true);
		// check if customer is a customer in bank
		Customer cust = repository.findByIdNumber(args[0]);
		if (cust == null) {
			returnString = "Customer is not a customer";
		} else {
			// get customers accounts in bank
			customerAccounts = accountService.getAllAccountsForCustomer(args[0]);
			// if customer doesnt have any accounts, ok to delete customer
			if (customerAccounts.size() == 0) {
				repository.delete(cust);
				returnString = "Customer is deleted";
			}
			// if customer has accounts, customer must active delete them first
			else {
				returnString="Customer needs to delete the accounts first";
				for (Account account : customerAccounts) {
					returnString = returnString + "\n" + account.getAccountName();
				}
			}
		}
		return returnString;
//		//first, find all users in db, easier to delete then
//		System.out.print(repository.findAll() + "\n");
//		String[] args = getUserInput(true);
//		repository.delete(repository.findByIdNumber(args[0]));
//		System.out.print(repository.findAll());
	}
	
	public String changePwdForCustomer() {
		String returnString;
		System.out.print(repository.findAll() + "\n");
		String[] args = getUserInput(true);
		Customer cust=repository.findByIdNumber(args[0]);
		if (cust!=null) {
			Random rand=new Random();
			String newPwd=cust.getPassWord()+rand.nextInt();
			cust.setPassWord(newPwd);
			repository.save(cust);
			returnString="New pwd for customer:"+cust.getIdNumber()+" is "+newPwd;
		}else {
			returnString="Customer doesnt exist so impossible to change pwd";
		}
		System.out.print(repository.findAll() + "\n");
		return returnString;
	}
	
	public void getAllCustomers() {
		System.out.println(repository.findAll());
	}

	private String[] getUserInput(boolean justIdInputFromUser) {
		String[] args = new String[] { "", "", "", "" };
		Scanner scan = new Scanner(System.in);
		System.out.print("Ange personnummer: ");
		args[0] = scan.nextLine();
		if (!justIdInputFromUser) {
			System.out.print("Ange förnamn: ");
			args[1] = scan.nextLine();
			System.out.print("Ange efternamn: ");
			args[2] = scan.nextLine();
			System.out.print("Välj lösenord: ");
			args[3] = scan.nextLine();
		}
		return args;
	}

	private boolean okToCreateUser(String birth) {
		boolean createNewUser = true;
		if (repository.findByIdNumber(birth) != null) {
			createNewUser = false;
		}
		return createNewUser;
	}

	
}
