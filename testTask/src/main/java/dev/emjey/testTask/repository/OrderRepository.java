package dev.emjey.testTask.repository;

import dev.emjey.testTask.entity.Order;
import org.springframework.data.repository.CrudRepository;

// This file is made by EmJey
// Project: testTast
// FileName: OrderRepository.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

public interface OrderRepository extends CrudRepository<Order, Long> {
}
