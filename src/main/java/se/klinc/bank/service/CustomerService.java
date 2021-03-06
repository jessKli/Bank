package se.klinc.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import se.klinc.bank.dao.Account;
import se.klinc.bank.dao.Customer;
import se.klinc.bank.dao.CustomerRepository;

@Service
@EnableMongoRepositories(basePackageClasses = CustomerRepository.class)
public class CustomerService {
    @Autowired
    CustomerRepository repository;
    @Autowired
    AccountService accountService;
    @Autowired
    PasswordEncoder passwordEncoder;


    protected Customer createNewCustomer() {
        Customer cust = new Customer();
        String hashedPwd = null;
        String[] args = getUserInput(false);
        if (okToCreateUser(args[0])) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPwd = passwordEncoder.encode(args[3]);
            cust = repository.insert(new Customer(args[0], args[1], args[2], hashedPwd));
            System.out.println("Customer is created");
        } else {
            System.out.println("Customer already exist");
            cust = null;
        }
        return cust;
    }

    protected Customer getCustomerByIdAndPwd() {
        String personalId = null;
        String pwd = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your customer id followed by your password:");
        personalId = scanner.next();
        pwd = scanner.next();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Customer cust = null;
//		= repository.findByIdNumber(personalId);
        Optional<Customer> c = repository.findById(personalId);
        if (c.isPresent()) {
            System.out.println("IS PRESENT");
            cust = c.get();
        } else {
            System.out.println("NOT PRESENT");
        }
        if (cust != null) {
            if (encoder.matches(pwd, cust.getPassWord())) {
            } else {
                System.out.println("Incorrect password");
                cust = null;
            }
        } else {
            System.out.println("No customer with that customerid");
        }
        return cust;
    }

    protected String getCustomerByIdAndPwd(String name, String password) {
        String personalId = name;
        String pwd = password;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Customer cust = null;
        Optional<Customer> c = repository.findById(personalId);
        if (c.isPresent()) {
            System.out.println("IS PRESENT");
            cust = c.get();
        } else {
            System.out.println("NOT PRESENT");
        }
        if (cust != null) {
            if (encoder.matches(pwd, cust.getPassWord())) {
            } else {
                System.out.println("Incorrect password");
                cust = null;
            }
        } else {
            System.out.println("No customer with that customerid");
        }
        return (cust != null) ? cust.toString() : "Not an existing user";
//		if(cust!=null) {
//		return cust.toString();
//		}else {
//			return "Not an existing user";
//		}
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected boolean deleteCustomer(Customer c) {
        boolean customerIsDeleted = false;
        List<Account> accountList = accountService.getAllAccountsForCustomer(c.getIdNumber());
        // if customer doesnt have any accounts, ok to delete customer
        if (accountList.size() == 0) {
            repository.delete(c);
            customerIsDeleted = true;
            System.out.println("Customer is deleted");
        }

        // if customer has accounts, customer must active delete them first
        else {
            System.out.println("Customer needs to delete the accounts first");

            accountList.forEach(a->System.out.println(a.getAccountName()));
        }
        return customerIsDeleted;
    }


    protected String changePwdForCustomer() {
        String returnString;
        System.out.print(repository.findAll() + "\n");
        String[] args = getUserInput(true);
        Optional<Customer> custCont = repository.findById(args[0]);
        Customer cust = null;
        if (custCont.isPresent()) {
            cust = custCont.get();
            Random rand = new Random();
            String newPwd = cust.getPassWord() + rand.nextInt();
            cust.setPassWord(newPwd);
            repository.save(cust);
            returnString = "New pwd for customer:" + cust.getIdNumber() + " is " + newPwd;
        } else {
            returnString = "Customer doesnt exist so impossible to change pwd";
        }
        System.out.print(repository.findAll() + "\n");
        return returnString;
    }

    public List<Customer> getAllCustomers() {

        return repository.findAll();
    }

    private String[] getUserInput(boolean justIdInputFromUser) {
        String[] args = new String[]{"", "", "", ""};
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
        if (repository.findById(birth).isPresent()) {
            createNewUser = false;
        }
        return createNewUser;
    }


}
