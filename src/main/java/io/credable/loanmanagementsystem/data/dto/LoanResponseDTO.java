package io.credable.loanmanagementsystem.data.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class LoanResponseDTO {


    private String customerNumber;
    private double amount;
    private String loanstatus;


    private Double limited_amount_for_loan;



    public LoanResponseDTO(double amount, String customerNumber, String loanStatus,Double limited_amount) {
        this.amount=amount;
        this.customerNumber=customerNumber;
        this.loanstatus=loanStatus;

        this.limited_amount_for_loan=limited_amount;

    }

}
