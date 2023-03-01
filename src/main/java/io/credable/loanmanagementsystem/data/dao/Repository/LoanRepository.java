package io.credable.loanmanagementsystem.data.dao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.credable.loanmanagementsystem.data.vo.LoanModel;

@Repository

public interface LoanRepository extends JpaRepository<LoanModel,Long> {

    LoanModel findByCustomerNumber(String customerNumber);

}
