package bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	private Repository repository;
	public static void main(String []args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println(repository.findAll());
		
	}
}
