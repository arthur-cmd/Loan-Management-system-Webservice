package io.credable.loanmanagementsystem.Soap.client;
import io.credable.loanmanagementsystem.customerclasses.CustomerRequest;
import io.credable.loanmanagementsystem.customerclasses.CustomerResponse;
import io.credable.loanmanagementsystem.transactionclasses.TransactionData;
import io.credable.loanmanagementsystem.transactionclasses.TransactionsRequest;
import io.credable.loanmanagementsystem.transactionclasses.TransactionsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;


public class TransactionClient extends WebServiceGatewaySupport{
    private static final Logger log = LoggerFactory.getLogger(TransactionClient.class);

    public List<TransactionData> getTransactions(String customerNumber){
        TransactionsRequest Trequest= new TransactionsRequest();
        Trequest.setCustomerNumber(customerNumber);
        log.info(("request information for" + customerNumber));
        TransactionsResponse Tresponse = new TransactionsResponse();
        Tresponse= (TransactionsResponse) getWebServiceTemplate().marshalSendAndReceive("http://trxapitest.credable.io:/service/transaction-data", Trequest);
        List<TransactionData> transactionData =  Tresponse.getTransactions();

        return transactionData;

    }


}
