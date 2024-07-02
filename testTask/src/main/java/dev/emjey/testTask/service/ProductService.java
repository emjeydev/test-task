package dev.emjey.testTask.service;

import dev.emjey.testTask.entity.Product;

import java.util.List;

// This file is made by EmJey
// Project: testTast
// FileName: ProductService.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

public interface ProductService {
    Product findProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);

    List<Product> findAllProducts();
}
