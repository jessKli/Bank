package se.klinc.bank.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import se.klinc.bank.TypeOfAccountActivity;

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

    public Transaction(String owner, Date d, String account, TypeOfAccountActivity type, BigDecimal amount) {
        this.ownerId = owner;
        this.date = d;
        this.accountId = account;
        this.type = type;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getDateAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateString = sdf.format(date);
        return dateString;
    }

    public String formatAmountAndActivity() {
        String returnString;
        switch (type) {
            case WITHDRAW: {
                returnString = "- ";
                break;
            }
            case ADDAMOUNT: {
                returnString = "+ ";
                break;
            }
            default: {
                returnString = "? ";
            }
        }
        return returnString += amount.toString();
    }

    public String toString() {
        String returnString= String.format(
                "Date: " + getDateAsString() + " "+formatAmountAndActivity());
        return returnString;

    }


}	
