package io.credable.loanmanagementsystem.service;
import  java.util.*;
import io.credable.loanmanagementsystem.data.vo.CustomerEntity;

public interface CustomerService {

    public List<CustomerEntity> findAll();
    public CustomerEntity findByCustomerId(Long CustomerID);
}
