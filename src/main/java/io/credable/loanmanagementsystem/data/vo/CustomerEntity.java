package io.credable.loanmanagementsystem.data.vo;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="customer")

public class CustomerEntity {
    @Serial
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long CustomerID;

    private long id;

    private String createdAt;
    private Date createdDate;
    private Date dob;
    private String email;
    private String firstName;
    private String gender;
    private String idNumber;
    private String idType;
    private String lastName;
    private String middleName;
    private String mobile;
    private String mounthlyIncome;
    private String status;


    public CustomerEntity(){

    }

    public CustomerEntity(String firstName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;

    }

    public long getCustomerID() {
        return CustomerID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getDob () {
        return dob;
    }

    public String getEmail(){
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender ;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getIdType() {
        return idType;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleNameName() {
        return middleName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMounthlyIncome() {
        return mounthlyIncome;
    }


    public String getStatus() {
        return status;
    }

    public long getId(){
        return id;
    }
}
