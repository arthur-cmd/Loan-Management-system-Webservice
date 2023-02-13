package io.credable.loanmanagementsystem.data.dao.Repository;

import io.credable.loanmanagementsystem.data.vo.CustomerEntity;
import  java.util.Optional;

import jdk.jfr.Enabled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    Optional<CustomerEntity> findByCustomerNumber(String CustomerNumber);
}
