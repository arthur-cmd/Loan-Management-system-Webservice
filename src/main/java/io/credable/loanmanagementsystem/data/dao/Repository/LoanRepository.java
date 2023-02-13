package io.credable.loanmanagementsystem.data.dao.Repository;

import io.credable.loanmanagementsystem.data.vo.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.credable.loanmanagementsystem.data.vo.LoanModel;

@Repository

public interface LoanRepository extends JpaRepository<LoanModel,Long> {

    LoanModel findByCustomerKYC(Model customerKYC);
}
