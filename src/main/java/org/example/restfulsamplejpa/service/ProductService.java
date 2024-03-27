package org.example.restfulsamplejpa.service;

import org.example.restfulsamplejpa.dto.ProductRequest;
import org.example.restfulsamplejpa.dto.ProductResponse;

import java.util.List;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct();
    ProductResponse createProduct(ProductRequest product);
    ProductResponse updateProduct(Long id, ProductRequest product);
    ProductResponse deleteProduct(Long id);
}

