package io.credable.loanmanagementsystem.controller;
import io.credable.loanmanagementsystem.Soap.client.SoapClient;
import io.credable.loanmanagementsystem.Soap.client.WebServiceConfiguration;
import io.credable.loanmanagementsystem.customerclasses.Customer;
import io.credable.loanmanagementsystem.customerclasses.CustomerRequest;
import io.credable.loanmanagementsystem.customerclasses.CustomerResponse;
import io.credable.loanmanagementsystem.data.vo.Model;
import io.credable.loanmanagementsystem.service.CustomerService;
//import io.credable.loanmanagementsystem.service.ServiceImplementation;


import jakarta.xml.ws.soap.SOAPFaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/restws")
public class CustomerController {
    private final CustomerService service;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private SoapClient client;

    private CustomerResponse response;

    @Autowired
    public CustomerController(CustomerService service, SoapClient client) {
        this.service = service;
        this.client = client;
    }

    @GetMapping  ("{customerNumber}")
    public Customer invokeSoapClientToGetCustomerNumber(@PathVariable String customerNumber){
        //CustomerResponse response = new CustomerResponse();
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerNumber(customerRequest.getCustomerNumber());
       // return response.getCustomer();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebServiceConfiguration.class);
        client = context.getBean(SoapClient.class);
        response = client.getCustomerNumber(customerNumber);

        return response.getCustomer();

    }

    @GetMapping("/subscribe/{customerNumber}")
    public ResponseEntity<Object> subscribeCustomer(@PathVariable String customerNumber) {

        Model existingCustomer = service.getCustomer(customerNumber);
        if (existingCustomer != null) {
            // customer found in local database, return the response
            log.info("Customer found in local database with customer number: " + customerNumber);
            return new ResponseEntity<>(Map.of("message", "Customer already subscribed", "response", existingCustomer), HttpStatus.OK);
        } else {
            try {
                log.info("Customer not found in local database, hit soap webservice" + customerNumber);
                // customer not found in local database, hit SOAP web service to get customer information
                CustomerResponse newCustomerResponse = client.getCustomerNumber(customerNumber);
                if (newCustomerResponse != null && newCustomerResponse.getCustomer() != null) {
                    // customer found in SOAP web service, save to local database
                    log.info("Customer found in SOAP web service, save to local database with customer number: " + customerNumber);
                    Model newCustomer = extractAndSaveCustomer(newCustomerResponse, customerNumber);
                    return new ResponseEntity<>(Map.of("message", "Customer subscribed successfully", "response", newCustomer), HttpStatus.OK);
                } else {
                    // customer not found in both local database and SOAP web service
                    log.info("Customer with customer number " + customerNumber + " not found.");
                    return new ResponseEntity<>("Customer with this customer number doesn't exist", HttpStatus.NOT_FOUND);
                }
            } catch (SOAPFaultException ex) {
                // handle the SOAP fault exception and return an appropriate response
                log.info("Error retrieving customer  " + customerNumber);
                return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private Model extractAndSaveCustomer(CustomerResponse newCustomerResponse, String customerNumber) {
        Customer customer = newCustomerResponse.getCustomer();
        if (customer != null) {
            Model newModel = new Model();
            newModel.setCustomerNumber(customer.getCustomerNumber());
            newModel.setFirstName(customer.getFirstName());
            newModel.setMiddleName(customer.getMiddleName());
            newModel.setLastName(customer.getLastName());
            newModel.setEmail(customer.getEmail());
            newModel.setMobile(customer.getMobile());
            newModel.setMonthlyIncome(customer.getMonthlyIncome());
            newModel.setIdNumber(customer.getIdNumber());
            return service.createCustomer(newModel);
            //return customerKYCService.createCustomerKYC(newCustomerKYC);
        } else {
            log.info("No customer information found");
            return null;
        }

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







