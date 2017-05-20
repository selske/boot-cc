package com.axxes.cc.boot;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.axxes.cc.boot.model.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    
    @RequestMapping(path="/{id}",method=GET)
    public Customer getCustomer(String id) {
        return new Customer("Kevin", "Sels");
    }
}
