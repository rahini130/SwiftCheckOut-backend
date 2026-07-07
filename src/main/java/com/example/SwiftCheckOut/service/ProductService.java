package com.example.SwiftCheckOut.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwiftCheckOut.entity.Product;
import com.example.SwiftCheckOut.repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;
  
  public Product createProduct(Product product){
    return productRepository.save(product);
  }

  public List<Product> getAll(){
        return productRepository.findAll();
    }

   public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

  public Product delete(Long id){
        Product product =productRepository.findById(id).orElse(null);
        if(product == null){
            return null;
        }
        productRepository.delete(product);
        return product;
    }

    
}
