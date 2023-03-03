package io.credable.loanmanagementsystem.service;

import io.credable.loanmanagementsystem.controller.QueryLoan;
import io.credable.loanmanagementsystem.data.dto.LoanRequestDTO;
import io.credable.loanmanagementsystem.data.dto.LoanResponseDTO;
import io.credable.loanmanagementsystem.data.dto.ScoringDTO;
import io.credable.loanmanagementsystem.data.vo.LoanModel;
import io.credable.loanmanagementsystem.data.dao.Repository.LoanRepository;
import io.credable.loanmanagementsystem.data.vo.Loanstatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private static final Logger log = LoggerFactory.getLogger(LoanService.class);
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
        newloan.setCustomer_id(loanRequestDTO.getCustomer_id());

       return Loanrepository.save(newloan);

   }




   public  ResponseEntity<LoanResponseDTO> requestLoan(LoanRequestDTO loanrquest)  {

        LoanModel loansave= createLoan(loanrquest);
        ScoringDTO scoringDTO = new ScoringDTO();
       String loanStatus;

        try{

         scoringDTO = queryLoan.queryScore(loanrquest.getCustomerNumber());
            log.info("transaction data are available " + scoringDTO);

           if (scoringDTO.getLimitAmount() >= loanrquest.getAmount()) {
               loanStatus = String.valueOf(Loanstatus.Succesfull);
           } else if (scoringDTO.getLimitAmount() == null) {  //TODO conditional
               loanStatus = String.valueOf(Loanstatus.Pending);

           } else {
               loanStatus = String.valueOf(Loanstatus.Rejected);
           }
       }
        catch (Exception e){
           log.info("no scoring results beacause of " + e.getMessage());
           throw new RuntimeException(e);
       }
       Double limited_amount= scoringDTO.getLimitAmount();
       LoanResponseDTO responseDTO = new LoanResponseDTO(loanrquest.getAmount(),loanrquest.getCustomerNumber(),loanStatus,limited_amount);
       responseDTO.setCustomerNumber(loansave.getCustomerNumber());
               return ResponseEntity.ok(responseDTO);
   }
}
