package io.credable.loanmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication


public class LoanManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanManagementSystemApplication.class, args);
    }

}
