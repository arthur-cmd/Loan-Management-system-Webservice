package io.credable.loanmanagementsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.credable.loanmanagementsystem.controller.QueryLoan;
import io.credable.loanmanagementsystem.data.dto.LoanRequestDTO;
import io.credable.loanmanagementsystem.data.dto.LoanResponseDTO;
import io.credable.loanmanagementsystem.data.dto.ScoringDTO;
import io.credable.loanmanagementsystem.data.vo.LoanModel;
import io.credable.loanmanagementsystem.data.dao.Repository.LoanRepository;
import io.credable.loanmanagementsystem.data.vo.Loanstatus;
import io.credable.loanmanagementsystem.data.vo.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.Query;

@Service
public class LoanService {

    private LoanRepository Loanrepository;

    private LoanModel loan;

    private QueryLoan queryLoan;

    public LoanService(LoanRepository loanrepository,QueryLoan queryLoan){
        this.Loanrepository = loanrepository;
        this.queryLoan=queryLoan;

    }

    public LoanModel getLoan(String customerNumber){
        return Loanrepository.findByCustomerNumber(customerNumber);
    }


   public LoanModel createLoan(LoanRequestDTO loanRequestDTO){
        LoanModel newloan= new LoanModel();
        newloan.setCustomerNumber(loanRequestDTO.getCustomerNumber());
        newloan.setAmount(loanRequestDTO.getAmount());
        newloan.setStatus(loanRequestDTO.getStatus());
        newloan.setId(loanRequestDTO.getId());

       return Loanrepository.save(newloan);

   }




   public  ResponseEntity<LoanResponseDTO> requestLoan(LoanRequestDTO loanrquest) throws JsonProcessingException {

        LoanModel loansave= createLoan(loanrquest);


       ScoringDTO scoringDTO= queryLoan.queryScore(loanrquest.getCustomerNumber());

       String loanStatus;


       if(scoringDTO.getLimitAmount() > loanrquest.getAmount()){
           loanStatus = String.valueOf(Loanstatus.Succesfull);
       }  else if (scoringDTO.getLimitAmount() == loanrquest.getAmount()) {
           loanStatus= String.valueOf(Loanstatus.Succesfull);

       }
       else {
           loanStatus= String.valueOf(Loanstatus.Rejected);
       }
//         LoanResponseDTO responseDTO = null;
//                 responseDTO.setAmount(loanrquest.getAmount());
//                 responseDTO.setCustomerNumber(loanrquest.getCustomerNumber());
//                 responseDTO.setStatus(Loanstatus.valueOf(loanStatus));
//                 responseDTO.setId(loanrquest.getId());

       LoanResponseDTO responseDTO = new LoanResponseDTO(loanrquest.getAmount(),loanrquest.getCustomerNumber(),loanrquest.getId(),loanStatus);
       responseDTO.setCustomerNumber(loansave.getCustomerNumber());
               return ResponseEntity.ok(responseDTO);
   }
}
