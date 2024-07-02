package dev.emjey.testTask.service;

import dev.emjey.testTask.entity.Customer;

import java.util.List;

// This file is made by EmJey
// Project: testTast
// FileName: CustomerService.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

public interface CustomerService {
    Customer getCustomerById(Long id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long id);

    List<Customer> getAllCustomers();
}
