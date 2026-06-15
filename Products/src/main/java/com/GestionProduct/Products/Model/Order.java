package com.GestionProduct.Products.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Integer quantity;

    private LocalDateTime orderDate = LocalDateTime.now();
}
