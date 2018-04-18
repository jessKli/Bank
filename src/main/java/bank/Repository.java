package bank;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface Repository extends MongoRepository<Customer, String>{
	@Query("{ 'idNumber' : ?0 }")
	 Customer findByIdNumber(String birth);

}
