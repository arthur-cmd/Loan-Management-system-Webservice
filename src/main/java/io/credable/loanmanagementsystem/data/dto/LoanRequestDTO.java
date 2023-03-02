package io.credable.loanmanagementsystem.data.dto;

import io.credable.loanmanagementsystem.data.vo.CustomerModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoanRequestDTO {



    private String customerNumber;
    private double amount;
    private CustomerModel customer_id;
}
