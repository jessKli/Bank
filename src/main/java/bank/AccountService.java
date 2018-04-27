package bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public final class AccountService {
	@Autowired
	AccountRepository repository;
	@Autowired
	TransactionRepository tranRepository;
	//create new account
	public void createAccount() {
		BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Ange ditt id:");
		String birth;
		try {
			birth = read.readLine();
			System.out.print("Ange namn på konto:");			
			String aName=read.readLine();
			aName = read.readLine();
			repository.insert(new Account(birth, new BigDecimal(0),aName));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public List<Account> getAllAccountsForCustomer(String birth) {
		return(repository.findByOwnerIdNumber(birth));
	}
	
	public List<Account> getAllAccounts() {
		return(repository.findAll());
	}
	public void deleteAccount() {
		System.out.println("Name of the account that should be deleted:");
		Scanner scanner=new Scanner(System.in);
		String accountName=scanner.nextLine();
		System.out.println("Your birthid:");
		String birth=scanner.nextLine();
		Account account=repository.findByNameAndOwnerid(accountName, birth);
		if(account!=null) {
		repository.delete(account);
		System.out.println(accountName +" is deleted");
		}
		else {
			System.out.println("Cant find account "+accountName);
		}

	}
	public void adjustTheAmountOfTheAccount() {
		System.out.println("Your birthid");
		BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
		try {
			String birth=read.readLine();
			System.out.println("Name of account");
			String name=read.readLine();
			Account account=repository.findByNameAndOwnerid(name, birth);
			if(account!=null) {
				System.out.println("Amount");
				String amount=read.readLine();
				TypeOfAccountActivity type=amount.substring(0, 1).equals("+")?TypeOfAccountActivity.ADDAMOUNT:TypeOfAccountActivity.WITHDRAW;
				System.out.println(type+" "+amount.substring(1));
				BigDecimal amountBD=new BigDecimal(amount.substring(1));
				if(account.setAmount(type,amountBD)) {
					repository.save(account);
					tranRepository.insert(new Transaction(birth, new Date(), account.getAccountId(), type, amountBD));
					System.out.println(tranRepository.findAll());
				}else {
					System.out.println("Not enough money on the account");}
			}else {
				System.out.println("Cant fint account");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
}
