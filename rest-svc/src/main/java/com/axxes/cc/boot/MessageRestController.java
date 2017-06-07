package com.axxes.cc.boot;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
@RequestMapping("/message")
public class MessageRestController {

    @Value("${cc.custom.message}")
    private String customMessage;
    
    @Value("${server.port:8080}")
    private String port;

    @RequestMapping(method = GET)
    public String getMessage() {
        return customMessage + " (port: " + port + ")";
    }
    
    @Autowired
    private RestTemplate restTemplate; 
    
    @RequestMapping("/eureka")
    public String getEurekaMessage() { 
        return restTemplate.exchange("http://rest-svc/message", HttpMethod.GET, null, String.class).getBody(); 
    } 

}
