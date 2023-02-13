package io.credable.loanmanagementsystem.controller;
import io.credable.loanmanagementsystem.data.vo.Model;
import io.credable.loanmanagementsystem.service.CustomerService;
//import io.credable.loanmanagementsystem.service.ServiceImplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restws")
public class CustomerController {
    private final CustomerService service;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

     @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }


//    @GetMapping("/customers")
//    public ResponseEntity<List<CustomerEntity>> retrieveAllCustomers(){
//        List <CustomerEntity> customers = service.findAll();
//        log.info("Customers list size= {}", customers.size());
//        if(customers.size()==0){
//
//            return ResponseEntity.noContent().build();
//
//
//        }
//        return ResponseEntity.ok(customers); // return 200, with json body
//    }

    @GetMapping("/customer/{CustomerNumber}")
    public Model getCustomer(@PathVariable String CustomerNumber){
        Model customer = service.getCustomer(CustomerNumber);
          log.info("customernumber returned is" + CustomerNumber);
      if (customer != null){
              return customer;
//            return  new ResponseEntity<>(Model, HttpStatus.OK);
        }
//
//        else{
//            log.info("Customer with id={} not found", CustomerNumber);
//            return ResponseEntity.notFound().build();
//        }

      return null;


    }

//    @PostMapping("/customers")
//    public ResponseEntity<Void> createCustomer(@RequestBody CustomerEntity customer){
//
//        CustomerEntity newcustomer =service.save(customer);
//        if (newcustomer != null){
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/CustomerNumber}")
//                    .buildAndExpand(newcustomer.getCustomerNumber()).toUri();
//            return ResponseEntity.created(location).build();
//
//        }
//        else{
//            log.info("Customer returned is NULL - Duplicate Customer found in the database");
//            return ResponseEntity.unprocessableEntity().build();
//        }




    }
    /*@GetMapping("/customers")
    public ResponseEntity<List<CustomerEntity>> retrieveAllCustomers() {
        // read from database

        List<CustomerEntity> customers = service.findAll();
        log.info("Customers list size= {}", customers.size());

        if (customers.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers); // return 200, with json body

    }*/



    //@GetMapping("/customers/{CustomerID}")
    //public ResponseEntity<CustomerEntity> getCustomer(@PathVariable String CustomerID) {
       // CustomerEntity customer = service.findByCustomerId(CustomerID);
        //if (customer != null) {
          //  return ResponseEntity.ok(customer); // return 200, with json body
        //} else {
          //  log.info("Customer with id={} not found", CustomerID);
            //return ResponseEntity.notFound().build();
        //}







