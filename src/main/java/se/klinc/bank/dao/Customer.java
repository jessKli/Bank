package se.klinc.bank.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {
    @Id
    private String idNumber;
    private String firstName;
    private String lastName;
    private String passWord;

    public Customer() {

    }

    public Customer(String birthDate, String first, String last, String pwd) {
        this.idNumber = birthDate;
        this.firstName = first;
        this.lastName = last;
        this.passWord = pwd;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String pwd) {
        passWord = pwd;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer:[idNumber=%s, firstName='%s', lastName='%s',passWord=%s]\n",
                idNumber, firstName, lastName, passWord);
    }


}
