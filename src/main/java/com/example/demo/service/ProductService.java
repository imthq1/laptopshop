package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }
    public Product save(Product product) {
        return this.productRepository.save(product);
    }
    public Product getProductByid(long id) {
        return this.productRepository.getProductById(id);
    }
    @Transactional
    public void deleteProductByid(long id) {
        productRepository.deleteById(id); // Use deleteById method
    }

}
