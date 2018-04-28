package bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
	public void createAccount(Customer c) {
		BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
		try {
			String birth = c.getIdNumber();
			System.out.println("Ange namn på konto:");			
			String aName=read.toString();
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
	public void deleteAccount(Customer c) {
		System.out.println("Name of the account that should be deleted:");
		Scanner scanner=new Scanner(System.in);
		String accountName=scanner.nextLine();
		String birth=c.getIdNumber();
		Account account=repository.findByNameAndOwnerid(accountName, birth);
		if(account!=null) {
		repository.delete(account);
		System.out.println(accountName +" is deleted");
		}
		else {
			System.out.println("Cant find account "+accountName);
		}

	}

	public void adjustTheAmountOfTheAccount(Customer cust) {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		List<Account> accountList = repository.findByOwnerIdNumber(cust.getIdNumber());
		Account account = null;
		if (accountList.size() == 0) {
			System.out.println("You dont have an account, please create one");
		} else if (accountList.size() == 1) {
			account = accountList.get(0);
		} else {

			System.out.println("Which of following accounts is the ONE:");
			for (Account a : accountList) {
				System.out.println(a.getAccountName());
			}
			System.out.println("Name of account");
			String name = null;
			try {
				name = read.readLine();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			account = repository.findByNameAndOwnerid(name, cust.getIdNumber());

		}
		//start the transaction
		if (account != null) {
			System.out.println("Amount");
			String amount = null;
			try {
				amount = read.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TypeOfAccountActivity type = amount.substring(0, 1).equals("+") ? TypeOfAccountActivity.ADDAMOUNT
					: TypeOfAccountActivity.WITHDRAW;
			BigDecimal amountBD = new BigDecimal(amount.substring(1));
			if (account.setAmount(type, amountBD)) {
				repository.save(account);
				tranRepository.insert(
						new Transaction(cust.getIdNumber(), new Date(), account.getAccountId(), type, amountBD));
			} else {
				System.out.println("Not enough money on the account");
			}
		}
	}

	public void printCustomerTransactions(Customer cust) {
		List<Transaction> transList = tranRepository.findByOwnerIdNumber(cust.getIdNumber());
		if (transList.size() == 0) {
			System.out.println("You dont have any transactions yet");
		} else {
			for (Transaction tran : transList) {
				System.out.println(tran.toString());
			}
		}
	}
		
//		if(List<Transaction> custTransactions=tranRepository.findById(cust.getIdNumber())){
//			
//		}
//		for(int i=0;i<custTransactions.s) {
//			System.out.println(tran.toString());
//		}
//	}
}
