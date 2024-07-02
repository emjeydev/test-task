package dev.emjey.testTask.service;

import dev.emjey.testTask.entity.Order;

import java.util.List;

// This file is made by EmJey
// Project: testTast
// FileName: OrderService.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

public interface OrderService {
    Order getOrder(Long customerId, Long productId);

    Order saveOrder(Order order, Long customerId, Long productId);

    Order updateOrder(int count, Long customerId, Long productId);

    void deleteOrder(Long customerId, Long productId);

    List<Order> getCustomerOrders(Long customerId);

    List<Order> getProductOrders(Long productId);

    List<Order> getAllOrders();
}
