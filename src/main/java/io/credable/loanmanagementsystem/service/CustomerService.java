package io.credable.loanmanagementsystem.service;

import io.credable.loanmanagementsystem.data.dao.Repository.CustomerRepository;
import io.credable.loanmanagementsystem.data.vo.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private  final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {

        this.repository=repository;

    }

    public Model getCustomer(String customerNumber){
        return repository.findByCustomerNumber(customerNumber);
    }


    //public List<CustomerEntity> findAll();
//public CustomerEntity findByCustomerNumber(String CustomerNumber);
//public CustomerEntity save(CustomerEntity customer);



    //public CustomerEntity findByStatus(String Status);
}
