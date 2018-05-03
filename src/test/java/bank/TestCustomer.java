package bank;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import se.klinc.bank.dao.Customer;
import se.klinc.bank.dao.CustomerRepository;
import se.klinc.bank.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCustomer {
	@Autowired
    private MockMvc mvc;
	@Autowired
	CustomerService service;
	@Autowired
	CustomerRepository repository;

	// test class Customer
	@Test
	public void createCustomer() {
		 Customer cust=new Customer("1996-03-24-9991","Rasmus","Forsten-Klinc", "dummyPwd");
		 assertTrue(cust!=null);
	}
	
	@Test
	public void changePwdForCustomer() {
		 Customer cust=new Customer("1996-03-24-9991","Rasmus","Forsten-Klinc", "dummyPwd");
		 cust.setPassWord("changed");
		 assertTrue(cust.getPassWord().equals("changed"));
	}
	@Test
	public void verifyToString() {
		 Customer cust=new Customer("1996-03-24-9991","Rasmus","Forsten-Klinc", "dummyPwd");
		 System.out.println(cust.toString());
		 assertTrue(cust.toString().contains("Rasmus"));
	}
	
//	//test BankController
//	@Test
//	public void webAppForVerifyStartPoint() throws Exception{
//		 mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
//         .andExpect(status().isOk())
//         .andExpect(content().string(equalTo("Welcome to our bank")));
//	}
//	//test CustomerRepository
//	//test findByIdNumber returns false if user doesnt exist
//	@Test
//	public void verifyFindByNumberIfUserDoesntExist() {
//		Random rand=new Random();
//		String searchBirth="test"+rand;
//		Boolean userExist=false;
//		//loop through all customers in db
//		for(Customer cust:repository.findAll()) {
//			if(cust.getIdNumber().equals(searchBirth)) {
//				userExist=true;
//				break;
//			}
//		}
//		assertTrue(!userExist);
//	}
	
}
