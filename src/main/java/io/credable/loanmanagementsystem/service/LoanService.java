package io.credable.loanmanagementsystem.service;

import io.credable.loanmanagementsystem.data.vo.LoanModel;
import io.credable.loanmanagementsystem.data.dao.Repository.LoanRepository;
import io.credable.loanmanagementsystem.data.vo.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private LoanRepository Loanrepository;

    public LoanService(LoanRepository loanrepository){
        this.Loanrepository = loanrepository;
    }

    public LoanModel getLoan(Model customerKYC){
        return Loanrepository.findByCustomerKYC(customerKYC);
    }

}
