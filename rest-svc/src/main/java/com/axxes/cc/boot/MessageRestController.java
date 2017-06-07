package com.axxes.cc.boot;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/message")
public class MessageRestController {

    @Value("${cc.custom.message}")
    private String customMessage;

    @RequestMapping(method = GET)
    public String getMessage() {
        return customMessage;
    }

}
