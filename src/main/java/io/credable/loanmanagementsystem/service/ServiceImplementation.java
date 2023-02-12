package io.credable.loanmanagementsystem.service;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import io.credable.loanmanagementsystem.data.dao.Repository.CustomerRepository;

import io.credable.loanmanagementsystem.data.vo.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ServiceImplementation implements CustomerService{
    private CustomerRepository repository;
    private static final Logger log = LoggerFactory.getLogger(ServiceImplementation.class);

    public ServiceImplementation (){

    }


    @Autowired
    ServiceImplementation(CustomerRepository repository){
        this.repository=repository;
    }


    @Override
    public List<CustomerEntity> findAll() {
        return null;
    }

    @Override
    public CustomerEntity findByCustomerId(Long CustomerID) {
        CustomerEntity c=repository.findByCustomerID(CustomerID).orElse(null);
        return c;
    }
}
