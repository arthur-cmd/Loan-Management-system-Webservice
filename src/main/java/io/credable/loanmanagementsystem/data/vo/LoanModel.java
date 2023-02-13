package io.credable.loanmanagementsystem.data.vo;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Loan")
public class LoanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Model customerKYC;

    @Column(name = "amount")
    private double amount;

    @Column(name = "status")
    private Loanstatus status;

}

