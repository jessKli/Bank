package se.klinc.bank.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import se.klinc.bank.TypeOfAccountActivity;
@Document
public  class Transaction{
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
	public String getAccountId() {
		return accountId;
	}
	public String getDateAsString() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String dateString=sdf.format(date);
		return dateString;
	}
	public String toString() {
		   return String.format(
				   "Date: "+getDateAsString()+",type=%s, amount='%s'\n",
//	                "date=%s,type=%s, amount='%s'\n",
	                type,amount);
//        return String.format(
//                "Transaction:[id=%s,ownerId=%s,date=%s,accountId=%s,type=%s, amount='%s'\n",
//                id,ownerId,date, accountId,type,amount);
    }
	
	
	
}	
