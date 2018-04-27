package bank;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Account {
	@Id
	private String id;
	private String accountOwnerIdNumber;
	private BigDecimal amount;
	private String accountName;


	public Account(String owner, BigDecimal amount, String accountName) {
		this.accountOwnerIdNumber=owner;
		this.amount=amount;
		this.accountName=accountName;
		
	}
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public String getAccountId() {
		return id;
	}
	public String getAccountOwnerIdNumber() {
		return accountOwnerIdNumber;
	}
	public String toString() {
        return String.format(
                "Account:[id=%s,accountOwnerIdNumber=%s, amount='%s', accountName='%s'\n",
                id, accountOwnerIdNumber,amount, accountName);
    }
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public boolean setAmount(TypeOfAccountActivity type, BigDecimal inputAmount) {
		boolean activityPerformed=false;
		if(type.name().equals(TypeOfAccountActivity.ADDAMOUNT.name())) {
			this.amount=this.amount.add(inputAmount);
			activityPerformed=true;
		}else {
			// check if enought amount on account
			if(this.amount.compareTo(inputAmount)>=0) {
				this.amount=this.amount.subtract(inputAmount);
				activityPerformed=true;
			}
		}
		return activityPerformed;
	}
}
