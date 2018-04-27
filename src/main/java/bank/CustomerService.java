package bank;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class CustomerService {
	@Autowired
	CustomerRepository repository;
	@Autowired
	AccountService accountService;
	@Autowired
	PasswordEncoder passwordEncoder;


	public Customer createNewCustomer() {
		Customer cust=new Customer();
		String hashedPwd=null;
		String[] args = getUserInput(false);
		if (okToCreateUser(args[0])) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			hashedPwd=passwordEncoder.encode(args[3]);
			cust=repository.insert(new Customer(args[0], args[1], args[2], hashedPwd));
			System.out.println("Customer is created");
		} else {
			System.out.println( "Customer already exist");
		}
		return cust;
	}
	
	public Customer getCustomerByIdAndPwd() {
		String personalId=null;
		String pwd=null;
		Scanner scanner= new Scanner(System.in);
		System.out.println("Please enter your customer id followed by your password:");
		personalId=scanner.next();
		pwd=scanner.next();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Customer cust=repository.findByIdNumber(personalId);
		if(encoder.matches(pwd, cust.getPassWord())) {
			return cust;
		}else {
			System.out.println("No customer with that customer id or password");
			return null;
		}
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	public void deleteCustomer(Customer c) {
		String returnString = null;
		List<Account> accountList=accountService.getAllAccountsForCustomer(c.getIdNumber());
			// if customer doesnt have any accounts, ok to delete customer
			if (accountList.size() == 0) {
				repository.delete(c);
				System.out.println("Customer is deleted");
			}
			// if customer has accounts, customer must active delete them first
			else {
				returnString="Customer needs to delete the accounts first";
				for (Account account : accountList) {
					returnString = returnString + "\n" + account.getAccountName();
				}
			}
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
