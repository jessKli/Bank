package bank;

import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repository;

	public String createNewCustomer() {
		String returnString;
		String[] args = getUserInput(false);
		if (okToCreateUser(args[0])) {
			repository.insert(new Customer(args[0], args[1], args[2], args[3]));
			returnString = "User is created";
		} else {
			returnString = "User already exist";
		}
		return returnString;
	}
	
	public Customer getCustomer() {
		System.out.print(repository.findAll() + "\n");
		String[] args = getUserInput(true);
		return repository.findByIdNumber(args[0]);
	}

	public void deleteCustomer() {	
		//first, find all users in db, easier to delete then
		System.out.print(repository.findAll() + "\n");
		String[] args = getUserInput(true);
		repository.delete(repository.findByIdNumber(args[0]));
		System.out.print(repository.findAll());
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
