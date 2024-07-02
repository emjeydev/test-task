package dev.emjey.testTask.web;

import dev.emjey.testTask.entity.Customer;
import dev.emjey.testTask.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This file is made by EmJey
// Project: testTast
// FileName: CustomerController.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

@Tag(name = "Customer Controller", description = "Create and retrieve customers")
@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    CustomerService customerService;

    @Operation(summary = "Retrieves a customer", description = "Returns a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of contact", content = @Content(schema = @Schema(implementation = Customer.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @Operation(summary = "Create Customer", description = "Creates a customer from the provided payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of customer"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer course) {
        return new ResponseEntity<>(customerService.saveCustomer(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Retrieves contacts", description = "Provides a list of all customers")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of customers", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Customer.class))))
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }
}
