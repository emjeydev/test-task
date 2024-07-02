package dev.emjey.testTask.web;

import dev.emjey.testTask.entity.Customer;
import dev.emjey.testTask.entity.Product;
import dev.emjey.testTask.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// This file is made by EmJey
// Project: testTast
// FileName: ProductController.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product course) {
        return new ResponseEntity<>(productService.saveProduct(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }
}
