package dev.emjey.testTask.web;

import dev.emjey.testTask.entity.Customer;
import dev.emjey.testTask.entity.Order;
import dev.emjey.testTask.entity.Product;
import dev.emjey.testTask.repository.CustomerRepository;
import dev.emjey.testTask.repository.ProductRepository;
import dev.emjey.testTask.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


// This file is made by EmJey
// Project: testTask
// FileName: OrderController.java
// Date: 2024/01/27
// Modified Date: 2024/04/13
// Email: emjeydev@gmail.com
// Github: emjeydev


@Tag(name = "Order Controller", description = "Create and retrieve order")
@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    OrderService orderService;

    CustomerRepository customerRepository;

    ProductRepository productRepository;

    @Operation(summary = "Retrieves an order", description = "Returns an order according to the customerId and productId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of order", content = @Content(schema = @Schema(implementation = Order.class))),
    })
    @GetMapping(value = "/customer/{customerId}/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable Long customerId, @PathVariable Long productId) {
        return new ResponseEntity<>(orderService.getOrder(customerId, productId), HttpStatus.OK);
    }

    @Operation(summary = "Create Order", description = "Creates an order from the provided payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of order"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission")
    })
    @PostMapping(value = "/customer/{customerId}/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order, @PathVariable Long customerId, @PathVariable Long productId) {
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

    @Operation(summary = "Delete an order", description = "Deletes an order according to ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the product")
    })
    @DeleteMapping(value = "/customer/{customerId}/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable Long customerId, @PathVariable Long productId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Retrieves a list of orders ", description = "Returns a list of order according to the customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of orders", content = @Content(schema = @Schema(implementation = Order.class))),
    })
    @GetMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getStudentGrades(@PathVariable Long customerId) {
        List<Order> customerOrders = orderService.getCustomerOrders(customerId);
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }

    @Operation(summary = "Retrieves a list of orders ", description = "Returns a list of order according to the productId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of orders", content = @Content(schema = @Schema(implementation = Order.class))),
    })
    @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getCourseGrades(@PathVariable Long productId) {
        List<Order> productOrders = orderService.getProductOrders(productId);
        return new ResponseEntity<>(productOrders, HttpStatus.OK);
    }

    @Operation(summary = "Retrieves a list of orders ", description = "Returns a list of all orders ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of orders", content = @Content(schema = @Schema(implementation = Order.class))),
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getGrades() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
}
