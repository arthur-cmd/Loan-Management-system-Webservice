package io.credable.loanmanagementsystem.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.credable.loanmanagementsystem.data.dto.LoanResponseDTO;
import io.credable.loanmanagementsystem.data.dto.ScoringDTO;
import io.credable.loanmanagementsystem.data.vo.LoanModel;
import io.credable.loanmanagementsystem.data.vo.Model;
import io.credable.loanmanagementsystem.service.LoanService;
import io.credable.loanmanagementsystem.data.dto.LoanRequestDTO;


import lombok.SneakyThrows;
import  org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/restws")
public class LoanController {

    private RestTemplate restTemplate;

    private QueryLoan queryLoan;

    private final ObjectMapper objectMapper = new ObjectMapper();


   // private RestTemplate restTemplate = new RestTemplate();

    private LoanService loanservice;
    private static final Logger log = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    public LoanController(LoanService loanservice,QueryLoan queryLoan){

        this.loanservice= loanservice;
        this.queryLoan=queryLoan;
    }


    @GetMapping( "/queryscore/{customerNumber}")
    public ResponseEntity<ScoringDTO> sendData(@PathVariable String customerNumber)  {

       // ResponseEntity<Object> response = queryLoan.queryScore(customerNumber);
        ResponseEntity<ScoringDTO> response= queryLoan.queryScore(customerNumber);
        return  ResponseEntity.ok(response.getBody());

    }





    @SneakyThrows
    @PostMapping("/customer/loan")
    public ResponseEntity<LoanResponseDTO> myPostMethod(@RequestBody LoanRequestDTO resourceDTO) {

        ResponseEntity<LoanResponseDTO> response = loanservice.requestLoan(resourceDTO);

        return ResponseEntity.ok(response.getBody());


    }



//        @PostMapping("/token")
//        public ResponseEntity<String> getToken(@RequestParam("endpoint") String endpoint,
//                                               @RequestParam("serviceName") String serviceName,
//                                               @RequestParam("username") String username,
//                                               @RequestParam("password") String password) {
//            RestTemplate restTemplate = new RestTemplate();
//
//
//            String url = "https://scoringtest.credable.io/ap1/vi/client/createClient" ;
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("client-token","a8b3a5bd-2a96-4f77-a618-c56607ded9cb");
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            Map<String, String> requestBody = new HashMap<>();
//            requestBody.put("http://localhost:8080/restws/handle",endpoint);
//            requestBody.put("serviceName", serviceName);
//            requestBody.put("", username);
//            requestBody.put("", password);
//
//            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
//
//            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//
//            return response;
//        }






//
//    @PostMapping("/handle")
//    public ResponseEntity<Object> handle(@RequestBody ResourceDTO resourceDTO) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://scoringtest.credable.io/ap1/vi/client/createClient"; // replace with your URI template
//        ResourceDTO requestBody= new ResourceDTO();
//          //Object requestBody = new Object(); // replace with your object
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//            Map<String, String> requestBody = new HashMap<>();
//            requestBody.put("http://localhost:8080/restws/handle",endpoint);
//            requestBody.put("serviceName", serviceName);
//            requestBody.put("", username);
//            requestBody.put("", password);
//
//        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);
//        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
//
//        Object responseBody = response.getBody();
//        return new ResponseEntity<Object>(responseBody, HttpStatus.CREATED);
//
//        //URI location = URI.create("https://scoringtest.credable.io/ap1/vi/client/createClient");
//        //HttpHeaders responseHeaders = new HttpHeaders();
//        //responseHeaders.setLocation(location);
//        //responseHeaders.set("EndpointUrl", "nameService");
//    }

    @GetMapping("/loan/{customerKYC}")
    public LoanModel getLoan(@PathVariable String customerNumber){
        LoanModel loan= loanservice.getLoan(customerNumber);
        log.info("Your loan is" + customerNumber);
        if (loan != null){
            return loan;
        }

        return null;

    }





}
