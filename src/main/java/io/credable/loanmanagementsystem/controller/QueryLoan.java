package io.credable.loanmanagementsystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.credable.loanmanagementsystem.Soap.client.SoapClient;
import io.credable.loanmanagementsystem.data.dto.ScoringDTO;
import io.credable.loanmanagementsystem.service.CustomerService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Configuration
public class QueryLoan {
    private static final Logger log = LoggerFactory.getLogger(QueryLoan.class);


    private  CustomerService service;



    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> createClientToken (String customernumber)  {
        String url = "https://scoringtest.credable.io/api/v1/client/createClient";
        //send request and receive response
        String request = "{ "
                + "\"url\": \"" + "https://78b9-41-207-240-14.eu.ngrok.io/transactionsApi/"+customernumber+ "\", "
                +"\"name\": \"" + "loan" + "\", "
                + "\"username\": \"" + "" + "\", "
                + "\"password\": \"" + "" + "\""
                + "}";
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(request,headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

            return ResponseEntity.ok(responseEntity.getBody());
    }


    @SneakyThrows
    public String createToken(String custumerNumber){
        ResponseEntity<String> clientToken= createClientToken(custumerNumber);
        String ttoken= clientToken.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(ttoken);
        String Clienttoken = jsonNode.get("token").asText();
        HttpHeaders headers= new HttpHeaders();
        headers.set("client-token",Clienttoken);
        HttpEntity<?> request = new HttpEntity<>(headers);
        String uri="https://scoringtest.credable.io/api/v1/scoring/initiateQueryScore/" + custumerNumber;
      //  ResponseEntity<String> response =restTemplate.getForEntity(uri,String.class,request);
       ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.GET,request,String.class);

        String token= response.getBody();

        return token;


    }

    @SneakyThrows
    public ScoringDTO queryScore(@PathVariable String customerNumber)  {

        ResponseEntity<String> clientToken= createClientToken(customerNumber);
        String ttoken= clientToken.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(ttoken);
        String Clienttoken = jsonNode.get("token").asText();
        HttpHeaders headers= new HttpHeaders();
        headers.set("client-token",Clienttoken);
        HttpEntity<Object> request = new HttpEntity<>(headers);

        String token= createToken(customerNumber);
        int maxretries = 2;
        int retry= 0;
        String uri= "https://scoringtest.credable.io/api/v1/scoring/queryScore/" + token ;
        ScoringDTO scorereponse = new ScoringDTO();

          while (scorereponse.getLimitAmount()== null && retry<maxretries) {
              try{
              Thread.sleep(40000);

              ResponseEntity<ScoringDTO> responsequery = restTemplate.exchange(uri, HttpMethod.GET, request, ScoringDTO.class);
              log.info("the response is " + responsequery);
              scorereponse = responsequery.getBody();
              retry++;
              }

              catch (Exception e){
                  log.info("no transction data retrieved " + new RuntimeException(e));
                  throw new RuntimeException(e);}


          }




        log.info(("the query score result" + scorereponse));
          Thread.sleep(10000);
         return scorereponse;
       // return  ResponseEntity.ok(responsequery.getBody());


    }

}
