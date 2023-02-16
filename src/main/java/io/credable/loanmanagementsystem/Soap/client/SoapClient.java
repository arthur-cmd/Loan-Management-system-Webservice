package io.credable.loanmanagementsystem.Soap.client;
import io.credable.loanmanagementsystem.customerclasses.CustomerRequest;
import io.credable.loanmanagementsystem.customerclasses.CustomerResponse;
import io.credable.loanmanagementsystem.customerclasses.ObjectFactory;


import io.credable.loanmanagementsystem.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import javax.xml.bind.JAXBElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class SoapClient extends WebServiceGatewaySupport{
    private static final Logger log=LoggerFactory.getLogger(SoapClient.class);
    public CustomerResponse getCustomerNumber(String CustomerNumber){
        //CustomerResponse customerResponse = null;
        //Object response = null;

        CustomerRequest request= new CustomerRequest();
        request.setCustomerNumber(CustomerNumber);

        log.info("Request for customer number" + CustomerNumber);

        CustomerResponse response= (CustomerResponse) getWebServiceTemplate().marshalSendAndReceive("https://kycapitest.credable.io/service/customer",request,new SoapActionCallback("")) ;

        return response;

        //CustomerService customerService = new

        //request.setCustomerNumber(CustomerNumber);
        //log.info("request information for " + CustomerNumber);
        //return (CustomerResponse) getWebServiceTemplate().marshalSendAndReceive("https://kycapitest.credable.io/service/customer", request, new SoapActionCallback(
        //        ""));
        //try{
          //  customerResponse =  (CustomerResponse) getWebServiceTemplate().marshalSendAndReceive("https://kycapitest.credable.io/service/customer", request, new SoapActionCallback(""));

            //log.info("Response from credable service 1 {}",  customerResponse.getCustomer() );
            //ModelMapper modelMapper = new ModelMapper();
            //customerResponse = modelMapper.map(response, CustomerResponse.class);

            //log.info("Response from credable service {}",  customerResponse.getCustomer() );
        //}catch (Exception e){
          //  log.error("Request failed with Exception {}", e.getMessage());
        //}

        //return customerResponse;
    }
}
