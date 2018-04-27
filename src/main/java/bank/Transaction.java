package bank;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Transaction {
	@Id
	private String id;
	private String ownerId;
	private Date date;
	private String accountId;
	private TypeOfAccountActivity type;
	private BigDecimal amount;
	
	public Transaction() {
		
	}
	public Transaction(String owner,Date d,String account,TypeOfAccountActivity type,BigDecimal amount) {
		this.ownerId=owner;
		this.date=d;
		this.accountId=account;
		this.type=type;
		this.amount=amount;
	}
	
	public String toString() {
        return String.format(
                "Transaction:[id=%s,ownerId=%s,date=%s,accountId=%s,type=%s, amount='%s'\n",
                id, ownerId,date, accountId,type,amount);
    }
	
}	
