package com.axxes.cc.boot.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.axxes.cc.boot.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, Long> {

    Collection<Customer> findByLastNameIgnoreCase(String lastName);
    
}
