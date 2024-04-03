package edu.iu.selzheng.primesservice.service;

import edu.iu.selzheng.primesservice.model.Customer;

import java.io.IOException;

public interface IAuthenticationService {
    Customer register(Customer customer) throws IOException;
}