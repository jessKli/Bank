package bank;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Customer, String>{

}
