package io.credable.loanmanagementsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.credable.loanmanagementsystem.service.CustomerService;
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


    private String ClientToken;




    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> createClientToken () throws JsonProcessingException {
        //send request and receive response
            String url="" ;
            String name="" ;
            String username="";
            String password="" ;
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> request = new HashMap<>();
            request.put(url,"http://localhost:8080/transactionsApi/234774784");
            request.put(name,"loan");
            request.put(username,"");
            request.put(password,"");
            HttpEntity<Object> requestEntity = new HttpEntity<>(request,headers);
            URI uri = URI.create("https://scoringtest.credable.io/api/v1/client/createClient");
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, requestEntity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json= objectMapper.readTree(String.valueOf(responseEntity));
            String token=json.get(String.valueOf(requestEntity)).asText();

            this.ClientToken=token;

            return null;
    }

    public ResponseEntity< Object> queryScore (@PathVariable String customerNumber) throws JsonProcessingException {
        //initiate query
        createClientToken();
        HttpHeaders headers= new HttpHeaders();
        headers.set("client",ClientToken);
        HttpEntity<Object> request = new HttpEntity<>(headers);
        String uri= "https://scoringtest.credable.io/ap1/vi/scoring/initiateQueryScore" + customerNumber;
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class,request);

        //Query score
        String tokeni= response.getBody();

        String uri1= "https://scoringtest.credable.aw3io/ap1/vi/scoring/queryScore/" + tokeni ;

        ResponseEntity<Object> responsequery=restTemplate.getForEntity(uri1,Object.class,request);


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
