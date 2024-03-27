package org.example.restfulsamplejpa.repository;

import org.example.restfulsamplejpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findProductByTitle(String title);

}
