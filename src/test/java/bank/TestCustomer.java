package bank;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
	
	@Test
	public void webAppForVerifyStartPoint() throws Exception{
		 mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(content().string(equalTo("Welcome to our bank")));
	}
	
//	@Test
//	public void jk() {
//		service.createUser("1974-08-15-1111");
//		repository.findAll();
//	}
	 
}
