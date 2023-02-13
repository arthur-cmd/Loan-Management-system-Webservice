//package io.credable.loanmanagementsystem.service;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import io.credable.loanmanagementsystem.data.dao.Repository.CustomerRepository;
//
//import io.credable.loanmanagementsystem.data.vo.CustomerEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
////@Transactional
//public class ServiceImplementation implements CustomerService{
//
//   private final CustomerRepository repository;
//    private static final Logger log = LoggerFactory.getLogger(ServiceImplementation.class);
//
//    @Autowired
//    public ServiceImplementation(CustomerRepository repository){
//
//        this.repository=repository;
//
//    }
//    @Override
//    public List<CustomerEntity> findAll() {
//        List<CustomerEntity> list= new ArrayList<CustomerEntity>();
//        repository.findAll().forEach(e -> list.add(e));
//        return list;
//    }
//
//    @Override
//    public CustomerEntity findByCustomerNumber(String CustomerNumber) {
//        CustomerEntity c=repository.findByCustomerNumber(CustomerNumber).orElse(null);
//        return c;
//
//    }
//
//    @Override
//    public CustomerEntity save(CustomerEntity customer) {
//        CustomerEntity duplicateCustomer=this.findByCustomerNumber(customer.getCustomerNumber());
//
//        if(duplicateCustomer== null){
//            return repository.save(customer);
//        }
//        log.info("Customer with CustomerNumber ={} found in database", customer.getCustomerNumber());
//        return null;
//    }
//
//}
