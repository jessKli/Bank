package bank;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;

@EnableMongoRepositories
@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private CustomerService custService;
	@Autowired
	private AccountService accountService;

	public static void main(String []args) {

		ConfigurableApplicationContext  ctx=SpringApplication.run(Application.class, args);
		ctx.close();
		
		
	}

	// run the application from localhost
	// @Bean
	// public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	// return args -> {
	// System.out.println("The bank is open");
	// };
	// }
	

	public void run(String... args) throws Exception {
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		while (!exit) {
			System.out.println(
					"How can we help you?\nPress:"
					+ "\nC for create new customer"
					+ "\nD for delete customer "
					+ "\nP for change password"
					+ "\nR for allcustomers"
					+ "\nA for delete account"
					+ "\nB for create account"
					+ "\nL for adjust account"
					+ "\nS for all accounts\nE to exit");
			String input = scanner.nextLine();
			switch (input.toUpperCase()) {
			case Constants.CREATE_NEW_CUSTOMER:
				System.out.println(custService.createNewCustomer());
				break;
			case Constants.DELETE_CUSTOMER:
				System.out.println(custService.deleteCustomer());
				break;
			case Constants.CHANGE_PWD:
				System.out.println("Change pwd");
				break;
			case Constants.GET_ALL_CUSTOMERS:
				custService.getAllCustomers();
				break;
			case Constants.GET_ALL_ACCOUNTS:
				List<Account> allAccounts=accountService.getAllAccounts();
				for(int i=0;i<allAccounts.size();i++) {
					System.out.println(allAccounts.get(i).toString());
				}
				break;
			case Constants.DELETE_ACCOUNT:
				accountService.deleteAccount();
				break;
			case Constants.CREATE_ACCOUNT:
				accountService.createAccount();
				break;
			case Constants.ADJUST_ACCOUNT:
				accountService.adjustTheAmountOfTheAccount();
				break;
			case Constants.EXIT:
				System.out.println("The application will terminate");
				exit=true;
				break;
			default:
				System.out.println("What do you want to do");
				break;
			}
		}
//		while (!exit) {
//			if (input.equalsIgnoreCase(Constants.CREATE_NEW_CUSTOMER)) {
//				System.out.println("Create cust");
////				custService.createNewCustomer();
//			}
//			else if(input.equals(Constants.CHANGE_PWD)) {
//				System.out.println("Change pwd");
//			}
//			else if(input.equals(Constants.EXIT)) {
//				System.out.println("Change pwd");
//				exit=true;
//			} else {
//				System.out.println("The application will terminate");
//				exit = true;
//			}
//		}
				//loop through all elements in customer table
//		System.out.println(repository.findAll());
//		//create new user if idNumber doesnt exist
//		String birth="1996-03-24-9991";
//		String first="Rasmus";
//		String last="Forsten-Klinc";
//		String pwd="dummyPwd";
//		boolean createNewUser=true;
//			if(repository.findByIdNumber(birth)!=null) {
//				System.out.println("User already exist");
//				createNewUser=false;
//		
//		}
//		if(createNewUser) {
//			repository.insert(new Customer(birth,first, last,pwd));
//		}
//		
//		//change password on the new customer
//		Customer cust=repository.findByIdNumber(birth);
//		cust.setPassWord("pwdIsChanged");
//		repository.save(cust);		
//		
//		//delete the new customer
//		repository.deleteById(repository.findByIdNumber(birth).getId());
//		
//		
	}
	
	
}
