package io.credable.loanmanagementsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.credable.loanmanagementsystem.controller.QueryLoan;
import io.credable.loanmanagementsystem.data.dto.LoanRequestDTO;
import io.credable.loanmanagementsystem.data.dto.ScoringDTO;
import io.credable.loanmanagementsystem.data.vo.LoanModel;
import io.credable.loanmanagementsystem.data.dao.Repository.LoanRepository;
import io.credable.loanmanagementsystem.data.vo.Model;
import org.springframework.stereotype.Service;

import javax.management.Query;

@Service
public class LoanService {

    private LoanRepository Loanrepository;



    private QueryLoan queryLoan;

    public LoanService(LoanRepository loanrepository){
        this.Loanrepository = loanrepository;

    }

    public LoanModel getLoan(Model customerKYC){
        return Loanrepository.findByCustomerKYC(customerKYC);
    }


   public LoanModel createLoan(LoanModel loanModel){
        LoanModel newloan= new LoanModel();
        newloan.setCustomerKYC(loanModel.getCustomerKYC());
        newloan.setAmount(loanModel.getAmount());
        newloan.setStatus(loanModel.getStatus());
        newloan.setId(loanModel.getId());

       return Loanrepository.save(newloan);

   }

   public LoanModel requestLoan(LoanRequestDTO loanRequestDTO) throws JsonProcessingException {

       ScoringDTO scoringDTO= queryLoan.queryScore(loanRequestDTO.getCustomerNumber());

       String loanStatus;


       if(scoringDTO.getLimitAmount() > LoanModel)

   }
}
