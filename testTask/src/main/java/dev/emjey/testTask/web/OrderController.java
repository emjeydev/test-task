package dev.emjey.testTask.web;

import dev.emjey.testTask.entity.Customer;
import dev.emjey.testTask.entity.Order;
import dev.emjey.testTask.entity.Product;
import dev.emjey.testTask.repository.CustomerRepository;
import dev.emjey.testTask.repository.ProductRepository;
import dev.emjey.testTask.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// This file is made by EmJey
// Project: GradeSubmission - Spring JPA
// FileName: OrderController.java
// Date: 2024/01/27
// Modified Date: 2024/04/13
// Email: emjeydev@gmail.com
// Github: emjeydev


@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    OrderService orderService;

    CustomerRepository customerRepository;

    ProductRepository productRepository;

    @GetMapping("/customer/{customerId}/product/{productId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long customerId, @PathVariable Long productId) {
        return new ResponseEntity<>(orderService.getOrder(customerId, productId), HttpStatus.OK);
    }

    @PostMapping("/customer/{customerId}/product/{productId}")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order, @PathVariable Long customerId, @PathVariable Long productId) {
        Customer customer = customerRepository.findById(customerId).get();
        Product product = productRepository.findById(productId).get();
        order.setCustomer(customer);
        order.setProduct(product);
        return new ResponseEntity<>(orderService.saveOrder(order, customerId, productId), HttpStatus.CREATED);
    }

    @PutMapping("/customer/{customerId}/product/{productId}")
    public ResponseEntity<Order> updateGrade(@RequestBody Order order, @PathVariable Long customerId, @PathVariable Long productId) {
        return new ResponseEntity<>(orderService.updateOrder(order.getCount(), customerId, productId), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{customerId}/product/{productId}")
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable Long customerId, @PathVariable Long productId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getStudentGrades(@PathVariable Long customerId) {
        List<Order> customerOrders = orderService.getCustomerOrders(customerId);
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Order>> getCourseGrades(@PathVariable Long productId) {
        List<Order> productOrders = orderService.getProductOrders(productId);
        return new ResponseEntity<>(productOrders, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getGrades() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
}
