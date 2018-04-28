package bank;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class Application implements CommandLineRunner{

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
		int input;
		Customer cust=new Customer();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Press:\n1 for create new customer\n2 for login");
		input=scanner.nextInt();
		if(input==Constants.CREATE_NEW_CUSTOMER) {
			cust=custService.createNewCustomer();
		}else if(input==Constants.LOGIN){
			cust=custService.getCustomerByIdAndPwd();
		}
		if(cust==null) {
			exit=true;
		}
		while (!exit) {
			System.out.println(
					"How can we help you?\nPress:"
					+ "\n3 for stop being a client in this bank"
					+ "\n4 for create account"
//					+ "\nP for change password"
//					+ "\nR for allcustomers"
					+ "\n5 for delete account"
					+ "\n6 for adjust account"
					+ "\n7 for all my transactions"
					+ "\n9 to exit");
			input = scanner.nextInt();
			switch (input) {
			case Constants.DELETE_CUSTOMER:
				if(custService.deleteCustomer(cust)) {
					exit=true;
				}
				break;
			case Constants.DELETE_ACCOUNT:
				accountService.deleteAccount(cust);
				break;
			case Constants.CREATE_ACCOUNT:
				accountService.createAccount(cust);
				break;
			case Constants.ADJUST_ACCOUNT:
				accountService.adjustTheAmountOfTheAccount(cust);
				break;
			case Constants.MY_TRANSACTIONS:
				accountService.printCustomerTransactions(cust);
				break;
			case Constants.EXIT:
				System.out.println("The application will terminate");
				exit = true;
				break;
			default:
				System.out.println("What do you want to do");
				break;
			}
		}
	}	
			
		
//		while (!exit) {
//			System.out.println(
//					"How can we help you?\nPress:"
//					+ "\nC for create new customer"
//					+ "\nD for delete customer "
//					+ "\nP for change password"
//					+ "\nR for allcustomers"
//					+ "\nA for delete account"
//					+ "\nB for create account"
//					+ "\nL for adjust account"
//					+ "\nS for all accounts\nE to exit");
//			String input = scanner.nextLine();
//			switch (input.toUpperCase()) {
//			case Constants.CREATE_NEW_CUSTOMER:
//				System.out.println(custService.createNewCustomer());
//				break;
//			case Constants.DELETE_CUSTOMER:
//				System.out.println(custService.deleteCustomer());
//				break;
//			case Constants.CHANGE_PWD:
//				System.out.println("Change pwd");
//				break;
//			case Constants.GET_ALL_CUSTOMERS:
//				custService.getAllCustomers();
//				break;
//			case Constants.GET_ALL_ACCOUNTS:
//				List<Account> allAccounts=accountService.getAllAccounts();
//				for(int i=0;i<allAccounts.size();i++) {
//					System.out.println(allAccounts.get(i).toString());
//				}
//				break;
//			case Constants.DELETE_ACCOUNT:
//				accountService.deleteAccount();
//				break;
//			case Constants.CREATE_ACCOUNT:
//				accountService.createAccount();
//				break;
//			case Constants.ADJUST_ACCOUNT:
//				accountService.adjustTheAmountOfTheAccount();
//				break;
//			case Constants.EXIT:
//				System.out.println("The application will terminate");
//				exit=true;
//				break;
//			default:
//				System.out.println("What do you want to do");
//				break;
//			}
//		}
		
//	}
	
	
}
