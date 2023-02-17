package io.credable.loanmanagementsystem.data.dao.Repository;

import io.credable.loanmanagementsystem.data.vo.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Model,Long> {

    Model findByCustomerNumber(String CustomerNumber);


}
