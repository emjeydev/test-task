package dev.emjey.testTask.service;

import dev.emjey.testTask.entity.Product;
import dev.emjey.testTask.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// This file is made by EmJey
// Project: testTast
// FileName: ProductServiceImpl.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev


@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
}
