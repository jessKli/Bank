package bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankService implements CommandLineRunner{


	@Autowired
	private CustomerService custService;
	@Autowired
	private AccountService accountService;

//	public static void main(String []args) {
////		boolean showMenu=true;
//		ConfigurableApplicationContext  ctx=SpringApplication.run(Application.class, args);
////		System.out.println("Do you want to see the menu?");
////		Scanner scanner = new Scanner(System.in);
////		if(scanner.nextLine().equals("false")) {
////			showMenu=false;
////		}
////		if(showMenu) {
////			showMenuOptions();
////		}
//		ctx.close();
		
		
//	}



	// run the application from localhost
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("The bank is open");
		};
	}
	public static void getExitMsg() {
		System.out.println("The application will terminate");
	}
	public Customer getCreateNewCustomer() {
		return custService.createNewCustomer();
	}
	public Customer getGetCustomerByIdAndPwd() {
		return custService.getCustomerByIdAndPwd();
	}
	public boolean getDeleteCustomer(Customer c) {
		return custService.deleteCustomer(c);
	}
	public void getDeleteAccount(Customer c) {
		accountService.createAccount(c);
	}
	public void getCreateAccount(Customer c) {
		accountService.deleteAccount(c);;
	}
	public void getAdjustTheAmountOfTheAccount(Customer c) {
		accountService.adjustTheAmountOfTheAccount(c);
	}
	public void getPrintCustomerTransactions(Customer c) {
		accountService.printCustomerTransactions(c);;
	}

	public void run(String... args) throws Exception {
	}
}
