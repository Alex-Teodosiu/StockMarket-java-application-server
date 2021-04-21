package com.sep3.javaapplicationserver;

import com.sep3.javaapplicationserver.network.RmiApplicationClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApplicationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplicationServerApplication.class, args);
        RmiApplicationClient client = new RmiApplicationClient();
    }

}
