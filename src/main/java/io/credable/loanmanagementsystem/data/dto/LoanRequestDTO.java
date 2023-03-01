package io.credable.loanmanagementsystem.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoanRequestDTO {



    private String customerNumber;
    private double amount;
}
