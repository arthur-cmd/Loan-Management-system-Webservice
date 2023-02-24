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

    private Long id;
    private String customerNumber;
    private double amount;
    private Loanstatus status;
}
