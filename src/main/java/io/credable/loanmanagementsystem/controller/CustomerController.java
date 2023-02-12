package io.credable.loanmanagementsystem.controller;
import io.credable.loanmanagementsystem.service.CustomerService;
import io.credable.loanmanagementsystem.service.ServiceImplementation;
import io.credable.loanmanagementsystem.data.vo.CustomerEntity;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
@RequestMapping("/restws")


public class CustomerController {
    private CustomerService service;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController() {

    }

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // URI - http://localhost:8080/restws/customers
    // RequestMapping name at the top of this file is "/restws"
    // GetMapping below is "/customers"
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerEntity>> retrieveAllCustomers() {
        // read from database

        List<CustomerEntity> customers = service.findAll();
        log.info("Customers list size= {}", customers.size());

        if (customers.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers); // return 200, with json body

    }

    @GetMapping("/customers/{CustomerID}")
    public ResponseEntity<CustomerEntity> getCustomer(@PathVariable long CustomerID) {
        CustomerEntity customer = service.findByCustomerId(CustomerID);
        if (customer != null) {
            return ResponseEntity.ok(customer); // return 200, with json body
        } else {
            log.info("Customer with id={} not found", CustomerID);
            return ResponseEntity.notFound().build();
        }

    }


}
