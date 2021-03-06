package se.klinc.bank.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    @Query("{ 'ownerId' : ?0 }")
    List<Transaction> findByOwnerIdNumber(String birth);

    @Query("{ 'accountId' : ?0 }")
    List<Transaction> findByAccountId(String accountId);
}
