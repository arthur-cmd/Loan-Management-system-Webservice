package io.credable.loanmanagementsystem.data.vo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "customer")

public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerNumber;

    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "email")
    private String email;


    private String firstName;

    private String lastName;

    private String middleName;
    @Column(name = "mobile")
    private String mobile;


    private String idNumber;


    private double monthlyIncome;
    @Column(name = "status")
    protected Status status;

    @Column(name = "gender")
    private Gender gender;


    private IdType idType;
}


