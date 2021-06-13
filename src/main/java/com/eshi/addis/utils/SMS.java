package com.eshi.addis.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Log4j2
public class SMS {
    private WebClient.Builder webClientBuilder;

    public SMS(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    //  http://10.1.12.156:13013/cgi-bin/sendsms?username=enat&password=enat&text=test&to=912492835
//    public void send(OTP otp) {
//        Mono<String> body = webClientBuilder.baseUrl("http://10.1.12.156:13013/cgi-bin/sendsms")
//                .build().get()
//                .uri(uriBuilder -> uriBuilder.queryParam("username", "enat")
//                        .queryParam("password", "enat")
//                        .queryParam("text", otp.getOtp())
//                        .queryParam("to", otp.getPhoneNo()).build())
//                .retrieve().bodyToMono(String.class);
//
//    }
}
