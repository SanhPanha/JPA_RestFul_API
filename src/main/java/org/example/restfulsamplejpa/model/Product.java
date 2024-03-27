package org.example.restfulsamplejpa.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name="product_tbl")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private float price;
}

