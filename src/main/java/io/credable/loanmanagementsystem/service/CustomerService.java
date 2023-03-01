package io.credable.loanmanagementsystem.service;

import io.credable.loanmanagementsystem.data.dao.Repository.CustomerRepository;
import io.credable.loanmanagementsystem.data.vo.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private  final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {

        this.repository=repository;

    }

    public CustomerModel getCustomer(String customerNumber){
        return repository.findByCustomerNumber(customerNumber);
    }

    public CustomerModel createCustomer(CustomerModel customer) {
        CustomerModel newCustomerKYC = new CustomerModel();
        newCustomerKYC.setCustomerNumber(customer.getCustomerNumber());
        newCustomerKYC.setFirstName(customer.getFirstName());
        newCustomerKYC.setMiddleName(customer.getMiddleName());
        newCustomerKYC.setLastName(customer.getLastName());
        newCustomerKYC.setEmail(customer.getEmail());
        newCustomerKYC.setMobile(customer.getMobile());
        newCustomerKYC.setMonthlyIncome(customer.getMonthlyIncome());
        newCustomerKYC.setIdNumber(customer.getIdNumber());
        return repository.save(newCustomerKYC);
    }


    //public List<CustomerEntity> findAll();
//public CustomerEntity findByCustomerNumber(String CustomerNumber);
//public CustomerEntity save(CustomerEntity customer);



    //public CustomerEntity findByStatus(String Status);
}
