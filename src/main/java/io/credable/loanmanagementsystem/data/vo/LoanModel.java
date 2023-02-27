package io.credable.loanmanagementsystem.data.vo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Loan")
public class LoanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Model customerKYC;
    private String customerNumber = String.valueOf(customerKYC);

   // String customerNumber = String.valueOf(customerKYC);



    @Column(name = "amount")
    private double amount;

    @Column(name = "status")
    private Loanstatus status;

}

