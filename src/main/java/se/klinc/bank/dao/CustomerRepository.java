package se.klinc.bank.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
public interface CustomerRepository extends MongoRepository<Customer, String> {
//	@Query("{ 'idNumber' : ?0 }")
//	Customer findByIdNumber(String birth);
//	
//	@Query("{'idNumber' :?#{[0]}}? {'password' :? #{[1]}}")
//	Customer findByIdNumberAndPwd(String pnr, String pwd);
//	
}
