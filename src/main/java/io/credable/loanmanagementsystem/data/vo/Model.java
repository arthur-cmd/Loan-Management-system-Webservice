package io.credable.loanmanagementsystem.data.vo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "mobile")
    private String mobile;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "monthlyIncome")
    private double monthlyIncome;
    @Column(name = "status")
    protected Status status;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "id_type")
    private IdType idType;
}

