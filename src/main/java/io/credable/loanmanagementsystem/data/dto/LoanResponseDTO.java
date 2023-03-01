package io.credable.loanmanagementsystem.data.dto;
import io.credable.loanmanagementsystem.data.vo.Loanstatus;
import io.credable.loanmanagementsystem.data.vo.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class LoanResponseDTO {


    private String customerNumber;
    private double amount;
    private String loanstatus;


    private Double limited_amount;



    public LoanResponseDTO(double amount, String customerNumber, String loanStatus,Double limited_amount) {
        this.amount=amount;
        this.customerNumber=customerNumber;
        this.loanstatus=loanStatus;

        this.limited_amount=limited_amount;

    }

}
