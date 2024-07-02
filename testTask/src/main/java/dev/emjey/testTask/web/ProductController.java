package dev.emjey.testTask.web;

import dev.emjey.testTask.entity.Product;
import dev.emjey.testTask.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

import java.util.List;


// This file is made by EmJey
// Project: testTask
// FileName: ProductController.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

@Tag(name = "Product Controller", description = "Create and retrieve products")
@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    @Operation(summary = "Retrieves a product", description = "Returns a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of product", content = @Content(schema = @Schema(implementation = Product.class))),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @Operation(summary = "Create Product", description = "Creates a product from the provided payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of product"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody Product course) {
        return new ResponseEntity<>(productService.saveProduct(course), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a Product", description = "Deletes a product according to provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the product")
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Retrieves Products", description = "Provides a list of all products")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))))
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }
}
