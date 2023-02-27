package io.credable.loanmanagementsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.credable.loanmanagementsystem.data.dto.ScoringDTO;
import io.credable.loanmanagementsystem.service.CustomerService;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueryLoan {

    private  CustomerService service;



    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> createClientToken ()  {
        String url = "https://scoringtest.credable.io/api/v1/client/createClient";
        //send request and receive response
        String request = "{ "
                + "\"url\": \"" + "https://54bf-217-29-131-14.eu.ngrok.io/transactionsApi/234774784"+ "\", "
                +"\"name\": \"" + "loan" + "\", "
                + "\"username\": \"" + "" + "\", "
                + "\"password\": \"" + "" + "\""
                + "}";
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(request,headers);
            String link = "https://scoringtest.credable.io/api/v1/client/createClient";
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

            return ResponseEntity.ok(responseEntity.getBody());
    }


    @SneakyThrows
    public String createToken(String custumerNumber){
        ResponseEntity<String> clientToken= createClientToken();
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
    public ResponseEntity<ScoringDTO> queryScore(@PathVariable String customerNumber)  {

        ResponseEntity<String> clientToken= createClientToken();
        String ttoken= clientToken.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(ttoken);
        String Clienttoken = jsonNode.get("token").asText();
        HttpHeaders headers= new HttpHeaders();
        headers.set("client-token",Clienttoken);
        HttpEntity<Object> request = new HttpEntity<>(headers);

        String token= createToken(customerNumber);

        String uri= "https://scoringtest.credable.io/api/v1/scoring/queryScore/" + token ;

        ResponseEntity<ScoringDTO> responsequery=restTemplate.exchange(uri,HttpMethod.GET,request,ScoringDTO.class);


        return  ResponseEntity.ok(responsequery.getBody());


    }
//
//    public ResponseEntity<ResponseDTO> QueryScore(@PathVariable String token){
//        URI uri = URI.create("https://scoringtest.credable.io/ap1/vi/scoring/queryScore/");
//        HttpHeaders headers=new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set(createClient().getBody());
//        HttpEntity<Object> request=new HttpEntity<>(response,headers);
//        ResponseEntity<ResponseDTO> responset= restTemplate.getForEntity(uri,request,ResponseDTO.class);
//
//        return   ResponseEntity.ok(responset.getBody());
//    }

}
