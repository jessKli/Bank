package se.klinc.bank.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hamcrest.number.OrderingComparison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.klinc.bank.TypeOfAccountActivity;
import se.klinc.bank.dao.Account;
import se.klinc.bank.dao.AccountRepository;
import se.klinc.bank.dao.Customer;
import se.klinc.bank.dao.Transaction;
import se.klinc.bank.dao.TransactionRepository;
@Service
public final class AccountService {
	@Autowired
	AccountRepository repository;
	@Autowired
	TransactionRepository tranRepository;
	//create new account
	protected void createAccount(Customer c) {
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
	
	protected List<Account> getAllAccountsForCustomer(String birth) {
		return(repository.findByOwnerIdNumber(birth));
	}
	
	protected List<Account> getAllAccounts() {
		return(repository.findAll());
	}
	protected void deleteAccount(Customer c) {
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

	protected void adjustTheAmountOfTheAccount(Customer cust) {
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

	protected void printCustomerTransactions(Customer cust) {
		
		List<Transaction> transList = tranRepository.findByOwnerIdNumber(cust.getIdNumber());
		if (transList.size() == 0) {
			System.out.println("You dont have any transactions yet");
		} else {
			Map<String, List<Transaction>> tranMap=new HashMap<>();
			for (Transaction tran:transList) {
				if(tranMap.containsKey(repository.findByAccountId(tran.getAccountId()).getAccountName())) {
					List<Transaction> tmpList=tranMap.get(repository.findByAccountId(tran.getAccountId()).getAccountName());
					tmpList.add(tran);
					
				}else {
					List<Transaction> tranList=new ArrayList<>();
					tranList.add(tran);
					tranMap.put(repository.findByAccountId(tran.getAccountId()).getAccountName(), tranList);
				}
			}
			
			for (Entry<String, List<Transaction>> entry:tranMap.entrySet()) {
			Collections.sort(entry.getValue(), new Comparator<Transaction>() {
		
				@Override
				public int compare(Transaction o1, Transaction o2) {
					// TODO Auto-generated method stub
					return -1;
				}
				});
			}
			
			for (Entry<String, List<Transaction>> entry:tranMap.entrySet()) {
				System.out.println("Account: "+entry.getKey()+" ");
				for(Transaction tran:entry.getValue()) {
				System.out.print(tran.toString());
				}
			}
			
//			for (Transaction tran : transList) {
//				System.out.print("Account: "+repository.findByAccountId(tran.getAccountId()).getAccountName()+" ");
//				
//				System.out.println(tran.toString());
//			}
		}
	}

}
