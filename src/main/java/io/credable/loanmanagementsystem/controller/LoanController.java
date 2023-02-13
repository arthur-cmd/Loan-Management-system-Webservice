package io.credable.loanmanagementsystem.controller;
import io.credable.loanmanagementsystem.data.vo.LoanModel;
import io.credable.loanmanagementsystem.data.vo.Model;
import io.credable.loanmanagementsystem.service.LoanService;

import  org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restws")
public class LoanController {

    private LoanService loanservice;
    private static final Logger log = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    public LoanController( LoanService loanservice){

        this.loanservice= loanservice;

    }

    @GetMapping("/loan/{customerKYC}")
    public LoanModel getLoan(@PathVariable Model customerKYC){
        LoanModel loan= loanservice.getLoan(customerKYC);
        log.info("Your loan is" + customerKYC);
        if (loan != null){
            return loan;
        }

        return null;

    }
}
