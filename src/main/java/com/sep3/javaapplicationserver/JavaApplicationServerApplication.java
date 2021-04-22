package com.sep3.javaapplicationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApplicationServerApplication {

    public static void main(String[] args) {

        System.setProperty("java.security.policy", "src/main/all.policy");
        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(new SecurityManager());
        }

        SpringApplication.run(JavaApplicationServerApplication.class, args);
    }

}
