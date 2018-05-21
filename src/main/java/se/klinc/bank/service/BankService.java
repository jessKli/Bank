package se.klinc.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import se.klinc.bank.dao.Customer;
@ComponentScan(basePackages = { "se.klinc.bank.service","se.klinc.bank"})
@SpringBootApplication
public class BankService implements CommandLineRunner{
	@Autowired
	private CustomerService custService;
	@Autowired
	private AccountService accountService;


//	public static void main(String []args) {
//////		boolean showMenu=true;
//		ConfigurableApplicationContext  ctx=SpringApplication.run(BankService.class, args);
////		System.out.println("Do you want to see the menu?");
//////		Scanner scanner = new Scanner(System.in);
//////		if(scanner.nextLine().equals("false")) {
//////			showMenu=false;
//////		}
//////		if(showMenu) {
//////			showMenuOptions();
//////		}
////		ctx.close();
//		
//		
//	}



	// run the application from localhost
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("The bank is open");
		};
	}
	public void run(String... args) throws Exception {
	}
	public static void getExitMsg() {
		System.out.println("The application will terminate");
	}
	public Customer getCreateNewCustomer() {
		return custService.createNewCustomer();
	}
	public String getGetCustomerByIdAndPwd(String name, String pwd) {
		return custService.getCustomerByIdAndPwd(name, pwd);
	}
	public Customer getGetCustomerByIdAndPwd() {
		return custService.getCustomerByIdAndPwd();
	}
	public boolean getDeleteCustomer(Customer c) {
		return custService.deleteCustomer(c);
	}
	public void getDeleteAccount(Customer c) {
		accountService.deleteAccount(c);
	}
	public void getCreateAccount(Customer c) {
		accountService.createAccount(c);;
	}
	public void getAdjustTheAmountOfTheAccount(Customer c) {
		accountService.adjustTheAmountOfTheAccount(c);
	}
	public void getPrintCustomerTransactions(Customer c) {
		accountService.printCustomerTransactions(c);
	}
	

	
}
