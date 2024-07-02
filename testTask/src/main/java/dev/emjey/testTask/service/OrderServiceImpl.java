package dev.emjey.testTask.service;

// This file is made by EmJey
// Project: testTast
// FileName: OrderServiceImpl.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

import dev.emjey.testTask.entity.Customer;
import dev.emjey.testTask.entity.Order;
import dev.emjey.testTask.entity.Product;
import dev.emjey.testTask.repository.CustomerRepository;
import dev.emjey.testTask.repository.OrderRepository;
import dev.emjey.testTask.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    CustomerRepository customerRepository;
    ProductRepository productRepository;


    @Override
    public Order getOrder(Long customerId, Long productId) {
        return orderRepository.findByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    public Order saveOrder(Order order, Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId).get();
        Product product = productRepository.findById(productId).get();
        order.setCustomer(customer);
        order.setProduct(product);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(int count, Long customerId, Long productId) {
        Order order = orderRepository.findByCustomerIdAndProductId(customerId, productId);
        order.setCount(count);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long customerId, Long productId) {
        orderRepository.deleteByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    public List<Order> getCustomerOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Order> getProductOrders(Long productId) {
        return orderRepository.findByProductId(productId);
    }

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }
}
