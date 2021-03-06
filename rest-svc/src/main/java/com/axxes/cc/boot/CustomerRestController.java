package com.axxes.cc.boot;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.axxes.cc.boot.dao.CustomerDao;
import com.axxes.cc.boot.model.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);

    
    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(path = "/{id:\\d*}", method = GET)
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerDao.findOne(id);
    }

    @RequestMapping(method=GET) 
    @Cacheable("customers")
    public Iterable<Customer> findAll() {
        LOGGER.info("find all");
        return customerDao.findAll();
    }
    
    @RequestMapping(method = PUT)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerDao.save(customer);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
                .path("/{id}") //
                .buildAndExpand(savedCustomer.getId()) //
                .toUri();
        
        return ResponseEntity.created(location).build();
    }

}
