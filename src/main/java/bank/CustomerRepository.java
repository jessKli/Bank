package bank;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{
	@Query("{ 'idNumber' : ?0 }")
	Customer findByIdNumber(String birth);
	
	@Query("{'idNumber' :?#{[0]}}? {'password' :? #{[1]}}")
	Customer findByIdNumberAndPwd(String pnr, String pwd);
	
}
