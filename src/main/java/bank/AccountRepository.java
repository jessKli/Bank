package bank;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
	@Query("{ 'accountOwnerIdNumber' : ?0 }")
	List<Account> findByOwnerIdNumber(String birth);
	
	@Query("{'accountName' :?#{[0]}}? {'accountOwnerIdNumber' :? #{[1]}}")
	Account findByNameAndOwnerid(String accountName, String birth);
	
}
