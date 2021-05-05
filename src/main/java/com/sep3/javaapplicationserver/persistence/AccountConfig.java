package com.sep3.javaapplicationserver.persistence;

import com.sep3.javaapplicationserver.model.Account;
import com.sep3.javaapplicationserver.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository repository) {
        return args -> {
            Account lucas = new Account(
                    "lucas",
                    "password"
            );

            Account alex = new Account(
                    "Alex",
                    "alexpassword"
            );

            repository.saveAll(
                    List.of(lucas, alex)
            );
        };
    }
}
