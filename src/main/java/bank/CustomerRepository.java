package bank;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerRepository extends MongoRepository<Customer, String>{
	@Query("{ 'idNumber' : ?0 }")
	Customer findByIdNumber(String birth);
}
