package com.sep3.javaapplicationserver.yahooapi.controller;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class YahooController {


    @GetMapping("")
    public HttpResponse<String> getResponse() throws UnirestException {
        HttpResponse<String> response = (HttpResponse<String>) Unirest.get("https://apidojo-yahoo-finance-v1.p.rapidapi.com/auto-complete?q=tesla&region=US")
                .header("x-rapidapi-key", "a0ec5070d9msh0098ebbc36fd3efp1dfef6jsn9f9e2d74b059")
                .header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                .asString();
        return response;
    }


}
