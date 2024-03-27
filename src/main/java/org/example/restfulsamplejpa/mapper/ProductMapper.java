package org.example.restfulsamplejpa.mapper;

import org.example.restfulsamplejpa.dto.ProductRequest;
import org.example.restfulsamplejpa.dto.ProductResponse;
import org.example.restfulsamplejpa.model.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse mapToProductResponse(Product product);
    Product mapRequestToProduct(ProductRequest productRequest);

    @AfterMapping
    default void setId(@MappingTarget Product product){
        if(product.getId()==null){
            product.setId(0L);
        }

    }
}


