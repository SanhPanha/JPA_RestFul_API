package org.example.restfulsamplejpa.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.restfulsamplejpa.dto.ProductRequest;
import org.example.restfulsamplejpa.dto.ProductResponse;
import org.example.restfulsamplejpa.mapper.ProductMapper;
import org.example.restfulsamplejpa.repository.ProductRepository;
import org.example.restfulsamplejpa.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // this is the constructor injection
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(product->productMapper.mapToProductResponse(product))
                .toList();
    }


    @Override
    public ProductResponse createProduct(ProductRequest request) {
        return productMapper.mapToProductResponse(
                productRepository.save(
                        productMapper.mapRequestToProduct(request)
                ));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest product) {
        var updatedProduct = productRepository.findById(id)
                .stream().findFirst()
                .orElseThrow();
        updatedProduct=productMapper.mapRequestToProduct(product);
        updatedProduct.setId(id);
//        System.out.println("Updated  Product is : "+ updatedProduct)
        return productMapper.mapToProductResponse(productRepository.save(updatedProduct));
    }

    @Override
    public ProductResponse deleteProduct(Long id) {
// find product by id , if product doesn't exist ,
// we throw not found exception
        var deletedProduct= productRepository.findById(id)
                .stream().findFirst()
                .orElseThrow();
        productRepository.deleteById(id);
        return productMapper.mapToProductResponse(deletedProduct);
    }
}

