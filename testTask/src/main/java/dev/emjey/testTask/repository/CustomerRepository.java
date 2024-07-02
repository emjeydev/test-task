package dev.emjey.testTask.repository;

import dev.emjey.testTask.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This file is made by EmJey
// Project: testTast
// FileName: CustomerRepository.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
